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
