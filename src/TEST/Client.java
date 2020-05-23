package TEST;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", 3000);

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        PrintWriter out = new PrintWriter(writer, true);

        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

        User u = (User)objectInputStream.readObject();

        System.out.println(u.getName());
        System.out.println(u.getSname());
    }
}
