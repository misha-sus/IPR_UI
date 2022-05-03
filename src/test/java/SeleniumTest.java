import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static AllSelenide.SelenideDriver.open;
import static mailPageObject.MailPageAuthorized.*;
import static mailPageObject.MainPage.buttonLogin;
import static mailPageObject.ModalWindowAuthorization.enterLoginAndPassword;
import static mailPageObject.ModalWindowMessages.*;

public class SeleniumTest {
    private static final String login = "micha26091997@mail.ru";
    private static final String password = "TprEU2Y3ta$u";
    final static String URL = "https://mail.ru/";

    @BeforeEach
    void start() { open(URL); }

    @Test
    void selectedItemWithRequest() {
        buttonLogin().click();
        enterLoginAndPassword(login, password);
        Assertions.assertTrue(imgLog().isDisplayed(), "Иконка mail не отображается ");

        buttonIncoming().click();
        Assertions.assertTrue(listMessages().isDisplayed(), "Список входящих сообщение не отображается");


        int numberMessagesSent = numberMessagesBeforeSend(buttonSent()) ;
        int numberMessagesMySelf = numberMessagesBeforeSend(buttonEmailsToYourself());

        buttonWriteLetter().click();
        senderEmail(login);
        enterMessageText("Say my name ? \nYou’re god damn right");
        buttonSend().click();
        buttonClose().click();

        Assertions.assertEquals(numberMessagesSent + 1, numberMessages(buttonSent(),numberMessagesSent));
        Assertions.assertEquals(numberMessagesMySelf + 1, numberMessages(buttonEmailsToYourself(),numberMessagesMySelf));
    }

    private static int numberMessagesBeforeSend(WebElement element)  {
        element.click();
        return list(-1).size();
    }
    private static int numberMessages(WebElement element,int numberOfElementsToBeMoreThan)  {
        element.click();
        return list(numberOfElementsToBeMoreThan).size();
    }
}

