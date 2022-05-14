package ui.AllSelenide;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SelenideDriver  implements AfterEachCallback {
    private static RemoteWebDriver driver;

    /**
     * Открывает сайт
     *
     * @param URL - ссылка
     */
    public static void open(String URL) {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(URL);
    }

    /**
     * Обертка для xpath
     */
    public static WebElement $x(By by) {
        return driver.findElement(by);
    }

    /**
     * Перейти в iframe
     */
    public static void iframe(WebElement webElement) {
        driver.switchTo().frame(webElement);
    }

    /**
     * Закрывает браузер
     */
    public static void close() {
        driver.quit();
    }

    /**
     * Ожидание по локатору
     */
    public static WebElement waitElement(By by) {
        return new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    /**
     * ожидание по минимальному количеству найденных элементов
     */
    public static List<WebElement> waitListElements(By by, int numberOfElementsToBeMoreThan) {
        return new WebDriverWait(driver, 5, 500)
                .until(ExpectedConditions
                        .numberOfElementsToBeMoreThan((by), numberOfElementsToBeMoreThan));
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) {
    }
}
