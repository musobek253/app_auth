package service;

import model.Rezalt;
import model.User;

import java.sql.*;

public class DbService {
    private String dbUrl = "jdbc:postgresql://localhost:5432/app_auth";
    private String dbPassword = "user";
    private String dbUserName = "postgres";


    public Rezalt registration(User user) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            String chekQueryPhoneNumber = "select count(*) from users where phonenumber=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(chekQueryPhoneNumber);
            preparedStatement.setString(1,user.getPhoneNumber());
            ResultSet resultSet = preparedStatement.executeQuery();
            int countByFile = 0;
            while (resultSet.next()) {
                countByFile = resultSet.getInt(1);
            }
            if (countByFile > 0) {
                return  new Rezalt("Bunday telefon nomer mavjud", false);
            }
            String socketQueryUsername = "select count(*) from users where username=?";
            preparedStatement = connection.prepareStatement(socketQueryUsername);
            preparedStatement.setString(1,user.getUserName());
            ResultSet resultSetUserName = preparedStatement.executeQuery();
            while (resultSetUserName.next()) {
                countByFile = resultSetUserName.getInt(1);
            }
            if (countByFile > 0) {
                return new Rezalt("bunday username mavjud", false);
            }
            String qurery = "insert into users(frist_name,last_name,username,phonenumber,password) values(?,?,?,?,?);";
            preparedStatement = connection.prepareStatement(qurery);
            preparedStatement.setString(1,user.getFristName());
            preparedStatement.setString(2,user.getLastName());
            preparedStatement.setString(3,user.getUserName());
            preparedStatement.setString(4,user.getPhoneNumber());
            preparedStatement.setString(5,user.getPassword());
            boolean execute = preparedStatement.execute();
            return new Rezalt("Successfuly registered", true);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        return new Rezalt("Server eror",false);

    }

    public User login(User user){
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
            String query = "select * from users where username = ? and password = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,user.getUserName());
            preparedStatement.setString(2,user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                int id = resultSet.getInt(1);
                String fristName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String userName = resultSet.getString(4);
                String phoneNumber = resultSet.getString(5);
                User user1 = new User(id,fristName,lastName,userName,phoneNumber);
                return user1;
            }
            return null;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
