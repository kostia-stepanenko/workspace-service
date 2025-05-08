package com.github.kostia.workspace_service.tenant;

import org.springframework.data.repository.CrudRepository;

public interface TenantConfigRepo extends CrudRepository<TenantConfig, Integer> {
    Iterable<TenantConfig> findByCustomerTenancyId(String customerTenancyId);
}
