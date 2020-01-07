package com.fuqiang.springbootmail.util;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>Title: MailServer</p>
 * <p>Description: MailServer</p>
 * <p>Copyright: Xi An BestTop Technologies, ltd. Copyright(c) 2018/p>
 *
 * @author Fuqiang
 * @version 0.0.0.1
 * <pre>Histroy:
 *       2020/1/6 0006 17:55 Create by Fuqiang
 * </pre>
 */
@Data
@Component
public class MailServer implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String to;
    private String subject;
    private String content;
    @Autowired
    private MailUtil mailUtil;

    @Override
    public void run() {
        mailUtil.sendSimpleMail(to, subject, content);
    }
}
