package co.istad.chan.seatinventoryservice.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.ToString;

import java.util.List;

public record BookingCreatedEvent(
        String bookingId,
        String userId,
        String showId,
        List<String> seatIds,
        Long amount
) {}