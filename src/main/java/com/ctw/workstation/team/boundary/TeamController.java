package com.ctw.workstation.team.boundary;

import com.ctw.workstation.team.entity.TeamDtoIn;
import com.ctw.workstation.team.entity.TeamDtoOut;
import com.ctw.workstation.team.service.TeamService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/workstation/teams")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TeamController {
    @Inject
     TeamService teamService;

    @POST
    public Response createTeam(TeamDtoIn teamDtoIn) {
        try {
            TeamDtoOut res = teamService.createTeam(teamDtoIn);
            return Response.status(Response.Status.CREATED).entity(res).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    public Response getTeams() {
        List<TeamDtoOut> res = teamService.findAllTeams();
        return Response.status(Response.Status.OK).entity(res).build();
    }

    @GET
    @Path("/{id}")
    public Response getTeam(@PathParam("id") long id) {
        try {
            TeamDtoOut res = teamService.findTeamById(id);
            return Response.status(Response.Status.OK).entity(res).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateTeam(@PathParam("id") long id, TeamDtoIn teamDtoIn) {
        try {
            TeamDtoOut res = teamService.updateTeam(id, teamDtoIn);
            return Response.status(Response.Status.OK).entity(res).build();
        }catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTeam(@PathParam("id") long id) {
        boolean res = teamService.deleteById(id);
        if (res) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
