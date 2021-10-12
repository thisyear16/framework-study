package com.mq.study.exchange.direct;

import com.mq.study.util.RabbitMqUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * 直连交换机
 */
public class DirectProducer {

    public static final String EXCHANGE_NAME="direct_logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtil.getChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        Map<String,String> msgMap=new HashMap<>();
        msgMap.put("info","info消息");
        msgMap.put("debug","debug消息");
        msgMap.put("warning","warning消息");
        msgMap.put("error","error消息");
        for (Map.Entry<String, String> item : msgMap.entrySet()) {
            String key=item.getKey();
            String message=item.getValue();
            channel.basicPublish(EXCHANGE_NAME,key,null,message.getBytes("UTF-8"));
            System.out.println("生产者发出的消息："+message);
        }

    }
}
