package com.github.kostia.workspace_service.tenant;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("TENANT_CONFIG")
public class TenantConfig {

    @Id
    @Column("ID")
    private Integer id;

    @Column("CUSTOMER_TENANCY_ID")
    private String customerTenancyId;

    @Column("LIFECYCLE_STATE")
    private String lifecycleState;

    @Column("VERSION")
    private Long version;

    // Constructors
    public TenantConfig() {
    }

    public TenantConfig(String customerTenancyId, String lifecycleState, Long version) {
        this.customerTenancyId = customerTenancyId;
        this.lifecycleState = lifecycleState;
        this.version = version;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerTenancyId() {
        return customerTenancyId;
    }

    public void setCustomerTenancyId(String customerTenancyId) {
        this.customerTenancyId = customerTenancyId;
    }

    public String getLifecycleState() {
        return lifecycleState;
    }

    public void setLifecycleState(String lifecycleState) {
        this.lifecycleState = lifecycleState;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
