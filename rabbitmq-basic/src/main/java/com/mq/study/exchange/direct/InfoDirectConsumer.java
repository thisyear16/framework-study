package com.mq.study.exchange.direct;

import com.mq.study.util.RabbitMqUtil;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * info 队列
 * @author gaoliangliang
 */
public class InfoDirectConsumer {
    public static final String EXCHANGE_NAME="direct_logs";
    public static final String QUEUE_NAME="console";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtil.getChannel();
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"info");
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"debug");
        System.out.println("等待接受消息.......");
        DeliverCallback deliverCallback=(consumerTag,message)->{
            System.out.println("接受到的消息是："+new String(message.getBody(),"UTF-8"));
        };
        CancelCallback cancelCallback=(consumerTag)->{

        };
        channel.basicConsume(QUEUE_NAME,deliverCallback,cancelCallback);
    }
}
