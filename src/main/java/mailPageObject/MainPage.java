package mailPageObject;

import org.openqa.selenium.WebElement;

import static AllSelenide.ByAttribute.byClassContaining;
import static AllSelenide.SelenideDriver.$x;

public class MainPage {
  /**
   * Кнопка 'Войти'
   */
  public static WebElement buttonLogin() {
    return $x(byClassContaining("resplash-btn_primary"));
  }
}

