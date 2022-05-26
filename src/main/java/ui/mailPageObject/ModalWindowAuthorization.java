package ui.mailPageObject;

import org.openqa.selenium.WebElement;
import ui.util.FileReadUser;
import ui.util.UserRole;

import static ui.AllSelenide.ByAttribute.*;
import static ui.AllSelenide.SelenideDriver.*;


public class ModalWindowAuthorization {

    /**
     * Перейти в iframe авторизации
     */
    public WebElement iframeLogin() {
        return $x(byClassContaining("ag-popup__frame__layout__iframe"));
    }

    /**
     * Поле для ввода логина
     */
    public WebElement accountName() {
        return $x(byPlaceholderContaining("Имя аккаунта"));
    }

    /**
     * Кнопка 'Ввести пароль'
     */
    public WebElement enterPassword() {
        return $x(byText("span", "Ввести пароль"));
    }

    /**
     * Поле для ввода пароля
     */
    public WebElement password() {
        return $x(byPlaceholderContaining("Пароль"));
    }

    /**
     * Кнопка 'Войти'
     */
    public WebElement enter() {
        return $x(byText("span", "Войти"));
    }

    /**
     * Полная авторизация по логину и паролю
     *
     * @param login    - Логин
     * @param password - Пароль
     */
    public void fullLoginAndPasswordAuthorization(String login, String password) {
        ModalWindowAuthorization modalWindowAuthorization = new ModalWindowAuthorization();
        iframe(modalWindowAuthorization.iframeLogin());
        modalWindowAuthorization.accountName().sendKeys(login);
        modalWindowAuthorization.enterPassword().click();
        modalWindowAuthorization.password().sendKeys(password);
        modalWindowAuthorization.enter().click();
    }

    /**
     * Авторизация по Роли
     *
     * @param userRole - Роль пользователя под которым авторизируемся.
     */
    public void openSiteAndAuthorization(UserRole userRole) {
        MainPage mainPage = new MainPage();
        FileReadUser fileReadUser = new FileReadUser();
        open(fileReadUser.getUserUI(userRole).getUrl());
        mainPage.buttonLogin().click();
        fullLoginAndPasswordAuthorization(fileReadUser.getUserUI(userRole).getLogin(),
                fileReadUser.getUserUI(userRole).getPassword());
    }
}