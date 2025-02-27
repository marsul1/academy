package com.ctw.workstation.team.entity;

import java.time.LocalDate;

public record TeamDtoOut(long id, String name, String product, String defaultLocation, LocalDate createdAt, LocalDate modifiedAt) {
}