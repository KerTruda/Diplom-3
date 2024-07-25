package ru.yandex.praktikum.pages;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@AllArgsConstructor
public class LoginPage {
    private final WebDriver driver;

    //Локатор перехода по ссылке Зарегистрироваться
    private final By registrationArea = By.xpath(".//a[(@class = 'Auth_link__1fOlj' and text()= 'Зарегистрироваться')]");
    //Локатор кнопки Войти
    private final By singInButton = By.xpath(".//button[text()='Войти']");
    //Локатор поля Email
    private final By emailField = By.xpath("//label[contains(text(),'Email')]/../input");
    //Локатор поля Пароль
    private final By passwordField = By.xpath("//label[contains(text(),'Пароль')]/../input");
    //Локатор перехода в личный кабинет в хедере странице
    private final By personalAreaHeader = By.xpath(".//p[contains(text(),'Личный Кабинет')]");
    //Локатор информации о некорректном пароле
    private final By errorMessageWrongPassword = By.xpath(".//p[text() = 'Некорректный пароль']");
    //Локатор перехода по ссылке Восстановить пароль
    private final By recoverPasswordArea = By.xpath(".//a[(@class = 'Auth_link__1fOlj' and text()= 'Восстановить пароль')]");
    //Локатор перехода в Конструктор в хедере странице
    private final By constructorAreaHeader = By.xpath(".//p[text()='Конструктор']");

    @Step("Нажать на ссылку Зарегистрироваться")
    public RegistrationPage clickRegistrationArea(){
        driver.findElement(registrationArea).click();
        return new RegistrationPage(driver);
    }

    @Step("Нажать на ссылку Восстановить пароль")
    public RecoverPasswordPage clickRecoverPasswordArea(){
        driver.findElement(recoverPasswordArea).click();
        return new RecoverPasswordPage(driver);
    }

    @Step("Нажать на кнопку Войти")
    public LoginPage clickSignInButton(){
        driver.findElement(singInButton).click();
        return this;
    }

    @Step("Заполнить поле Email")
    public void setEmail(String userEmail) {
        driver.findElement(emailField).sendKeys(userEmail);
    }

    @Step("Заполнить поле Пароль")
    public void setPassword(String userPassword) {
        driver.findElement(passwordField).sendKeys(userPassword);
    }

    @Step("Нажать на кнопку Личный кабинет в хедере страницы")
    public LoginPage clickPersonalAreaHeader(){
        driver.findElement(personalAreaHeader).click();
        return new LoginPage(driver);
    }

    @Step("Нажать на кнопку Конструктор в хедере страницы")
    public MainPage clickConstructorAreaHeader(){
        driver.findElement(constructorAreaHeader).click();
        return new MainPage(driver);
    }

    @Step("Войти в Личный кабинет")
    public MainPage singInPersonalArea(String userEmail, String userPassword){
        clickPersonalAreaHeader();
        setEmail(userEmail);
        setPassword(userPassword);
        clickSignInButton();

        return new MainPage(driver);
    }

    @Step("Кнопка Войти отображается?")
    public boolean isSingInButtonDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(singInButton));

        return driver.findElement(singInButton).isDisplayed();
    }

}
