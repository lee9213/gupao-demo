package com.lee9213.mq.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.After;
import org.junit.Before;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-22 11:10
 */
public class AbstractTest {
    private Connection connection = null;
    protected Session session;

    @Before
    public void before() {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.0.18:61616");
        try {
            connection = connectionFactory.createConnection();
            connection.setClientID("client-0001001");
            connection.start();
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

    @After
    public void after() {
        if (session != null) {
            try {
                session.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
