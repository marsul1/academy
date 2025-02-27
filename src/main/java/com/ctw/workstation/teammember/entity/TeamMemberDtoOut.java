package com.ctw.workstation.teammember.entity;

import java.time.LocalDate;

public record TeamMemberDtoOut(long id, long teamId, String name, String ctwId, LocalDate createdAt, LocalDate modifiedAt) {
}
