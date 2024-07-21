import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praktikum.models.User;
import ru.yandex.praktikum.pages.MainPage;

import java.time.Duration;

import static org.junit.Assert.assertTrue;
import static ru.yandex.praktikum.user.UserGenerator.getRandomUser;
import static ru.yandex.praktikum.utils.Utils.randomString;

public class RegistrationTests {
    private WebDriver driver;
    private User user;

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        user = getRandomUser();
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
    }
}
