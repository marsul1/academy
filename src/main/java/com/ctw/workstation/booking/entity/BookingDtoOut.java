package com.ctw.workstation.booking.entity;

import java.time.LocalDate;
import java.util.UUID;

public record BookingDtoOut(long id, long rackId, long requesterId, LocalDate bookFrom, LocalDate bookTo,
                            LocalDate createdAt, LocalDate modifiedAt) {
}
