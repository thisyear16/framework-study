package com.mq.study.basic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *简单生产者
 * @author gaoliangliang
 */
public class Producer {

    public static final String QUEUE_NAME="hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory=new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        /**
         *                                    String queue,
         *                                    boolean durable,
         *                                    boolean exclusive,
         *                                    boolean autoDelete,
         *                                    Map<String, Object> arguments
         *                                    /**
         * * 生成一个队列
         * * 1.队列名称
         * * 2.队列里面的消息是否持久化 默认消息存储在内存中
         * * 3.该队列是否只供一个消费者进行消费 是否进行共享 true 可以多个消费者消费
         * * 4.是否自动删除 最后一个消费者端开连接以后 该队列是否自动删除 true 自动删除
         * * 5.其他参数
         * */
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        /**
         * 发送一个消息
         * 1.发送到那个交换机
         * 2.路由的 key 是哪个
         * 3.其他的参数信息
         * 4.发送消息的消息体
         */
        String message="hello";
//        channel.basicPublish("","",null,message.getBytes("UTF-8"));


        for (int i = 0; i < 10; i++) {
            channel.basicPublish("",QUEUE_NAME,null,(message+i).getBytes());

        }

        System.out.println("消息发送完毕");
    }
}
