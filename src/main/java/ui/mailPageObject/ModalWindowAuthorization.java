package ui.mailPageObject;

import org.openqa.selenium.WebElement;

import static ui.AllSelenide.ByAttribute.*;
import static ui.AllSelenide.SelenideDriver.*;


public class ModalWindowAuthorization {

    /**
     * Перейти в iframe авторизации
     */
    public  WebElement iframeLogin() {
        return $x(byClassContaining("ag-popup__frame__layout__iframe"));
    }

    /**
     * Поле для ввода логина
     */
    public  WebElement accountName() {
        return $x(byPlaceholderContaining("Имя аккаунта"));
    }

    /**
     * Кнопка 'Ввести пароль'
     */
    public  WebElement enterPassword() {
        return $x(byText("span", "Ввести пароль"));
    }

    /**
     * Поле для ввода пароля
     */
    public  WebElement password() {
        return $x(byPlaceholderContaining("Пароль"));
    }

    /**
     * Кнопка 'Войти'
     */
    public  WebElement enter() {
        return $x(byText("span", "Войти"));
    }

    /**
     * Полная авторизация по логину и паролю
     *
     * @param login    - Логин
     * @param password - Пароль
     */
    public static void fullLoginAndPasswordAuthorization(String login, String password) {
        ModalWindowAuthorization modalWindowAuthorization = new ModalWindowAuthorization();
        iframe(modalWindowAuthorization.iframeLogin());
        modalWindowAuthorization.accountName().sendKeys(login);
        modalWindowAuthorization.enterPassword().click();
        modalWindowAuthorization.password().sendKeys(password);
        modalWindowAuthorization.enter().click();
    }

    public static void openMailAndAuthorization() {
        final String login = "micha26091997@mail.ru";
        final String password = "TprEU2Y3ta$u";
        String URL = "https://mail.ru/";
        final MainPage mainPage = new MainPage();
        open(URL);
        mainPage.buttonLogin().click();
        fullLoginAndPasswordAuthorization(login, password);
    }
}