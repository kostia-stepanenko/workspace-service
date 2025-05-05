package com.github.kostia.workspace_service.tenant;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/tenants")
public class TenantConfigController {

    private final TenantConfigRepo tenantConfigRepo;

    public TenantConfigController(TenantConfigRepo tenantConfigRepo) {
        this.tenantConfigRepo = tenantConfigRepo;
    }

    // READ by ID
    @GetMapping("/{id}")
    public Optional<TenantConfig> getTenantById(@PathVariable Integer id) {
        return tenantConfigRepo.findById(id);
    }

    // READ by customerTenancyId
    @GetMapping("/by-customer/{customerTenancyId}")
    public Optional<TenantConfig> getTenantByCustomerTenancyId(@PathVariable String customerTenancyId) {
        return tenantConfigRepo.findByCustomerTenancyId(customerTenancyId);
    }

    // READ all
    @GetMapping
    public Iterable<TenantConfig> getAllTenants() {
        return tenantConfigRepo.findAll();
    }

    // CREATE
    @PostMapping
    public ResponseEntity<TenantConfig> createTenant(@RequestBody TenantConfig tenantConfig) {
        TenantConfig saved = tenantConfigRepo.save(tenantConfig);
        return ResponseEntity.ok(saved);
    }
}