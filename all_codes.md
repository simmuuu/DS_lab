# FTP Client Server

## FTPClient.java
```java
import java.io.*;
import java.net.*;
import java.util.Scanner;

// FTP Client
public class FTPClient {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        try {
            System.out.println("=== FTP Client ===");
            System.out.println("1. Upload File");
            System.out.println("2. Download File");
            System.out.print("Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            
            Socket socket = new Socket("localhost", 5000);
            PrintStream out = new PrintStream(socket.getOutputStream());
            
            System.out.print("Enter filename: ");
            String filename = sc.nextLine();
            
            if (choice == 1) {
                // Upload
                out.println("UPLOAD");
                out.println(filename);
                
                FileInputStream fis = new FileInputStream(filename);
                int data;
                while ((data = fis.read()) != -1) {
                    out.write(data);
                }
                fis.close();
                System.out.println("File uploaded!");
                
            } else if (choice == 2) {
                // Download
                out.println("DOWNLOAD");
                out.println(filename);
                
                FileOutputStream fos = new FileOutputStream("downloaded_" + filename);
                DataInputStream in = new DataInputStream(socket.getInputStream());
                int data;
                while ((data = in.read()) != -1) {
                    fos.write(data);
                }
                fos.close();
                System.out.println("File downloaded!");
            }
            
            socket.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        sc.close();
    }
}
```

## FTPServer.java
```java
import java.io.*;
import java.net.*;

// FTP Server
public class FTPServer {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(5000);
            System.out.println("FTP Server started on port 5000...");
            
            while (true) {
                Socket client = server.accept();
                System.out.println("Client connected!");
                
                DataInputStream in = new DataInputStream(client.getInputStream());
                
                String command = in.readLine(); // UPLOAD or DOWNLOAD
                String filename = in.readLine();
                
                if (command.equals("UPLOAD")) {
                    // Receive file
                    FileOutputStream fos = new FileOutputStream(filename);
                    int data;
                    while ((data = in.read()) != -1) {
                        fos.write(data);
                    }
                    fos.close();
                    System.out.println("File uploaded: " + filename);
                    
                } else if (command.equals("DOWNLOAD")) {
                    // Send file
                    FileInputStream fis = new FileInputStream(filename);
                    PrintStream out = new PrintStream(client.getOutputStream());
                    int data;
                    while ((data = fis.read()) != -1) {
                        out.write(data);
                    }
                    fis.close();
                    System.out.println("File sent: " + filename);
                }
                
                client.close();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

---

# DNS Name Server

## DNS.java
```java
import java.net.*;
import java.util.Scanner;

// DNS - All in main
public class DNS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n=== DNS Lookup ===");
            System.out.println("1. Hostname to IP");
            System.out.println("2. IP to Hostname");
            System.out.println("3. Exit");
            System.out.print("Choice: ");
            
            int choice = sc.nextInt();
            sc.nextLine();
            
            if (choice == 3) break;
            
            try {
                if (choice == 1) {
                    System.out.print("Enter hostname: ");
                    String hostname = sc.nextLine();
                    InetAddress addr = InetAddress.getByName(hostname);
                    System.out.println("IP: " + addr.getHostAddress());
                    
                } else if (choice == 2) {
                    System.out.print("Enter IP: ");
                    String ip = sc.nextLine();
                    InetAddress addr = InetAddress.getByName(ip);
                    System.out.println("Hostname: " + addr.getHostName());
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        sc.close();
    }
}
```

---

# Chat Server

## ChatServer.java
```java
import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(6767);
        System.out.println("Server running...");
        
        Socket s = ss.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintStream out = new PrintStream(s.getOutputStream());
        BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
            System.out.print("Client: " + in.readLine() + "\nYou: ");
            out.println(kb.readLine());
        }
    }
}
```

## ChatClient.java
```java
import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket(host: "localhost", port: 6767);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintStream out = new PrintStream(s.getOutputStream());
        BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
            System.out.print(s: "You: ");
            out.println(kb.readLine());
            System.out.println("Server: " + in.readLine());
        }
    }
}
```

---

# RPC DateService

## DateService.java
```java
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(targetNamespace = "http://date/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface DateService {
    @WebMethod
    String getCurrentDate();
    
    @WebMethod
    String getCurrentTime();
    
    @WebMethod
    String getCurrentDateTime();
}
```

## DateServiceImpl.java
```java
import javax.jws.WebService;
import java.util.Date;
import java.text.SimpleDateFormat;

@WebService(
    serviceName = "DateService",
    endpointInterface = "DateService",
    targetNamespace = "http://date/"
)
public class DateServiceImpl implements DateService {
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

## DateServer.java
```java
import javax.xml.ws.Endpoint;

public class DateServer {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:7779/ws/date", new DateServiceImpl());
        System.out.println("Date Service published...");
    }
}
```

## DateClient.java
```java
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class DateClient {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:7779/ws/date?wsdl");
        QName qname = new QName("http://date/", "DateService");
        Service service = Service.create(url, qname);
        DateService ds = service.getPort(DateService.class);
        
        System.out.println("Current Date: " + ds.getCurrentDate());
        System.out.println("Current Time: " + ds.getCurrentTime());
        System.out.println("Current DateTime: " + ds.getCurrentDateTime());
    }
}
```

---

# RPC HelloWorld

## HelloWorld.java
```java
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(targetNamespace = "http://hello/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface HelloWorld {
    @WebMethod
    String sayHello(String name);
}
```

## HelloWorldImpl.java
```java
import javax.jws.WebService;

@WebService(
    serviceName = "HelloWorld",
    endpointInterface = "HelloWorld",
    targetNamespace = "http://hello/"
)
public class HelloWorldImpl implements HelloWorld {
    public String sayHello(String name) {
        return "Hello " + name + " from RPC Service!";
    }
}
```

## Publisher.java
```java
import javax.xml.ws.Endpoint;

public class Publisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:7779/ws/hello", new HelloWorldImpl());
        System.out.println("Service published...");
    }
}
```

## HelloWorldClient.java
```java
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class HelloWorldClient {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:7779/ws/hello?wsdl");
        QName qname = new QName("http://hello/", "HelloWorld");
        Service service = Service.create(url, qname);
        HelloWorld hw = service.getPort(HelloWorld.class);
        System.out.println(hw.sayHello("Distributed Systems"));
    }
}
```

---

# MapReduce WordCount

## WordCount.java
```java
import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCount {
    
    // Mapper Class
    public static class WordMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
        public void map(LongWritable key, Text value, Context con) throws IOException, InterruptedException {
            String line = value.toString();
            String[] words = line.split(" ");
            for (String word : words) {
                con.write(new Text(word.toUpperCase()), new IntWritable(1));
            }
        }
    }
    
    // Reducer Class
    public static class WordReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
        public void reduce(Text word, Iterable<IntWritable> values, Context con) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get();
            }
            con.write(word, new IntWritable(sum));
        }
    }
    
    // Main Method
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = new Job(conf, "wordcount");
        
        job.setJarByClass(WordCount.class);
        job.setMapperClass(WordMapper.class);
        job.setReducerClass(WordReducer.class);
        
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
```

---

# Three Tier Architecture - Banking

## BankDAO.java
```java
// Data Tier
import java.util.HashMap;

public class BankDAO {
    private HashMap<String, Double> accounts = new HashMap<>();
    
    public void createAccount(String accNo, double initialBalance) {
        accounts.put(accNo, initialBalance);
    }
    
    public double getBalance(String accNo) {
        return accounts.getOrDefault(accNo, -1.0);
    }
    
    public void updateBalance(String accNo, double newBalance) {
        accounts.put(accNo, newBalance);
    }
}
```

## BankService.java
```java
// Application Tier
public class BankService {
    BankDAO dao = new BankDAO();
    
    public void createAccount(String accNo, double initialBalance) {
        dao.createAccount(accNo, initialBalance);
    }
    
    public double checkBalance(String accNo) {
        return dao.getBalance(accNo);
    }
    
    public boolean deposit(String accNo, double amount) {
        double balance = dao.getBalance(accNo);
        if (balance >= 0) {
            dao.updateBalance(accNo, balance + amount);
            return true;
        }
        return false;
    }
    
    public boolean withdraw(String accNo, double amount) {
        double balance = dao.getBalance(accNo);
        if (balance >= amount && balance >= 0) {
            dao.updateBalance(accNo, balance - amount);
            return true;
        }
        return false;
    }
    
    public boolean transfer(String fromAcc, String toAcc, double amount) {
        double fromBalance = dao.getBalance(fromAcc);
        double toBalance = dao.getBalance(toAcc);
        if (fromBalance >= amount && fromBalance >= 0 && toBalance >= 0) {
            dao.updateBalance(fromAcc, fromBalance - amount);
            dao.updateBalance(toAcc, toBalance + amount);
            return true;
        }
        return false;
    }
}
```

## BankingApp.java
```java
// Presentation Tier
import java.util.Scanner;

public class BankingApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankService service = new BankService();
        
        while (true) {
            System.out.println("\n=== Banking System ===");
            System.out.println("1. Create Account");
            System.out.println("2. Check Balance");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Transfer");
            System.out.println("6. Exit");
            System.out.print("Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            
            if (choice == 1) {
                System.out.print("Account Number: ");
                String acc = sc.nextLine();
                System.out.print("Initial Balance: ");
                double bal = sc.nextDouble();
                service.createAccount(acc, bal);
                System.out.println("Account created!");
                
            } else if (choice == 2) {
                System.out.print("Account Number: ");
                String acc = sc.nextLine();
                double bal = service.checkBalance(acc);
                System.out.println(bal >= 0 ? "Balance: " + bal : "Account not found!");
                
            } else if (choice == 3) {
                System.out.print("Account Number: ");
                String acc = sc.nextLine();
                System.out.print("Amount: ");
                double amt = sc.nextDouble();
                boolean success = service.deposit(acc, amt);
                System.out.println(success ? "Deposit successful" : "Account not found!");
                
            } else if (choice == 4) {
                System.out.print("Account Number: ");
                String acc = sc.nextLine();
                System.out.print("Amount: ");
                double amt = sc.nextDouble();
                boolean success = service.withdraw(acc, amt);
                System.out.println(success ? "Withdraw successful" : "Insufficient balance or account not found!");
                
            } else if (choice == 5) {
                System.out.print("From Account: ");
                String from = sc.nextLine();
                System.out.print("To Account: ");
                String to = sc.nextLine();
                System.out.print("Amount: ");
                double amt = sc.nextDouble();
                boolean success = service.transfer(from, to, amt);
                System.out.println(success ? "Transfer successful" : "Transfer failed!");
                
            } else if (choice == 6) {
                System.out.println("Thank you!");
                break;
            }
        }
        sc.close();
    }
}
```

---

# Three Tier Architecture - ECommerce

## ECommerceDAO.java
```java
// Data Tier
import java.util.*;

public class ECommerceDAO {
    private HashMap<Integer, String> products = new HashMap<>();
    private HashMap<Integer, Double> prices = new HashMap<>();
    private HashMap<String, List<Integer>> cart = new HashMap<>();
    
    public void addProduct(int id, String name, double price) {
        products.put(id, name);
        prices.put(id, price);
    }
    
    public void viewProducts() {
        System.out.println("\n--- Products ---");
        for (int id : products.keySet()) {
            System.out.println(id + ". " + products.get(id) + " - $" + prices.get(id));
        }
    }
    
    public void addToCart(String user, int productId) {
        if (!cart.containsKey(user)) {
            cart.put(user, new ArrayList<>());
        }
        cart.get(user).add(productId);
    }
    
    public void viewCart(String user) {
        if (!cart.containsKey(user) || cart.get(user).isEmpty()) {
            System.out.println("Cart is empty!");
            return;
        }
        System.out.println("\n--- Your Cart ---");
        double total = 0;
        for (int id : cart.get(user)) {
            System.out.println(products.get(id) + " - $" + prices.get(id));
            total += prices.get(id);
        }
        System.out.println("Total: $" + total);
    }
    
    public void clearCart(String user) {
        cart.put(user, new ArrayList<>());
    }
}
```

## ECommerceService.java
```java
// Application Tier
public class ECommerceService {
    ECommerceDAO dao = new ECommerceDAO();
    
    public void addProduct(int id, String name, double price) {
        dao.addProduct(id, name, price);
    }
    
    public void viewProducts() {
        dao.viewProducts();
    }
    
    public void addToCart(String user, int productId) {
        dao.addToCart(user, productId);
    }
    
    public void viewCart(String user) {
        dao.viewCart(user);
    }
    
    public void checkout(String user) {
        dao.viewCart(user);
        System.out.println("\nOrder placed successfully!");
        dao.clearCart(user);
    }
}
```

## ECommerceApp.java
```java
// Presentation Tier
import java.util.Scanner;

public class ECommerceApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ECommerceService service = new ECommerceService();
        
        // Add sample products
        service.addProduct(1, "Laptop", 999.0);
        service.addProduct(2, "Mouse", 29.0);
        service.addProduct(3, "Keyboard", 79.0);
        
        String user = null;
        
        while (true) {
            if (user == null) {
                System.out.println("\n=== E-Commerce ===");
                System.out.println("1. Login");
                System.out.println("2. View Products");
                System.out.println("3. Exit");
                System.out.print("Choice: ");
                int choice = sc.nextInt();
                sc.nextLine();
                
                if (choice == 1) {
                    System.out.print("Username: ");
                    user = sc.nextLine();
                    System.out.println("Welcome " + user + "!");
                } else if (choice == 2) {
                    service.viewProducts();
                } else if (choice == 3) {
                    break;
                }
            } else {
                System.out.println("\n=== Welcome " + user + " ===");
                System.out.println("1. View Products");
                System.out.println("2. Add to Cart");
                System.out.println("3. View Cart");
                System.out.println("4. Checkout");
                System.out.println("5. Logout");
                System.out.print("Choice: ");
                int choice = sc.nextInt();
                
                if (choice == 1) {
                    service.viewProducts();
                } else if (choice == 2) {
                    System.out.print("Product ID: ");
                    int id = sc.nextInt();
                    service.addToCart(user, id);
                    System.out.println("Added to cart!");
                } else if (choice == 3) {
                    service.viewCart(user);
                } else if (choice == 4) {
                    service.checkout(user);
                } else if (choice == 5) {
                    user = null;
                    System.out.println("Logged out!");
                }
            }
        }
        sc.close();
    }
}
```

---

# Three Tier Architecture - Ticket Reservation

## TicketDAO.java
```java
// Data Tier
import java.util.HashMap;

public class TicketDAO {
    private HashMap<Integer, String> seats = new HashMap<>();
    
    public TicketDAO() {
        // Initialize 10 seats
        for (int i = 1; i <= 10; i++) {
            seats.put(i, null);
        }
    }
    
    public void viewSeats() {
        System.out.println("\n--- Seat Status ---");
        for (int i = 1; i <= 10; i++) {
            System.out.println("Seat " + i + ": " + (seats.get(i) == null ? "Available" : "Booked"));
        }
    }
    
    public String getSeatOwner(int seatNo) {
        return seats.get(seatNo);
    }
    
    public void bookSeat(int seatNo, String user) {
        seats.put(seatNo, user);
    }
    
    public void cancelSeat(int seatNo) {
        seats.put(seatNo, null);
    }
    
    public void viewUserBookings(String user) {
        System.out.println("\n--- Your Bookings ---");
        boolean found = false;
        for (int seat : seats.keySet()) {
            if (user.equals(seats.get(seat))) {
                System.out.println("Seat " + seat);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No bookings found!");
        }
    }
}
```

## TicketService.java
```java
// Application Tier
public class TicketService {
    TicketDAO dao = new TicketDAO();
    
    public void viewSeats() {
        dao.viewSeats();
    }
    
    public boolean bookTicket(String user, int seatNo) {
        if (seatNo >= 1 && seatNo <= 10 && dao.getSeatOwner(seatNo) == null) {
            dao.bookSeat(seatNo, user);
            return true;
        }
        return false;
    }
    
    public boolean cancelTicket(String user, int seatNo) {
        if (user.equals(dao.getSeatOwner(seatNo))) {
            dao.cancelSeat(seatNo);
            return true;
        }
        return false;
    }
    
    public void myBookings(String user) {
        dao.viewUserBookings(user);
    }
}
```

## TicketReservation.java
```java
// Presentation Tier
import java.util.Scanner;

public class TicketReservation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TicketService service = new TicketService();
        String user = null;
        
        while (true) {
            if (user == null) {
                System.out.println("\n=== Ticket Reservation ===");
                System.out.println("1. Login");
                System.out.println("2. View Available Seats");
                System.out.println("3. Exit");
                System.out.print("Choice: ");
                int choice = sc.nextInt();
                sc.nextLine();
                
                if (choice == 1) {
                    System.out.print("Username: ");
                    user = sc.nextLine();
                    System.out.println("Welcome " + user + "!");
                } else if (choice == 2) {
                    service.viewSeats();
                } else if (choice == 3) {
                    break;
                }
            } else {
                System.out.println("\n=== Welcome " + user + " ===");
                System.out.println("1. View Seats");
                System.out.println("2. Book Ticket");
                System.out.println("3. Cancel Ticket");
                System.out.println("4. My Bookings");
                System.out.println("5. Logout");
                System.out.print("Choice: ");
                int choice = sc.nextInt();
                sc.nextLine();
                
                if (choice == 1) {
                    service.viewSeats();
                } else if (choice == 2) {
                    System.out.print("Seat Number (1-10): ");
                    int seat = sc.nextInt();
                    boolean success = service.bookTicket(user, seat);
                    System.out.println(success ? "Booking successful!" : "Seat not available!");
                } else if (choice == 3) {
                    System.out.print("Seat Number: ");
                    int seat = sc.nextInt();
                    boolean success = service.cancelTicket(user, seat);
                    System.out.println(success ? "Ticket cancelled!" : "Invalid seat or not your booking!");
                } else if (choice == 4) {
                    service.myBookings(user);
                } else if (choice == 5) {
                    user = null;
                    System.out.println("Logged out!");
                }
            }
        }
        sc.close();
    }
}
```

---

# Three Tier Architecture - Student Management

## StudentDAO.java
```java
// Data Tier
import java.util.HashMap;

public class StudentDAO {
    private HashMap<Integer, Student> students = new HashMap<>();
    
    static class Student {
        int id;
        String name;
        double marks;
        
        Student(int id, String name, double marks) {
            this.id = id;
            this.name = name;
            this.marks = marks;
        }
    }
    
    public void addStudent(int id, String name, double marks) {
        students.put(id, new Student(id, name, marks));
    }
    
    public String getStudent(int id) {
        if (students.containsKey(id)) {
            Student s = students.get(id);
            return "ID: " + s.id + ", Name: " + s.name + ", Marks: " + s.marks + ", Grade: " + getGrade(s.marks);
        }
        return null;
    }
    
    public boolean updateMarks(int id, double marks) {
        if (students.containsKey(id)) {
            students.get(id).marks = marks;
            return true;
        }
        return false;
    }
    
    public boolean deleteStudent(int id) {
        return students.remove(id) != null;
    }
    
    public void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found!");
        } else {
            System.out.println("\n--- All Students ---");
            for (Student s : students.values()) {
                System.out.println("ID: " + s.id + ", Name: " + s.name + ", Marks: " + s.marks + ", Grade: " + getGrade(s.marks));
            }
        }
    }
    
    private String getGrade(double marks) {
        if (marks >= 90) return "A";
        if (marks >= 80) return "B";
        if (marks >= 70) return "C";
        if (marks >= 60) return "D";
        return "F";
    }
}
```

## StudentService.java
```java
// Application Tier
public class StudentService {
    StudentDAO dao = new StudentDAO();
    
    public void addStudent(int id, String name, double marks) {
        dao.addStudent(id, name, marks);
    }
    
    public String viewStudent(int id) {
        return dao.getStudent(id);
    }
    
    public boolean updateMarks(int id, double marks) {
        return dao.updateMarks(id, marks);
    }
    
    public boolean deleteStudent(int id) {
        return dao.deleteStudent(id);
    }
    
    public void viewAllStudents() {
        dao.viewAllStudents();
    }
}
```

## StudentManagement.java
```java
// Presentation Tier
import java.util.Scanner;

public class StudentManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentService service = new StudentService();
        
        while (true) {
            System.out.println("\n=== Student Management ===");
            System.out.println("1. Add Student");
            System.out.println("2. View Student");
            System.out.println("3. Update Marks");
            System.out.println("4. Delete Student");
            System.out.println("5. View All Students");
            System.out.println("6. Exit");
            System.out.print("Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            
            if (choice == 1) {
                System.out.print("Student ID: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("Name: ");
                String name = sc.nextLine();
                System.out.print("Marks: ");
                double marks = sc.nextDouble();
                service.addStudent(id, name, marks);
                System.out.println("Student added!");
                
            } else if (choice == 2) {
                System.out.print("Student ID: ");
                int id = sc.nextInt();
                String info = service.viewStudent(id);
                System.out.println(info != null ? info : "Student not found!");
                
            } else if (choice == 3) {
                System.out.print("Student ID: ");
                int id = sc.nextInt();
                System.out.print("New Marks: ");
                double marks = sc.nextDouble();
                boolean success = service.updateMarks(id, marks);
                System.out.println(success ? "Marks updated!" : "Student not found!");
                
            } else if (choice == 4) {
                System.out.print("Student ID: ");
                int id = sc.nextInt();
                boolean success = service.deleteStudent(id);
                System.out.println(success ? "Student deleted!" : "Student not found!");
                
            } else if (choice == 5) {
                service.viewAllStudents();
                
            } else if (choice == 6) {
                System.out.println("Goodbye!");
                break;
            }
        }
        sc.close();
    }
}
```
