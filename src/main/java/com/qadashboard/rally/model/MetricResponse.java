package com.qadashboard.rally.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class MetricResponse {

    private final String title;
    private final String value;
}