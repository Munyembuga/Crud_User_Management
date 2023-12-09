/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.java.usermangement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import net.java.usermanagement.model.Users;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

//    public String jdbcUrl = "";
//    public String uername = "";
//    public String password = "";
    private static final String INSERT_USER_SQL = "INSERT INTO users" + " (name,email,country) VALUES " + " (?,?,?);";
    private static final String SELECT_ALL_USERBYID = "select id,name,email,country  from users where id=?;";
    private static final String SELECT_ALL_USER = "SELECT * FROM users";
    private static final String DELETE_USER_ID = "delete from users where id = ?;";
    private static final String UPDATE_USER_BYID = "update users set name=?, email=?,country =? where id= ?;";
    private static final String SELECT_ALL_USERBYNAME = "select id,name,email,country  from users where name=?;";

    protected Connection getConnection() {

        Connection con = null;
        try {
            String jdbcUrl = "jdbc:mysql://localhost:3306/usersystemmanagement";
            String username = "root";
            String password = "Mudenge2002@";

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connected to the database!");

        } catch (Exception e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
            e.printStackTrace();

        }
        return con;
    }

    public void insert(Users users) {

        try (Connection Conn = getConnection(); PreparedStatement psa = Conn.prepareStatement(INSERT_USER_SQL);) {

            psa.setString(1, users.getName());
            psa.setString(2, users.getEmail());
            psa.setString(3, users.getCountry());
            psa.executeUpdate();
            System.out.println(" row(s) inserted.");
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public int update(Users users) {
        int rowupadate = 0;
        Connection Conn = getConnection();
        PreparedStatement psa = null;
        try {
            psa = Conn.prepareStatement(UPDATE_USER_BYID);
            psa.setString(1, users.getName());
            psa.setString(2, users.getEmail());
            psa.setString(3, users.getCountry());
            psa.setInt(4, users.getId());
            psa.executeUpdate();
            rowupadate = psa.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return rowupadate;

    }

    public Users  seclectUser(int id) {
      Users user = null;

        try (Connection Conn = getConnection(); 
                PreparedStatement psa = Conn.prepareStatement(SELECT_ALL_USERBYID);) {

            psa.setInt(1, id);
            System.out.println(psa);
            ResultSet rs = psa.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                user = new Users(id, name, email, country);

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return user;

    }
    
    public  List<Users>  seclectUserByname(int id) {
       List<Users> user = new ArrayList<>();

        try (Connection Conn = getConnection(); 
                PreparedStatement psa = Conn.prepareStatement(SELECT_ALL_USERBYID);) {

            psa.setInt(1, id);
            System.out.println(psa);
            ResultSet rs = psa.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                user.add(new Users(id, name, email, country));

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return user;

    }
    
    

    public List<Users> seclectAllUser() {
        List<Users> user = new ArrayList<>();

        try (Connection Conn = getConnection();
                PreparedStatement psa = Conn.prepareStatement(SELECT_ALL_USER);
                 ) {

            System.out.println(psa);
           ResultSet rs = psa.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                user.add(new Users(id, name, email, country));

            }
        } catch (SQLException  e) {
            e.printStackTrace();

        }
        return user;

    }

    public int deleteUser(int id) {
        Connection Conn = getConnection();
        PreparedStatement psa = null;
        int rowupadate = 0;
        try {
            psa = Conn.prepareStatement(DELETE_USER_ID);
            psa.setInt(1, id);
            rowupadate = psa.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowupadate;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
