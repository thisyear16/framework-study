package com.mq.study.exchange.topic;

import com.mq.study.util.RabbitMqUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * 主題模式
 */
public class TopicProducer {

    public static final String EXCHANGE_NAME="topic_logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtil.getChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        Map<String,String> msgMap=new HashMap<>();
        msgMap.put("quick.orange.rabbit","被队列 Q1Q2 接收到");
        msgMap.put("lazy.orange.elephant","被队列 Q1Q2 接收到");
        msgMap.put("quick.orange.fox","被队列 Q1 接收到");
        msgMap.put("lazy.brown.fox","被队列 Q2 接收到");
        for (Map.Entry<String, String> queueEntry : msgMap.entrySet()) {
            String key = queueEntry.getKey();
            String message = queueEntry.getValue();
            channel.basicPublish(EXCHANGE_NAME,key,null,message.getBytes("UTF-8"));
            System.out.println("生产者发出的消息："+message);
        }
    }

}
