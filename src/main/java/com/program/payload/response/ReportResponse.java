package com.program.payload.response;

public class ReportResponse {

    private String username;

    private String email;

    private String categoryName;

    private String statusName;

    private String departmentName;

    private Integer acceptedEvents;

    private Integer rejectedEvents;

    private Integer leftEvents;

    private String kpiSum;


    public ReportResponse(String username, String email, String categoryName, String statusName, String departmentName, Integer acceptedEvents, Integer rejectedEvents, Integer leftEvents,String kpiSum) {
        this.username = username;
        this.email = email;
        this.categoryName = categoryName;
        this.statusName = statusName;
        this.departmentName = departmentName;
        this.acceptedEvents = acceptedEvents;
        this.rejectedEvents = rejectedEvents;
        this.leftEvents = leftEvents;
        this.kpiSum = kpiSum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getAcceptedEvents() {
        return acceptedEvents;
    }

    public void setAcceptedEvents(Integer acceptedEvents) {
        this.acceptedEvents = acceptedEvents;
    }

    public Integer getRejectedEvents() {
        return rejectedEvents;
    }

    public void setRejectedEvents(Integer rejectedEvents) {
        this.rejectedEvents = rejectedEvents;
    }

    public Integer getLeftEvents() {
        return leftEvents;
    }

    public void setLeftEvents(Integer leftEvents) {
        this.leftEvents = leftEvents;
    }

    public String getKpiSum() {
        return kpiSum;
    }

    public void setKpiSum(String kpiSum) {
        this.kpiSum = kpiSum;
    }
}
