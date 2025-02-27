package com.ctw.workstation.booking.repository;

import com.ctw.workstation.booking.entity.Booking;
import com.ctw.workstation.rack.entity.Rack;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class BookingRepository implements PanacheRepository<Booking> {


    public List<Booking> findByRacks(List<Long>racks) {
        return list("where rack.id in ?", racks);
    }
    /*
    public List<Booking> findBookingByPeriod (long teamId, LocalDate bookFrom, LocalDate bookTo) {
        return list("where team_id = 1? and book_to <= 2?", teamId, bookTo);
    } */
}
