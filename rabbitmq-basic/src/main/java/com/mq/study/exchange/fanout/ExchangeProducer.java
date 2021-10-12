package com.mq.study.exchange.fanout;

import com.mq.study.util.RabbitMqUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * 交换机相关
 * @author gaoliangliang
 */
public class ExchangeProducer {


    public static final String LOG_EXCHANGE="logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtil.getChannel();
        channel.exchangeDeclare(LOG_EXCHANGE, BuiltinExchangeType.FANOUT);
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入：");
        while (scanner.hasNext()){
            String msg=scanner.nextLine();
            channel.basicPublish(LOG_EXCHANGE,"",null,msg.getBytes());
            System.out.println("生产者发出的消息："+msg);
        }


    }


}
