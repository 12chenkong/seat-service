package co.istad.chan.seatinventoryservice.service;

import co.istad.chan.seatinventoryservice.domain.SeatInventory;
import co.istad.chan.seatinventoryservice.domain.SeatStatus;
import co.istad.chan.seatinventoryservice.events.BookingCreatedEvent;
import co.istad.chan.seatinventoryservice.events.SeatReservedEvent;
import co.istad.chan.seatinventoryservice.messaging.SeatReserveProducer;
import co.istad.chan.seatinventoryservice.repository.SeatInventoryRepository;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SeatInventoryService {

    private final SeatInventoryRepository seatInventoryRepository;
    private final SeatReserveProducer seatReserveProducer;

    public void handleBooking(BookingCreatedEvent event){

//        fetch all seats for the show and seat numbers in the booking event
        List<SeatInventory>seats = seatInventoryRepository.findByShowIdAndSeatNumberIn(event.showId(), event.seatIds());

//        checking if all  seats available or not
        boolean allAvailable=seats.stream()
                .allMatch(s->s.getStatus()== SeatStatus.AVAILABLE);

        log.info("allAvailable:{}",allAvailable);

        if(allAvailable){
//            Update seat status to LOCKED and set the current booking ID
            seats.forEach(s->{
                s.setStatus(SeatStatus.LOCKED);
                s.setCurrentBookingId(event.bookingId());
            });

            seatInventoryRepository.saveAll(seats);
//             will push saved event to payment service via Kafka


        }else {
            log.warn("SeatInventoryService:: Seat locking fail for bookingID{}. some seats are not available",event.bookingId());
            seatReserveProducer.publishSeatReserveEvents(new SeatReservedEvent(
                    event.bookingId(),
                    false,
                    event.amount()
            ));
        }

    }

}
