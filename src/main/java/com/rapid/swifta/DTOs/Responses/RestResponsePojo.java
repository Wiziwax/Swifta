package com.rapid.swifta.DTOs.Responses;

import lombok.Data;

@Data
public class RestResponsePojo<T> {
    private String message;
    private Boolean success = true;
    private T data;
    private Integer status = 200;
}