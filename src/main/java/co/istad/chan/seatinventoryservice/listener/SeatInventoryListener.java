package co.istad.chan.seatinventoryservice.listener;

import co.istad.chan.seatinventoryservice.events.BookingCreatedEvent;
import co.istad.chan.seatinventoryservice.service.SeatInventoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.KafkaListeners;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeatInventoryListener {

    private final SeatInventoryService seatInventoryService;

    @KafkaListener(topics = "movie-booking-events", groupId = "seat-service-group")
    public void consumeBookingEvent(BookingCreatedEvent bookingCreatedEvent) {

        seatInventoryService.handleBooking(bookingCreatedEvent);

    }

}
