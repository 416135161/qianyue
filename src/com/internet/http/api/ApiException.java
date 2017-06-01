package com.internet.http.api;

/**
 * 根据接口返回的code, data决定的业务逻辑上的Exception
 * @date 2014-8-27
 * @author declan.z(declan.zhang@gmail.com)
 *
 */
public class ApiException extends Exception {
    
    private static final long serialVersionUID = 1L;

    private int errorCode;
    
    private String errorMessage;

    public ApiException(String errorMessage) {
        super();
        this.errorMessage = errorMessage;
    }
    
    public ApiException(String errorMessage, Throwable throwable) {
        super(throwable);
        this.errorMessage = errorMessage;
    }

    public ApiException(int errorCode) {
        super();
        this.errorCode = errorCode;
    }
    
    public ApiException(int errorCode, Throwable throwable) {
        super(throwable);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
    
    
}
