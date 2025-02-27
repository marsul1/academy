package com.ctw.workstation.booking.entity;

import java.time.LocalDate;
import java.util.UUID;

public record BookingDtoIn (long rackId, long requesterId, LocalDate bookFrom, LocalDate bookTo) {
}
