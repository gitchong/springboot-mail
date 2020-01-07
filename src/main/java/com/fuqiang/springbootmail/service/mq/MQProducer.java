package com.fuqiang.springbootmail.service.mq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuqiang.springbootmail.callback.MyConfirmCallback;
import com.fuqiang.springbootmail.callback.MyReturnCallback;
import com.fuqiang.springbootmail.config.RabbitMQConfig;
import com.fuqiang.springbootmail.model.MyMail;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * <p>Title: MQProducer</p>
 * <p>Description: MQProducer</p>
 * <p>Copyright: Xi An BestTop Technologies, ltd. Copyright(c) 2018/p>
 *
 * @author Fuqiang
 * @version 0.0.0.1
 * <pre>Histroy:
 *       2019/12/30 0030 9:21 Create by Fuqiang
 * </pre>
 */
@Component
public class MQProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    public void sendUser(Object object) throws JsonProcessingException {
        // 自定义消息唯一标识
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(UUID.randomUUID().toString().replace("-", ""));
        Message message = MessageBuilder
                .withBody(objectMapper.writeValueAsBytes(object))
                .setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT)
                .build();
        /**
         *   没有正确的路由键位，才会执行returnCallback
         */
        this.rabbitTemplate.convertAndSend(RabbitMQConfig.MAIL_EXCHANGE, RabbitMQConfig.USER_ROUTING_KEY, message, correlationData);
    }

    public void sendMail(Object object) throws JsonProcessingException {
        // 自定义消息唯一标识
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(UUID.randomUUID().toString().replace("-", ""));
        Message message = MessageBuilder
                .withBody(objectMapper.writeValueAsBytes(object))
                .setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT)
                .build();
        /**
         *   没有正确的路由键位，才会执行returnCallback
         */
        this.rabbitTemplate.convertAndSend(RabbitMQConfig.MAIL_EXCHANGE, RabbitMQConfig.MAIL_ROUTING_KEY, message, correlationData);
    }

    public void send(Object object) throws JsonProcessingException {
        // 自定义消息唯一标识
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(UUID.randomUUID().toString().replace("-", ""));
        Message message = MessageBuilder
                .withBody(objectMapper.writeValueAsBytes(object))
                .setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT)
                .build();
        /**
         *   没有正确的路由键位，才会执行returnCallback
         */
        this.rabbitTemplate.convertAndSend(RabbitMQConfig.MAIL_EXCHANGE, RabbitMQConfig.ROUTING_KEY, message, correlationData);
    }
}
