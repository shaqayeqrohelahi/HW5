package repository;

import Data.User;

import java.sql.*;

public class UserRepository {
    public void createTable() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "CREATE TABLE user_tbl (idUser int primary key not null ," +
                "username varchar(30),nationalCode varchar(10)," +
                "birthday date,password varchar(16))";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeQuery();
        connection.close();
    }


    public void signUp() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "insert into user_tbl  (username,nationalCode,birthady,password)values" +
                "(?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        User user = new User();
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getNationalCode());
        preparedStatement.setDate(3, (Date) user.getBirthday());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.executeUpdate();
        connection.close();
    }
    public void updatePassword(User user) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "UPDATE user_tbl SET user WHERE  password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, String.valueOf(user));
        preparedStatement.executeUpdate();
        connection.close();
    }
    public boolean matchPassword(User user) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT username,password FROM user_tbl where usernam = ?" +
                " && password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,user.getUsername());
        preparedStatement.setString(2,user.getPassword());
        ResultSet resultSet = preparedStatement.executeQuery();
        connection.close();
        while (resultSet.next()) {
            return true;
        }
        return false;
    }
    private User buildUser(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            User user = new User();
            String username = resultSet.getString("username");
            String nationalCode = resultSet.getString("nationalCode");
            Date birthday = resultSet.getDate("birthday");
            String passWord = resultSet.getString("passWord");

            user.setUsername(username);
            user.setNationalCode(nationalCode);
            user.setBirthday(birthday);
            user.setPassword(passWord);
            return user;
        }
        return null;
    }
}
