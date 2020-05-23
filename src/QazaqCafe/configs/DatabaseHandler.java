package QazaqCafe.configs;

import QazaqCafe.classes.Admin;
import QazaqCafe.classes.Seat;
import QazaqCafe.classes.Waiter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DatabaseHandler extends Configs{
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    public void signUpWaiter(Waiter waiter) {
        String insert = "INSERT INTO " + Const.WAITER_TABLE + "(" + Const.WAITER_NAME + "," + Const.WAITER_SURNAME + ","
                + Const.WAITER_AGE + "," + Const.WAITER_LOGIN + "," + Const.WAITER_PASSWORD + "," + Const.WAITER_POSITION  + ")"
                + "VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, waiter.getName());
            prSt.setString(2, waiter.getSurname());
            prSt.setInt(3, waiter.getAge());
            prSt.setString(4, waiter.getLogin());
            prSt.setString(5, waiter.getPassword());
            prSt.setString(6, waiter.getPosition());

            prSt.executeLargeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void signUpAdmin(Admin admin) {
        String insert = "INSERT INTO " + Const.ADMIN_TABLE + "(" + Const.ADMIN_LOGIN + "," + Const.ADMIN_PASSWORD + ")"
                + "VALUES(?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, admin.getLogin());
            prSt.setString(2, admin.getPassword());

            prSt.executeLargeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getWaiter(Waiter waiter) {
        ResultSet resSet = null;
        String select = "SELECT * FROM " + Const.WAITER_TABLE + " WHERE " + Const.WAITER_LOGIN + "=? AND " + Const.WAITER_PASSWORD + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, waiter.getLogin());
            prSt.setString(2, waiter.getPassword());

            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;
    }

    public ResultSet getAdmin(Admin admin) {
        ResultSet resSet = null;
        String select = "SELECT * FROM " + Const.ADMIN_TABLE + " WHERE " + Const.ADMIN_LOGIN + "=? AND " + Const.ADMIN_PASSWORD + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, admin.getLogin());
            prSt.setString(2, admin.getPassword());

            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;
    }

    public ResultSet getSeat(Seat seat) throws SQLException, ClassNotFoundException {
        ResultSet resSet = null;
        String select = "SELECT * FROM " + Const.SEAT_TABLE;

        PreparedStatement prSt = getDbConnection().prepareStatement(select);
        resSet = prSt.executeQuery();

        return resSet;
    }
}
