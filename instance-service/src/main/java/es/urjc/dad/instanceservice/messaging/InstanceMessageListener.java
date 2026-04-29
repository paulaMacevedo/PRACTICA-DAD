package es.urjc.dad.instanceservice.messaging;

import es.urjc.dad.instanceservice.dto.IpAssignResponse;
import es.urjc.dad.instanceservice.dto.IpUnassignResponse;
import es.urjc.dad.instanceservice.model.Instance;
import es.urjc.dad.instanceservice.repository.InstanceRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class InstanceMessageListener {

    private final InstanceRepository repository;
    private final InstanceMessageProducer messageProducer;

    public InstanceMessageListener(InstanceRepository repository,
            InstanceMessageProducer messageProducer) {
        this.repository = repository;
        this.messageProducer = messageProducer;
    }

    // This method is triggered automatically when a message arrives in this queue
    @RabbitListener(queues = "ip-assign-responses")
    public void receiveIpAssignResponse(IpAssignResponse response) {
        if (response.isSuccess() && response.getIp() != null) {
            Optional<Instance> optionalInstance = repository.findByName(response.getServerName());

            if (optionalInstance.isPresent()) {
                Instance instance = optionalInstance.get();
                // Assign the IP received from net-service
                instance.setIp(response.getIp());
                // Change status from PENDING to RUNNING
                instance.setStatus("RUNNING");

                // Save the changes in the database
                repository.save(instance);
            }
        }
    }

    @RabbitListener(queues = "ip-unassign-responses")
    public void receiveIpUnassignResponse(IpUnassignResponse response) {
        if (response.isSuccess()) {
            Optional<Instance> optionalInstance = repository.findByName(response.getServerName());

            if (optionalInstance.isPresent()) {
                Instance instance = optionalInstance.get();
                if ("RESTARTING".equals(instance.getStatus())) {
                    messageProducer.requestIpAssignment(instance.getName(), instance.getMask());
                }
            }
        }

    }
}
