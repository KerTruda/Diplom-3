package ru.yandex.praktikum.pages;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@AllArgsConstructor
public class RecoverPasswordPage {
    private final WebDriver driver;

    //Локатор кнопки Войти
    private final By singInButton = By.xpath(".//a[text()='Войти']");

    @Step("Нажать на кнопку Войти")
    public LoginPage clickSignInButton(){
        driver.findElement(singInButton).click();
        return new LoginPage(driver);
    }
}
