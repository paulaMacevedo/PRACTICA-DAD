package es.urjc.dad.api_service.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import es.urjc.dad.api_service.config.RabbitConfig;

@Service
public class NetworkProducer {

    private final RabbitTemplate rabbitTemplate;

    public NetworkProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendNetworkCreationRequest(String message) {
        rabbitTemplate.convertAndSend(RabbitConfig.NOMBRE_BASE, message);
    }

    public void sendNetworkDeleteRequest(Long networkId) {
        rabbitTemplate.convertAndSend("net-delete-requests", networkId);
    }
}
