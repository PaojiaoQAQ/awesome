package com.example.demo.rabbitmq.practice.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TopicComsumer {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("121.36.216.197");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("123456");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setAutomaticRecoveryEnabled(true);
        connectionFactory.setNetworkRecoveryInterval(3000);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        String queueName = "topic_queue";
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName,true,consumer);
        while(true){
            //获取消息，如果没有消息，这一步将会一直阻塞
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.out.println("收到消息：" + msg);
        }
    }
}
