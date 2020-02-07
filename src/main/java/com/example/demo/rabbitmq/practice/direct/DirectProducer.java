package com.example.demo.rabbitmq.practice.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class DirectProducer {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("47.102.159.64");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");
        Connection connection = connectionFactory.newConnection();
        String exchangeName = "direct_exchange";
        String queueName = "direct_queue";
        String type = "direct";
        String routingkey = "test.direct";
        String msg = "this is direct";
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(exchangeName, type,true,false,false,null);
        channel.queueDeclare(queueName, false, false, false, null);
        channel.queueBind(queueName, exchangeName, routingkey);
        channel.basicPublish(exchangeName,routingkey,null,msg.getBytes());
        channel.close();
        connection.close();
    }
}
