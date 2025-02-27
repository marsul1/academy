package com.ctw.workstation.team.service;

import com.ctw.workstation.team.entity.Team;
import com.ctw.workstation.team.entity.TeamDtoIn;
import com.ctw.workstation.team.entity.TeamDtoOut;
import com.ctw.workstation.team.repository.TeamRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TeamService {
    @Inject
    TeamRepository teamRepository;

    @Transactional
    public TeamDtoOut createTeam (TeamDtoIn teamIn) {
        Team team = new Team();
        team.name = teamIn.name();
        team.product = teamIn.product();
        team.defaultLocation = teamIn.defaultLocation();
        team.createdAt = LocalDate.now();

        teamRepository.persistAndFlush(team);
        return new TeamDtoOut(team.id, team.name, team.product, team.defaultLocation, team.createdAt, team.modifiedAt);
    }

    @Transactional
    public List<TeamDtoOut> findAllTeams (){
        return new ArrayList<>(teamRepository.listAll().stream().toList()).stream()
                .map(team -> new TeamDtoOut(team.id, team.name, team.product, team.defaultLocation, team.createdAt, team.modifiedAt)).toList();
    }

    @Transactional
    public TeamDtoOut findTeamById (long id) {
        Team team = teamRepository.findById(id);
        if (team == null) {
            throw new IllegalArgumentException("No team found with id " + id);
        }
        return new TeamDtoOut(team.id, team.name, team.product, team.defaultLocation, team.createdAt, team.modifiedAt);
    }

    @Transactional
    public TeamDtoOut updateTeam (long id, TeamDtoIn teamIn) {
        Team team = teamRepository.findById(id);
        if (team == null) {
            throw new IllegalArgumentException("No team found with id " + id);
        }
        team.name = teamIn.name();
        team.product = teamIn.product();
        team.defaultLocation = teamIn.defaultLocation();
        team.modifiedAt = LocalDate.now();
        teamRepository.persistAndFlush(team);

        return new TeamDtoOut(id, team.name, team.product, team.defaultLocation, team.createdAt, team.modifiedAt);
    }

    @Transactional
    public boolean deleteById (long id) {
        return teamRepository.deleteById(id);
    }
}
