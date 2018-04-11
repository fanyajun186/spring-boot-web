package com.neo.activemq;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

public class ActiveMQConsumer {

    @Test
    public void testCoustemQueue() throws Exception{
		//1.连接mq
        ConnectionFactory  factory = new ActiveMQConnectionFactory("tcp://172.18.5.98:61616");
        //2.创建connection
        Connection connection = factory.createConnection();
        //启动
        connection.start();
        //3.创建一个session
        //发送消息的提示,  false自动提交   true手动提交
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE );
        
        //4.设置队列发送的方式
        Queue  queue  = session.createQueue("test-queue");
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(new MessageListener(){

            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    String str = textMessage.getText();
                    System.out.println(str+"=======");
                } catch (JMSException e) {
                    e.printStackTrace();
                }
                
            }
            
        });
        System.in.read();
        //6.关闭连接
        consumer.close();
        session.close();
        connection.close();
    }

}
