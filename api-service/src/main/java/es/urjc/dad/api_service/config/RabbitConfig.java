package es.urjc.dad.api_service.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// this entity is going to send messages between applications without talking directly
@Configuration
public class RabbitConfig {
    public static final String NOMBRE_BASE= "net-creation-request";

    @Bean
    public Queue queue(){
        return new Queue(NOMBRE_BASE,false);
    }
}
