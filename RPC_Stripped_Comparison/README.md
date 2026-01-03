# RPC Programs - What Can Be Stripped Out?

## Overview
This document demonstrates optional elements in JAX-WS RPC programs that can be removed while the program still functions correctly.

---

## Elements That Can Be Stripped

### 1. **targetNamespace** Attribute (Optional)
- **Default Behavior**: If not specified, JAX-WS generates a namespace from the package name
- **Example**:
  ```java
  // Full version
  @WebService(targetNamespace = "http://hello/")
  
  // Stripped version (still works)
  @WebService
  ```

### 2. **serviceName** Attribute (Optional)
- **Default Behavior**: Defaults to implementation class name + "Service" 
- **Example**:
  ```java
  // Full version
  @WebService(serviceName = "HelloWorld", endpointInterface = "HelloWorld")
  
  // Stripped version (still works)
  @WebService(endpointInterface = "HelloWorld")
  ```

### 3. **@WebMethod** Annotation (Optional)
- **Default Behavior**: All public methods in @WebService are automatically exposed
- **Example**:
  ```java
  // Full version
  @WebMethod
  String sayHello(String name);
  
  // Stripped version (still works)
  String sayHello(String name);
  ```

### 4. **@SOAPBinding** Annotation (Optional)
- **Default Behavior**: Defaults to DOCUMENT/LITERAL style
- **Note**: RPC style is not mandatory; DOCUMENT style is the default and works fine
- **Example**:
  ```java
  // Full version
  @WebService
  @SOAPBinding(style = SOAPBinding.Style.RPC)
  
  // Stripped version (still works, uses DOCUMENT style)
  @WebService
  ```

### 5. **endpointInterface** Attribute (Optional for Simple Cases)
- **Default Behavior**: Can be inferred when implementation directly contains the service methods
- **Example**:
  ```java
  // Full version
  @WebService(endpointInterface = "com.example.HelloWorld")
  public class HelloWorldImpl implements HelloWorld
  
  // Stripped version (works without interface)
  @WebService
  public class HelloWorldImpl {
      public String sayHello(String name) { ... }
  }
  ```

---

## Comparison Examples

### Example 1: HelloWorld Service

#### Full Version (With All Optional Elements)
```java
// Interface
@WebService(targetNamespace = "http://hello/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface HelloWorld {
    @WebMethod
    String sayHello(String name);
}

// Implementation
@WebService(serviceName = "HelloWorld",
            endpointInterface = "HelloWorld",
            targetNamespace = "http://hello/")
public class HelloWorldImpl implements HelloWorld {
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
```

#### Minimal/Stripped Version (Still Works!)
```java
// No separate interface needed
@WebService
public class HelloWorldImpl {
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
```

### Example 2: DateService

#### Full Version
```java
// Interface
@WebService(targetNamespace = "http://date/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface DateService {
    @WebMethod String getCurrentDate();
    @WebMethod String getCurrentTime();
    @WebMethod String getCurrentDateTime();
}

// Implementation
@WebService(serviceName = "DateService",
            endpointInterface = "DateService",
            targetNamespace = "http://date/")
public class DateServiceImpl implements DateService {
    public String getCurrentDate() { ... }
    public String getCurrentTime() { ... }
    public String getCurrentDateTime() { ... }
}
```

#### Minimal/Stripped Version
```java
// Single class, no interface
@WebService
public class DateServiceImpl {
    public String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }
    
    public String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }
    
    public String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
}
```

---

## What CANNOT Be Stripped

### Mandatory Elements

1. **@WebService** annotation - Required to mark the class as a web service
2. **Endpoint.publish()** call in server - Required to publish the service
3. **Service.create()** in client - Required to create service proxy
4. **Public methods** - Only public methods can be exposed as web service operations
5. **Implementation logic** - The actual business logic in methods

---

## Benefits of Stripping

1. **Simpler Code**: Less boilerplate, easier to read
2. **Faster Development**: Quick prototyping without worrying about all attributes
3. **Less Maintenance**: Fewer things to update when refactoring
4. **Still Functional**: Program works identically with defaults

---

## When to Use Full Version vs Stripped Version

### Use Full Version When:
- You need specific namespace control for interoperability
- You want explicit service naming for clarity
- You need to hide certain public methods (use @WebMethod selectively)
- Working with legacy systems requiring specific WSDL structure
- Multiple services need distinct namespaces

### Use Stripped Version When:
- Building simple services for learning/prototyping
- Internal services where WSDL structure doesn't matter
- Default behavior meets all requirements
- You prefer minimal, clean code

---

## Testing Both Versions

Both versions produce working web services. The WSDL will look slightly different (different namespaces, service names) but the functionality is identical.

**Full Version WSDL**: 
- Custom namespace: `http://hello/`
- Service name: `HelloWorld`

**Stripped Version WSDL**:
- Auto-generated namespace: Based on package
- Service name: `HelloWorldImplService` (class name + "Service")

**Client adjustment needed**: Update QName in client to match the actual service name from WSDL.

---

## Conclusion

JAX-WS provides sensible defaults that allow you to strip out many optional annotations and attributes. For educational purposes and simple services, the minimal version is often sufficient and makes the code more readable. As requirements grow more complex, you can always add back the optional elements for finer control.

**Key Takeaway**: Start simple with just `@WebService`, and add complexity only when needed!
