package mailPageObject;

import org.openqa.selenium.WebElement;

import static AllSelenide.ByAttribute.*;
import static AllSelenide.SelenideDriver.$x;
import static AllSelenide.SelenideDriver.iframe;

public class ModalWindowAuthorization {

    public static WebElement iframeLogin() {
        return $x(byClassContaining("ag-popup__frame__layout__iframe"));
    }

    public static void enterLoginAndPassword(String login, String password) {
        iframe(iframeLogin());
        $x(byPlaceholderContaining("Имя аккаунта")).sendKeys(login);
        $x(byText("span","Ввести пароль")).click();
        $x(byPlaceholderContaining("Пароль")).sendKeys(password);
        $x(byText("span","Войти")).click();
    }
}