package com.coffee.shop.util;

public enum StatusCode {

    S100("Success"),
    E100("Invalid argument"),
    E200("Data not found");

    private String description;

    StatusCode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
