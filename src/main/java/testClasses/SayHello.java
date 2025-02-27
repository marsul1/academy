package testClasses;

public class SayHello {
    public String sayHello(String name) {
        if (name != null) {
            return "Hello " + name;
        } else {
            return "Hello" ;
        }
    }
}
