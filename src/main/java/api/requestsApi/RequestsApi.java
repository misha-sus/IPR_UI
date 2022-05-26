package api.requestsApi;

import api.Pojo.Car;
import api.Pojo.User;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

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
     * Метод отправлять get-запрос для пользователей
     */
    public Response getUsers() {
        return given()
                .spec(specification("/users"))
                .when()
                .get();
    }

    /**
     * Метод отправлять post-запрос для добавлении машины
     * @param carReq - Машину которую добавится в БД
     */
    public Response postAddCar(Car carReq) {
       return given()
                .spec(specification("/addCar"))
                .body(carReq)
                .when()
                .post();
    }

    /**
     * Метод отправлять post-запрос для добавления пользователя
     * @param userReq - Пользователь который добавится в БД
     */
    public Response postAddUser(User userReq) {
       return given()
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
    public Response postAddMoneyUser(Integer idAddUser, Double moneyAdd) {
       return given()
                .spec(specification(String.format("/user/%s/money/%s", idAddUser, moneyAdd)))
                .post();
    }

    /**
     * Метод отправлять post-запрос для покупки машины пользователю
     * @param idAddUser - id Пользователя которому покупаем машину
     * @param idAddCar  - id Машины который ходим купить пользователю
     */
    public Response postAddCarUser(Integer idAddUser, Integer idAddCar) {
       return given()
                .spec(specification(String.format("/user/%s/buyCar/%s", idAddUser, idAddCar)))
                .post();
    }
}
