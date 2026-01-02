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
