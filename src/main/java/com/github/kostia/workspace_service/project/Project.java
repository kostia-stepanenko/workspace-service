package com.github.kostia.workspace_service.project;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("PROJECT")
public class Project {
    @Id
    @Column("ID")
    private Integer id;

    @Column("COMPARTMENT_ID")
    private Integer compartmentId;

    @Column("TENANT_ID")
    private Integer tenantId;

    @Column("DISPLAY_NAME")
    private String displayName;

    @Column("SHORT_NAME")
    private String shortName;

    @Column("DESCRIPTION")
    private String description;

    @Column("LIFECYCLE_STATE")
    private String lifecycleState;

    @Column("CONFIG_STATUS")
    private String configStatus;

    @Column("STORES")
    private String stores;

    @Column("GROUPS")
    private String groups;

    @Column("PROTECTED")
    private Boolean isProtected;

    @Column("VETTED")
    private Boolean vetted;

    @Column("VERSION")
    private Long version;

    // Default constructor
    public Project() {
    }

    // Full constructor
    public Project(Integer compartmentId, Integer tenantId, String displayName, String shortName,
                   String description, String lifecycleState, String configStatus,
                   String stores, String groups, Boolean isProtected, Boolean vetted, Long version) {
        this.compartmentId = compartmentId;
        this.tenantId = tenantId;
        this.displayName = displayName;
        this.shortName = shortName;
        this.description = description;
        this.lifecycleState = lifecycleState;
        this.configStatus = configStatus;
        this.stores = stores;
        this.groups = groups;
        this.isProtected = isProtected;
        this.vetted = vetted;
        this.version = version;
    }

// Getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompartmentId() {
        return compartmentId;
    }

    public void setCompartmentId(Integer compartmentId) {
        this.compartmentId = compartmentId;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLifecycleState() {
        return lifecycleState;
    }

    public void setLifecycleState(String lifecycleState) {
        this.lifecycleState = lifecycleState;
    }

    public String getConfigStatus() {
        return configStatus;
    }

    public void setConfigStatus(String configStatus) {
        this.configStatus = configStatus;
    }

    public String getStores() {
        return stores;
    }

    public void setStores(String stores) {
        this.stores = stores;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public Boolean getIsProtected() {
        return isProtected;
    }

    public void setIsProtected(Boolean isProtected) {
        this.isProtected = isProtected;
    }

    public Boolean getVetted() {
        return vetted;
    }

    public void setVetted(Boolean vetted) {
        this.vetted = vetted;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}