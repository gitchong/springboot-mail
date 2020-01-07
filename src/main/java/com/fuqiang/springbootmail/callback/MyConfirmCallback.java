package com.fuqiang.springbootmail.callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * <p>Title: MyConfirmCallback</p>
 * <p>Description: MyConfirmCallback</p>
 * <p>Copyright: Xi An BestTop Technologies, ltd. Copyright(c) 2018/p>
 *
 * @author Fuqiang
 * @version 0.0.0.1
 * <pre>Histroy:
 *       2019/12/30 0030 16:26 Create by Fuqiang
 * </pre>
 */
public class MyConfirmCallback implements RabbitTemplate.ConfirmCallback {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 发送确认的回调方法(消息是否到达交换机)
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.info("消息回调ID为: {}", correlationData.getId());
        if (ack) {
            logger.info("消息发送确认成功，已到达交换机");
        } else {
            logger.info("消息发送确认失败，原因: {}", cause);
        }
    }
}
