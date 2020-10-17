package com.aim.procurementapp.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PurchaseReq {
    private String pid;
    private String requisitionerName;
    private String orderDate;
    private String material;
    private int quantity;
    private String supplier;
    private String address;
    private String status;
    private double total;

    public PurchaseReq() {
    }

    public PurchaseReq(String requisitionerName, String orderDate, String material, int quantity, String supplier, String address, double total) {
        this.requisitionerName = requisitionerName;
        this.orderDate = orderDate;
        this.material = material;
        this.quantity = quantity;
        this.supplier = supplier;
        this.address = address;
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getRequisitionerName() {
        return requisitionerName;
    }

    public void setRequisitionerName(String requisitionerName) {
        this.requisitionerName = requisitionerName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static boolean validateJavaDate(String strDate)
    {

        if (strDate.trim().equals(""))
            return true;
        else {
            SimpleDateFormat sdfrmt = new SimpleDateFormat("MM/dd/yy");
            sdfrmt.setLenient(false);
            try {
                Date javaDate = sdfrmt.parse(strDate);
                System.out.println(strDate+" is valid date format");
            } catch (ParseException e) {
                System.out.println(strDate+" is Invalid Date format");
                return false;
            }
            return true;
        }
    }

    public static boolean validateQuantity(int qty){

        if (qty <= 0) {
            System.out.println("Invalid quantity");
            return false;
        }else if(qty > 1000){
            System.out.println("Invalid quantity");
            return false;
        }else {
            System.out.println("Valid quantity");
            return true;
        }
    }

}
