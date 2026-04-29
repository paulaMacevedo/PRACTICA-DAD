package es.urjc.dad.instanceservice.messaging;

import es.urjc.dad.instanceservice.dto.IpAssignRequest;
import es.urjc.dad.instanceservice.dto.IpUnassignRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class InstanceMessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public InstanceMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void requestIpAssignment(String serverName, String mask) {
        IpAssignRequest request = new IpAssignRequest(serverName, mask);
        // We send the request to the queue defined by the net-service
        rabbitTemplate.convertAndSend("ip-assign-requests", request);
    }

    public void requestIpUnassignment(String serverName) {
        IpUnassignRequest request = new IpUnassignRequest(serverName);
        rabbitTemplate.convertAndSend("ip-unassign-requests", request);
    }
}

