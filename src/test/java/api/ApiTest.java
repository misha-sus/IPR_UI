package api;

import api.Pojo.Car;
import api.Pojo.User;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiTest {
    final String URL = "http://77.50.236.203:4880";
    final String ADDUSER = "/addUser";
    final String ADDCAR = "/addCar";
    final String CARS = "/cars";
    final String USERS = "/users";
    private final JdbcConnection jdbcConnection = new JdbcConnection();

    @Test
    @DisplayName("Запросом в базу, проверить количество пользователей.")
    public void checkNumberUsers() {
        Assertions.assertEquals(userList().size(), jdbcConnection.usersBD().size());
    }

    @Test
    @DisplayName("Добавить машину, проверить в базе, что она появилась.")
    public void addCarAndCheckBDAppear() {
        Car carReq = new Car("Electric", "Toyoto", "bZ4X", 60000.0);
        given()
                .spec(specification(ADDCAR))
                .body(carReq)
                .when()
                .post();
        Assertions.assertTrue(jdbcConnection.carsBD().contains(carReq));
    }

    @Test
    @Order(1)
    @DisplayName("Добавить пользователя, проверить в базе, что она появилась.")
    public void addUserAndCheckBDAppear() {
        User userReq = new User("Bill", "Johnson", 40, "MALE", 900000.0);
        given()
                .spec(specification(ADDUSER))
                .body(userReq)
                .when()
                .post();
        Assertions.assertTrue(jdbcConnection.usersBD().contains(userReq));
    }

    @Test
    @Order(2)
    @DisplayName("Добавить деньги пользователю, проверить в базе, что она появилась.")
    public void addUserMoneyAndCheckBDAppear() {
        int idAddUser = userList().size();
        Double moneyBeforeAdd = userList().get(idAddUser - 1).getMoney();
        Double moneyAdd = 100000.0;
        given()
                .spec(specification(String.format("/user/%s/money/%s", idAddUser, moneyAdd)))
                .post();
        Assertions.assertEquals(moneyAdd + moneyBeforeAdd,
                jdbcConnection.usersBD().get(idAddUser - 1).getMoney());
    }

    @Test
    @Order(3)
    @DisplayName("Купить пользователю машину, проверить в базе, что она появилась.")
    public void addUserCarAndCheckBDAppear() {
        int indexAddUser = userList().size();
        int indexAddCar = carsList().size() - 1;
        int idAddCar = carsList().get(indexAddCar).getId();
        given()
                .spec(specification(String.format("/house/3/settle/%s", indexAddUser)))
                .post();
        given()
                .spec(specification(String.format("/user/%s/buyCar/%s", indexAddUser, idAddCar)))
                .post();
        Assertions.assertEquals(indexAddUser, jdbcConnection.carsBD().get(indexAddCar).getPerson_id());
    }

    private RequestSpecification specification(String basePath) {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(URL)
                .setBasePath(basePath)
                .setContentType(ContentType.JSON);
        return builder.build();
    }

    /**
     * @param basePath - Базовый путь
     * @return - Ответ по нужному get запросу
     */
    private Response response(String basePath) {
        return given()
                .spec(specification(basePath))
                .when()
                .get()
                .then()
                .extract().response();
    }

    /**
     * @return - Список User полученный с помощью get запроса
     */
    private List<User> userList() {
        return Arrays.asList(response(USERS).getBody().as(User[].class));
    }

    /**
     * @return - Список Car полученный с помощью get запроса
     */
    private List<Car> carsList() {
        return Arrays.asList(response(CARS).getBody().as(Car[].class));
    }
}
