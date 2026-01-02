import java.io.*;
import java.net.*;

public class FTPServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(6767);
        Socket s = ss.accept();

        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

        String op = dis.readUTF();
        String file = dis.readUTF();

        if (op.equalsIgnoreCase("upload")) {
            FileOutputStream fos = new FileOutputStream("server_" + file);
            int ch;
            while ((ch = dis.read()) != -1) {
                fos.write(ch);
                System.out.print((char) ch); // output on server side
            }
            fos.close();
        } else if (op.equalsIgnoreCase("download")) {
            FileInputStream fis = new FileInputStream(file);
            int ch;
            while ((ch = fis.read()) != -1) {
                dos.write(ch);
            }
            fis.close();
        }

        s.close();
        ss.close();
    }
}
