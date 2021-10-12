package com.mq.study.controller;

import com.mq.study.config.MqQueueConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * @author gaoll
 * @description 发送消息
 * @date 2021-10-12 14:30
 */
@RequestMapping
@RestController
public class SendTestController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("sendMessage/{msg}")
    public void sayHello(@PathVariable("msg")String msg) throws UnsupportedEncodingException {
        rabbitTemplate.convertAndSend(MqQueueConfig.ROUTING_KEY,MqQueueConfig.DEAD_EXCHANGE,msg.getBytes("UTF-8"));
    }

}
