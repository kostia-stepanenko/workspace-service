CREATE TABLE TENANT_CONFIG
(
    id                  INT              NOT NULL AUTO_INCREMENT,
    lifecycleState      VARCHAR(64)      NOT NULL,
    version             VARCHAR(50)      NOT NULL,
    customerTenancyId   INT              NOT NULL,
    PRIMARY KEY (id)
);