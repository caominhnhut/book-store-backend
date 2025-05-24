package com.sts.model;

import com.sts.exception.Error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseData<T>{

    private T data;

    private Error error;
}
