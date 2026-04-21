package es.urjc.dad.net_service.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import es.urjc.dad.net_service.config.RabbitConfig;
import es.urjc.dad.net_service.dto.IpAssignRequest;
import es.urjc.dad.net_service.dto.IpAssignResponse;
import es.urjc.dad.net_service.dto.IpUnassignRequest;
import es.urjc.dad.net_service.dto.IpUnassignResponse;
import es.urjc.dad.net_service.service.NetworkLifecycleService;

@Component
public class NetworkMessageListener {

	private final NetworkLifecycleService networkLifecycleService;
	private final RabbitTemplate rabbitTemplate;

	public NetworkMessageListener(NetworkLifecycleService networkLifecycleService,
			RabbitTemplate rabbitTemplate) {
		this.networkLifecycleService = networkLifecycleService;
		this.rabbitTemplate = rabbitTemplate;
	}

	@RabbitListener(
			queues = {RabbitConfig.NET_CREATION_REQUESTS})
	public void handleNetworkCreationRequest(String mask) {
		networkLifecycleService.createNetwork(mask);
	}

	@RabbitListener(queues = RabbitConfig.NET_DELETE_REQUESTS)
	public void handleNetworkDeleteRequest(Long networkId) {
		networkLifecycleService.deleteNetwork(networkId);
	}

	@RabbitListener(queues = RabbitConfig.IP_ASSIGN_REQUESTS)
	public void handleIpAssignRequest(IpAssignRequest request) {
		IpAssignResponse response = networkLifecycleService.assignIp(request);
		rabbitTemplate.convertAndSend(RabbitConfig.IP_ASSIGN_RESPONSES, response);
	}

	@RabbitListener(queues = RabbitConfig.IP_UNASSIGN_REQUESTS)
	public void handleIpUnassignRequest(IpUnassignRequest request) {
		IpUnassignResponse response = networkLifecycleService.unassignIp(request);
		rabbitTemplate.convertAndSend(RabbitConfig.IP_UNASSIGN_RESPONSES, response);
	}
}
