package com.mq.study.deadletter;

import com.mq.study.util.RabbitMqUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * 死信队列消费者
 */
public class DeadLetterConsumer01 {

    public static final String DEAD_EXCHANGE="dead_exchange";
    public static final String DEAD_QUEUE="dead_queue";


    public static void main(String[] args) throws IOException, TimeoutException {

        Channel channel = RabbitMqUtil.getChannel();
        channel.exchangeDeclare(DEAD_EXCHANGE, BuiltinExchangeType.DIRECT);
        channel.queueDeclare(DEAD_QUEUE,false,false,false,null);
        channel.queueBind(DEAD_QUEUE,DEAD_EXCHANGE,"lisi");
        DeliverCallback deliverCallback=(consumerTag,message)->{
            System.out.println("consumer接受到死信的消息"+new String(message.getBody(),"UTF-8"));
        };
        CancelCallback cancelCallback=(consumerTag)->{};
               channel.basicConsume(DEAD_QUEUE,true,deliverCallback,cancelCallback);
    }

}
