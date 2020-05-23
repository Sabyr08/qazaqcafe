package QazaqCafe;

import QazaqCafe.classes.Admin;
import QazaqCafe.classes.Waiter;
import QazaqCafe.configs.DatabaseHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Server {
    public static void main(String[] args) throws IOException{
        ServerSocket server = new ServerSocket(8000);

        Socket socket = server.accept();

        DatabaseHandler dbHandler = new DatabaseHandler();
        Admin admin = new Admin();
        Waiter waiter = new Waiter();

        BufferedReader bufferedReader =
                new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()
                        )
                );

        BufferedWriter bufferedWriter =
                new BufferedWriter(
                        new OutputStreamWriter(
                                socket.getOutputStream()
                        )
                );

        PrintWriter printWriter = new PrintWriter(bufferedWriter, true);

        while (true) {
            String login = bufferedReader.readLine();
            String password = bufferedReader.readLine();

            admin.setLogin(login);
            admin.setPassword(password);

            waiter.setLogin(login);
            waiter.setPassword(password);

            ResultSet resultAd = dbHandler.getAdmin(admin);
            ResultSet result = dbHandler.getWaiter(waiter);

            int counterWaiter = 0;
            while (true) {
                try {
                    if (!result.next()) break;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                counterWaiter++;
            }

            int counterAd = 0;
            while (true) {
                try {
                    if (!resultAd.next()) break;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                counterAd++;
            }

            if (counterAd >= 1)
                printWriter.println("admin");
            else if (counterWaiter >= 1)
                printWriter.println("waiter");
            else
                printWriter.println("error");

            if (bufferedReader.readLine().equals("connected")) {
                socket.close();
                break;
            }
        }

        Socket socket1 = server.accept();

        BufferedReader bufferedReader1 =
                new BufferedReader(
                        new InputStreamReader(
                                socket1.getInputStream()
                        )
                );

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket1.getOutputStream());
        PrintWriter printWriter1 = new PrintWriter(bufferedWriter, true);

        String con = bufferedReader1.readLine();

        if (con.equals("waiter")) {
            try {
                ResultSet result = dbHandler.getWaiter(waiter);

                int id = 0;
                String name = "";
                String sname = "";
                int age = 0;
                String login = "";
                String position = "";
                String img = "";

                while (result.next()) {
                    id = result.getInt(1);
                    name = result.getString(2);
                    sname = result.getString(3);
                    age = result.getInt(4);
                    login = result.getString(5);
                    position = result.getString(7);
                    img = result.getString(8);
                }

                Waiter waiter1 = new Waiter(id, name, sname, age, login, position, img);

                objectOutputStream.writeObject(waiter1);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (con.equals("admin")) {
            try {
                ResultSet result1 = dbHandler.getAdmin(admin);

                String login1 = "";

                while (result1.next()) {
                    login1 = result1.getString(2);
                }

                Admin admin1 = new Admin(login1);

                objectOutputStream.writeObject(admin1);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else System.out.println("error");
    }
}
