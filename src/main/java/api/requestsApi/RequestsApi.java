package api.requestsApi;

import api.Pojo.Car;
import api.Pojo.User;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static api.util.FileReadProperty.API_URL;
import static io.restassured.RestAssured.given;

/**
 * Спецификация тестового API
 */
public class RequestsApi {

    private RequestSpecification specification(String basePath) {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(API_URL)
                .setBasePath(basePath)
                .setContentType(ContentType.JSON);
        return builder.build();
    }

    /**
     * @param basePath - Базовый путь
     * @return - Ответ по нужному get запросу
     */
    public Response response(String basePath) {
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
    public List<User> getUsersList() {
        List<User> users = Arrays.asList((response("/users").getBody().as(User[].class)));
        return users.stream().sorted(User::compareTo).collect(Collectors.toList());
    }

    /**
     * Метод отправлять post-запрос для добавлении машины
     * @param carReq - Машину которую добавится в БД
     */
    public void postAddCar(Car carReq) {
        given()
                .spec(specification("/addCar"))
                .body(carReq)
                .when()
                .post();
    }

    /**
     * Метод отправлять post-запрос для добавления пользователя
     * @param userReq - Пользователь который добавится в БД
     */
    public void postAddUser(User userReq) {
        given()
                .spec(specification("/addUser"))
                .body(userReq)
                .when()
                .post();
    }

    /**
     * Метод отправлять post-запрос для добавления пользователю денег
     * @param idAddUser - id Пользователя которому добавляем деньги
     * @param moneyAdd  - Количество денег добавляемые пользователю
     */
    public void postAddMoneyUser(Integer idAddUser, Double moneyAdd) {
        given()
                .spec(specification(String.format("/user/%s/money/%s", idAddUser, moneyAdd)))
                .post();
    }

    /**
     * Метод отправлять post-запрос для покупки машины пользователю
     * @param idAddUser - id Пользователя которому покупаем машину
     * @param idAddCar  - id Машины который ходим купить пользователю
     */
    public void postAddCarUser(Integer idAddUser, Integer idAddCar) {
        given()
                .spec(specification(String.format("/user/%s/buyCar/%s", idAddUser, idAddCar)))
                .post();
    }
}
