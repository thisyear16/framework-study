package com.mq.study.durable;

import com.mq.study.util.RabbitMqUtil;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 接受持久化消息
 */
public class DurableConsumer {

    public static final String DURABLE_QUEUE="durable_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtil.getChannel();
        channel.basicConsume(DURABLE_QUEUE,(consumerTag,message)->{
            System.out.println("接受到持久化队列的消息"+new String(message.getBody(),"UTF-8"));
        },
        (consumerTag)->{
            //消息被撤回的时候执行的操作
        });
    }

}
