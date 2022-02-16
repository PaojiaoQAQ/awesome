package com.example.demo.common.practice.pojo;

public class ResponseEntity {
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    private String status;
    private String data;
}
