package com.example.demo.rabbitmq.practice.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TopicProducer {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("47.102.159.64");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");
        Connection connection = connectionFactory.newConnection();
        String exchangeName = "topic_exchange";
        String queueName = "topic_queue";
        String type = "topic";
        String routingkey1 = "test.key1";
        String routingkey2 = "test.key2";
        String routingkey3 = "test.key3.aaaa";
        String bindingKey = "test.#";
        String msg = "this is topic";
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(exchangeName, type,true,false,false,null);
        channel.queueDeclare(queueName, false, false, false, null);
        channel.queueBind(queueName, exchangeName, bindingKey);
        channel.basicPublish(exchangeName,routingkey1,null,msg.getBytes());
        channel.basicPublish(exchangeName,routingkey2,null,msg.getBytes());
        channel.basicPublish(exchangeName,routingkey3,null,msg.getBytes());
        channel.close();
        connection.close();
    }
}
