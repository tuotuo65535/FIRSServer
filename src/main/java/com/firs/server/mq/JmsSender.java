/**
 * @author chenzy 20180529 MQ发送测试 
 */
package com.firs.server.mq;

import javax.jms.Queue;
import javax.jms.Topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JmsSender {
	
	private static final Logger logger = LoggerFactory.getLogger(JmsSender.class);
	
	@Autowired
	private JmsMessagingTemplate jmsTemplate;

	@Autowired
	private Queue queue;
	
	@Autowired
	private Topic topic;

	public void sendByQueue(String message) {
		System.out.println("sendByQueue" + message);
		this.jmsTemplate.convertAndSend(queue, message);
		logger.info("发送队列消息:" + message);
	}

	public void sendByTopic(String message) {
		this.jmsTemplate.convertAndSend(topic, message);
		logger.info("发送主题消息:" + message);
	}
}
