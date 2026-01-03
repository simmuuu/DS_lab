# RPC Hello World Service

## Overview
Basic "Hello World" Remote Procedure Call (RPC) service demonstrating JAX-WS web service implementation with SOAP protocol.

**NEW**: Learn what can be stripped from RPC programs! See `/RPC_Stripped_Comparison` folder for working examples.

## Files
- **HelloWorld.java** - Service Interface
- **HelloWorldImpl.java** - Service Implementation
- **Publisher.java** - Publishes the service
- **HelloWorldClient.java** - Consumes the service

## Code Explanation

### HelloWorld.java (Interface)
```java
@WebService(targetNamespace = "http://hello/")  → Declares web service with namespace
@SOAPBinding(style = Style.RPC)                  → RPC-style SOAP binding
@WebMethod                                       → Exposes method as web operation
String sayHello(String name)                     → Remote method signature
```

### HelloWorldImpl.java (Implementation)
```java
@WebService(serviceName = "HelloWorld",        → Service name
            endpointInterface = "HelloWorld",  → Implements interface
            targetNamespace = "http://hello/") → Service namespace
```

### Publisher.java
```java
Endpoint.publish("http://localhost:7779/ws/hello", impl)
// Publishes service at specified URL
```

### HelloWorldClient.java
```java
URL url = new URL("...?wsdl")                        → WSDL location
QName qname = new QName("http://hello/", "HelloWorld")  → Service identifier (use HelloWorld, not HelloWorldImplService)
Service service = Service.create(...)                 → Create service
HelloWorld hw = service.getPort(...)                  → Get proxy
hw.sayHello("name")                                   → Call remote method
```

## How to Run
```bash
# Compile
javac *.java

# Terminal 1 - Start Server
java Publisher

# Terminal 2 - Run Client
java HelloWorldClient
```

**Output:** `Hello Distributed Systems from RPC Service!`

---

## What Can Be Stripped from RPC Programs?

This example demonstrates the **full version** with all optional elements. Many parts can be removed while the program still works!

### Elements That CAN Be Stripped:
✅ `targetNamespace` attribute  
✅ `serviceName` attribute  
✅ `endpointInterface` attribute  
✅ `@WebMethod` annotation  
✅ `@SOAPBinding` annotation  
✅ Separate interface file (HelloWorld.java)  

### Elements That CANNOT Be Stripped:
❌ `@WebService` annotation (required)  
❌ `Endpoint.publish()` call (required)  
❌ Implementation code (required)  

### Minimal Working Version:
```java
@WebService
public class HelloWorldImpl {
    public String sayHello(String name) {
        return "Hello " + name + " from RPC Service!";
    }
}
```

**Code Reduction**: ~40% less code by stripping optional elements!

See `/RPC_Stripped_Comparison` folder for complete working examples comparing full vs stripped versions.

---

## Viva Questions

### Basic Questions
1. **What is a Web Service?**
   - A software system designed to support interoperable machine-to-machine interaction over a network.

2. **What is @WebMethod annotation?**
   - Marks a method to be exposed as a web service operation.

3. **What does Endpoint.publish() do?**
   - Creates and publishes a web service endpoint at the specified URL.

4. **What is the WSDL URL for this service?**
   - http://localhost:7779/ws/hello?wsdl

5. **What is Service.create() used for?**
   - Creates a Service instance from WSDL to invoke web service operations.

### Intermediate Questions
6. **Explain the RPC invocation flow.**
   - Client calls method → Stub serializes → HTTP request → Server receives → Skeleton deserializes → Method executes → Response sent back

7. **What is getPort() method?**
   - Returns a proxy object that implements the service interface for making remote calls.

8. **Why is targetNamespace important?**
   - Uniquely identifies the service and avoids naming conflicts in XML.

9. **What happens if server is not running?**
   - Client throws ConnectException or ServiceException.

10. **What is the default HTTP method for SOAP?**
    - POST (carries XML payload in body)

### Advanced Questions
11. **How does SOAP differ from JSON-RPC?**
    - SOAP: XML-based, heavyweight, WS-* standards
    - JSON-RPC: JSON-based, lightweight, simpler

12. **What is SOAPAction header?**
    - HTTP header indicating the intent of the SOAP request.

13. **Can RPC be asynchronous?**
    - Yes, using callbacks or polling mechanisms.

14. **What is service contract?**
    - WSDL defines the contract - operations, messages, data types.

15. **What is interoperability in web services?**
    - Ability for different platforms/languages to communicate using standard protocols.

16. **What is the role of XML Schema in WSDL?**
    - Defines data types used in service messages.
