package com.github.kostia.workspace_service.TenantConfigTests;

import com.github.kostia.workspace_service.tenant.TenantConfig;
import com.github.kostia.workspace_service.tenant.TenantConfigRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJdbcTest
class TenantConfigRepoTest {

    @Autowired
    private TenantConfigRepo tenantConfigRepo;

    @Test
    void saveAndFindByCustomerTenancyId() {
        // Arrange
        TenantConfig config = new TenantConfig(1, "cust-123", "ACTIVE", 1L);
        tenantConfigRepo.save(config);

        // Act
        Optional<TenantConfig> found = tenantConfigRepo.findByCustomerTenancyId("cust-123");

        // Assert
        assertThat(found).isPresent();
        assertThat(found.get().getLifecycleState()).isEqualTo("ACTIVE");
        assertThat(found.get().getVersion()).isEqualTo(1L);
    }

    @Test
    void deleteById() {
        // Arrange
        TenantConfig config = new TenantConfig(1, "cust-123", "ACTIVE", 1L);
        config = tenantConfigRepo.save(config);

        // Act
        tenantConfigRepo.deleteById(config.getId());

        // Assert
        Optional<TenantConfig> maybeTenantConfigAfterDeletion =
                tenantConfigRepo.findById(config.getId());

        assertTrue(maybeTenantConfigAfterDeletion.isEmpty());
    }
}
