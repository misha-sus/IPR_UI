package api;

import api.Pojo.Car;
import api.Pojo.User;
import api.requestsApi.RequestsApi;
import api.service.CarService;
import api.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ApiTest {
    UserService user = new UserService();
    CarService car = new CarService();
    RequestsApi requestsApi = new RequestsApi();

    @Test
    @DisplayName("Запросом в базу, проверить количество пользователей.")
    public void checkNumberUsers() {
        Assertions.assertEquals(getUsersList(), user.getAllUsers());
    }

    @Test
    @DisplayName("Добавить машину, проверить в базе, что она появилась.")
    public void addCarAndCheckBDAppear() {
        Car carReq = new Car();
        carReq.setEngineType("Electric")
                .setMark("BMV")
                .setModel("bZ4X")
                .setPrice(60000.0);

        requestsApi.postAddCar(carReq).then().statusCode(201);

        Assertions.assertTrue(car.getAll().contains(carReq));
    }

    @Test
    @DisplayName("Добавить пользователя, проверить в базе, что она появилась.")
    public void addUserAndCheckBDAppear() {
        User userReq = new User();
        userReq.setFirstName("Bill")
                .setSecondName("Johnson")
                .setAge(40)
                .setSex("MALE")
                .setMoney(900000.0);

        requestsApi.postAddUser(userReq).then().statusCode(201);

        Assertions.assertTrue(user.getAllUsers().contains(userReq));
    }

    @Test
    @DisplayName("Добавить деньги пользователю, проверить в базе, что она появилась.")
    public void addUserMoneyAndCheckBDAppear() {
        int randomIndex =  (int) (Math.random() * user.getAllUsers().size());
        int randomId = user.getAllUsers().get(randomIndex).getId();
        Double moneyBeforeAdd = getUsersList().get(randomId - 1).getMoney();
        Double moneyAdd = 1000.0;

        requestsApi.postAddMoneyUser(randomId, moneyAdd).then().statusCode(200);

        Assertions.assertEquals(moneyAdd + moneyBeforeAdd,
                user.getAllUsers().get(randomId - 1).getMoney());
    }

    @Test
    @DisplayName("Купить пользователю машину, проверить в базе, что она появилась.")
    public void addUserCarAndCheckBDAppear() {
        int index =  (int) (Math.random() * user.getAllUsersOwnHouse().size());
        int randomId = user.getAllUsersOwnHouse().get(index).getId();
        int idAddCar = car.getAll().size();

        requestsApi.postAddCarUser(randomId, idAddCar).then().statusCode(200);

        Assertions.assertEquals(randomId, car.getAll().get(idAddCar - 1).getPerson_id());
    }

    /**
     * @return - Список User полученный с помощью get запроса
     */
    private List<User> getUsersList() {
        List<User> users = Arrays.asList(requestsApi.getUsers().getBody().as(User[].class));
        return users.stream().sorted(User::compareTo).collect(Collectors.toList());
    }
}
