package com.github.kostia.workspace_service.tenant;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Route("tenant-config")
public class TenantConfigView extends VerticalLayout {

    private final TenantConfigRepo tenantConfigRepo;
    private final Grid<TenantConfig> grid = new Grid<>(TenantConfig.class);
    private final Binder<TenantConfig> binder = new Binder<>(TenantConfig.class);

    private final TextField customerTenancyId = new TextField("Customer Tenancy ID");
    private final TextField lifecycleState = new TextField("Lifecycle State");
    private final TextField version = new TextField("Version");

    private final Button saveButton = new Button("Save");
    private final Button deleteButton = new Button("Delete");
    private final Button clearButton = new Button("Clear");

    private TenantConfig currentTenantConfig;

    @Autowired
    public TenantConfigView(TenantConfigRepo tenantConfigRepo) {
        this.tenantConfigRepo = tenantConfigRepo;

        // Configure Grid
        grid.setColumns("id", "customerTenancyId", "lifecycleState", "version");
        grid.setItems(StreamSupport.stream(tenantConfigRepo.findAll().spliterator(), false)
                .collect(Collectors.toList()));
        grid.asSingleSelect().addValueChangeListener(event -> editTenantConfig(event.getValue()));

        // Configure Form
        binder.bindInstanceFields(this);

        saveButton.addClickListener(e -> saveTenantConfig());
        deleteButton.addClickListener(e -> deleteTenantConfig());
        clearButton.addClickListener(e -> clearForm());

        HorizontalLayout buttons = new HorizontalLayout(saveButton, deleteButton, clearButton);
        VerticalLayout formLayout = new VerticalLayout(customerTenancyId, lifecycleState, version, buttons);

        add(grid, formLayout);
    }

    private void saveTenantConfig() {
        if (currentTenantConfig == null) {
            currentTenantConfig = new TenantConfig();
        }
        binder.writeBeanIfValid(currentTenantConfig);
        tenantConfigRepo.save(currentTenantConfig);
        refreshGrid();
        clearForm();
        Notification.show("TenantConfig saved");
    }

    private void deleteTenantConfig() {
        if (currentTenantConfig != null && currentTenantConfig.getId() != null) {
            tenantConfigRepo.deleteById(currentTenantConfig.getId());
            refreshGrid();
            clearForm();
            Notification.show("TenantConfig deleted");
        }
    }

    private void clearForm() {
        currentTenantConfig = null;
        binder.readBean(null);
    }

    private void editTenantConfig(TenantConfig tenantConfig) {
        if (tenantConfig == null) {
            clearForm();
        } else {
            currentTenantConfig = tenantConfig;
            binder.readBean(tenantConfig);
        }
    }

    private void refreshGrid() {
        grid.setItems(StreamSupport.stream(tenantConfigRepo.findAll().spliterator(), false)
                .collect(Collectors.toList()));
    }
}

/*
http://localhost:8081/tenant-config Url for testing
 */