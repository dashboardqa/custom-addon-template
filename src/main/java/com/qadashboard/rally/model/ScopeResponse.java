package com.qadashboard.rally.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ScopeResponse {

    private final Long id;
    private final String key;
    private final String addonId;
    private final String cleanName;
    private final String iconUrl;
    private final String desc;
    private final boolean dateFilterable;
    private final String displayType;
}
