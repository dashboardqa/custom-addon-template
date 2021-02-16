package com.qadashboard.rally.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RallyApiEnum {

    BASE("https://rally1.rallydev.com/slm/webservice/v2.0"),
    WORKSPACES("/Workspace"),
    PROJECTS("/Workspace/{workspace}/Projects"),
    ITERATIONS("/Project/{project}/Iterations"),
    ARTIFACT_QUERY("/artifact?compact=true&fetch=PlanEstimate&types=HierarchicalRequirement&pagesize=100");

    private final String path;
}