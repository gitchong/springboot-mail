package com.fuqiang.springbootmail.config;

import com.fuqiang.springbootmail.callback.MyConfirmCallback;
import com.fuqiang.springbootmail.callback.MyReturnCallback;
import lombok.Data;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.SerializerMessageConverter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * <p>Title: RabbitMQConfig</p>
 * <p>Description: RabbitMQConfig</p>
 * <p>Copyright: Xi An BestTop Technologies, ltd. Copyright(c) 2018/p>
 *
 * @author Fuqiang
 * @version 0.0.0.1
 * <pre>Histroy:
 *       2019/12/30 0030 9:18 Create by Fuqiang
 * </pre>
 */
@Configuration
@Data
@ConfigurationProperties(prefix = "spring.rabbitmq")
public class RabbitMQConfig {

    public static final String QUEUE = "QUEUE";
    public static final String MAIL_QUEUE = "MAIL_QUEUE";
    public static final String USER_QUEUE = "USER_QUEUE";

    public static final String MAIL_EXCHANGE = "MAIL_EXCHANGE";

    public static final String ROUTING_KEY = "ROUTING_KEY";
    public static final String MAIL_ROUTING_KEY = "MAIL_ROUTING_KEY";
    public static final String USER_ROUTING_KEY = "USER_ROUTING_KEY";

    private String username;
    private String password;
    private String host;
    private String port;
    private String virtualHost;

    @Bean
    public Queue mailQueue() {
        return new Queue(MAIL_QUEUE);
    }

    @Bean
    public Queue queue() {
        return new Queue(QUEUE);
    }

    @Bean
    public Queue userQueue() {
        return new Queue(USER_QUEUE);
    }

    /**
     * Fanout 就是我们熟悉的广播模式或者订阅模式，给Fanout交换机发送消息，绑定了这个交换机的所有队列都收到这个消息。
     *
     * @return
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(MAIL_EXCHANGE);
    }

    @Bean
    public Binding bindingMailExchange(Queue mailQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(mailQueue).to(topicExchange).with(MAIL_ROUTING_KEY);
    }

    @Bean
    public Binding bindingExchange(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with(ROUTING_KEY);
    }

    @Bean
    public Binding bindingUserExchange(Queue userQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(userQueue).to(topicExchange).with(USER_ROUTING_KEY);
    }

    @Bean("rabbitTemplate")
    @Scope("prototype")
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMandatory(true);
        template.setMessageConverter(new SerializerMessageConverter());
        /**
         *   一个rabbitTemplate只设置一次回调方法
         */
        template.setReturnCallback(new MyReturnCallback());
        template.setConfirmCallback(new MyConfirmCallback());
        return template;
    }

}
