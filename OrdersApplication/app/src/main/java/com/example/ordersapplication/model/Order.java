package com.example.ordersapplication.model;

public class Order {
    private String orderId;
    private Integer serviceTypeId;
    private String serviceType;
    private String model;
    private String startTime;
    private String plates;
    private String pyramidColor;
    private Integer pyramidNumber;
    private Integer orderStatusId;
    private String orderStatus;
    private boolean assigned;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(Integer serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getPlates() {
        return plates;
    }

    public void setPlates(String plates) {
        this.plates = plates;
    }

    public String getPyramidColor() {
        return pyramidColor;
    }

    public void setPyramidColor(String pyramidColor) {
        this.pyramidColor = pyramidColor;
    }

    public Integer getPyramidNumber() {
        return pyramidNumber;
    }

    public void setPyramidNumber(Integer pyramidNumber) {
        this.pyramidNumber = pyramidNumber;
    }

    public Integer getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(Integer orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }
}
