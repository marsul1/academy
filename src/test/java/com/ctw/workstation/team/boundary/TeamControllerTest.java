//package com.ctw.workstation.team.boundary;
//
//import com.ctw.workstation.config.CtwAcademyTestProfile;
//import com.ctw.workstation.team.entity.TeamDtoIn;
//import com.ctw.workstation.team.entity.TeamDtoOut;
//import io.quarkus.logging.Log;
//import io.quarkus.test.junit.QuarkusTest;
//import io.quarkus.test.junit.TestProfile;
//import io.restassured.common.mapper.TypeRef;
//import io.restassured.http.ContentType;
//import org.apache.http.HttpStatus;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//
//import java.util.List;
//
//import static io.restassured.RestAssured.given;
//import static org.junit.jupiter.api.Assertions.assertAll;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@QuarkusTest
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@TestProfile(CtwAcademyTestProfile.class)
//class TeamControllerTest {
//
//    //I know that id is 1 because its a serial number.
//    TeamDtoIn team = new TeamDtoIn("Marsul", "Carro", "Lisboa");
//    int teamId = 1;
//
//
//
//    @Test
//    @Order(1)
//    void create_team_and_get() {
//        //Create team
//        TeamDtoOut response = given()
//                .header("Content-Type", "application/json")
//                .and()
//                .body(team)
//                .when()
//                .post("workstation/teams")
//                .then()
//                .extract()
//                .as(TeamDtoOut.class);
//
//        assertAll(
//                () -> assertEquals(team.name(),response.name()),
//                () -> assertEquals(team.defaultLocation(),response.defaultLocation()),
//                () -> assertEquals(team.product(),response.product())
//        );
//        //Get team
//    }
//
//
//    @Test
//    @Order(2)
//    void get_team() {
//        TeamDtoOut response = given()
//                .contentType(ContentType.JSON)
//                .when()
//                .pathParams("id", teamId)
//                .get("workstation/teams/{id}")
//                .then()
//                .statusCode(200)
//                .extract()
//                .as(TeamDtoOut.class);
//        Log.info(response);
//    }
//
//    @Test
//    @Order(3)
//    @DisplayName("Fetching all teams")
//    void fetching_all_teams() {
//       List<TeamDtoOut> teamDtoOuts =  given()
//                .contentType(ContentType.JSON)
//                .when()
//                .get("/workstation/teams")
//                .then()
//                .statusCode(200)
//                .extract()
//                .as(new TypeRef<List<TeamDtoOut>>() {});
//    }
//
//    @Test
//    @Order(4)
//    void update_team() {
//        TeamDtoIn teamUpdate = new TeamDtoIn("Marsul", "Fabrica", "Lisboa");
//        TeamDtoOut result = given()
//                .when()
//                .pathParams("id", teamId)
//                .contentType(ContentType.JSON)
//                .body(teamUpdate)
//                .put("/workstation/teams/{id}")
//                .then()
//                .statusCode(HttpStatus.SC_OK)
//                .extract()
//                .as(TeamDtoOut.class);
//
//        assertAll(
//                () -> assertEquals(teamUpdate.product(), result.product())
//        );
//    }
//
//    @Test
//    @Order(5)
//    void delete_team() {
//        given()
//                .when()
//                .pathParams("id", teamId)
//                .delete("workstation/teams/{id}" )
//                .then()
//                .statusCode(HttpStatus.SC_OK);
//    }
//
//
//}