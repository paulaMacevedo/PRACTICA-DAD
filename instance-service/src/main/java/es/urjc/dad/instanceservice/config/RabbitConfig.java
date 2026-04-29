package es.urjc.dad.instanceservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    // This bean ensures that messages are serialized to JSON
    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue ipAssignResponsesQueue() {
        return new Queue("ip-assign-responses", true);
    }

    @Bean
    public Queue ipUnassignResponsesQueue() {
        return new Queue("ip-unassign-responses", true);
    }
}
