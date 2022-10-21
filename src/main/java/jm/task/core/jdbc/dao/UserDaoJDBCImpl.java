package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

//    Connection connection = Util.getConnection();


    private static final String createTable = "create table users(id Int Primary Key NOT NULL, name VARCHAR(30) NOT NULL, lastName VARCHAR(30) NOT NULL, age Int NOT NULL)";

    private static final String DROP_TABLE_USERS = "drop table users";
    private static final String SAVE_USER = "insert into users(id,name,lastName,age) values(?,?,?,?)";
    private static final String REMOVE_BY_ID = "delete from users where id =?";
    private static final String SELECT_FROM_USERS = "select * from users";
    private static final String CLEAN_TABLE = "DELETE from users";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(createTable)) {
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {

        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DROP_TABLE_USERS)) {
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {

        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SAVE_USER)) {
                preparedStatement.setLong(1, (int) (Math.random() * 6000) + 1);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, lastName);
                preparedStatement.setByte(4, age);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {

        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_BY_ID)) {
                preparedStatement.setInt(1, (byte) id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {

        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection connection = Util.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_USERS)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong(1));
                    user.setName(resultSet.getString(2));
                    user.setLastName(resultSet.getString(3));
                    user.setAge(resultSet.getByte(4));
                    list.add(user);
                }
            }
            return list;
        } catch (SQLException e) {

        }
        return list;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(CLEAN_TABLE)) {
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {

        }
    }
}
