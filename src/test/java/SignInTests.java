import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.models.User;
import ru.yandex.praktikum.pages.MainPage;
import ru.yandex.praktikum.user.UserClient;

import java.time.Duration;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ru.yandex.praktikum.user.UserGenerator.getRandomUser;
import static ru.yandex.praktikum.utils.WebDriverCreator.createWebDriver;

public class SignInTests {
    private WebDriver driver;
    private UserClient userClient;
    private User user;
    private String bearerToken;

    @Before
    public void setup() {
        driver = createWebDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        user = getRandomUser();
        userClient = new UserClient();
    }

    @Test
    @DisplayName("Вход в личный кабинет через хедер страницы")
    @Description("Проверка, что можно войти в личный кабинет через хедер страницы")
    public void singInPersonalAreaHeaderTest() {
        userClient.registerUser(user);

        Boolean actual = new MainPage(driver)
                .open()
                .clickPersonalAreaHeader()
                .singInPersonalArea(user.getEmail(),user.getPassword())
                .clickPersonalAreaHeaderAuthorizedUser()
                .isSetEmail(user.getEmail());

        assertTrue("Email не соответствует!", actual);
    }

    @Test
    @DisplayName("Вход в личный кабинет через кнопку Войти в аккаунт на главной странице")
    @Description("Проверка, что можно войти в личный кабинет через кнопку Войти в аккаунт на главной странице")
    public void singInAccountButtonTest() {
        userClient.registerUser(user);

        Boolean actual = new MainPage(driver)
                .open()
                .clickSingInAccountButton()
                .singInPersonalArea(user.getEmail(),user.getPassword())
                .clickPersonalAreaHeaderAuthorizedUser()
                .isSetEmail(user.getEmail());

        assertTrue("Email не соответствует!", actual);
    }

    @Test
    @DisplayName("Вход в личный кабинет через кнопку Войти в форме регистрации")
    @Description("Проверка, что можно войти в личный кабинет через кнопку Войти в форме регистрации")
    public void singInFromRegistrationPageTest() {
        userClient.registerUser(user);

        Boolean actual = new MainPage(driver)
                .open()
                .clickPersonalAreaHeader()
                .clickRegistrationArea()
                .clickSingInButton()
                .singInPersonalArea(user.getEmail(),user.getPassword())
                .clickPersonalAreaHeaderAuthorizedUser()
                .isSetEmail(user.getEmail());

        assertTrue("Email не соответствует!", actual);
    }

    @Test
    @DisplayName("Вход в личный кабинет через кнопку Войти в форме восстановления пароля")
    @Description("Проверка, что можно войти в личный кабинет через кнопку Войти в форме восстановления пароля")
    public void singInFromRecoverPasswordPageTest() {
        userClient.registerUser(user);

        Boolean actual = new MainPage(driver)
                .open()
                .clickPersonalAreaHeader()
                .clickRecoverPasswordArea()
                .clickSignInButton()
                .singInPersonalArea(user.getEmail(),user.getPassword())
                .clickPersonalAreaHeaderAuthorizedUser()
                .isSetEmail(user.getEmail());

        assertTrue("Email не соответствует!", actual);
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
