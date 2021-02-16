-- scope
INSERT INTO SCOPE (id, key, dashboard_addon_id, clean_name, icon_url, description, date_filterable, display_type)
VALUES (2, 'metrics_with_query', '60092cbc2e5f596ca362e0cc', 'Metrics with Query', '/assets/images/icons/azure_devops_boards.png', 'desc', 0, 'CUSTOM_TEXT_PANEL');

-- scope fields
INSERT INTO SCOPE_FIELD (name, key, type, validators, placeholder, multi_select, affects, scope_id)
VALUES ('Metric Query', 'metric_query', 'input', 'required', 'Please type a metric query', 0, 'project', 2);

INSERT INTO SCOPE_FIELD (name, key, type, validators, placeholder, multi_select, affects, content, scope_id)
VALUES ('Metric Type', 'metric_type', 'dropdown', 'required', 'Please select a metric type', 0, null, 'Count#Effort',2);

INSERT INTO SCOPE_FIELD (name, key, type, validators, placeholder, multi_select, affects, scope_id)
VALUES ('Metric Title', 'metric_title', 'input', 'required', 'Please type a metric title', 0, null, 2);
