package com.ctw.workstation.rack.entity;

public record RackDtoIn( String serialNumber, RackStatus status, int teamId, String defaultLocation ) {
}
