package com.qadashboard.rally.model.mapper;

import com.qadashboard.rally.domain.entity.Scope;
import com.qadashboard.rally.model.ScopeResponse;
import org.springframework.stereotype.Component;

@Component
public class ScopeMapper {

    public ScopeResponse toResponse(Scope scope) {
        return ScopeResponse.builder()
                .id(scope.getId())
                .key(scope.getKey())
                .addonId(scope.getDashboardAddonId())
                .cleanName(scope.getCleanName())
                .iconUrl(scope.getIconUrl())
                .desc(scope.getDescription())
                .dateFilterable(scope.isDateFilterable())
                .displayType(scope.getDisplayType())
                .build();
    }
}