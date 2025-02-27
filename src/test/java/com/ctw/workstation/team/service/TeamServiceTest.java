package com.ctw.workstation.team.service;

import com.ctw.workstation.team.entity.Team;
import com.ctw.workstation.team.entity.TeamDtoIn;
import com.ctw.workstation.team.entity.TeamDtoOut;
import com.ctw.workstation.team.repository.TeamRepository;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
class TeamServiceTest {

    @Inject
    TeamService teamService;

    @Inject
    TeamRepository teamRepository;

    @Test
    void create_team() {
        //GIVEN
        TeamDtoIn teamDtoIn =  new TeamDtoIn("Test", "Test", "Test");
        //WHEN
        TeamDtoOut teamDtoOut = teamService.createTeam(teamDtoIn);
        //THEN
        assertThat(teamDtoOut)
                .usingRecursiveComparison()
                .ignoringFields("id","modifiedAt", "createdAt")
                .isEqualTo(teamDtoIn);

        assertThat(teamRepository.findById(teamDtoOut.id())).isNotNull();
        assertThat(teamRepository.findByIdOptional(teamDtoOut.id())).isPresent();
    }

    @Test
    void create_team_mock() {
        //GIVEN
        TeamRepository teamRepository = Mockito.mock(TeamRepository.class);
        QuarkusMock.installMockForType(teamRepository, TeamRepository.class);

        Mockito.doThrow(new IllegalArgumentException("nao pode meu"))
                        .when(teamRepository)
                        .persist(Mockito.any(Team.class));
        //WHEN
        TeamDtoIn teamDtoIn =  new TeamDtoIn("Test", "Test", "Test");
        TeamDtoOut teamDtoOut = teamService.createTeam(teamDtoIn);
        //THEN
        assertThat(teamDtoOut)
                .usingRecursiveComparison()
                .ignoringFields("id","modifiedAt", "createdAt")
                .as("DTO returned with the expected data")
                .isEqualTo(teamDtoIn);

        assertThat(teamRepository.findById(teamDtoOut.id())).isNotNull();
        assertThat(teamRepository.findByIdOptional(teamDtoOut.id())).isPresent();
    }
}