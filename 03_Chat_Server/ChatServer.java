import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] a) throws Exception {
        ServerSocket ss = new ServerSocket(6767);
        Socket s = ss.accept();

        BufferedReader br =
                new BufferedReader(new InputStreamReader(s.getInputStream()));

        String msg;
        while ((msg = br.readLine()) != null) {
            System.out.println("Client: " + msg);
        }

        br.close();
        s.close();
        ss.close();
    }
}
