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
import net.java.usermanagement.model.SignUp;

public class UserDao {

//    public String jdbcUrl = "";
//    public String uername = "";
//    public String password = "";
    private static final String INSERT_USER_SQL = "INSERT INTO users" + " (name,email,country) VALUES " + " (?,?,?);";
    private static final String SELECT_ALL_USERBYID = "select id,name,email,country  from users where id=?;";
    private static final String SELECT_ALL_USER = "SELECT * FROM users";
    private static final String DELETE_USER_ID = "delete from users where id = ?;";
    private static final String UPDATE_USER_BYID = "update users set name=?, email=?,country =? where id= ?;";
    private static final String SELECT_ALL_USERBYNAME = "select id,name,email,country  from users where name= ?;";
    private static final String SIGNUP ="INSERT INTO signup" + " (username,email,phone,password) VALUES " + " (?,?,?,?);";
    private static final String SELECT_LOGIN= "select *  from signup where email= ? AND password=? ;";
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
    
    public void signup(SignUp signup) {

        try (Connection Conn = getConnection(); PreparedStatement psa = Conn.prepareStatement(SIGNUP);) {

            psa.setString(1, signup.getName());
            psa.setString(2, signup.getEmail());
            psa.setString(3, signup.getPhone());
            psa.setString(4, signup.getPassword());
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

    public Users seclectUser(int id) {
        Users user = null;

        try (Connection Conn = getConnection(); PreparedStatement psa = Conn.prepareStatement(SELECT_ALL_USERBYID);) {

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
    public SignUp login(String email,String passw) {
     SignUp signup = null;

        try (Connection Conn = getConnection(); PreparedStatement psa = Conn.prepareStatement(SELECT_LOGIN);) {
           
            psa.setString(1, email);
            psa.setString(2, passw);
            System.out.println(psa);
            ResultSet rs = psa.executeQuery();
            while (rs.next()) {
              
                 String name = rs.getString("username");
                String emails = rs.getString("email");
                String phone = rs.getString("phone");
                signup =  new SignUp(name, emails, phone);
//                signup = new SignUp( name, email, phone);

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return signup;

    }
    
    public List<Users> seclectUserByname(String name) {
        List<Users> user = new ArrayList<>();

        try (Connection Conn = getConnection(); PreparedStatement psa = Conn.prepareStatement(SELECT_ALL_USERBYNAME);) {

            psa.setString(1, name);
            System.out.println(psa);
            ResultSet rs = psa.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");

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

        try (Connection Conn = getConnection(); PreparedStatement psa = Conn.prepareStatement(SELECT_ALL_USER);) {

            System.out.println(psa);
            ResultSet rs = psa.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                user.add(new Users(id, name, email, country));

            }
        } catch (SQLException e) {
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

    public List<Users> selectUsersWithPagination(int offset, int recordsPerPage) {
        List<Users> users = new ArrayList<>();
        String SELECT_USERS_WITH_PAGINATION = "SELECT * FROM users LIMIT ?, ?;";

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS_WITH_PAGINATION)) {

            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, recordsPerPage);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");
                users.add(new Users(id, name, email, country));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public int getTotalRecords() {
        int totalRecords = 0;
        String COUNT_TOTAL_RECORDS = "SELECT COUNT(*) FROM users;";

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(COUNT_TOTAL_RECORDS); ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                totalRecords = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalRecords;
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
