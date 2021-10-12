package com.mq.study.deadletter;

import com.mq.study.util.RabbitMqUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 死信队列
 * @author gaoliangliang
 */
public class DeadLetterProducer {

    public static final String NORMAL_EXCHANGE="normal_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtil.getChannel();
        channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().expiration("5000").build();
        for (int i = 0; i < 1000 ; i++) {
            String message="message======="+i;
            channel.basicPublish(NORMAL_EXCHANGE,"zhangsan",properties,message.getBytes());
            System.out.println(Thread.currentThread().getName()+"发送的消息为："+message);
        }
    }
}
