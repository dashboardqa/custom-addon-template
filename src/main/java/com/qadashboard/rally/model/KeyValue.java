package com.qadashboard.rally.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class KeyValue {

    private final String key;
    private final String value;
}
