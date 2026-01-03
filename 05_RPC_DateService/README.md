# RPC Date Service

## Overview
Remote Procedure Call (RPC) service that provides date and time functions. Uses JAX-WS (Java API for XML Web Services) with SOAP protocol.

**NEW**: See `/RPC_Stripped_Comparison` folder for examples of what can be stripped from RPC programs while still working!

## Files
- **DateService.java** - Service Interface with @WebService annotation
- **DateServiceImpl.java** - Implementation of the service
- **DateServer.java** - Publishes the service
- **DateClient.java** - Consumes the service

## Code Explanation

### DateService.java (Interface)
```java
@WebService(targetNamespace = "http://date/")  → Marks as web service with namespace
@SOAPBinding(style = Style.RPC)                 → Uses RPC style binding
@WebMethod                                      → Marks method as remotely callable
```

### DateServiceImpl.java (Implementation)
```java
@WebService(serviceName = "DateService",        → Service name
            endpointInterface = "DateService",  → Links to interface
            targetNamespace = "http://date/")   → SOAP namespace
SimpleDateFormat                                → Formats date/time
```

### DateServer.java (Publisher)
```java
Endpoint.publish(url, service)    → Publishes service at URL
```

### DateClient.java (Consumer)
```java
URL                              → WSDL location
QName("http://date/", "DateService")  → Service qualified name (use DateService, not DateServiceImplService)
Service.create()                 → Creates service instance
service.getPort()                → Gets service proxy
```

## How to Run
```bash
# Compile all files
javac *.java

# Terminal 1 - Start Server
java DateServer

# Terminal 2 - Run Client
java DateClient
```

---

## What Can Be Stripped from RPC Programs?

Many annotations and attributes in RPC programs are **optional** and can be removed while the program still works:

### Optional Elements (Can Strip):
1. **targetNamespace** - Auto-generated from package name
2. **serviceName** - Defaults to ClassName+"Service"  
3. **endpointInterface** - Not needed if you don't use separate interface
4. **@WebMethod** - All public methods exposed by default
5. **@SOAPBinding** - Defaults to DOCUMENT/LITERAL style
6. **Interface file** - Can define methods directly in implementation

### Required Elements (Cannot Strip):
1. **@WebService** - Required to mark class as web service
2. **Endpoint.publish()** - Required to publish the service
3. **Implementation logic** - The actual business code

### See Working Examples:
Check the `/RPC_Stripped_Comparison` folder for complete side-by-side examples of:
- Full version (with all optional elements)
- Stripped version (minimal code that still works)

**Key Takeaway**: You can reduce RPC code by ~40-60% by stripping optional elements!

---

## Viva Questions

### Basic Questions
1. **What is RPC?**
   - Remote Procedure Call - allows a program to execute a procedure on a remote server as if it were local.

2. **What is SOAP?**
   - Simple Object Access Protocol - XML-based messaging protocol for web services.

3. **What is WSDL?**
   - Web Services Description Language - XML document describing the web service interface.

4. **What is JAX-WS?**
   - Java API for XML Web Services - Java standard for creating SOAP web services.

5. **What does @WebService annotation do?**
   - Marks a class/interface as a web service endpoint.

### Intermediate Questions
6. **What is the difference between RPC and Document style?**
   - RPC: Method parameters wrapped, operation-centric
   - Document: Raw XML, message-centric, more flexible

7. **What is endpoint in web services?**
   - URL where the service is available for clients to access.

8. **What is QName?**
   - Qualified Name - consists of namespace URI and local part to uniquely identify XML elements.

9. **What is targetNamespace?**
   - Unique identifier for the web service, used in WSDL and SOAP messages.

10. **How does client find service methods?**
    - By reading WSDL file which describes all available operations.

### Advanced Questions
11. **What is the difference between SOAP and REST?**
    - SOAP: XML-based, strict standards, WS-Security
    - REST: Lightweight, JSON/XML, HTTP methods, stateless

12. **What is marshalling and unmarshalling?**
    - Marshalling: Converting Java objects to XML
    - Unmarshalling: Converting XML to Java objects

13. **What is stub and skeleton in RPC?**
    - Stub: Client-side proxy that marshals parameters
    - Skeleton: Server-side component that unmarshals and invokes method

14. **What transport protocols does SOAP use?**
    - Primarily HTTP/HTTPS, but can use SMTP, TCP, JMS.

15. **What is WS-Security?**
    - Standard for securing SOAP messages with encryption and digital signatures.

16. **How is RPC different from local procedure call?**
    - RPC involves network communication, serialization, potential failures, and latency.
