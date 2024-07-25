package ru.yandex.praktikum.pages;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@AllArgsConstructor
public class RegistrationPage {
    private final WebDriver driver;

    //Локатор поля Имя
    private final By nameField = By.xpath("//label[contains(text(),'Имя')]/../input");
    //Локатор поля Email
    private final By emailField = By.xpath("//label[contains(text(),'Email')]/../input");
    //Локатор поля Пароль
    private final By passwordField = By.xpath("//label[contains(text(),'Пароль')]/../input");
    //Локатор кнопки Зарегистрироваться
    private final By registrationButton = By.xpath(".//button[text()='Зарегистрироваться']");
    //Локатор кнопки Войти
    private final By singInButton = By.xpath("//a[text()='Войти']");
    //Локатор информации о некорректном пароле
    private final By errorMessageShortPassword = By.xpath(".//p[text()='Некорректный пароль']");

    @Step("Заполнить поле Имя")
    public RegistrationPage setName(String userName) {
        driver.findElement(nameField).sendKeys(userName);
        return this;
    }

    @Step("Заполнить поле Email")
    public RegistrationPage setEmail(String userEmail) {
        driver.findElement(emailField).sendKeys(userEmail);
        return this;
    }

    @Step("Заполнить поле Пароль")
    public RegistrationPage setPassword(String userPassword) {
        driver.findElement(passwordField).sendKeys(userPassword);
        return this;
    }

    @Step("Нажать на кнопку Зарегистрироваться")
    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }

    @Step("Зарегистрироваться новым пользователем")
    public LoginPage registrationNewUser(String name,String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegistrationButton();
        return new LoginPage(driver);
    }

    @Step("Зарегистрироваться новым пользователем с коротким паролем")
    public RegistrationPage registrationNewUserWithShortPassword(String name,String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegistrationButton();
        return new RegistrationPage(driver);
    }

    @Step("Нажать на кнопку Войти")
    public LoginPage clickSingInButton() {
        driver.findElement(singInButton).click();
        return new LoginPage(driver);
    }

    @Step("Ошибка некорректного пароля отображается?")
    public boolean isErrorMessageShortPassword(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageShortPassword));

        return driver.findElement(errorMessageShortPassword).isDisplayed();
    }
}
