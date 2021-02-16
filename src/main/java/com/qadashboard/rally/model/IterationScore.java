package com.qadashboard.rally.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class IterationScore {

    private final String iterationName;
    private final double velocity;
    private final double estimate;
}
