package es.urjc.dad.net_service.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

	public static final String NET_CREATION_REQUESTS = "net-creation-requests";
	public static final String NET_CREATION_REQUEST_LEGACY = "net-creation-request";
	public static final String NET_DELETE_REQUESTS = "net-delete-requests";
	public static final String IP_ASSIGN_REQUESTS = "ip-assign-requests";
	public static final String IP_ASSIGN_RESPONSES = "ip-assign-responses";
	public static final String IP_UNASSIGN_REQUESTS = "ip-unassign-requests";
	public static final String IP_UNASSIGN_RESPONSES = "ip-unassign-responses";

	@Bean
	public Queue netCreationRequestsQueue() {
		return new Queue(NET_CREATION_REQUESTS, false);
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public Queue netDeleteRequestsQueue() {
		return new Queue(NET_DELETE_REQUESTS, false);
	}

	@Bean
	public Queue ipAssignRequestsQueue() {
		return new Queue(IP_ASSIGN_REQUESTS, false);
	}

	@Bean
	public Queue ipAssignResponsesQueue() {
		return new Queue(IP_ASSIGN_RESPONSES, false);
	}

	@Bean
	public Queue ipUnassignRequestsQueue() {
		return new Queue(IP_UNASSIGN_REQUESTS, false);
	}

	@Bean
	public Queue ipUnassignResponsesQueue() {
		return new Queue(IP_UNASSIGN_RESPONSES, false);
	}
}
