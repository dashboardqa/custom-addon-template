package com.qadashboard.rally.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@Builder
@RequiredArgsConstructor
public class KeyLabel implements Serializable {

    private final String key;
    private final String label;
}
