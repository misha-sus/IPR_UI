package ui.util;

import com.google.gson.Gson;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Для чтения json файла и сохранение в UserUI
 */
public class FileReadUser {

    /**
     * Метод храняет данные в класс UserUI
     * @param role - Роль
     * @return     - Пользователя по найденной роли
     */
    public UserUI getUserUI(UserRole role) {
        String userJson = readJsonFile("src/main/resources/credentials/IFT.json");
        Gson gson = new Gson();
        UserUI[] userArray = gson.fromJson(userJson, UserUI[].class);
        for(UserUI userUI1 : userArray) {
            if(role.toString().equals(userUI1.getRole()))
                return userUI1;
        }
        return null;
    }

    /**
     * Метод читает Json файл
     * @param fileName - Путь до файла
     * @return         - String распарсенного Json файла
     */
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), StandardCharsets.UTF_8);
            int ch = 0;
            StringBuilder sb = new StringBuilder();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
