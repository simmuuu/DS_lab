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
