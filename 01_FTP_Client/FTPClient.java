import java.io.*;
import java.net.*;

public class FTPClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 6767);

        BufferedReader kb = new BufferedReader(
                new InputStreamReader(System.in));
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

        System.out.print("Upload/Download: ");
        String op = kb.readLine();
        System.out.print("Filename: ");
        String file = kb.readLine();

        dos.writeUTF(op);
        dos.writeUTF(file);
        dos.flush();

        if (op.equalsIgnoreCase("upload")) {
            FileInputStream fis = new FileInputStream(file);
            int ch;
            while ((ch = fis.read()) != -1) {
                dos.write(ch);
            }
            fis.close();
        } else if (op.equalsIgnoreCase("download")) {
            FileOutputStream fos = new FileOutputStream("downloaded_" + file);
            int ch;
            while ((ch = dis.read()) != -1) {
                fos.write(ch);
                System.out.print((char) ch); // to see output on client
            }
            fos.close();
        }

        s.close();
    }
}
