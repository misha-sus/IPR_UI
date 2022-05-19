package api.service;

import api.Pojo.Car;
import api.dao.CarDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс для работы с таблицей Car из БД
 */
public class CarService implements CarDAO {

    /**
     * @return - Список отсортированых машин полученный из базы данных.
     */
    @Override
    public List<Car> getAll() {
        List<Car> carsBD = new ArrayList<>();
        ResultSet rsCar = null;
        try {
            var dbConnection = jb.getDBConnection();
            var statement = dbConnection.createStatement();
            rsCar = statement.executeQuery("SELECT * From CAR");
            while (rsCar.next()) {
                carsBD.add(new Car()
                        .setId(rsCar.getInt("id"))
                        .setMark(rsCar.getString("mark"))
                        .setModel(rsCar.getString("model"))
                        .setPrice(rsCar.getDouble("price"))
                        .setEngineTypeId(rsCar.getInt("engine_type_id"))
                        .setPersonId(rsCar.getInt("person_id")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rsCar != null) {
                    rsCar.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return carsBD.stream().sorted(Car::compareTo).collect(Collectors.toList());
    }
}
