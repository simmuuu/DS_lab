# FTP Client-Server Program

## Overview
File Transfer Protocol (FTP) implementation using Java Socket programming for uploading and downloading files between client and server.

## Files
- **FTPServer.java** - Server that listens for client connections
- **FTPClient.java** - Client that connects to server for file transfer

## Code Explanation

### FTPServer.java
```
ServerSocket(5000)     → Creates server on port 5000
ss.accept()            → Waits for client connection
DataInputStream        → Reads commands from client
FileOutputStream       → Writes received file (upload)
FileInputStream        → Reads file to send (download)
```

### FTPClient.java
```
Socket("localhost", 5000) → Connects to server
PrintStream              → Sends commands to server
FileInputStream          → Reads file to upload
FileOutputStream         → Writes downloaded file
```

## How It Works
1. Server starts and waits on port 5000
2. Client connects and sends command (UPLOAD/DOWNLOAD)
3. Client sends filename
4. File is transferred byte by byte

## How to Run
```bash
# Terminal 1 - Start Server
javac FTPServer.java
java FTPServer

# Terminal 2 - Start Client
javac FTPClient.java
java FTPClient
```

---

## Viva Questions

### Basic Questions
1. **What is FTP?**
   - File Transfer Protocol - used to transfer files between client and server over a network.

2. **What is a Socket?**
   - A socket is an endpoint for communication between two machines. It combines IP address and port number.

3. **What is the difference between ServerSocket and Socket?**
   - ServerSocket: Listens for incoming connections (server-side)
   - Socket: Used for actual communication (both sides)

4. **What port is used in this program?**
   - Port 5000

5. **What is the purpose of accept() method?**
   - Blocks and waits for a client to connect, returns a Socket for communication.

### Intermediate Questions
6. **Explain the flow of file upload.**
   - Client sends "UPLOAD" command → sends filename → reads file bytes → sends to server → server writes bytes to file

7. **What are DataInputStream and PrintStream used for?**
   - DataInputStream: Read primitive data types and strings
   - PrintStream: Write formatted output

8. **Why do we use try-catch in socket programming?**
   - Network operations can fail (connection refused, timeout, etc.), so we handle IOException.

9. **What happens if server is not running?**
   - Client throws ConnectionRefused exception.

10. **How is binary data transferred?**
    - Using read() and write() methods that transfer byte by byte.

### Advanced Questions
11. **What is the difference between TCP and UDP?**
    - TCP: Connection-oriented, reliable, ordered delivery
    - UDP: Connectionless, faster, no guarantee of delivery

12. **How would you handle multiple clients?**
    - Use multithreading - create a new thread for each client connection.

13. **What is port number range?**
    - 0-65535; 0-1023 are well-known ports, 1024-49151 are registered, 49152-65535 are dynamic.

14. **What is localhost?**
    - 127.0.0.1 - refers to the current machine itself.

15. **How to make file transfer more efficient?**
    - Use BufferedInputStream/BufferedOutputStream for buffered I/O.
