package com.neo.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

public class ActiveMQProducer {

  //Quqe方式发送消息
    @Test
    public void sendQuqes() throws Exception{
        //1.连接mq
        ConnectionFactory  factory = new ActiveMQConnectionFactory("tcp://:61616");
        //2.创建connection
        Connection connection = factory.createConnection();
        //3.创建一个session
        //发送消息的提示,  false自动提交   true手动提交
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE );
        
        //4.设置队列发送的方式
        Queue  queue  = session.createQueue("test-queue");
        //创建生产者
        MessageProducer producer = session.createProducer(queue);
        //消息内容
        TextMessage message = session.createTextMessage("hello test queue  MQ!!!");
        //5.发送消息
        producer.send(message);
        //6.关闭连接
        producer.close();
        session.close();
        connection.close();
    }

    
}
