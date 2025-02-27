package com.ctw.workstation.booking.boundary;

import com.ctw.workstation.booking.entity.BookingDtoIn;
import com.ctw.workstation.booking.entity.BookingDtoOut;
import com.ctw.workstation.booking.service.BookingService;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.MDC;

import java.util.List;

@Path("/workstation/bookings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookingController {

    //TODO all the methods are uncompleted

    @Inject
    BookingService bookingService;

    @POST
    public Response createBooking(BookingDtoIn bookingDto) {
        try {
            BookingDtoOut res = bookingService.createBooking(bookingDto);
            return Response.status(Response.Status.CREATED).entity(res).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    public Response getAllBookings() {
        MDC.put("bookingId", "1234");

        Log.info("getAllBookings");

        List<BookingDtoOut> res = bookingService.findAllBookings();
        return Response.status(Response.Status.OK).entity(res).build();
    }

    @GET
    @Path("/{id}")
    public Response getBookingById(@PathParam("id") long id) {
        try {
            BookingDtoOut res = bookingService.getBookingById(id);
            return Response.status(Response.Status.OK).entity(res).build();
        }catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateBooking(@PathParam("id") long id, BookingDtoIn bookingDto) {
        try {
            BookingDtoOut res = bookingService.updateBooking(id, bookingDto);
            return Response.status(Response.Status.OK).entity(res).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBooking(@PathParam("id") long id) {
        boolean res = bookingService.deleteBooking(id);
        if (res) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    @GET
    @Path("/team/{teamId}")
    public Response getTeamBookings(@PathParam("teamId") long teamId) {
        List<BookingDtoOut> bookingDtoOuts = bookingService.findAllBookingsForTeam(teamId);
        return Response.status(Response.Status.OK).entity(bookingDtoOuts).build();
    }

    @GET
    @Path("/team/{teamId}/period")
    public Response getPeriodBookings(@PathParam("teamId") long teamId) {
        List<BookingDtoOut> bookingDtoOuts = bookingService.findAllBookingsForTeam(teamId);
        return Response.status(Response.Status.OK).entity(bookingDtoOuts).build();
    }
}
