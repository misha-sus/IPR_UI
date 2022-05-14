package ui.mailPageObject;

import org.openqa.selenium.WebElement;

import static ui.AllSelenide.ByAttribute.byClassContaining;
import static ui.AllSelenide.SelenideDriver.$x;

public class MainPage {
  /**
   * Кнопка 'Войти'
   */
  public static WebElement buttonLogin() {
    return $x(byClassContaining("resplash-btn_primary"));
  }
}

