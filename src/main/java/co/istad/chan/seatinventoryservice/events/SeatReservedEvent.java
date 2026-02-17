package co.istad.chan.seatinventoryservice.events;


public record SeatReservedEvent(String bookingId, boolean reserved, long amount) {}
