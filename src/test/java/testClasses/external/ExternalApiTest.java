package testClasses.external;
/*
import com.ctw.workstation.config.CtwAcademyTestProfile;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestProfile(CtwAcademyTestProfile.class)
class ExternalApiTest {

    @Inject
    HelloExtAcademy helloExtAcademy;

    @Test
    void test_hello() {

        //given
        String name = "Rennan";

        stubFor(post(urlEqualTo("/external/hello"))
                .willReturn(
                        aResponse().withHeader("Content-Type", "application/json")
                                .withBody(
                                        """ {
                                                "message": "Hello world!"
                                                }
                                                """
                                )
                ));

        //when
        String res = helloExtAcademy.sayHello(name);

        //then
        assertThat(res)
                .isEqualTo("Hello Rennan");
    }

}*/