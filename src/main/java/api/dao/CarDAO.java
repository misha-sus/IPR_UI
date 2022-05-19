package api.dao;

import api.util.JdbcConnection;
import api.Pojo.Car;

import java.util.List;

import static api.util.JdbcConnection.getInstanceJdbcConnection;

public interface CarDAO {
    JdbcConnection jb = getInstanceJdbcConnection();
    //read
    List<Car> getAll();
}
