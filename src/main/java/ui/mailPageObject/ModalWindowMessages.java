package ui.mailPageObject;

import ui.AllSelenide.ByAttribute;
import org.openqa.selenium.WebElement;

import static ui.AllSelenide.ByAttribute.*;
import static ui.AllSelenide.SelenideDriver.$x;

public class ModalWindowMessages {

    /**
     * Поле ввода отправителя
     *
     * @param email - email отправителя
     */
    public  void senderEmail(String email) {
        $x(byClassContaining("container--H9")).sendKeys(email);
    }

    /**
     * Поле ввода сообщения
     *
     * @param text - текст сообщения
     */
    public  void enterMessageText(String text) {
        $x(byAttributeContaining("div", "role", "textbox")).sendKeys(text);
    }

    /**
     * Кнопка 'Отправить'
     */
    public  WebElement buttonSend() {
        return $x(byText("Отправить"));
    }

    /**
     * Кнопка 'Закрыть'
     */
    public  WebElement buttonClose() {
        return $x(ByAttribute.byAttributeContaining("span", "title", "Закрыть"));
    }
}
