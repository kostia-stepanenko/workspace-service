package com.github.kostia.workspace_service.tenant;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/tenants")
public class TenantConfigController {

    private final TenantConfigRepo tenantConfigRepo;

    public TenantConfigController(TenantConfigRepo tenantConfigRepo) {
        this.tenantConfigRepo = tenantConfigRepo;
    }

    @GetMapping("/{id}")
    public Optional<TenantConfig> getTenantById(@PathVariable Integer id) {
        return tenantConfigRepo.findById(id);
    }

    @GetMapping("/by-customer/{customerTenancyId}")
    public Optional<TenantConfig> getTenantByCustomerTenancyId(@PathVariable String customerTenancyId) {
        return tenantConfigRepo.findByCustomerTenancyId(customerTenancyId);
    }

    @GetMapping
    public Iterable<TenantConfig> getAllTenants() {
        return tenantConfigRepo.findAll();
    }
}