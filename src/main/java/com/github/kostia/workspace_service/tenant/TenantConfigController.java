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

    // PUT A.K.A UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity<TenantConfig> updateTenant(
            @PathVariable Integer id,
            @RequestBody TenantConfig updatedTenant) {

        return tenantConfigRepo.findById(id)
                .map(existing -> {
                    updatedTenant.setId(id); // ensure the correct ID is set
                    TenantConfig saved = tenantConfigRepo.save(updatedTenant);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTenant(@PathVariable Integer id) {
        if (tenantConfigRepo.existsById(id)) {
            tenantConfigRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}