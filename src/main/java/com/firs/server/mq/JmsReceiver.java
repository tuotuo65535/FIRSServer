/**
 * @author chenzy 20180529 MQ 监听器实现
 */
package com.firs.server.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.firs.server.netty.NettyServer;

@Service
public class JmsReceiver {
	
	private static final Logger logger = LoggerFactory.getLogger(JmsReceiver.class);

	@JmsListener(destination = JmsConfiguration.QUEUE_NAME)
	public void receiveByQueue(String message) {
		logger.info("接收队列消息:" + message);
	}

	@JmsListener(destination = JmsConfiguration.TOPIC_NAME, containerFactory="topicListenerContainer")
	public void receiveByTopic(String message) {
		logger.info("接收主题消息:" + message);
	}
}
