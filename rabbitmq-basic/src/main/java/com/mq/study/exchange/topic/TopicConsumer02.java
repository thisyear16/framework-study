package com.mq.study.exchange.topic;

import com.mq.study.util.RabbitMqUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 主题交换机消费者
 */
public class TopicConsumer02 {

    public static final String EXCHANGE_NAME="topic_logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtil.getChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        channel.queueDeclare("Q2",false,false,false,null);
        channel.queueBind("Q2",EXCHANGE_NAME,"lazy.#");
        channel.queueBind("Q2",EXCHANGE_NAME,"*.*.rabbit");
        DeliverCallback deliverCallback=(consumerTag,message)->{
            System.out.println("Q2接收到的消息："+new String(message.getBody(),"UTF-8"));
        };
        CancelCallback cancelCallback=(consumerTag)->{};
        channel.basicConsume("Q2",deliverCallback,cancelCallback);
    }
}
