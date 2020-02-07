package com.example.demo.rabbitmq.practice.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class FanoutProducer {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("47.102.159.64");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");
        Connection connection = connectionFactory.newConnection();
        String exchangeName = "fanout_exchange";
        String queueName1 = "fanout_queue1";
        String queueName2 = "fanout_queue2";
        String queueName3 = "fanout_queue3";
        String type = "fanout";
        String routingkey = "";
        String msg = "this is fanout";
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(exchangeName, type,true,false,false,null);
        channel.queueDeclare(queueName1, false, false, false, null);
        channel.queueDeclare(queueName2, false, false, false, null);
        channel.queueDeclare(queueName3, false, false, false, null);
        channel.queueBind(queueName1, exchangeName, routingkey);
        channel.queueBind(queueName2, exchangeName, routingkey);
        channel.queueBind(queueName3, exchangeName, routingkey);
        channel.basicPublish(exchangeName,routingkey,null,msg.getBytes());
        channel.close();
        connection.close();
    }
}
