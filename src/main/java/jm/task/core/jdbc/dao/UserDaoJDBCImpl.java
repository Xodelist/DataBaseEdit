package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserDaoJDBCImpl implements UserDao {

    private Statement statement = null;
    public UserDaoJDBCImpl() {
        try {
            statement = Util.getConnection().createStatement();
        } catch (SQLException e) {
            System.out.println("Statement error");
        }
    }


    public void createUsersTable() {
            try {
                statement.execute("CREATE TABLE if not exists users_table " +
                        "(user_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY UNIQUE," +
                        "user_name VARCHAR(20) NOT NULL ," +
                        "user_lastname VARCHAR(20) NOT NULL ," +
                        "user_age INT NOT NULL )");
            } catch (SQLException e) {
                System.out.println("Unable to create table");
                System.out.println(e.getMessage());
            }
    }

    public void dropUsersTable() {
        try {
            statement.execute("DROP TABLE if exists users_table");
        } catch (SQLException e){
            System.out.println("unable to remove table");
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            String sql = " INSERT INTO users.users_table ( user_name, user_lastname, user_age ) VALUES (?,?,?);";
            PreparedStatement preparedStatement = statement.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, (int) age);
            preparedStatement.execute();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String sql = " DELETE FROM users.users_table WHERE user_id = ? ";
        try {
            PreparedStatement preparedStatement = statement.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> resultList = new ArrayList<>();
        try {
            ResultSet res = statement.executeQuery("select * from users.users_table;");
            while (res.next()) {
                resultList.add(
                        new User( res.getString(2),res.getString(3), res.getByte(4) ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultList;
    }

    public void cleanUsersTable() {
        try {
            statement.execute("DELETE FROM users.users_table;");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
