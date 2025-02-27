package testClasses.external;

public class ExternalMessageServiceImpl implements ExternalMessageService {

    @Override
    public String sayHelloFromOuterSpace(String name) {
        return "Hello from outer space " + name;
    }

    @Override
    public String sayHelloFromOuterSpace() {
        return "Hello from outer space";
    }

    @Override
    public void doSomething() {

    }
}
