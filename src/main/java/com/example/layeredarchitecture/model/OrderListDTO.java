package com.example.layeredarchitecture.model;

import java.math.BigDecimal;
import java.time.LocalDate;


public class OrderListDTO {
    private String oid;
    private LocalDate date;
    private String customerID;
    private String customerName;
    private String itemCode;
    private String itemDesc;
    private int qty;
    private BigDecimal unitPrice;

    public OrderListDTO(String oid, LocalDate date, String customerID, String customerName, String itemCode, String itemDesc, int qty, BigDecimal unitPrice) {
        this.oid = oid;
        this.date = date;
        this.customerID = customerID;
        this.customerName = customerName;
        this.itemCode = itemCode;
        this.itemDesc = itemDesc;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
}
