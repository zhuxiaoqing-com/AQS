package com.example.demo1.hah;


import org.springframework.stereotype.Service;

import java.io.Serializable;
@Service
public  class UserResult2 implements Serializable {

    private Integer errorCode;
    private String message;

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
