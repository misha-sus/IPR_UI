package api.dao;

import api.util.JdbcConnection;
import api.Pojo.User;

import java.util.List;

import static api.util.JdbcConnection.getInstanceJdbcConnection;

public interface UserDAO {
    JdbcConnection jb = getInstanceJdbcConnection();
    //read
    List<User> getBDUsers(String sql) ;
}
