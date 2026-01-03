# Understanding RPC Programs: What Can Be Stripped?

## Question
**"In RPC programs, what can we strip out but the program still works?"**

## Quick Answer

In JAX-WS RPC programs, you can strip out many **optional** annotations and attributes while the program continues to function correctly:

### ✅ Can Be Stripped (Optional)
1. **`targetNamespace`** - Auto-generated from package name
2. **`serviceName`** - Defaults to ClassName + "Service"
3. **`endpointInterface`** - Not needed without separate interface
4. **`@WebMethod`** - All public methods are auto-exposed
5. **`@SOAPBinding`** - Defaults to DOCUMENT/LITERAL style
6. **Separate interface file** - Can define methods directly in implementation

### ❌ Cannot Be Stripped (Required)
1. **`@WebService`** - Marks class as web service
2. **`Endpoint.publish()`** - Publishes the service
3. **Implementation code** - The actual business logic

## Code Examples

### Full Version (with everything)
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

### Stripped Version (minimal)
```java
// No interface needed!
@WebService
public class HelloWorldImpl {
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
```

**Result**: ~60% less code, same functionality!

## What's Included in This Repository

### 1. Working Examples
📁 **`/RPC_Stripped_Comparison`** - Complete comparison folder with:
- Full version (with all optional elements)
- Stripped version (minimal code)
- Detailed comparison documentation
- Side-by-side analysis

### 2. Updated Original Programs
The original RPC programs now include comments explaining what can be stripped:
- 📁 `/05_RPC_DateService` - Date/Time RPC service (with comments)
- 📁 `/06_RPC_HelloWorld` - Hello World RPC service (with comments)

## Documentation Files

| File | Description |
|------|-------------|
| `RPC_STRIPPING_GUIDE.md` | This overview (you are here) |
| `RPC_Stripped_Comparison/README.md` | Detailed explanation of strippable elements |
| `RPC_Stripped_Comparison/COMPARISON.md` | Side-by-side code comparison |

## Key Learnings

### 1. JAX-WS Has Smart Defaults
Most annotations have sensible default values, so you don't need to specify everything explicitly.

### 2. Interface is Optional
For simple services, you can skip the separate interface and define methods directly in the implementation class.

### 3. Trade-offs
- **Stripped Version**: Cleaner, shorter code; less control over WSDL structure
- **Full Version**: More control, explicit naming; more boilerplate

### 4. When to Use Each

**Use Stripped Version**:
- Learning and prototyping
- Simple internal services
- When default WSDL structure is acceptable

**Use Full Version**:
- Production systems requiring specific WSDL structure
- Interoperability with other systems
- When explicit control is needed

## Testing

Both versions produce fully functional web services. You can verify this by:

1. Running the server (Publisher)
2. Checking the WSDL at `http://localhost:port/ws/service?wsdl`
3. Running the client and verifying output

**Note**: The WSDL will look slightly different (different namespaces, service names), but the functionality is identical.

## Impact on Client Code

The client must adjust to match the WSDL:

### Full Version Client
```java
QName qname = new QName("http://hello/", "HelloWorld");
```

### Stripped Version Client
```java
// IMPORTANT: Check WSDL for actual auto-generated values
// Service name will be ClassName + "Service" = "HelloWorldImplService"
// Namespace is auto-generated based on package (check WSDL for exact value)

// Example (must verify against actual WSDL):
String namespace = "...";  // Check targetNamespace in WSDL
String serviceName = "HelloWorldImplService";
QName qname = new QName(namespace, serviceName);
```

**Tip**: Always check the WSDL first to get the correct namespace and service name!

## Exam/Viva Questions

### Q1: What is the minimum required annotation for a JAX-WS service?
**A**: Only `@WebService` is required on the implementation class.

### Q2: Can we remove @WebMethod annotations?
**A**: Yes, all public methods are automatically exposed as web service operations.

### Q3: What happens if we don't specify targetNamespace?
**A**: JAX-WS auto-generates a namespace based on the package name.

### Q4: Is a separate interface required for RPC?
**A**: No, you can define methods directly in the implementation class.

### Q5: What's the benefit of stripping optional elements?
**A**: Cleaner code, faster development, less maintenance, easier to understand.

### Q6: Does stripping affect performance?
**A**: No, the runtime behavior is identical. Only WSDL structure differs slightly.

## References

- Java EE 6 JAX-WS Documentation
- SOAP RPC vs Document Style
- Web Services Description Language (WSDL) Specification

## Try It Yourself!

1. Navigate to `/RPC_Stripped_Comparison/full_version`
2. Compile and run the full version
3. Navigate to `/RPC_Stripped_Comparison/stripped_version`
4. Compile and run the stripped version
5. Compare the WSDLs and observe they both work!

---

**Conclusion**: Start with the minimal stripped version for simplicity, and add optional elements only when you need specific control over the WSDL structure!

🎯 **Remember**: Optional elements can be stripped, but @WebService, Endpoint.publish(), and implementation code are mandatory!
