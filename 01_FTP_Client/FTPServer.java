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
