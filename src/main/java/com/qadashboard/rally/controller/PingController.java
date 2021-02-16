package com.qadashboard.rally.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PingController {

    /**
     * This endpoint is required
     * @return Map<String, String>
     */
    @GetMapping("/ping")
    public Map<String, String> ping() {
        Map<String, String> statusMap = new HashMap<>();
        statusMap.put("status", "UP");
        return statusMap;
    }
}
