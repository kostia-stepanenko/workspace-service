CREATE TABLE TENANT_CONFIG
(
    id                      INT            AUTO_INCREMENT,
    customer_tenancy_id     VARCHAR(128)   NOT NULL UNIQUE,
    lifecycle_state VARCHAR(64) NOT NULL CHECK (lifecycle_state IN ('ACTIVE', 'DELETED')),
    version                 BIGINT,
    PRIMARY KEY (id)
);
