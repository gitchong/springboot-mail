package com.fuqiang.springbootmail.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fuqiang.springbootmail.model.MyMail;
import com.fuqiang.springbootmail.service.mq.MQProducer;
import com.fuqiang.springbootmail.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>Title: MailController</p>
 * <p>Description: MailController</p>
 * <p>Copyright: Xi An BestTop Technologies, ltd. Copyright(c) 2018/p>
 *
 * @author Fuqiang
 * @version 0.0.0.1
 * <pre>Histroy:
 *       2019/12/28 0028 10:39 Create by Fuqiang
 * </pre>
 */
@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailUtil mailUtil;
    @Autowired
    private MQProducer mqProducer;

    @PostMapping("/send/sendObject")
    public String sendObject(@RequestBody MyMail mail) {
        try {
            mqProducer.sendMail(mail);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "ok";
    }

    /**
     * 发送普通文本邮件
     */
    @PostMapping("/sendSimple")
    public String sendSimple(@RequestParam String to, @RequestParam String subject, @RequestParam String content) {
        try {
            mailUtil.sendSimpleMail(to, subject, content);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * 发送HTML邮件
     */
    @PostMapping("/sendHTML")
    public String sendHTML(@RequestParam String to, @RequestParam String subject, @RequestParam String content) {
        try {
            mailUtil.sendHtmlMail(to, subject, content);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * 发送附件邮件
     */
    @PostMapping("/sendAttachment")
    public String sendAttachment(@RequestParam String to, @RequestParam String subject, @RequestParam String content, @RequestParam String filePath) {
        try {
            mailUtil.sendAttachmentsMail(to, subject, content, filePath);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
