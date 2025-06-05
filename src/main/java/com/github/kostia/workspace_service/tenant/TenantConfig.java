package com.github.kostia.workspace_service.tenant;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@AllArgsConstructor
@Data
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
}
