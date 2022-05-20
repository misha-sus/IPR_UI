package api;

import api.Pojo.Car;
import api.Pojo.User;
import api.requestsApi.RequestsApi;
import api.service.CarService;
import api.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ApiTest {
    UserService user = new UserService();
    CarService car = new CarService();
    RequestsApi requestsApi = new RequestsApi();

    @Test
    @DisplayName("Запросом в базу, проверить количество пользователей.")
    public void checkNumberUsers() {
        Assertions.assertEquals(requestsApi.getUsersList(), user.getAll());
    }

    @Test
    @DisplayName("Добавить машину, проверить в базе, что она появилась.")
    public void addCarAndCheckBDAppear() {
        Car carReq = new Car();
        carReq.setEngineType("Electric")
                .setMark("BMV")
                .setModel("bZ4X")
                .setPrice(60000.0);

        requestsApi.postAddCar(carReq);

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

        requestsApi.postAddUser(userReq);

        Assertions.assertTrue(user.getAll().contains(userReq));
    }

    @Test
    @DisplayName("Добавить деньги пользователю, проверить в базе, что она появилась.")
    public void addUserMoneyAndCheckBDAppear() {
        int idAddUser = 2;
        Double moneyBeforeAdd = requestsApi.getUsersList().get(idAddUser - 1).getMoney();
        Double moneyAdd = 1000.0;

        requestsApi.postAddMoneyUser(idAddUser, moneyAdd);

        Assertions.assertEquals(moneyAdd + moneyBeforeAdd,
                user.getAll().get(idAddUser - 1).getMoney());
    }

    @Test
    @DisplayName("Купить пользователю машину, проверить в базе, что она появилась.")
    public void addUserCarAndCheckBDAppear() {
        int idAddUser = 23;
        int idAddCar = 6;
        Assumptions.assumeFalse(user.getAll().get(idAddUser-1).getHouse_id()==0);

        requestsApi.postAddCarUser(idAddUser, idAddCar);

        Assertions.assertEquals(idAddUser, car.getAll().get(idAddCar - 1).getPerson_id());
    }
}
