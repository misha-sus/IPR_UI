package mailPageObject;

import AllSelenide.ByAttribute;
import org.openqa.selenium.WebElement;

import static AllSelenide.ByAttribute.*;
import static AllSelenide.SelenideDriver.$x;

public class ModalWindowMessages {

    /**
     * Поле ввода отправителя
     *
     * @param email - email отправителя
     */
    public static void senderEmail(String email) {
        $x(byClassContaining("container--H9")).sendKeys(email);
    }

    /**
     * Поле ввода сообщения
     *
     * @param text - текст сообщения
     */
    public static void enterMessageText(String text) {
        $x(byAttributeContaining("div", "role", "textbox")).sendKeys(text);
    }

    /**
     * Кнопка 'Отправить'
     */
    public static WebElement buttonSend() {
        return $x(byText("Отправить"));
    }

    /**
     * Кнопка 'Закрыть'
     */
    public static WebElement buttonClose() {
        return $x(ByAttribute.byAttributeContaining("span", "title", "Закрыть"));
    }
}
