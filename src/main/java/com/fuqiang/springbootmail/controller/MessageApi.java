package com.fuqiang.springbootmail.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.fuqiang.springbootmail.common.BaseController;
import com.fuqiang.springbootmail.common.ResultResponse;
import com.fuqiang.springbootmail.util.AliYunMessageUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Title: MessageApi</p>
 * <p>Description: MessageApi</p>
 * <p>Copyright: Xi An BestTop Technologies, ltd. Copyright(c) 2018/p>
 *
 * @author Fuqiang
 * @version 0.0.0.1
 * <pre>Histroy:
 *       2020/1/2 0002 18:22 Create by Fuqiang
 * </pre>
 */
@RestController
@RequestMapping("/aliyun")
public class MessageApi extends BaseController {

    /**
     * 发送短信验证码
     *
     * @param
     */
    @PostMapping("/sendMessage")
    public ResultResponse sendMessage(@RequestParam String mobile) {
        /** 阿里云发送短信 */
        SendSmsResponse sendSmsResponse = AliYunMessageUtil.sendMessage(mobile);
        if (sendSmsResponse.getCode().equals("OK")) {
            return okResponse("短信发送成功");
        }
        return errorResponse("短信发送失败");
    }

}
