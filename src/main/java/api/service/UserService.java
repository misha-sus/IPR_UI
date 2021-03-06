package api.service;

import api.Pojo.User;
import api.dao.UserDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс для работы с таблицей PERSON из БД
 */
public class UserService implements UserDAO {

    /**
     *
     * @return - Полный список из таблицы PERSON
     */
    public List<User> getAllUsers(){
        return getBDUsers("SELECT * From PERSON");
    }

    /**
     *
     * @return - Список User у которых есть дом из таблицы PERSON
     */
    public List<User> getAllUsersOwnHouse(){ return getBDUsers("SELECT * From PERSON WHERE house_id IS NOT NULL"); }

    /**
     * 
     * @param sqlRequest - SQL запрос
     * @return - Список отсортированых пользователей полученный из базы данных.
     */
    @Override
    public List<User> getBDUsers(String sqlRequest)  {
        List<User> usersBD = new ArrayList<>();
        ResultSet rsUser = null;
        try {
            var dbConnection = jb.getDBConnection();
            var statement = dbConnection.createStatement();
            rsUser = statement.executeQuery(sqlRequest);
            while (rsUser.next()) {
                usersBD.add(new User()
                        .setId(rsUser.getInt("id"))
                        .setFirstName(rsUser.getString("first_name"))
                        .setSecondName(rsUser.getString("second_name"))
                        .setAge(rsUser.getInt("age"))
                        .setSex(rsUser.getString("sex"))
                        .setMoney(rsUser.getDouble("money"))
                        .setHouse_id(rsUser.getInt("house_id")));
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
        return usersBD.stream().sorted(User::compareTo).collect(Collectors.toList());
    }
}
