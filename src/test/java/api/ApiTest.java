package api;

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
    @Order(1)
    @DisplayName("Запросом в базу, проверить количество пользователей.")
    public void checkNumberUsers() {
        Assertions.assertEquals(userList().size(), jdbcConnection.usersBD().size());
    }

    @Test
    @Order(2)
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
    @Order(3)
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
    @Order(4)
    @DisplayName("Добавить деньги пользователю, проверить в базе, что она появилась.")
    public void addUserMoneyAndCheckBDAppear() {
        int indexAddUser = userList().size();
        Double moneyBeforeAdd = userList().get(indexAddUser - 1).getMoney();
        Double moneyAdd = 100000.0;
        given()
                .spec(specification(String.format("/user/%s/money/%s", indexAddUser, moneyAdd)))
                .post();
        Assertions.assertEquals(moneyAdd + moneyBeforeAdd,
                jdbcConnection.usersBD().get(indexAddUser - 1).getMoney());
    }

    @Test
    @Order(5)
    @DisplayName("Купить пользователю машину, проверить в базе, что она появилась.")
    public void addUserCarAndCheckBDAppear() {
        int indexAddUser = userList().size() ;
        int indexAddCar = carsList().size() ;
        int idAddCar = carsList().get(indexAddCar -1).getId() ;
        given()
                .spec(specification(String.format("/house/3/settle/%s", indexAddUser)))
                .post();
        given()
                .spec(specification(String.format("/user/%s/buyCar/%s", indexAddUser,idAddCar)))
                .post();
        Assertions.assertEquals(indexAddUser, jdbcConnection.carsBD().get(indexAddCar-1).getPerson_id());
    }

    private RequestSpecification specification(String basePath) {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(URL)
                .setBasePath(basePath)
                .setContentType(ContentType.JSON);
        return builder.build();
    }

    private Response response (String basePath){
        return  given()
                .spec(specification(basePath))
                .when()
                .get()
                .then()
                .extract().response();
    }

    private List<User> userList() {
        return Arrays.asList(response(USERS).getBody().as(User[].class));
    }

    private List<Car> carsList() {
        return Arrays.asList(response(CARS).getBody().as(Car[].class));
    }
}
