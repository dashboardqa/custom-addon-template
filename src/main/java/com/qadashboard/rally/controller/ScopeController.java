package com.qadashboard.rally.controller;

import com.qadashboard.rally.domain.entity.Scope;
import com.qadashboard.rally.domain.entity.ScopeField;
import com.qadashboard.rally.model.KeyValue;
import com.qadashboard.rally.model.ScopeFieldResponse;
import com.qadashboard.rally.model.ScopeResponse;
import com.qadashboard.rally.model.mapper.ScopeFieldMapper;
import com.qadashboard.rally.model.mapper.ScopeMapper;
import com.qadashboard.rally.service.WidgetService;
import com.qadashboard.rally.service.ScopeFieldService;
import com.qadashboard.rally.service.ScopeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ScopeController {

    private static final String ITERATION_VELOCITY = "iteration_velocity";
    private static final String METRICS_WITH_QUERY = "metrics_with_query";

    private final ScopeService scopeService;
    private final ScopeFieldService scopeFieldService;
    private final WidgetService widgetService;

    private final ScopeMapper scopeMapper;
    private final ScopeFieldMapper scopeFieldMapper;

    /**
     * This endpoint is required
     * @return List<ScopeResponse>
     */
    @GetMapping("/scopes")
    public List<ScopeResponse> findAllByAddonId() {
        // YOU_CAN_CHANGE
        return scopeService.findAll()
                .stream()
                .map(scopeMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * This endpoint is required
     * @param id scope id
     * @return List<ScopeFieldResponse>
     */
    @GetMapping("/scopes/{id}/fields")
    public List<ScopeFieldResponse> findAllByScopeId(@PathVariable Long id) {
        // YOU_CAN_CHANGE

        return scopeFieldService.findAllByScopeId(id)
                .stream()
                .map(scopeFieldMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * This endpoint is required if you use the "affects" like {@link ScopeField#getAffects()}
     * @param id scope id
     * @param field key of field (for example: PROJECT)
     * @param params affected fields value (for example: ?workspace=workspaceId)
     * @return
     */
    @GetMapping("/scopes/{id}/fields/{field}")
    public List<KeyValue> findAllByScopeIdAndField(@PathVariable Long id, @PathVariable String field,
                                                   @RequestParam Map<String, String> params) {
        // YOU_CAN_CHANGE

        return scopeFieldService.findFieldData(id, field, params);
    }

    /**
     * This endpoint is required
     * @param id scope id
     * @param params affected fields value (for example: ?workspace=workspaceId)
     * @return MetricResponse or Object (Echart Objects)
     */
    @GetMapping("/scopes/{id}/widget/data")
    public Object findDataByScope(@PathVariable Long id, @RequestParam Map<String, String> params) {
        // YOU_CAN_CHANGE

        Scope existsScope = scopeService.findById(id);

        if (ITERATION_VELOCITY.equals(existsScope.getKey())) {
            return widgetService.velocityAndEstimateChart(params.get("project"));
        } else if (METRICS_WITH_QUERY.equals(existsScope.getKey())) {
            return widgetService.metricsChart(params.get("metric_title"), params.get("metric_type"), params.get("metric_query"));
        }

        // given scope is not defined
        throw new UnsupportedOperationException();
    }
}
