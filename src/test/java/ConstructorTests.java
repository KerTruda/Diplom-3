import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.pages.MainPage;

import java.time.Duration;

import static org.junit.Assert.assertTrue;
import static ru.yandex.praktikum.utils.WebDriverCreator.createWebDriver;

public class ConstructorTests {
    private WebDriver driver;

    @Before
    public void setup() {
        driver = createWebDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    @Description("Проверка, что можно перейти в раздел 'Булки' ")
    public void checkTabBunsTest() {
        Boolean actual = new MainPage(driver)
                .open()
                .clickSaucesButton()
                .clickBunButton()
                .isBunsDisplayed();

        assertTrue("Таб Булки не отображается!", actual);
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    @Description("Проверка, что можно перейти в раздел 'Соусы' ")
    public void checkTabSaucesTest() {
        Boolean actual = new MainPage(driver)
                .open()
                .clickSaucesButton()
                .isSaucesDisplayed();

        assertTrue("Таб Соусы не отображается!", actual);
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    @Description("Проверка, что можно перейти в раздел 'Начинки' ")
    public void checkTabFillingsTest() {
        Boolean actual = new MainPage(driver)
                .open()
                .clickFillingsButton()
                .isFillingsDisplayed();

        assertTrue("Таб Начинки не отображается!", actual);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
