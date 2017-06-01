package com.internet.http.api.data;


/**
 * 公用的接口返回数据结构
 * @date 2014-8-27
 * @author declan.z(declan.zhang@gmail.com)
 *
 */
public class ApiResult {
    
    public static final int SUCCESS = 0;
    public static final int FAIL = -1;
    
    private int status;
    
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public boolean isSuccess() {
        return status == SUCCESS;
    }
    
    
}
