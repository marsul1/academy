package com.ctw.workstation.teammember.boundary;

import com.ctw.workstation.teammember.entity.TeamMemberDtoIn;
import com.ctw.workstation.teammember.entity.TeamMemberDtoOut;
import com.ctw.workstation.teammember.service.TeamMemberService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/workstation/team-member")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TeamMemberController {

    @Inject
    TeamMemberService teamMemberService;

    @POST
    public Response createTeamMember(TeamMemberDtoIn teamMemberDtoIn) {
        try {
            TeamMemberDtoOut teamMemberDtoOut = teamMemberService.createTeamMember(teamMemberDtoIn);
            return Response.status(Response.Status.CREATED).entity(teamMemberDtoOut).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    public Response getTeamMembers() {
        List<TeamMemberDtoOut> teamMemberDtoOuts = teamMemberService.getTeamMembers();
        return Response.status(Response.Status.OK).entity(teamMemberDtoOuts).build();
    }

    @GET
    @Path("/{id}")
    public Response getTeamMember(@PathParam("id") long id) {
        try {
            TeamMemberDtoOut teamMemberDtoOut = teamMemberService.getTeamMemberById(id);
            return Response.status(Response.Status.OK).entity(teamMemberDtoOut).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateTeamMember(@PathParam("id") long id, TeamMemberDtoIn teamMemberDtoIn) {
        try {
            TeamMemberDtoOut teamMemberDtoOut = teamMemberService.updateTeamMember(id, teamMemberDtoIn);
            return Response.status(Response.Status.OK).entity(teamMemberDtoOut).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
