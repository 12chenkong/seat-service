package co.istad.chan.seatinventoryservice.service;

import co.istad.chan.seatinventoryservice.messaging.SeatReserveProducer;
import co.istad.chan.seatinventoryservice.repository.SeatInventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SeatInventoryService {

    private final SeatInventoryRepository seatInventoryRepository;
    private final SeatReserveProducer seatReserveProducer;

    public void handleBooking(){

    }

}
