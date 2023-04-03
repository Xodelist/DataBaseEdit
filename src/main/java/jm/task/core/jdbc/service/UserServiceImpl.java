package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDaoJDBCImpl userConnection = new UserDaoJDBCImpl();
    public void createUsersTable() {
        userConnection.createUsersTable();
    }

    public void dropUsersTable() {
        userConnection.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userConnection.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userConnection.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userConnection.getAllUsers();
    }

    public void cleanUsersTable() {
        userConnection.cleanUsersTable();
    }
}
