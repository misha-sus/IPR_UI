package api;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcConnection {
    //  Учетные данные базы данных
    private static final String DB_URL = "jdbc:postgresql://77.50.236.203:4832/pflb_trainingcenter";
    private static final String USER = "pflb-at-read";
    private static final String PASS = "PflbQaTraining2354";

    private final List<User> usersBD = new ArrayList<>();
    private final List<Car> carsBD = new ArrayList<>();

    private Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName("org.postgresql.Driver");
            dbConnection = DriverManager.getConnection(DB_URL, USER, PASS);
            return dbConnection;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

    public void getResultSet() {
        try {
            var dbConnection = getDBConnection();
            var statement = dbConnection.createStatement();
            // выбираем данные с БД PERSON
            ResultSet rsUser = statement.executeQuery("SELECT * From PERSON");
            while (rsUser.next()) {
                Integer userid = rsUser.getInt("id");
                Integer age = rsUser.getInt("age");
                String username = rsUser.getString("first_name");
                String username2 = rsUser.getString("second_name");
                Double money = rsUser.getDouble("money");
                usersBD.add(new User(userid, username, username2, age,null, money));
            }
            // выбираем данные с БД CAR
            ResultSet rsCar = statement.executeQuery("SELECT * From CAR");
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
        }
    }

    public List<User> usersBD() {
        getResultSet();
        return usersBD;
    }

    public List<Car> carsBD() {
        getResultSet();
        return carsBD;
    }
}
