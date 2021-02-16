-- scope
INSERT INTO SCOPE (id, key, dashboard_addon_id, clean_name, icon_url, description, date_filterable, display_type)
VALUES (1, 'iteration_velocity', '60092cbc2e5f596ca362e0cc', 'Iteration Velocity', '/assets/images/icons/sprint.png', 'desc', 0, 'CUSTOM_ECHARTS');

-- scope fields
INSERT INTO SCOPE_FIELD (name, key, type, validators, placeholder, multi_select, affects, scope_id)
VALUES ('Workspace', 'workspace', 'dropdown', 'required', 'Please select a workspace', 0, 'project', 1);

INSERT INTO SCOPE_FIELD (name, key, type, validators, placeholder, multi_select, affects, scope_id)
VALUES ('Project', 'project', 'dropdown', 'required', 'Please select a project', 0, null, 1);
