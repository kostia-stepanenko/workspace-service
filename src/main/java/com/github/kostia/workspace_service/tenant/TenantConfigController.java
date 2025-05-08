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

    // READ all or by TenantId
    @GetMapping
    public ResponseEntity<?> getTenants(@RequestParam(name = "customerTenancyId", required = false) String customerTenancyId) {
        if (customerTenancyId != null) {
            return tenantConfigRepo.findByCustomerTenancyId(customerTenancyId)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.ok(tenantConfigRepo.findAll());
        }
    }

    // CREATE
    @PostMapping
    public ResponseEntity<TenantConfig> createTenant(@RequestBody TenantConfig tenantConfig) {
        TenantConfig saved = tenantConfigRepo.save(tenantConfig);
        return ResponseEntity.ok(saved);
    }
}