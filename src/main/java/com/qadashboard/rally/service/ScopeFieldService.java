package com.qadashboard.rally.service;

import com.qadashboard.rally.domain.entity.ScopeField;
import com.qadashboard.rally.domain.repository.ScopeFieldRepository;
import com.qadashboard.rally.exception.RecordNotFoundException;
import com.qadashboard.rally.model.KeyValue;
import com.qadashboard.rally.service.handler.WorkspaceHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ScopeFieldService {

    private static final String DELIMITER = ",";
    private static final String WORKSPACE = "workspace";
    private static final String PROJECT = "project";

    private final ScopeFieldRepository scopeFieldRepository;
    private final WorkspaceHandler workspaceHandler;

    public List<ScopeField> findAllByScopeId(Long scopeId) {
        return scopeFieldRepository.findAllByScope_Id(scopeId)
                .orElseThrow(() -> new RecordNotFoundException("Widget fields cannot be found for scope: " + scopeId));
    }

    public List<KeyValue> findFieldData(Long scopeId, String key, Map<String, String> params) {
        // in this example we did not use scopeId because of fields are not depends on scope
        switch (key) {
            case WORKSPACE:
                return workspaceHandler.findWorkspaces();
            case PROJECT:
                List<String> workspaces = Arrays.asList(params.get(WORKSPACE).split(DELIMITER));
                return workspaceHandler.findProjects(workspaces);
            default:
                return Collections.emptyList();
        }
    }
}
