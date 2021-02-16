package com.qadashboard.rally.service;

import com.qadashboard.rally.model.IterationScore;
import com.qadashboard.rally.model.MetricResponse;
import com.qadashboard.rally.service.handler.WorkspaceHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class WidgetService {

    private static final String BARCHART_TEMPLATE = "echart_templates/barchart_template.json";

    private static final String SERIES = "series";
    private static final String DATA = "data";
    private static final String X_AXIS = "xAxis";

    private final WorkspaceHandler workspaceHandler;
    private final ResourceLoader resourceLoader;

    public String velocityAndEstimateChart(String projectId) {
        List<IterationScore> iterationVelocityAndEstimate = workspaceHandler.findIterationVelocityAndEstimates(projectId);

        List<Double> estimates = iterationVelocityAndEstimate.stream()
                .map(IterationScore::getEstimate)
                .collect(Collectors.toList());

        List<Double> velocities = iterationVelocityAndEstimate.stream()
                .map(IterationScore::getVelocity)
                .collect(Collectors.toList());

        List<String> labels = iterationVelocityAndEstimate.stream()
                .map(IterationScore::getIterationName)
                .collect(Collectors.toList());

        return prepareChartTemplate(estimates, velocities, labels);
    }

    public MetricResponse metricsChart(String metricTitle, String metricType, String query) {
        return workspaceHandler.findMetricsWithQuery(metricTitle, metricType, query);
    }

    private String prepareChartTemplate(List<Double> estimates, List<Double> velocities, List<String> labels) {
        String chartTemplate = readResource();
        try {
            JSONObject jsonObject = new JSONObject(chartTemplate);
            JSONArray series = (JSONArray) jsonObject.get(SERIES);
            JSONArray xAxes = (JSONArray) jsonObject.get(X_AXIS);

            // put estimates into chart template's json
            JSONObject estimateSeries = (JSONObject) series.get(0);
            estimateSeries.put(DATA, new JSONArray(estimates));

            // put velocities into chart template's json
            JSONObject velocitySeries = (JSONObject) series.get(1);
            velocitySeries.put(DATA, new JSONArray(velocities));

            // put chart labels into chart template's json
            JSONObject xAxis = (JSONObject) xAxes.get(0);
            xAxis.put(DATA, new JSONArray(labels));

            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            log.error("An exception occured when manipulating chart's json template. {}", e.getMessage());
            return null;
        }
    }

    private String readResource() {
        try {
            final InputStream inputStream = resourceLoader.getResource(
                    ResourceLoader.CLASSPATH_URL_PREFIX + BARCHART_TEMPLATE).getInputStream();
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalArgumentException("file not found, " + BARCHART_TEMPLATE);
        }
    }
}
