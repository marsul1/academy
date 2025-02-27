package com.ctw.workstation.booking.service;

import com.ctw.workstation.booking.entity.Booking;
import com.ctw.workstation.booking.entity.BookingDtoIn;
import com.ctw.workstation.booking.entity.BookingDtoOut;
import com.ctw.workstation.booking.repository.BookingRepository;
import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.teammember.entity.TeamMember;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class BookingService {

    @Inject
    BookingRepository bookingRepository;

    @Transactional
    public List<BookingDtoOut> findAllBookings() {
        Log.info("Find all bookings");
        return new ArrayList<>(bookingRepository.listAll()).stream()
                .map(booking ->
                        new BookingDtoOut(
                                booking.id, booking.rack.id, booking.teamMember.id, booking.bookFrom, booking.bookTo, booking.createdAt, booking.modifiedAt
                        )).toList();
    }

    // Book a rack for a team
    @Transactional
    public BookingDtoOut createBooking(BookingDtoIn bookingDtoIn) {
        Rack rack = Rack.findById(bookingDtoIn.rackId());
        if (rack == null) {
            throw new IllegalArgumentException("Rack with id " + bookingDtoIn.rackId() + " not found");
        }
        TeamMember teamMember = TeamMember.findById(bookingDtoIn.requesterId());
        if (teamMember == null) {
            throw new IllegalArgumentException("TeamMember with id " + bookingDtoIn.requesterId() + " not found");
        }

        Booking booking = new Booking();
        booking.rack = rack;
        booking.teamMember = teamMember;
        booking.bookFrom = bookingDtoIn.bookFrom();
        booking.bookTo = bookingDtoIn.bookTo();
        booking.createdAt = LocalDate.now();
        bookingRepository.persistAndFlush(booking);

        return new BookingDtoOut(
                booking.id,
                booking.rack.id,
                booking.teamMember.id,
                booking.bookFrom,
                booking.bookTo,
                booking.createdAt,
                booking.modifiedAt
        );
    }

    public boolean deleteBooking(long id) {
        return bookingRepository.deleteById(id);
    }

    public BookingDtoOut getBookingById(long id) {
        Booking booking = bookingRepository.findById(id);
        if (booking == null) {
            throw new IllegalArgumentException("Booking with id " + id + " not found");
        }
        return new BookingDtoOut(
                booking.id,
                booking.rack.id,
                booking.teamMember.id,
                booking.bookFrom,
                booking.bookTo,
                booking.createdAt,
                booking.modifiedAt
        );
    }


    public BookingDtoOut updateBooking(long id, BookingDtoIn bookingDtoIn) {
        Booking booking = bookingRepository.findById(id);
        if (booking == null) {
            throw new IllegalArgumentException("Booking with id " + id + " not found");
        }
        Rack rack = Rack.findById(bookingDtoIn.rackId());
        if (rack == null) {
            throw new IllegalArgumentException("Rack with  id " + bookingDtoIn.rackId() + " not found");
        }
        TeamMember teamMember = TeamMember.findById(bookingDtoIn.requesterId());
        if (teamMember == null) {
            throw new IllegalArgumentException("TeamMember with id " + bookingDtoIn.requesterId() + " not found");
        }
        booking.rack = rack;
        booking.teamMember = teamMember;
        booking.bookFrom = bookingDtoIn.bookFrom();
        booking.bookTo = bookingDtoIn.bookTo();
        booking.modifiedAt = LocalDate.now();
        bookingRepository.persistAndFlush(booking);
        return new BookingDtoOut(
                booking.id,
                booking.rack.id,
                booking.teamMember.id,
                booking.bookFrom,
                booking.bookTo,
                booking.createdAt,
                booking.modifiedAt
        );
    }

    public List<BookingDtoOut> findAllBookingsForTeam(long teamId) {
        List<Long> racks = Rack.findByTeamId(teamId).stream().map(rack -> rack.id).toList();
        List<Booking> bookings = bookingRepository.findByRacks(racks);
        return bookings.stream().map(booking ->
                new BookingDtoOut(
                        booking.id, booking.rack.id, booking.teamMember.id, booking.bookFrom, booking.bookTo, booking.createdAt, booking.modifiedAt
                )).toList();
    }
/*
    public List<BookingDtoOut> findBookingForTeamByPeriod(long teamId, BookingDtoIn bookingDtoIn) {
        // parameter should come in the body
        List<Long> racks = Rack.findByTeamId(teamId).stream().map(rack -> rack.id).toList();
        List<Booking> bookings = bookingRepository.
        List<Booking> bookings = bookingRepository.findBookingByPeriod(teamId, bookingDtoIn.bookFrom(), bookingDtoIn.bookTo());
        return bookings.stream().map(booking ->
                new BookingDtoOut(
                        booking.id, booking.rack.id, booking.teamMember.id, booking.bookFrom, booking.bookTo, booking.createdAt, booking.modifiedAt
                )).toList();
    } */
}
