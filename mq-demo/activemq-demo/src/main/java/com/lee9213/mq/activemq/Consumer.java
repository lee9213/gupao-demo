package com.lee9213.mq.activemq;

import org.junit.Test;

import javax.jms.*;
import java.io.IOException;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-22 10:07
 */
public class Consumer extends AbstractTest {


    @Test
    public void testQueue() {
        try {
            Queue queue = session.createQueue("queue-1");

            MessageConsumer consumer = session.createConsumer(queue);
            TextMessage message = (TextMessage) consumer.receive();
            message.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
            System.out.println(message.getText());
//            message.acknowledge();
            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTopic() {
        try {
            Topic topic = session.createTopic("topic-1");

            MessageConsumer consumer = session.createConsumer(topic);
//            MessageConsumer consumer = session.createDurableSubscriber(topic,"client-0001001");


            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {

                    try {
                        TextMessage textMessage = (TextMessage) message;
                        System.out.println(textMessage.getText());

                        session.commit();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (JMSException e) {
            e.printStackTrace();
        }

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testTopic2() {
        try {
            Topic topic = session.createTopic("topic-1");

            MessageConsumer consumer = session.createConsumer(topic);
//            MessageConsumer consumer = session.createDurableSubscriber(topic, "client-0001001");

            TextMessage message = (TextMessage) consumer.receive();
            System.out.println(message.getText());

        } catch (JMSException e) {
            e.printStackTrace();
        }

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
