package mailPageObject;

import org.openqa.selenium.WebElement;

import java.util.List;

import static AllSelenide.ByAttribute.*;
import static AllSelenide.SelenideDriver.*;

public class MailPageAuthorized {

    /**
     * Кнопка 'Написать письмо'
     */
    public static WebElement buttonWriteLetter() {
        return $x(byText("Написать письмо"));
    }

    /**
     * Кнопка 'Входящие'
     */
    public static WebElement buttonIncoming() {
        return waitElement(byText("Входящие"));
    }

    /**
     * Кнопка 'Письма себе'
     */
    public static WebElement buttonEmailsToYourself() {
        return $x(byHref("tomyself"));
    }

    /**
     * Кнопка 'Отправленные'
     */
    public static WebElement buttonSent() {
        return waitElement(byHref("sent"));
    }

    /**
     * Иконка логотипа
     */
    public static WebElement imgLog() {
        return waitElement(byClassContaining("logo__img"));
    }

    /**
     * Список входящих сообщение
     */
    public static WebElement listMessages() {
        return waitElement(byClassContaining("letter-list__react"));
    }

    /**
     * Список сообщений
     *
     * @param numberOfElementsToBeMoreThan -  минимальное количество возможных сообщений
     */
    public static List<WebElement> list(int numberOfElementsToBeMoreThan) {
        return waitListElements((byClassContaining("llc__background")), numberOfElementsToBeMoreThan);
    }
}
