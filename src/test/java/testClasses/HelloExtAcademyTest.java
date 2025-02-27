package testClasses;

import org.apache.commons.lang3.NotImplementedException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class HelloExtAcademyTest {

/*
    @Test
    void hello_from_outer_space_with_spy (){

        //given
        String name = "Marsul";
        HelloExtAcademy helloExtAcademy = new HelloExtAcademy();

        //spy config
        ExternalMessageService externalMessageService = new ExternalMessageServiceImpl();
        ExternalMessageService externalMessageServiceSpy = Mockito.spy(ExternalMessageService.class);
        helloExtAcademy.externalMessageService = externalMessageServiceSpy;

        Mockito.doReturn("Hello World").when(externalMessageServiceSpy).sayHelloFromOuterSpace();

        //when
        String result =helloExtAcademy.sayHello(name);

        //then
        assertThat(result)
                .isEqualTo("Hello world");

        
    }

    @Test
    void hello_from_outer_space_with_mock(){
        //given
        String name = "Marsul";
        HelloExtAcademy helloExtAcademy = new HelloExtAcademy();
       //mock config
        ExternalMessageService externalMessageServiceMock = Mockito.mock(ExternalMessageService.class);
        helloExtAcademy.externalMessageService = externalMessageServiceMock;

        Mockito.when(externalMessageServiceMock.sayHelloFromOuterSpace()).thenReturn(
                .thenThrow(new NotImplementedException("This eature is"))
        );

        String result =helloExtAcademy.sayHello(name);

        assertThat(result)
                .isEqualTo()



    } */

}