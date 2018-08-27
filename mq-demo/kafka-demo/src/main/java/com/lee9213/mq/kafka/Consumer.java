package com.lee9213.mq.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.Test;

import java.util.Collections;
import java.util.Properties;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-23 20:55
 */
public class Consumer {

    @Test
    public void test1() throws InterruptedException {

        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "192.168.0.18:9092,192.168.0.18:9093,192.168.0.18:9094");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "KafkaConsumerDemo1");
//        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList("lucky_draw_chance_topic"));

//        consumer.assign(Collections.singletonList(new TopicPartition("lucky_draw_chance_topic",1)));
        while (true) {
            ConsumerRecords<String, String> consumerRecord = consumer.poll(1000);
            for (ConsumerRecord record : consumerRecord) {
                System.out.println("message receive:" + record.value() + "-> topic:"+record.topic()+"-> partition:"+record.partition());
//                consumer.commitAsync();

//                Thread.sleep(200);
            }
        }
    }
    @Test
    public void test2() throws InterruptedException {

        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "192.168.0.18:9092,192.168.0.18:9093,192.168.0.18:9094");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "KafkaConsumerDemo2");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");

        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList("lucky_draw_chance_topic"));
//        consumer.assign(Collections.singletonList(new TopicPartition("lucky_draw_chance_topic",1)));
        while (true) {
            ConsumerRecords<String, String> consumerRecord = consumer.poll(1000);
            for (ConsumerRecord record : consumerRecord) {
                System.out.println("message receive:" + record.value() + "-> topic:"+record.topic()+"-> partition:"+record.partition());
//                Thread.sleep(200);
            }
        }
    }
    @Test
    public void test3() throws InterruptedException {

        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "192.168.0.18:9092,192.168.0.18:9093,192.168.0.18:9094");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "KafkaConsumerDemo2");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList("lucky_draw_chance_topic"));
//        consumer.assign(Collections.singletonList(new TopicPartition("lucky_draw_chance_topic",1)));
        while (true) {
            ConsumerRecords<String, String> consumerRecord = consumer.poll(1000);
            for (ConsumerRecord record : consumerRecord) {
                System.out.println("message receive:" + record.value() + "-> topic:"+record.topic()+"-> partition:"+record.partition());
//                Thread.sleep(200);
            }
        }
    }
}
