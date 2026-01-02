# Distributed Systems Lab - Complete Programs

A comprehensive collection of Java programs for Distributed Systems lab covering socket programming, RPC, MapReduce, and 3-tier architecture.

---

## 📁 Program Structure

| # | Folder | Topic | Programs |
|---|--------|-------|----------|
| 1 | 01_FTP_Client | File Transfer Protocol | FTPServer.java, FTPClient.java |
| 2 | 02_DNS_Name_Server | Domain Name Server | DNS.java |
| 3 | 03_Chat_Server | Client-Server Chat | ChatServer.java, ChatClient.java |
| 4 | 05_RPC_DateService | RPC Date Service | DateService.java, DateServiceImpl.java, DateServer.java, DateClient.java |
| 5 | 06_RPC_HelloWorld | RPC Hello World | HelloWorld.java, HelloWorldImpl.java, Publisher.java, HelloWorldClient.java |
| 6 | 07_MapReduce_WordCount | Hadoop MapReduce | WordCount.java |
| 7 | 08_Three_Tier_Architecture | 3-Tier Apps | Banking, E-Commerce, Ticket Reservation, Student Management |

---

## 1️⃣ FTP Client-Server (01_FTP_Client)

**Purpose:** File transfer between client and server using sockets.

**Files:**
- `FTPServer.java` - Listens on port 5000, handles upload/download
- `FTPClient.java` - Connects to server, sends/receives files

**Key Concepts:**
- Socket, ServerSocket
- FileInputStream, FileOutputStream
- DataInputStream, PrintStream

**Run:**
```bash
java FTPServer    # Terminal 1
java FTPClient    # Terminal 2
```

---

## 2️⃣ DNS Name Server (02_DNS_Name_Server)

**Purpose:** Hostname to IP and IP to hostname resolution.

**Files:**
- `DNS.java` - Complete DNS lookup program

**Key Concepts:**
- InetAddress.getByName()
- getHostAddress(), getHostName()
- Forward and Reverse lookup

**Run:**
```bash
java DNS
```

---

## 3️⃣ Chat Server (03_Chat_Server)

**Purpose:** Real-time messaging between client and server.

**Files:**
- `ChatServer.java` - Accepts connections, exchanges messages
- `ChatClient.java` - Connects and chats with server

**Key Concepts:**
- Socket programming
- BufferedReader, PrintStream
- Two-way communication

**Run:**
```bash
java ChatServer    # Terminal 1
java ChatClient    # Terminal 2
```

---

## 4️⃣ RPC Date Service (05_RPC_DateService)

**Purpose:** Remote Procedure Call service providing date/time functions.

**Files:**
- `DateService.java` - Service interface (@WebService)
- `DateServiceImpl.java` - Implementation
- `DateServer.java` - Publishes service
- `DateClient.java` - Consumes service

**Key Concepts:**
- JAX-WS, SOAP, WSDL
- @WebService, @WebMethod, @SOAPBinding
- Endpoint.publish(), Service.create()

**Run:**
```bash
java DateServer    # Terminal 1
java DateClient    # Terminal 2
```

---

## 5️⃣ RPC Hello World (06_RPC_HelloWorld)

**Purpose:** Basic RPC service demonstrating web service concepts.

**Files:**
- `HelloWorld.java` - Service interface
- `HelloWorldImpl.java` - Implementation
- `Publisher.java` - Publishes service
- `HelloWorldClient.java` - Client consumer

**Key Concepts:**
- SOAP RPC style
- QName, targetNamespace
- Remote method invocation

**Run:**
```bash
java Publisher         # Terminal 1
java HelloWorldClient  # Terminal 2
```

---

## 6️⃣ MapReduce Word Count (07_MapReduce_WordCount)

**Purpose:** Distributed word counting using Hadoop MapReduce.

**Files:**
- `WordCount.java` - Mapper, Reducer, and Job configuration
- `input.txt` - Sample input file

**Key Concepts:**
- Mapper<LongWritable, Text, Text, IntWritable>
- Reducer<Text, IntWritable, Text, IntWritable>
- Map → Shuffle → Reduce phases

**Run:**
```bash
javac -classpath $(hadoop classpath) WordCount.java
jar cf wordcount.jar WordCount*.class
hadoop jar wordcount.jar WordCount /input /output
```

---

## 7️⃣ Three-Tier Architecture (08_Three_Tier_Architecture)

### 7.1 Banking Application (01_Banking)

**Purpose:** Online banking with deposit, withdraw, transfer.

**Files:**
- `BankingApp.java` - Presentation Tier (UI)
- `BankService.java` - Application Tier (Business Logic)
- `BankDAO.java` - Data Tier (Database)

**Features:** Create Account, Check Balance, Deposit, Withdraw, Transfer

---

### 7.2 E-Commerce (02_ECommerce)

**Purpose:** Online shopping with cart and checkout.

**Files:**
- `ECommerceApp.java` - Presentation Tier
- `ECommerceService.java` - Application Tier
- `ECommerceDAO.java` - Data Tier

**Features:** Login, View Products, Add to Cart, Checkout

---

### 7.3 Ticket Reservation (03_TicketReservation)

**Purpose:** Seat booking system for events/travel.

**Files:**
- `TicketReservation.java` - Presentation Tier
- `TicketService.java` - Application Tier
- `TicketDAO.java` - Data Tier

**Features:** View Seats, Book Ticket, Cancel Ticket, My Bookings

---

### 7.4 Student Management (04_StudentManagement)

**Purpose:** Student records with CRUD and grading.

**Files:**
- `StudentManagement.java` - Presentation Tier
- `StudentService.java` - Application Tier
- `StudentDAO.java` - Data Tier

**Features:** Add Student, View, Update Marks, Delete, Grade Calculation

---

## 🎯 Quick Reference

### Socket Programming
```java
ServerSocket ss = new ServerSocket(port);
Socket s = ss.accept();
Socket s = new Socket("localhost", port);
```

### DNS
```java
InetAddress addr = InetAddress.getByName(host);
String ip = addr.getHostAddress();
```

### RPC
```java
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
Endpoint.publish(url, new ServiceImpl());
Service.create(url, qname).getPort(Interface.class);
```

### MapReduce
```java
class Mapper<LongWritable, Text, Text, IntWritable>
class Reducer<Text, IntWritable, Text, IntWritable>
context.write(key, value);
```

### 3-Tier
```
Presentation (UI) → Service (Logic) → DAO (Data)
```

---

## 📚 Lab Manual Reference

| Item | Program | Folder |
|------|---------|--------|
| i-ii | FTP Client/Server | 01_FTP_Client |
| iii-iv | DNS Name Server | 02_DNS_Name_Server |
| v | RPC Date Service | 05_RPC_DateService |
| vi | RPC Hello World | 06_RPC_HelloWorld |
| vii | Chat Server | 03_Chat_Server |
| viii | MapReduce Word Count | 07_MapReduce_WordCount |
| xi | 3-Tier Banking | 08_Three_Tier_Architecture/01_Banking |
| xii | 3-Tier E-Commerce | 08_Three_Tier_Architecture/02_ECommerce |
| xiii | 3-Tier Ticket Reservation | 08_Three_Tier_Architecture/03_TicketReservation |
| xiv | 3-Tier Student Management | 08_Three_Tier_Architecture/04_StudentManagement |

---

## 🚀 How to Compile & Run

### Single File Programs
```bash
javac ProgramName.java
java ProgramName
```

### Client-Server Programs
```bash
# Terminal 1 - Server
java ServerName

# Terminal 2 - Client
java ClientName
```

### Hadoop MapReduce
```bash
javac -classpath $(hadoop classpath) WordCount.java
jar cf wc.jar WordCount*.class
hadoop jar wc.jar WordCount /input /output
```

---

**All Programs Ready for Exam! 🎉**
