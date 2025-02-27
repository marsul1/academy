package com.ctw.workstation.rack.boundary;

import com.ctw.workstation.rack.entity.RackDtoIn;
import com.ctw.workstation.rack.entity.RackDtoOut;
import com.ctw.workstation.rack.entity.RackStatus;
import com.ctw.workstation.rack.service.RackService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/workstation/racks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RackController {

    @Inject
    RackService rackService;

    @POST
    public Response addRack(RackDtoIn rackDtoIn) {
        RackDtoOut res = rackService.addRack(rackDtoIn);
        return Response.status(Response.Status.CREATED).entity(res).build();
    }

    @GET
    @Path("/{id}")
    public Response getRack(@PathParam("id") long id) {
        try {
            RackDtoOut res = rackService.findRackById(id);
            return Response.status(Response.Status.OK).entity(res).build();
        }catch(IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    public List<RackDtoOut> getAllRacks(@QueryParam("status") String status) {
        List<RackDtoOut> res = rackService.findAllRacksByStatus(status);
        return res;
    }

    @PUT
    @Path("/{id}")
    public Response updateRack(@PathParam("id") long id, RackDtoIn rackDtoIn) {
        try {
            RackDtoOut res = rackService.updateRack(id, rackDtoIn);
            return Response.status(Response.Status.OK).entity(res).build();
        }catch(IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteRack(@PathParam("id") long id) {
        boolean res = rackService.deleteRack(id);
        if (res) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
