package com.mq.study.ack;

import com.mq.study.util.RabbitMqUtil;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * 消息应答
 */
public class Producer01 {

    public static final String QUEUE_NAME="ack_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtil.getChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入：");
        while (scanner.hasNext()){
            channel.basicPublish("",QUEUE_NAME,null,scanner.nextLine().getBytes("UTF-8"));
        }
    }

}
