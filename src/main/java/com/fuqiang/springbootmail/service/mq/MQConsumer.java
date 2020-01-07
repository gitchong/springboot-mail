package com.fuqiang.springbootmail.service.mq;

import com.alibaba.fastjson.JSON;
import com.fuqiang.springbootmail.config.RabbitMQConfig;
import com.fuqiang.springbootmail.model.MyMail;
import com.fuqiang.springbootmail.model.UserEntity;
import com.fuqiang.springbootmail.util.AliYunMessageServer;
import com.fuqiang.springbootmail.util.MailServer;
import com.fuqiang.springbootmail.util.MailUtil;
import com.rabbitmq.client.Channel;
import com.sun.jnlp.ExtendedServiceNSBImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.*;

/**
 * <p>Title: MQConsumer</p>
 * <p>Description: MQConsumer</p>
 * <p>Copyright: Xi An BestTop Technologies, ltd. Copyright(c) 2018/p>
 *
 * @author Fuqiang
 * @version 0.0.0.1
 * <pre>Histroy:
 *       2019/12/30 0030 9:34 Create by Fuqiang
 * </pre>
 */
@Component
public class MQConsumer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MailUtil mailUtil;
    @Autowired
    private MailServer mailServer;

    @RabbitHandler
    @RabbitListener(queues = {RabbitMQConfig.USER_QUEUE})
    public void processUser(Channel channel, Message message) throws Exception {
        try {
            String content = new String(message.getBody(), "UTF-8");
            logger.info("收到来自MAIL_QUEUE队列的消息: {}", content);
            UserEntity userEntity = JSON.parseObject(content, UserEntity.class);
            try {
                ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
                        2,
                        1000,
                        TimeUnit.MILLISECONDS,
                        new SynchronousQueue<Runnable>(),
                        Executors.defaultThreadFactory(),
                        new ThreadPoolExecutor.AbortPolicy());
                mailServer.setTo("fuqiangvn@163.com");
                mailServer.setSubject("恭喜您!注册成功");
                mailServer.setContent("尊敬的" + userEntity.getUsername() + "，恭喜您，注册验证通过。");
                threadPoolExecutor.submit(mailServer);
                /**
                 *  若该条消息已经被成功消费，则从队列删除。否则，下次接着消费这条消息，直到被成功消费，才从队列删除(跟是否开启重试无关)
                 *  比RabbitMQ默认ack方式更加安全
                 */
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                logger.info("消息消费成功");
            } catch (IOException e) {
                e.printStackTrace();
                //丢弃这条消息
//            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
                logger.info("消息消费失败，原因: {}", e.getCause());
            }
        } catch (UnsupportedEncodingException e) {
            throw new Exception("消息转码失败，请查看原因", e.getCause());
        }
    }

    @RabbitHandler
    @RabbitListener(queues = {RabbitMQConfig.MAIL_QUEUE})
    public void processMail(Channel channel, Message message) throws Exception {
        try {
            String content = new String(message.getBody(), "UTF-8");
            logger.info("收到来自MAIL_QUEUE队列的消息: {}", content);
            MyMail myMail = JSON.parseObject(content, MyMail.class);
            try {
                mailUtil.sendSimpleMail(myMail.getTo(), myMail.getSubject(), myMail.getContent());
                /**
                 *  若该条消息已经被成功消费，则从队列删除。否则，下次接着消费这条消息，直到被成功消费，才从队列删除(跟是否开启重试无关)
                 *  比RabbitMQ默认ack方式更加安全
                 */
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                logger.info("消息消费成功");
            } catch (IOException e) {
                e.printStackTrace();
                //丢弃这条消息
//            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
                logger.info("消息消费失败，原因: {}", e.getCause());
            }
        } catch (UnsupportedEncodingException e) {
            throw new Exception("消息转码失败，请查看原因", e.getCause());
        }
    }

    @RabbitHandler
    @RabbitListener(queues = {RabbitMQConfig.QUEUE})
    public void process(Channel channel, Message message) throws Exception {
        try {
            String content = new String(message.getBody(), "UTF-8");
            logger.info("收到来自QUEUE队列的消息: {}", content);
            try {
                /**
                 *  若该条消息已经被成功消费，则从队列删除。否则，下次接着消费这条消息，直到被成功消费，才从队列删除(跟是否开启重试无关)
                 *  比RabbitMQ默认ack方式更加安全
                 */
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                logger.info("消息消费成功");
            } catch (IOException e) {
                e.printStackTrace();
                //丢弃这条消息
//            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
                logger.info("消息消费失败，原因: {}", e.getCause());
            }
        } catch (UnsupportedEncodingException e) {
            throw new Exception("消息转码失败，请查看原因", e.getCause());
        }
    }
}
