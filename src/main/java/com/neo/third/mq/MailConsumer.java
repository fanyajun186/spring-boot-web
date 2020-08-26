package com.neo.third.mq;


import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.neo.config.RabbitConfig;
import com.neo.domain.mail.Mail;
import com.neo.domain.mail.MsgLog;
import com.neo.service.MsgLogService;
import com.neo.util.MailUtil;
import com.rabbitmq.client.Channel;

import groovy.util.logging.Slf4j;

@Component
@Slf4j
public class MailConsumer {
	
	private static Logger log = LoggerFactory.getLogger(MailConsumer.class);

    @Autowired
    private MsgLogService msgLogService;

    @Autowired
    private MailUtil mailUtil;

    @RabbitListener(queues = RabbitConfig.MAIL_QUEUE_NAME)
    public void consume(Message message, Channel channel) throws IOException {
    	
    	
    	
      /*  Mail mail = MessageHelper.msgToObj(message, Mail.class);
        
        log.info("收到消息: {}", mail.toString());

        String msgId = mail.getMsgId();

        //查库判断状态
        MsgLog msgLog = msgLogService.selectByMsgId(msgId);
        if (null == msgLog || msgLog.getStatus().equals(0)) {// 消费幂等性
            log.info("重复消费, msgId: {}", msgId);
            return;
        }

        MessageProperties properties = message.getMessageProperties();
        long tag = properties.getDeliveryTag();

        //发邮件
        boolean success = mailUtil.send(mail);
        if (success) {
            msgLogService.updateStatus(msgId, 0);
            channel.basicAck(tag, false);// 消费确认
        } else {
            channel.basicNack(tag, false, true);
        }*/
    }

}