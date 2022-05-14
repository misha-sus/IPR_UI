package ui.mailPageObject;

import org.openqa.selenium.WebElement;

import static ui.AllSelenide.ByAttribute.*;
import static ui.AllSelenide.SelenideDriver.$x;
import static ui.AllSelenide.SelenideDriver.iframe;

public class ModalWindowAuthorization {

    /**
     * Перейти в iframe авторизации
     */
    public static WebElement iframeLogin() {
        return $x(byClassContaining("ag-popup__frame__layout__iframe"));
    }

    /**
     * Поле для ввода логина
     */
    public static WebElement accountName() {
        return $x(byPlaceholderContaining("Имя аккаунта"));
    }

    /**
     * Кнопка 'Ввести пароль'
     */
    public static WebElement enterPassword() {
        return $x(byText("span", "Ввести пароль"));
    }

    /**
     * Поле для ввода пароля
     */
    public static WebElement password() {
        return $x(byPlaceholderContaining("Пароль"));
    }

    /**
     * Кнопка 'Войти'
     */
    public static WebElement enter() {
        return $x(byText("span", "Войти"));
    }

    /**
     * Полная авторизация по логину и паролю
     *
     * @param login    - Логин
     * @param password - Пароль
     */
    public static void fullLoginAndPasswordAuthorization(String login, String password) {
        iframe(iframeLogin());
        accountName().sendKeys(login);
        enterPassword().click();
        password().sendKeys(password);
        enter().click();
    }
}