package com.github.kostia.workspace_service.tenant;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TenantConfigRepo extends CrudRepository<TenantConfig, Integer> {
    Optional<TenantConfig> findByCustomerTenancyId(String customerTenancyId);
}
