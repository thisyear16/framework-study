package com.mq.study.ack;

import com.mq.study.util.RabbitMqUtil;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 自动应答消费者端
 * @author gaoliangliang
 */

public class Consumer {
    public static final String QUEUE_NAME="ack_queue";
    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtil.getChannel();
        /**
         * String queue, DeliverCallback deliverCallback, CancelCallback cancelCallback
         */
        DeliverCallback deliverCallback=(consumerTag,message)->{
            System.out.println("接受到的消息是："+new String(message.getBody(),"UTF-8"));
            /**
             * multiple 的 true 和 false 代表不同意思
             * true 代表批量应答 channel 上未应答的消息
             * 比如说 channel 上有传送 tag 的消息 5,6,7,8 当前 tag 是8 那么此时
             *  5-8 的这些还未应答的消息都会被确认收到消息应答
             *  false 同上面相比
             */
            //false 只应答接收到的那个消息，true应答所有传递过来的消息
            channel.basicAck(message.getEnvelope().getDeliveryTag(),false);
        };
        CancelCallback cancelCallback=(consumerTag)->{

        };
//        channel.basicQos(1); 设置为1 的时候，队列为不公平队列，不会轮训分发消息
        boolean ack=false;
        /**
         * String queue, boolean autoAck, DeliverCallback deliverCallback, CancelCallback cancelCallback
         */
        channel.basicConsume(QUEUE_NAME,false,deliverCallback,cancelCallback);

    }
}
