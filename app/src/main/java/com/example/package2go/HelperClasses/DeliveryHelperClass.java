package com.example.package2go.HelperClasses;

public class DeliveryHelperClass {
    private String customerName;
    private String customerAddress;
    private String packageDesc;
    private String customerPhoneNo;
    private String packagePrice;
    private String deliveryId;
    private String status;

    public DeliveryHelperClass() {
    }

    public DeliveryHelperClass(String deliveryId, String customerName, String customerAddress, String packageDesc, String customerPhoneNo, String packagePrice, String status) {
        this.customerName = customerName;
        this.deliveryId = deliveryId;
        this.customerAddress = customerAddress;
        this.packageDesc = packageDesc;
        this.customerPhoneNo = customerPhoneNo;
        this.packagePrice = packagePrice;
        this.status = status;
    }

    // Getters and setters

    public String getDeliveryId() { return deliveryId; }
    public void setDeliveryId(String deliveryId) { this.deliveryId = deliveryId; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerAddress() { return customerAddress; }
    public void setCustomerAddress(String customerAddress) { this.customerAddress = customerAddress; }

    public String getPackageDesc() { return packageDesc; }
    public void setPackageDesc(String packageDesc) { this.packageDesc = packageDesc; }

    public String getCustomerPhoneNo() { return customerPhoneNo; }
    public void setCustomerPhoneNo(String customerPhoneNo) { this.customerPhoneNo = customerPhoneNo; }

    public String getPackagePrice() { return packagePrice; }
    public void setPackagePrice(String packagePrice) { this.packagePrice = packagePrice; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}