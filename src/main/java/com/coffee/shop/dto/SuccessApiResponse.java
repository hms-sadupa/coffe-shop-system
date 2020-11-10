package com.coffee.shop.dto;

public class SuccessApiResponse implements ApiResponse {

    private static final String DATA = "data";

    private Object data;

    public SuccessApiResponse(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
