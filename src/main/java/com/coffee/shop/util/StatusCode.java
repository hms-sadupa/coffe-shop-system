package com.coffee.shop.util;

public enum StatusCode {

    E100("Invalid argument"),
    E200("Data not found"),
    E300("Internal Error");

    private String description;

    StatusCode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
