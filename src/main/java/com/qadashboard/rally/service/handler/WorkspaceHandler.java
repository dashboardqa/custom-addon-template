package com.qadashboard.rally.service.handler;

import com.jayway.jsonpath.JsonPath;
import com.qadashboard.rally.model.IterationScore;
import com.qadashboard.rally.model.KeyValue;
import com.qadashboard.rally.model.MetricResponse;
import com.qadashboard.rally.model.enums.RallyApiEnum;
import com.qadashboard.rally.service.RallyClient;
import com.qadashboard.rally.service.utility.EndpointTemplateUrlReplacer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WorkspaceHandler {

    // JSONPath fields in rally api response to parse
    private static final String QUERY_RESULTS_JSON_PATH = "$.QueryResult.Results";
    private static final String OBJECT_ID_JSON_PATH = "_refObjectUUID";
    private static final String OBJECT_NAME_JSON_PATH = "_refObjectName";
    private static final String PLAN_ESTIMATE_JSON_PATH = "PlanEstimate";
    private static final String PLANNED_VELOCITY_JSON_PATH = "PlannedVelocity";

    // metric type options
    private static final String COUNT = "count";
    private static final String EFFORT = "effort";

    private final RallyClient rallyClient;

    public List<KeyValue> findWorkspaces() {
        String endpoint = RallyApiEnum.WORKSPACES.getPath();
        String response = rallyClient.executeGet(endpoint);
        return getResults(response);
    }

    public List<KeyValue> findProjects(List<String> workspaces) {
        return workspaces.stream()
                .flatMap(item -> extractProjects(item).stream())
                .collect(Collectors.toList());
    }

    public List<IterationScore> findIterationVelocityAndEstimates(String projectId) {
        String endpoint = RallyApiEnum.ITERATIONS.getPath();
        endpoint = EndpointTemplateUrlReplacer.replaceProject(endpoint, projectId);
        String response = rallyClient.executeGet(endpoint);
        return getIterationScores(response);
    }

    public MetricResponse findMetricsWithQuery(String metricTitle, String metricType, String query) {
        String endpoint = RallyApiEnum.ARTIFACT_QUERY.getPath();
        endpoint = EndpointTemplateUrlReplacer.appendQuery(endpoint, query);
        String response = rallyClient.executeGet(endpoint);
        return getMetrics(metricTitle, metricType, response);
    }

    private MetricResponse getMetrics(String title, String metricType, String response) {
        List<Object> results = JsonPath.read(response, QUERY_RESULTS_JSON_PATH);

        double total = 0;
        if (COUNT.equals(metricType)) {
            total = results.size();
        } else if (EFFORT.equals(metricType)) {
            total = results.stream()
                    .map(result -> (Double) JsonPath.read(result, PLAN_ESTIMATE_JSON_PATH))
                    .filter(Objects::nonNull)
                    .mapToDouble(d -> d)
                    .sum();
        }
        return MetricResponse.builder()
                .title(title)
                .value(String.valueOf(total))
                .build();
    }

    private List<KeyValue> extractProjects(String workspaceId) {
        String endpoint = RallyApiEnum.PROJECTS.getPath();
        endpoint = EndpointTemplateUrlReplacer.replaceWorkspace(endpoint, workspaceId);
        String response = rallyClient.executeGet(endpoint);
        return getResults(response);
    }

    private List<KeyValue> getResults(String response) {
        List<Object> results = JsonPath.read(response, QUERY_RESULTS_JSON_PATH);
        return results.stream().map(result -> {
            String id = JsonPath.read(result, OBJECT_ID_JSON_PATH);
            String name = JsonPath.read(result, OBJECT_NAME_JSON_PATH);
            return KeyValue.builder()
                    .key(id)
                    .value(name)
                    .build();
        }).collect(Collectors.toList());
    }

    private List<IterationScore> getIterationScores(String response) {
        List<Object> results = JsonPath.read(response, QUERY_RESULTS_JSON_PATH);
        return results.stream().map(result -> {
            String iterationName = JsonPath.read(result, OBJECT_NAME_JSON_PATH);
            Double estimate = JsonPath.read(result, PLAN_ESTIMATE_JSON_PATH);
            Double velocity = JsonPath.read(result, PLANNED_VELOCITY_JSON_PATH);
            return IterationScore.builder()
                    .iterationName(iterationName)
                    .estimate(estimate == null ? 0.0 : estimate)
                    .velocity(velocity == null ? 0.0 : velocity)
                    .build();
        }).collect(Collectors.toList());
    }
}
