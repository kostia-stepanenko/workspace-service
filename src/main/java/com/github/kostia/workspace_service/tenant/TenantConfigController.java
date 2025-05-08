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
    @GetMapping
    public Iterable<TenantConfig> getAllTenants(@RequestParam Optional<String> customerTenancyId) {
        if (customerTenancyId.isPresent()) {
            return tenantConfigRepo.findByCustomerTenancyId(customerTenancyId.get());
        } else {
            return tenantConfigRepo.findAll();
        }
    }

    // CREATE
    @PostMapping
    public ResponseEntity<TenantConfig> createTenant(@RequestBody TenantConfig tenantConfig) {
        TenantConfig saved = tenantConfigRepo.save(tenantConfig);
        return ResponseEntity.ok(saved);
    }
}