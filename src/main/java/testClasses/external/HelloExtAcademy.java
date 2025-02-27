package testClasses.external;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class HelloExtAcademy {

    @Inject
    @RestClient
    ExternalApi externalApi;

    ExternalMessageService externalMessageService;
    public String sayHello(String name) {
        if (name != null) {
            return externalApi.hello(new ExternalRequest(name)).message();
        }
        return  externalApi.hello().message();
    }
}
