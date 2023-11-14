package com.example.demo.kafka.controller;

import com.example.demo.common.entity.Result;
import com.example.demo.utils.JacksonJsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description
 * @author tanyue
 * @Date 2023/11/13
 **/
@RestController
@RequestMapping({"/kafka"})
@Slf4j
public class KafkaController
{
    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;
    @RequestMapping({"/producerSend/{topic}/{message}"})
    public Result<Object> sendKafkaMessage(@PathVariable("topic") String topic, @PathVariable("message") String message) {
        ProducerRecord<String, Object> record = new ProducerRecord(topic, "user", message);
        this.kafkaTemplate.send(record).addCallback(sendResult -> {
            String data = JacksonJsonUtils.toJsonString(sendResult.getRecordMetadata());
            log.info("发送kafka消息成功，信息:{}", data);
        }, throwable -> log.error("发送kafka消息异常:{}", throwable.getMessage()));
        return (new Result()).success();
    }
}
