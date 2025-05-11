package com.github.kostia.workspace_service.TenantConfigRepoTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kostia.workspace_service.tenant.TenantConfig;
import com.github.kostia.workspace_service.tenant.TenantConfigController;
import com.github.kostia.workspace_service.tenant.TenantConfigRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TenantConfigController.class)
class TenantConfigControllerTest {

    @Autowired
    private MockMvc mockMvc;


    //'org.springframework.boot.test.mock.mockito.MockBean' is deprecated and marked for removal
    // (still works nonetheless)
    @MockBean
    private TenantConfigRepo tenantConfigRepo;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("GET /api/tenants should return all tenants")
    void getAllTenants() throws Exception {
        TenantConfig tenant = new TenantConfig("cust-123", "ACTIVE", 1L);
        tenant.setId(1);
        Mockito.when(tenantConfigRepo.findAll()).thenReturn(List.of(tenant));

        mockMvc.perform(get("/api/tenants"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].customerTenancyId").value("cust-123"));
    }

    @Test
    @DisplayName("GET /api/tenants?customerTenancyId=cust-123 should return a tenant")
    void getTenantByCustomerTenancyId() throws Exception {
        TenantConfig tenant = new TenantConfig("cust-123", "ACTIVE", 1L);
        tenant.setId(1);
        Mockito.when(tenantConfigRepo.findByCustomerTenancyId("cust-123")).thenReturn(Optional.of(tenant));

        mockMvc.perform(get("/api/tenants").param("customerTenancyId", "cust-123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerTenancyId").value("cust-123"));
    }

    @Test
    @DisplayName("POST /api/tenants should create a tenant")
    void createTenant() throws Exception {
        TenantConfig input = new TenantConfig("cust-456", "ACTIVE", 1L);
        TenantConfig saved = new TenantConfig("cust-456", "ACTIVE", 1L);
        saved.setId(2);

        Mockito.when(tenantConfigRepo.save(any())).thenReturn(saved);

        mockMvc.perform(post("/api/tenants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.customerTenancyId").value("cust-456"));
    }

    @Test
    @DisplayName("PUT /api/tenants/update/1 should update tenant if found")
    void updateTenant() throws Exception {
        TenantConfig existing = new TenantConfig("cust-123", "ACTIVE", 1L);
        existing.setId(1);
        TenantConfig updated = new TenantConfig("cust-123", "DELETED", 2L);
        updated.setId(1);

        Mockito.when(tenantConfigRepo.findById(1)).thenReturn(Optional.of(existing));
        Mockito.when(tenantConfigRepo.save(any())).thenReturn(updated);

        mockMvc.perform(put("/api/tenants/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lifecycleState").value("DELETED"))
                .andExpect(jsonPath("$.version").value(2));
    }

    @Test
    @DisplayName("DELETE /api/tenants/1 should delete tenant if found")
    void deleteTenant() throws Exception {
        Mockito.when(tenantConfigRepo.existsById(1)).thenReturn(true);

        mockMvc.perform(delete("/api/tenants/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("GET /api/tenants/1 should return a tenant by ID")
    void getTenantById() throws Exception {
        TenantConfig tenant = new TenantConfig("cust-123", "ACTIVE", 1L);
        tenant.setId(1);
        Mockito.when(tenantConfigRepo.findById(1)).thenReturn(Optional.of(tenant));

        mockMvc.perform(get("/api/tenants/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerTenancyId").value("cust-123"));
    }
}
