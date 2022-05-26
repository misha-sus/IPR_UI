package ui.mailPageObject;

import org.openqa.selenium.WebElement;

import java.util.List;

import static ui.AllSelenide.ByAttribute.*;
import static ui.AllSelenide.SelenideDriver.*;

public class MailPageAuthorized {

    /**
     * Кнопка 'Написать письмо'
     */
    public WebElement buttonWriteLetter() {
        return $x(byText("Написать письмо"));
    }

    /**
     * Кнопка 'Входящие'
     */
    public WebElement buttonIncoming() {
        return waitElement(byText("Входящие"));
    }

    /**
     * Кнопка 'Письма себе'
     */
    public WebElement buttonEmailsToYourself() {
        return $x(byHref("tomyself"));
    }

    /**
     * Кнопка 'Отправленные'
     */
    public WebElement buttonSent() {
        return waitElement(byHref("sent"));
    }

    /**
     * Иконка логотипа
     */
    public WebElement imgLog() {
        return waitElement(byClassContaining("logo__img"));
    }

    /**
     * Список входящих сообщение
     */
    public WebElement listMessages() {
        return waitElement(byClassContaining("letter-list__react"));
    }

    /**
     * Список сообщений
     *
     * @param numberOfElementsToBeMoreThan -  минимальное количество возможных сообщений
     */
    public List<WebElement> list(int numberOfElementsToBeMoreThan) {
        return waitListElements((byClassContaining("llc__background")), numberOfElementsToBeMoreThan);
    }
}
