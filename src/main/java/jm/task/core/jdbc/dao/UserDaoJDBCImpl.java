//package jm.task.core.jdbc.dao;
//
//import jm.task.core.jdbc.model.User;
//import jm.task.core.jdbc.util.Util;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserDaoJDBCImpl implements UserDao {
//
//
//    public UserDaoJDBCImpl() {
//
//    }
//
//    public void createUsersTable() {
//        try (Connection connection = Util.getConnection();
//        PreparedStatement preparedStatement = connection.prepareStatement("create table users" +
//                "(id Int Primary Key NOT NULL, name VARCHAR(30) NOT NULL," +
//                " lastName VARCHAR(30) NOT NULL, age Int NOT NULL)")) {
//
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void dropUsersTable() {
//        try (Connection connection = Util.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement("drop table users")) {
//
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void saveUser(String name, String lastName, byte age) {
//        try (Connection connection = Util.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement("insert into users(id,name,lastName,age) values(?,?,?,?)")) {
//
//            preparedStatement.setLong(1, (int) (Math.random() * 6000) + 1);
//            preparedStatement.setString(2, name);
//            preparedStatement.setString(3, lastName);
//            preparedStatement.setByte(4, age);
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void removeUserById(long id) {
//        try (Connection connection = Util.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement("delete from users where id =?")) {
//            preparedStatement.setInt(1, (byte) id);
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public List<User> getAllUsers() {
//        List<User> list = new ArrayList<>();
//        try (Connection connection = Util.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement("select * from users")) {
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                User user = new User();
//                user.setId(resultSet.getLong(1));
//                user.setName(resultSet.getString(2));
//                user.setLastName(resultSet.getString(3));
//                user.setAge(resultSet.getByte(4));
//                list.add(user);
//            }
//            resultSet.close();
//
//            return list;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    public void cleanUsersTable() {
//        try (Connection connection = Util.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement("DELETE from users")) {
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
