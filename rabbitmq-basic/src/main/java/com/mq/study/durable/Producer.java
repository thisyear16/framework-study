package com.mq.study.durable;

import com.mq.study.util.RabbitMqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 持久化队列
 * 消息持久化
 */
public class Producer {

    public static final String DURABLE_QUEUE="durable_queue";


    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtil.getChannel();
        channel.queueDeclare(DURABLE_QUEUE,true,false,false,null);
        String msg="这是一个持久化队列发出的持久化消息";
        channel.basicPublish("",DURABLE_QUEUE,false, MessageProperties.PERSISTENT_BASIC,msg.getBytes("UTF-8"));
    }
}
