package co.istad.chan.seatinventoryservice.messaging;

import co.istad.chan.seatinventoryservice.events.SeatReservedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class SeatReserveProducer {

    private final KafkaTemplate<String, SeatReservedEvent> template;

    public void publishSeatReserveEvents(SeatReservedEvent reservedEvent) {
        try {
            log.info("SeatReserveProducer:: Publishing seatReserved event for bookingId {}", reservedEvent.bookingId());
            template.send("seat-reserved-topic",reservedEvent.bookingId(), reservedEvent);
        } catch (Exception e) {
            log.error("SeatReserveProducer:: Error while publishing seatReserved event for bookingId {}: {}", reservedEvent.bookingId(), e.getMessage());
        }
    }
}
