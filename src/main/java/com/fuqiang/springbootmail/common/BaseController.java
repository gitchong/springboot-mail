package com.fuqiang.springbootmail.common;


/**
 * <p>Title: BaseController</p>
 * <p>Description: BaseController</p>
 * <p>Copyright: Xi An BestTop Technologies, ltd. Copyright(c) 2018/p>
 *
 * @author Fuqiang
 * @version 0.0.0.1
 * <pre>Histroy:
 *       2019/11/18 0018 9:18 Create by Fuqiang
 * </pre>
 */
public class BaseController {

    public BaseController() {
    }

    public ResultResponse okResponse() {
        return new ResultResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc(), null);
    }

    public <T> ResultResponse okResponse(T data) {
        return new ResultResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc(), data);
    }

    public <T> ResultResponse okResponse(String message, T data) {
        return new ResultResponse(ResponseCode.SUCCESS.getCode(), message, data);
    }

    public <T> ResultResponse okResponse(int code, String message, T data) {
        return new ResultResponse(code, message, data);
    }

    public <T> ResultResponse hintResponse(String message, T data) {
        return new ResultResponse(ResponseCode.HINT.getCode(), message, data);
    }

    public ResultResponse hintResponse(String message) {
        return new ResultResponse(ResponseCode.HINT.getCode(), message, null);
    }

    public <T> ResultResponse warningResponse(String message, T data) {
        return new ResultResponse(ResponseCode.WARNING.getCode(), message, data);
    }

    public ResultResponse warningResponse(String message) {
        return new ResultResponse(ResponseCode.WARNING.getCode(), message, null);
    }

    public ResultResponse errorResponse() {
        return new ResultResponse(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc(), null);
    }

    public <T> ResultResponse errorResponse(String message) {
        return new ResultResponse(ResponseCode.ERROR.getCode(), message, null);
    }

    public <T> ResultResponse errorResponse(String message, T data) {
        return new ResultResponse(ResponseCode.ERROR.getCode(), message, data);
    }
}
