package com.mq.study.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author gaoll
 * @description mq配置文件
 * @date 2021-10-12 11:44
 */
@Component
@Configuration
public class MqQueueConfig {

    public static final String ACK_QUEUE="ack_queue";
    public static final String DEAD_EXCHANGE="dead_exchange";
    public static final String ROUTING_KEY="abc";

    @Bean("ackQueue")
    public Queue ackQueue(){
        return new Queue(ACK_QUEUE);
    }

    @Bean("deadExchange")
    public DirectExchange deadExchange(){
        return new DirectExchange(DEAD_EXCHANGE);
    }
    @Bean
    public Binding binding(@Qualifier("ackQueue") Queue ackQueue,
                           @Qualifier("deadExchange") DirectExchange deadExchange){
        return BindingBuilder.bind(ackQueue).to(deadExchange).with(ROUTING_KEY);
    }

}
