package es.urjc.dad.api_service.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// this entity is going to send messages between applications without talking directly
@Configuration
public class RabbitConfig {
	public static final String NOMBRE_BASE = "net-creation-requests";

	@Bean
	public Queue queue() {
		return new Queue(NOMBRE_BASE, false);
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
}
