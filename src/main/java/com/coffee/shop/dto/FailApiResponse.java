package com.coffee.shop.dto;

import com.coffee.shop.util.StatusCode;

import java.util.HashMap;
import java.util.Map;

public class FailApiResponse implements ApiResponse {

    private static final String CODE = "code";
    private static final String MESSAGE = "message";

    private Map<String, Object> error;

    public FailApiResponse(StatusCode statusCode, String message) {
        this.error = new HashMap<>();
        error.put(CODE, statusCode);
        error.put(MESSAGE, message);
    }

    public Map<String, Object> getError() {
        return error;
    }

    public void setError(Map<String, Object> error) {
        this.error = error;
    }
}
