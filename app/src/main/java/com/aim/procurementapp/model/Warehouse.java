package com.aim.procurementapp.model;

public class Warehouse {
    private String warehouse;
    private String material;
    private String status;
    private int quantity;

    public Warehouse(String warehouse, String material, String status, int quantity) {
        this.warehouse = warehouse;
        this.material = material;
        this.status = status;
        this.quantity = quantity;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
