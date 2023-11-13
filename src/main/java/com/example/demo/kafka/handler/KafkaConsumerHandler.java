package com.example.demo.kafka.handler;

import com.example.demo.kafka.constant.MessageConst;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description
 * @author tanyue
 * @Date 2023/11/13
 **/
@Component
@Slf4j
public class KafkaConsumerHandler
{
    @KafkaListener(id = "testConsumer", topics = { MessageConst.KAFKA_TEST_TOPIC })
    public void testTopicConsumer(List<ConsumerRecord<String, Object>> recordList) {
        log.info("开始消费kafka,topic:{},消费数量:{}", "test-topic", recordList.size());
        recordList.forEach(record -> {
            log.info("record:{}", record.value());
        });
    }
}
