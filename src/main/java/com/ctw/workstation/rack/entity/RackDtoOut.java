package com.ctw.workstation.rack.entity;

import java.time.LocalDate;

public record RackDtoOut (long id, String serialNumber, RackStatus status, long teamId, String defaultLocation, LocalDate createdAt, LocalDate modifiedAt) { }
