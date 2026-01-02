import javax.jws.WebService;

@WebService(
    endpointInterface = "HelloWorld",
    targetNamespace = "http://hello/"
)
public class HelloWorldImpl implements HelloWorld {
    public String sayHello(String name) {
        return "Hello " + name + " from RPC Service!";
    }
}
