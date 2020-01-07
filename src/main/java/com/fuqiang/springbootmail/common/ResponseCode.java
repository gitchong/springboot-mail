package com.fuqiang.springbootmail.common;

/**
 * <p>Title: ResponseCode</p>
 * <p>Description: ResponseCode</p>
 * <p>Copyright: Xi An BestTop Technologies, ltd. Copyright(c) 2018/p>
 *
 * @author Fuqiang
 * @version 0.0.0.1
 * <pre>Histroy:
 *       2019/11/18 0018 9:12 Create by Fuqiang
 * </pre>
 */
public enum ResponseCode {

    SUCCESS(200, "SUCCESS"),
    // 提示
    HINT(600,"PROMPT"),
    // 警告
    WARNING(400,"WARNING"),
    ERROR(550, "ERROR");

    private final int code;
    private final String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
