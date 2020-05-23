package TEST;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException, Exception {
        ServerSocket sSocket = new ServerSocket(3000);

        Socket accept = sSocket.accept();

        BufferedReader reader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));
        PrintWriter out = new PrintWriter(writer, true);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(accept.getOutputStream());

        User u = new User("asdasd", "1as5d4");
        System.out.println(u);

        objectOutputStream.writeObject(u);

    }
}
