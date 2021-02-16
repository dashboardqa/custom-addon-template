package com.qadashboard.rally.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class ScopeFieldResponse {

    private final String name;
    private final String key;
    private final String type;
    private final List<String> validators;
    private final String placeholder;
    private final boolean multiSelect;
    private final List<String> affects;
    private final Object data;
}
