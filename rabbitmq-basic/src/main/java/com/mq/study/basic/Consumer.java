package com.mq.study.basic;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者
 */
public class Consumer {

    public static final String QUEUE_NAME="hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory=new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        /**
         * String queue, DeliverCallback deliverCallback, CancelCallback cancelCallback
         */
        DeliverCallback deliverCallback=(consumerTag,message)->{
            System.out.println("接受到消息"+new String(message.getBody(),"UTF-8"));

        };
        CancelCallback cancelCallback =(consumerTag)->{

        };
        channel.basicConsume(QUEUE_NAME,deliverCallback,cancelCallback);
    }

}
