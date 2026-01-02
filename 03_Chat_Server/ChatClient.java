import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] a) throws Exception {
        Socket s = new Socket("localhost", 6767);

        PrintStream out = new PrintStream(s.getOutputStream());
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        String msg;
        while (!(msg = br.readLine()).equals("exit")) {
            out.println(msg);
        }

        out.close();
        s.close();
    }
}
