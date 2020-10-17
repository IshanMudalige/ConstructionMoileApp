package com.aim.procurementapp;

import com.aim.procurementapp.model.PurchaseReq;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test case for PurchaseReq class
 * Example local unit test, which will execute on the development machine (host).
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class PurchaseReqTest {

    PurchaseReq actPurchase;
    PurchaseReq expPurchase;

    @Before
    public void init(){
        actPurchase = new PurchaseReq("John", "20/10/20", "Cement", 50, "Hardware", "Kandy", 25000.00);
        expPurchase = new PurchaseReq();
    }

    /**
     * Positive test case Test PurchaseReq class all the setters and getter
     */
    @Test
    public void testGetterSetter(){
        expPurchase.setRequisitionerName("John");
        expPurchase.setOrderDate("20/10/20");
        expPurchase.setMaterial("Cement");
        expPurchase.setQuantity(50);
        expPurchase.setSupplier("Hardware");
        expPurchase.setAddress("Kandy");
        expPurchase.setTotal(25000.00);

        assertEquals("name getter/setter OK",expPurchase.getRequisitionerName(),actPurchase.getRequisitionerName());
        assertEquals("name getter/setter OK",expPurchase.getAddress(),actPurchase.getAddress());
        assertEquals("name getter/setter OK",expPurchase.getMaterial(),actPurchase.getMaterial());
        assertEquals("name getter/setter OK",expPurchase.getOrderDate(),actPurchase.getOrderDate());
        assertEquals("name getter/setter OK",expPurchase.getSupplier(),actPurchase.getSupplier());
        assertEquals("name getter/setter OK",expPurchase.getQuantity(),actPurchase.getQuantity());
        assertEquals("name getter/setter OK",expPurchase.getTotal(),actPurchase.getTotal(),0.00f);

    }

    /**
     * Positive and Negative test cases for quantity
     * using Boundary Value and Equivalence Partitioning techniques
     */
    @Test
    public void test_quantity(){
        //Boundary Value Analysis
        assertFalse(PurchaseReq.validateQuantity(-1));
        assertFalse(PurchaseReq.validateQuantity(0));
        assertTrue(PurchaseReq.validateQuantity(1));
        assertTrue(PurchaseReq.validateQuantity(999));
        assertTrue(PurchaseReq.validateQuantity(1000));
        assertFalse(PurchaseReq.validateQuantity(1001));

        //Equivalence Partitioning
        assertFalse(PurchaseReq.validateQuantity(-10));
        assertTrue(PurchaseReq.validateQuantity(100));
        assertFalse(PurchaseReq.validateQuantity(2000));
    }
    
    /**
     * Positive test case. Test date validation method with correct format date
     */
    @Test
    public void valid_Date() {
        assertTrue(PurchaseReq.validateJavaDate("12/10/20"));
    }

    /**
     * Negative test case. Test date validation with incorrect date format ,using dots as separator
     */
    @Test
    public void invalid_date_useDots() {
        assertFalse(PurchaseReq.validateJavaDate("12.10.20"));
    }

    /**
     * Negative test case. Test date validator with incorrect date format, using text format
     */
    @Test
    public void invalid_date_text() {
        assertFalse(PurchaseReq.validateJavaDate("12 APR 2020"));
    }

    /**
     * Negative test case. Test date validator with incorrect date format, using dash as separators
     */
    @Test
    public void invalid_date_dash() {
        assertFalse(PurchaseReq.validateJavaDate("20-10-2020"));
    }

    /**
     * Negative test case. Test date validator with incorrect date format, using dash as commas
     */
    @Test
    public void invalid_date_comma() {
        assertFalse(PurchaseReq.validateJavaDate("12,10,20"));
    }

}