package com.mq.study.exchange.fanout;

import com.mq.study.util.RabbitMqUtil;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 交换机消费者
 * @author gaoliangliang
 */
public class ExchangeConsumer {

    public static final String LOG_EXCHANGE="logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtil.getChannel();
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName,LOG_EXCHANGE,"");
        System.out.println("等待接受消息，并把消息打印到屏幕.......");
        DeliverCallback deliverCallback=(consumerTag,message)->{
            System.out.println(Thread.currentThread().getName()+"接受到的消息为："+new String(message.getBody(),"UTF-8"));
        };
        CancelCallback cancelCallback=(consumerTag)->{

        };
        channel.basicConsume(queueName,deliverCallback,cancelCallback);

    }

}
