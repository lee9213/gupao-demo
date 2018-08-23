package com.lee9213.mq.activemq;

import org.junit.Test;

import javax.jms.*;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-22 10:07
 */
public class Producer extends AbstractTest{

    @Test
    public void testQueue() {
        try {
            Queue queue = session.createQueue("queue-1");
            MessageProducer producer = session.createProducer(queue);
            TextMessage textMessage = session.createTextMessage("hello, i'm activemq queue");
            producer.send(textMessage);
            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testTopic(){
        try {
            Topic topic = session.createTopic("topic-1");
            MessageProducer producer = session.createProducer(topic);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);

            TextMessage textMessage = session.createTextMessage("hello, i'm activemq topic");

            producer.send(textMessage);

            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
