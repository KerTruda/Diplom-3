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
import static ru.yandex.praktikum.utils.Utils.randomString;
import static ru.yandex.praktikum.utils.WebDriverCreator.createWebDriver;

public class RegistrationTests {
    private WebDriver driver;
    private User user;
    private UserClient userClient;
    private String bearerToken;

    @Before
    public void setup() {
        driver = createWebDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        user = getRandomUser();
        userClient = new UserClient();
    }

    @Test
    @DisplayName("Регистрация пользователя с валидными данными")
    @Description("Проверка, что можно зарегистрировать пользователя с валидными данными ")
    public void registrationNewUserTest() {

        Boolean actual = new MainPage(driver)
                .open()
                .clickPersonalAreaHeader()
                .clickRegistrationArea()
                .registrationNewUser(user.getName(), user.getEmail(), user.getPassword())
                .singInPersonalArea(user.getEmail(), user.getPassword())
                .clickPersonalAreaHeaderAuthorizedUser()
                .isSetEmail(user.getEmail());

        assertTrue("Email не соответствует!", actual);

    }

    @Test
    @DisplayName("Регистрация пользователя с коротким паролем")
    @Description("Проверка, что нельзя зарегистрировать пользователя с паролем менее 6 символов")
    public void registrationNewUserWithShortPassword(){
        user.setPassword(randomString(5));

        Boolean actual = new MainPage(driver)
                .open()
                .clickPersonalAreaHeader()
                .clickRegistrationArea()
                .registrationNewUserWithShortPassword(user.getName(), user.getEmail(), user.getPassword())
                .isErrorMessageShortPassword();

        assertTrue("Сообщение не отображается!", actual);
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
