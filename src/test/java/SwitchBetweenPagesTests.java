import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praktikum.models.User;
import ru.yandex.praktikum.pages.MainPage;
import ru.yandex.praktikum.user.UserClient;

import java.time.Duration;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ru.yandex.praktikum.user.UserGenerator.getRandomUser;

public class SwitchBetweenPagesTests {
    private WebDriver driver;
    private UserClient userClient;
    private User user;
    private String bearerToken;

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        user = getRandomUser();
        userClient = new UserClient();
    }

    @Test
    @DisplayName("Переход в Личный кабинет")
    @Description("Проверка перехода в раздел Личный кабинет по клику из Главной страницы")
    public void goToPersonalAreaFromMainTest(){
        Boolean actual = new MainPage(driver)
                .open()
                .clickPersonalAreaHeader()
                .isSingInButtonDisplayed();

        assertTrue("Кнопка Войти не отображается!", actual);
    }

    @Test
    @DisplayName("Переход в Конструктор")
    @Description("Проверка перехода в раздел Конструктор по клику из Личного кабинета")
    public void goToConstructorAreaFromPersonalAreaTest(){
        Boolean actual = new MainPage(driver)
                .open()
                .clickSingInAccountButton()
                .clickConstructorAreaHeader()
                .isBunsDisplayed();

        assertTrue("Сандры Буллок нет!", actual);
    }

    @Test
    @DisplayName("Переход в Конструктор по Лого")
    @Description("Проверка перехода в раздел Конструктор по клику на Лого из Личного кабинета")
    public void goToConstructorAreaFromPersonalAreaLogoTest(){
        userClient.registerUser(user);

        Boolean actual = new MainPage(driver)
                .open()
                .clickSingInAccountButton()
                .singInPersonalArea(user.getEmail(), user.getPassword())
                .clickPersonalAreaHeaderAuthorizedUser()
                .clickLogoButton()
                .isBunsDisplayed();

        assertTrue("Тут Сандры Буллок тоже нет!", actual);
    }

    @After
    public void tearDown() {
        driver.quit();
        Response response = userClient.loginUser(user);
        if (response.statusCode() == SC_OK){
            bearerToken = response.path("accessToken");
            assertEquals("User successfully removed", userClient.deleteUser(bearerToken).path("message"));
        }
    }
}
