package com.ctw.workstation.teammember.service;


import com.ctw.workstation.team.entity.Team;
import com.ctw.workstation.teammember.entity.TeamMember;
import com.ctw.workstation.teammember.entity.TeamMemberDtoIn;
import com.ctw.workstation.teammember.entity.TeamMemberDtoOut;
import com.ctw.workstation.teammember.repository.TeamMemberRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TeamMemberService {

    @Inject
    TeamMemberRepository teamMemberRepository;

    @Transactional
    public TeamMemberDtoOut createTeamMember(TeamMemberDtoIn teamMemberDtoIn) {
        Team team = Team.findById(teamMemberDtoIn.teamId());
        if (team == null) {
            throw new IllegalArgumentException("Team with id " + teamMemberDtoIn.teamId() + " not found");
        }

        if (teamMemberRepository.findByCtwId(teamMemberDtoIn.ctwId()) != null) {
            throw new IllegalArgumentException("Team with id " + teamMemberDtoIn.teamId() + " already exists");
        }
        TeamMember teamMember = new TeamMember();
        teamMember.name = teamMemberDtoIn.name();
        teamMember.ctwId = teamMemberDtoIn.ctwId();
        teamMember.team = team;
        teamMember.createdAt = LocalDate.now();
        teamMemberRepository.persistAndFlush(teamMember);

        return new TeamMemberDtoOut(
                teamMember.id,
                teamMember.team.id,
                teamMember.name,
                teamMember.ctwId,
                teamMember.createdAt,
                teamMember.modifiedAt
        );
    }
    @Transactional
    public TeamMemberDtoOut getTeamMemberById(long teamMemberId) {
        TeamMember teamMember = teamMemberRepository.findById(teamMemberId);
        return new TeamMemberDtoOut(
                teamMember.id,
                teamMember.team.id,
                teamMember.name,
                teamMember.ctwId,
                teamMember.createdAt,
                teamMember.modifiedAt
        );
    }
    @Transactional
    public boolean deleteTeamMember(long teamMemberId) {
        return teamMemberRepository.deleteById(teamMemberId);
    }

    @Transactional
    public TeamMemberDtoOut updateTeamMember(long id, TeamMemberDtoIn teamMemberDtoIn) {
        TeamMember teamMember = teamMemberRepository.findById(id);
        if (teamMember == null) {
            throw new IllegalArgumentException("Team member with id " + id + " not found");
        }

        Team team = Team.findById(teamMemberDtoIn.teamId());

        if ( team == null) {
            throw new IllegalArgumentException("Team with id " + teamMemberDtoIn.teamId() + " not found");
        }
        teamMember.name = teamMemberDtoIn.name();
        teamMember.ctwId = teamMemberDtoIn.ctwId();
        teamMember.modifiedAt = LocalDate.now();
        teamMember.team = team;
        teamMemberRepository.persistAndFlush(teamMember);
        return new TeamMemberDtoOut(
                teamMember.id,
                teamMember.team.id,
                teamMember.name,
                teamMember.ctwId,
                teamMember.createdAt,
                teamMember.modifiedAt
        );
    }

    @Transactional
    public List<TeamMemberDtoOut> getTeamMembers() {
        List<TeamMember> teamMembers = new ArrayList<>(teamMemberRepository.listAll());

        return teamMembers.stream().map(teamMember ->
                new TeamMemberDtoOut(
                        teamMember.id,
                        teamMember.team.id,
                        teamMember.name,
                        teamMember.ctwId,
                        teamMember.createdAt,
                        teamMember.modifiedAt
                )
        ).toList();
    }
}
