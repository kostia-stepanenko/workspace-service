package com.github.kostia.workspace_service.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
}