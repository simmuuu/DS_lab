# Chat Server Program

## Overview
Simple client-server chat application using Java Socket programming. Server and client can exchange messages in real-time.

## Files
- **ChatServer.java** - Server that accepts client connections
- **ChatClient.java** - Client that connects and chats

## Code Explanation

### ChatServer.java
```java
ServerSocket(5000)           → Server listens on port 5000
ss.accept()                  → Accepts client connection
BufferedReader               → Reads messages from client
PrintStream                  → Sends messages to client
BufferedReader(System.in)    → Reads keyboard input
```

### ChatClient.java
```java
Socket("localhost", 5000)    → Connects to server
BufferedReader               → Reads server messages
PrintStream                  → Sends messages to server
BufferedReader(System.in)    → Reads keyboard input
```

### Message Flow
```
Client types → PrintStream → Network → BufferedReader → Server displays
Server types → PrintStream → Network → BufferedReader → Client displays
```

## How to Run
```bash
# Terminal 1 - Start Server
javac ChatServer.java
java ChatServer

# Terminal 2 - Start Client
javac ChatClient.java
java ChatClient
```

---

## Viva Questions

### Basic Questions
1. **What is client-server architecture?**
   - A model where client requests services and server provides them. Server waits for connections, client initiates.

2. **What is the role of ServerSocket?**
   - Listens for incoming client connections on a specified port.

3. **What does accept() do?**
   - Blocks until a client connects, then returns a Socket for communication.

4. **What is BufferedReader used for?**
   - Reads text from input stream efficiently with buffering.

5. **What is PrintStream?**
   - Output stream that prints formatted data.

### Intermediate Questions
6. **How is two-way communication achieved?**
   - Using both InputStream (reading) and OutputStream (writing) on the same socket.

7. **Why use BufferedReader over DataInputStream for chat?**
   - BufferedReader is better for text/line-based communication with readLine() method.

8. **What happens when client disconnects?**
   - Server's readLine() returns null or throws exception.

9. **How to handle multiple clients?**
   - Create a new thread for each client connection.

10. **What is the difference between print() and println()?**
    - println() adds newline character, important for readLine() to work.

### Advanced Questions
11. **What is blocking I/O?**
    - Operations that wait (block) until data is available. readLine() blocks until line is received.

12. **How would you implement group chat?**
    - Server maintains list of all client sockets, broadcasts message to all clients.

13. **What is the difference between TCP and UDP for chat?**
    - TCP: Reliable, ordered, connection-based (better for chat)
    - UDP: Fast, connectionless (used for real-time gaming)

14. **What is socket timeout?**
    - Maximum time to wait for data; prevents indefinite blocking.

15. **How to make chat secure?**
    - Use SSL/TLS sockets (SSLSocket), encrypt messages.

16. **What is the maximum message size in TCP?**
    - No fixed limit, but practical limit based on buffer sizes (typically 64KB per segment).
