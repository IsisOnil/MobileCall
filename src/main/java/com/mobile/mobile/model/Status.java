package com.mobile.mobile.model;

public class Status {
    
    private int code;
    private String message;
    private Object data;    

    public Status() {
        super();
    }

    public Status(int code,String message, Object data) {
        super();
        this.code = code;
        this.message = message;
        this.data=data;
    }

    public int getCode() { return code; }
    public String getMessage() { return message; }
    public Object getData() { return data ; }

    public void setcode(int code) {this.code=code;}
    public void setMessage(String message) {this.message=message;}
    public void setData(Object data) {this.data=data;}
    
}
