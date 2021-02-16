package com.qadashboard.rally.service.utility;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.net.URLEncoder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EndpointTemplateUrlReplacer {

    public static String replaceWorkspace(String endpointTemplate, String workspace) {
        return endpointTemplate.replace("{workspace}", workspace);
    }

    public static String replaceProject(String endpointTemplate, String project) {
        return endpointTemplate.replace("{project}", project);
    }

    public static String appendQuery(String endpointTemplate, String query) {
        return endpointTemplate.concat("&query=").concat(encode(query));
    }

    @SneakyThrows
    private static String encode(String query) {
        return URLEncoder.encode(query, "UTF-8");
    }
}
