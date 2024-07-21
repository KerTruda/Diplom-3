package ru.yandex.praktikum.pages;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@AllArgsConstructor
public class PersonalAreaPage {
    private final WebDriver driver;

    //Локатор кнопки выхода
    private final By singOutButton = By.xpath(".//button[text()='Выход']");
    //Локатор перехода в Конструктор в хедере странице
    private final By constructorAreaHeader = By.xpath(".//p[text()='Конструктор']");
    //Локатор перехода по Лого в хедере странице
    private final By logoButton = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");


    @Step("Нажать кнопку Выйти")
    public LoginPage clickSingOutButton(){
        driver.findElement(singOutButton).click();
        return new LoginPage(driver);
    }

    @Step("Нажать на Лого")
    public MainPage clickLogoButton(){
        driver.findElement(logoButton).click();
        return new MainPage(driver);
    }

    @Step("Email авторизованного пользователя верный?")
    public boolean isSetEmail(String userEmail){
        return driver.findElement(By.xpath(".//input[@value='" + userEmail + "']")).isDisplayed();
    }
}
