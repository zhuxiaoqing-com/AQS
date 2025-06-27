package com.example.demo1.hah;




import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Service
public  class UserResult<T> implements Serializable {
    private  Map<Integer, String> errorMap = new ConcurrentHashMap<Integer, String>(){
        {
            put(0,"查询成功!");
            put(1001,"服务器未找到");
            put(1002,"签名信息错误");
            put(1003,"超时");
            put(1004,"用户未找到");
        }
    };
    private Integer errorCode;
    private T message;


    // 根据错误码获取错误信息 自定义的信息
    public String getMyMessage() {
        String message = errorMap.get(errorCode);
        if (message == null || message.isEmpty()) {
            return "未知错误码";
        } else {
            return message;
        }
    }
    // 判断调用是否返回成功
    public boolean getResult() {
        if(errorCode == 0 ) {
            return true;
        } else {
            return false;
        }
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }
}
