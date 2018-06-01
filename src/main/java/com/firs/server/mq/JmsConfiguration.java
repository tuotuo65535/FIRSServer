/**
 * @author chenzy 20180529 MQ 监听器
 */
package com.firs.server.mq;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

@Configuration
@EnableJms
public class JmsConfiguration {
	
	public static final String QUEUE_NAME = "device.queue";
	public static final String TOPIC_NAME = "device.topic";

	@Bean
	public Queue queue() {
		return new ActiveMQQueue(QUEUE_NAME);
	}

	@Bean
	public Topic topic() {
		return new ActiveMQTopic(TOPIC_NAME);
	}
	
	/** 
     * JmsListener注解默认只接收queue消息,如果要接收topic消息,需要设置containerFactory 
     */  
    @Bean  
    public JmsListenerContainerFactory<?> topicListenerContainer(ConnectionFactory activeMQConnectionFactory) {  
        DefaultJmsListenerContainerFactory topicListenerContainer = new DefaultJmsListenerContainerFactory();  
        topicListenerContainer.setPubSubDomain(true);  
        topicListenerContainer.setConnectionFactory(activeMQConnectionFactory);  
        return topicListenerContainer;  
    }  
}
