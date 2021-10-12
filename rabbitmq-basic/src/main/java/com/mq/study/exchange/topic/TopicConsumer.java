package com.mq.study.exchange.topic;

import com.mq.study.util.RabbitMqUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 主题交换机消费者
 * @author gaoliangliang
 */
public class TopicConsumer {

    public static final String EXCHANGE_NAME="topic_logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtil.getChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        channel.queueDeclare("Q1",false,false,false,null);
        channel.queueBind("Q1",EXCHANGE_NAME,"*.orange.*");
        DeliverCallback deliverCallback=(consumerTag,message)->{
            System.out.println("Q1接收到的消息："+new String(message.getBody(),"UTF-8"));
        };
        CancelCallback cancelCallback=(consumerTag)->{};
        channel.basicConsume("Q1",deliverCallback,cancelCallback);
    }
}
