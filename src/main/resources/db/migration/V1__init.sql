CREATE TABLE IF NOT EXISTS scope (
    id INT AUTO_INCREMENT PRIMARY KEY,
    key VARCHAR(250) NOT NULL,
    dashboard_addon_id VARCHAR(250) NOT NULL,
    clean_name VARCHAR(250) NOT NULL,
    icon_url VARCHAR(250) DEFAULT NULL,
    description VARCHAR(250) DEFAULT NULL,
    date_filterable BIT DEFAULT 0,
    display_type VARCHAR(250) DEFAULT NOT NULL CHECK display_type in('CUSTOM_ECHARTS', 'CUSTOM_TEXT_PANEL')
);

CREATE TABLE IF NOT EXISTS scope_field (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    key VARCHAR(250) NOT NULL,
    type VARCHAR(250) NOT NULL CHECK type in('input', 'dropdown'),
    validators VARCHAR(250) DEFAULT NULL,
    placeholder VARCHAR(250) DEFAULT NULL,
    multi_select BIT DEFAULT 0,
    affects VARCHAR(250) DEFAULT NULL,
    content VARCHAR(250) DEFAULT NULL,
    scope_id INT NOT NULL
);