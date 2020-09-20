package com.aim.procurementapp.model;

import java.io.Serializable;

public class Supplier implements Serializable {
    private String id;
    private String name;
    private String material;
    private double unitPrice;

    public Supplier(String id, String name, String material, double unitPrice) {
        this.id = id;
        this.name = name;
        this.material = material;
        this.unitPrice = unitPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
