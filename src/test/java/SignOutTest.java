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

public class SignOutTest {
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
    @DisplayName("Выход из аккаунта авторизованного пользователя")
    @Description("Проверка, что можно выйти из аккаунта авторизованного пользователя")
    public void canSingOutFromProfileTest(){
        userClient.registerUser(user);

        Boolean actual = new MainPage(driver)
                .open()
                .clickSingInAccountButton()
                .singInPersonalArea(user.getEmail(), user.getPassword())
                .clickPersonalAreaHeaderAuthorizedUser()
                .clickSingOutButton()
                .isSingInButtonDisplayed();

        assertTrue("Кнопка Войти не отображается!", actual);
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
