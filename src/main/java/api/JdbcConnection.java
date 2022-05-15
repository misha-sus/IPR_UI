package api;

import api.Pojo.Car;
import api.Pojo.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcConnection {
    private static final String DB_URL = "jdbc:postgresql://77.50.236.203:4832/pflb_trainingcenter";
    private static final String LOGIN = "pflb-at-read";
    private static final String PASSWORD = "PflbQaTraining2354";

    private final List<User> usersBD = new ArrayList<>();
    private final List<Car> carsBD = new ArrayList<>();

    /**
     * Подключение к базе данных.
     */
    private Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName("org.postgresql.Driver");
            dbConnection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
            return dbConnection;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

    /**
     * Запрос к таблице PERSON
     * Добавляет все найденые элементы таблица в список User
     */
    public void getResultSetUser() {
        ResultSet rsUser = null;
        try {
            var dbConnection = getDBConnection();
            var statement = dbConnection.createStatement();
            rsUser = statement.executeQuery("SELECT * From PERSON");
            while (rsUser.next()) {
                Integer userid = rsUser.getInt("id");
                String firstName = rsUser.getString("first_name");
                String secondName = rsUser.getString("second_name");
                Integer age = rsUser.getInt("age");
                String sex = rsUser.getString("sex");
                Double money = rsUser.getDouble("money");
                usersBD.add(new User(userid, firstName, secondName, age, sex, money));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rsUser != null) {
                    rsUser.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Запрос к таблице CAR
     * Добавляет все найденые элементы таблица в список Car
     */
    public void getResultSetCar() {
        ResultSet rsCar = null;
        try {
            var dbConnection = getDBConnection();
            var statement = dbConnection.createStatement();
            rsCar = statement.executeQuery("SELECT * From CAR");
            while (rsCar.next()) {
                Integer id = rsCar.getInt("id");
                String mark = rsCar.getString("mark");
                String model = rsCar.getString("model");
                Double price = rsCar.getDouble("price");
                Integer engine_type_id = rsCar.getInt("engine_type_id");
                Integer person_id = rsCar.getInt("person_id");
                carsBD.add(new Car(id, mark, model, price, engine_type_id, person_id));
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
    }

    /**
     * @return - список User взятый из БД
     */
    public List<User> usersBD() {
        getResultSetUser();
        return usersBD;
    }

    /**
     * @return - список Car взятый из БД
     */
    public List<Car> carsBD() {
        getResultSetCar();
        return carsBD;
    }
}
