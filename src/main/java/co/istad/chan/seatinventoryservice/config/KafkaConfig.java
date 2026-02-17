package co.istad.chan.seatinventoryservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic createTransactionTopic() {
        return new NewTopic("seat-reserved-topic", 3, (short) 1);
    }
}
