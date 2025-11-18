package com.Renault.MicrosericeGarage.entity.response;

public class ApiResponseFactory {

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(message, data);
    }

    public static ApiResponse<Void> success(String message) {
        return new ApiResponse<>(message, null);
    }
}
