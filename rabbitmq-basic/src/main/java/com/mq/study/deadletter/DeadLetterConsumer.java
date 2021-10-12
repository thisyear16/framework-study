package com.mq.study.deadletter;

import com.mq.study.util.RabbitMqUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * 死信队列消费者
 */
public class DeadLetterConsumer {
    public static final String NORMAL_EXCHANGE="normal_exchange";
    public static final String NORMAL_QUEUE="normal_queue";
    public static final String DEAD_EXCHANGE="dead_exchange";
    public static final String DEAD_QUEUE="dead_queue";


    public static void main(String[] args) throws IOException, TimeoutException {

        Channel channel = RabbitMqUtil.getChannel();
        channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
        channel.exchangeDeclare(DEAD_EXCHANGE, BuiltinExchangeType.DIRECT);
        channel.queueDeclare(DEAD_QUEUE,false,false,false,null);
        channel.queueBind(DEAD_QUEUE,DEAD_EXCHANGE,"lisi");
        DeliverCallback deliverCallback=(consumerTag,message)->{
            System.out.println("consumer接受到的消息"+new String(message.getBody(),"UTF-8"));
        };
        CancelCallback cancelCallback=(consumerTag)->{};
        Map<String, Object> arguments=new HashMap<>();
        arguments.put("x-dead-letter-exchange",DEAD_EXCHANGE);
        arguments.put("x-dead-letter-routing-key","lisi");

        channel.queueDeclare(NORMAL_QUEUE,false,false,false,arguments);
        channel.queueBind(NORMAL_QUEUE,NORMAL_EXCHANGE,"zhangsan");
        channel.basicConsume(NORMAL_QUEUE,true,deliverCallback,cancelCallback);
    }

}
