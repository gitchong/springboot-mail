package com.fuqiang.springbootmail.util;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

/**
 * <p>Title: AliYunMessageServer</p>
 * <p>Description: AliYunMessageServer</p>
 * <p>Copyright: Xi An BestTop Technologies, ltd. Copyright(c) 2018/p>
 *
 * @author Fuqiang
 * @version 0.0.0.1
 * <pre>Histroy:
 *       2020/1/7 0007 9:36 Create by Fuqiang
 * </pre>
 */
@Data
@Component
public class AliYunMessageServer implements Callable<SendSmsResponse> {

    private String mobile;

    @Override
    public SendSmsResponse call() throws Exception {
        return AliYunMessageUtil.sendMessage(mobile);
    }
}
