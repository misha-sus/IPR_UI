import AllSelenide.SelenideDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebElement;

import static AllSelenide.SelenideDriver.open;
import static mailPageObject.MailPageAuthorized.*;
import static mailPageObject.MainPage.buttonLogin;
import static mailPageObject.ModalWindowAuthorization.fullLoginAndPasswordAuthorization;
import static mailPageObject.ModalWindowMessages.*;

public class SeleniumTest {
    private static final String login = "micha26091997@mail.ru";
    private static final String password = "TprEU2Y3ta$u";
    private final static String URL = "https://mail.ru/";

    @BeforeEach
    void start() {
        open(URL);
        buttonLogin().click();
        fullLoginAndPasswordAuthorization(login, password);
    }

    @Test
    @DisplayName("Проверить отображение иконки логина.")
    void displayLoginIcon() {
        Assertions.assertTrue(imgLog().isDisplayed(), "Иконка mail не отображается ");
    }

    @Test
    @DisplayName("Проверка отображения списка входящих сообщений.")
    void displayingListIncomingMessages() {
        buttonIncoming().click();
        Assertions.assertTrue(listMessages().isDisplayed(), "Список входящих сообщение не отображается");
    }

    @Test
    @DisplayName("Проверить, что список писем увеличился после отправки себе с двух сторон")
    void listEmailsEnlargedAfterSending() {
        int numberMessagesSent = numberMessagesBeforeSend(buttonSent());
        int numberMessagesMySelf = numberMessagesBeforeSend(buttonEmailsToYourself());
        buttonWriteLetter().click();
        senderEmail(login);
        enterMessageText("Say my name ? \nYou’re god damn right");
        buttonSend().click();
        buttonClose().click();
        Assertions.assertEquals(numberMessagesMySelf + 1, numberMessages(buttonEmailsToYourself(), numberMessagesMySelf));
        Assertions.assertEquals(numberMessagesSent + 1, numberMessages(buttonSent(), numberMessagesSent));
    }

    /**
     * Нажимает на элемент в меню и считает количество найденных элементов
     *
     * @param element - нажимае на нужный элемент
     * @return - количество сообщений
     */
    private static int numberMessagesBeforeSend(WebElement element) {
        element.click();
        return list(-1).size();
    }

    /**
     * Нажимает на элемент в меню и считает количество найденных элементов
     *
     * @param element                      - нажимае на нужный элемент в меню
     * @param numberOfElementsToBeMoreThan - число элементов больше чем заданное значение
     * @return -количество сообщений
     */
    private static int numberMessages(WebElement element, int numberOfElementsToBeMoreThan) {
        element.click();
        return list(numberOfElementsToBeMoreThan).size();
    }

    @AfterEach
    void close() {
        SelenideDriver.close();
    }
}

