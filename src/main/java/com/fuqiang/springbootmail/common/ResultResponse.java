package com.fuqiang.springbootmail.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * <p>Title: ServerResponse</p>
 * <p>Description: ServerResponse</p>
 * <p>Copyright: Xi An BestTop Technologies, ltd. Copyright(c) 2018/p>
 *
 * @author Fuqiang
 * @version 0.0.0.1
 * <pre>Histroy:
 *       2019/11/18 0018 9:09 Create by Fuqiang
 * </pre>
 */
// 保证序列化json的时候,如果是null的对象,key也会消失
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ResultResponse<T> implements Serializable {
    private int status;
    private String message;
    private T data;

    private ResultResponse(int status) {
        this.status = status;
    }

    private ResultResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }

    public ResultResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResultResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    @JsonIgnore
    // 使之不在json序列化结果当中
    public boolean isSuccess() {
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    public int getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public static <T> ResultResponse<T> createBySuccess() {
        return new ResultResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ResultResponse<T> createBySuccessMessage(String msg) {
        return new ResultResponse<T>(ResponseCode.SUCCESS.getCode(), msg);
    }

    public static <T> ResultResponse<T> createBySuccess(T data) {
        return new ResultResponse<T>(ResponseCode.SUCCESS.getCode(), data);
    }

    public static <T> ResultResponse<T> createBySuccess(String msg, T data) {
        return new ResultResponse<T>(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    public static <T> ResultResponse<T> createByError() {
        return new ResultResponse<T>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
    }

    public static <T> ResultResponse<T> createByErrorMessage(String errorMessage) {
        return new ResultResponse<T>(ResponseCode.ERROR.getCode(), errorMessage);
    }

    public static <T> ResultResponse<T> createByErrorCodeMessage(int errorCode, String errorMessage) {
        return new ResultResponse<T>(errorCode, errorMessage);
    }
}
