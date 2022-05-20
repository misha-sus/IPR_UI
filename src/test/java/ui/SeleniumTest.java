package ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import ui.AllSelenide.SelenideDriver;
import ui.mailPageObject.MailPageAuthorized;
import ui.mailPageObject.ModalWindowMessages;
import ui.util.UserRole;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ui.mailPageObject.ModalWindowAuthorization.openSiteAndAuthorization;

public class SeleniumTest {
    private final static MailPageAuthorized mailPageAuthorized = new MailPageAuthorized();
    private final static ModalWindowMessages modalWindowMessages = new ModalWindowMessages();

    @BeforeEach
    void start() {
       openSiteAndAuthorization(UserRole.Misha);
    }

    @Test
    @DisplayName("Проверить отображение иконки логина.")
    void displayLoginIcon() {
        assertTrue(mailPageAuthorized.imgLog().isDisplayed(), "Иконка mail не отображается ");
    }

    @Test
    @DisplayName("Проверка отображения списка входящих сообщений.")
    void displayingListIncomingMessages() {
        mailPageAuthorized.buttonIncoming().click();
        assertTrue(mailPageAuthorized.listMessages().isDisplayed(), "Список входящих сообщение не отображается");
    }

    @Test
    @DisplayName("Проверить, что список писем увеличился после отправки себе с двух сторон")
    void listEmailsEnlargedAfterSending() {
        mailPageAuthorized.buttonSent().click();
        int numberMessagesSent = numberMessagesBeforeSend();
        mailPageAuthorized.buttonEmailsToYourself().click();
        int numberMessagesMySelf = numberMessagesBeforeSend();

        mailPageAuthorized.buttonWriteLetter().click();
        modalWindowMessages.senderEmail("micha26091997@mail.ru");
        modalWindowMessages.enterMessageText("Say my name ? \nYou’re god damn right");
        modalWindowMessages.buttonSend().click();
        modalWindowMessages.buttonClose().click();

        assertEquals(numberMessagesMySelf + 1, numberMessages(mailPageAuthorized.buttonEmailsToYourself(), numberMessagesMySelf));
        assertEquals(numberMessagesSent + 1, numberMessages(mailPageAuthorized.buttonSent(), numberMessagesSent));
    }

    /**
     * Считает количество найденных элементов
     *
     * @return - количество сообщений
     */
    private static int numberMessagesBeforeSend() {
        return mailPageAuthorized.list(-1).size();
    }

    /**
     * Нажимает на элемент в меню и считает количество найденных элементов
     *
     * @param element                      - нажимает на нужный элемент в меню
     * @param numberOfElementsToBeMoreThan - число элементов больше чем заданное значение
     * @return -количество сообщений
     */
    private static int numberMessages(WebElement element, int numberOfElementsToBeMoreThan) {
        element.click();
        return mailPageAuthorized.list(numberOfElementsToBeMoreThan).size();
    }

    @AfterEach
    void close() {
        SelenideDriver.close();
    }
}

