package ru.yandex.praktikum.pages;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static ru.yandex.praktikum.utils.Specification.BASE_URI;

@AllArgsConstructor
public class MainPage {
    private final WebDriver driver;

    //Локатор перехода в личный кабинет в хедере странице
    private final By personalAreaHeader = By.xpath(".//p[contains(text(),'Личный Кабинет')]");
    //Локатор кнопки Войти в аккаунт на главной странице
    private final By singInAccountButton = By.xpath(".//button[text()='Войти в аккаунт']");
    //Локатор таба Булки
    private final By bunButton = By.xpath(".//div[span[text()='Булки']]");
    //Локатор таба Соусы
    private final By saucesButton = By.xpath(".//div[span[text()='Соусы']]");
    //Локатор таба Начинки
    private final By fillingsButton = By.xpath(".//div[span[text()='Начинки']]");
    //Локатор активного таба Булки
    private final By bunTab = By.xpath("//div[contains(span/text(),'Булки') and contains(@class,'current')]");
    //Локатор активного таба Соусы
    private final By saucesTab = By.xpath("//div[contains(span/text(),'Соусы') and contains(@class,'current')]");
    //Локатор активного таба Начинки
    private final By fillingsTab = By.xpath("//div[contains(span/text(),'Начинки') and contains(@class,'current')]");

    @Step("Открыть URL")
    public MainPage open() {
        driver.get(BASE_URI);
        return this;
    }

    @Step("Нажать на кнопку Личный кабинет в хедере страницы")
    public LoginPage clickPersonalAreaHeader(){
        driver.findElement(personalAreaHeader).click();
        return new LoginPage(driver);
    }

    @Step("Нажать на кнопку Личный кабинет в хедере страницы авторизованным пользователем")
    public PersonalAreaPage clickPersonalAreaHeaderAuthorizedUser(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(personalAreaHeader));

        driver.findElement(personalAreaHeader).click();
        return new PersonalAreaPage(driver);
    }

    @Step("Нажать на кнопку Войти в аккаунт на Главной странице")
    public LoginPage clickSingInAccountButton(){
        driver.findElement(singInAccountButton).click();
        return new LoginPage(driver);
    }

    @Step("Нажать на таб Булки")
    public MainPage clickBunButton(){
        driver.findElement(bunButton).click();
        return new MainPage(driver);
    }

    @Step("Нажать на таб Соусы")
    public MainPage clickSaucesButton(){
        driver.findElement(saucesButton).click();
        return new MainPage(driver);
    }

    @Step("Нажать на таб Начинки")
    public MainPage clickFillingsButton(){
        driver.findElement(fillingsButton).click();
        return new MainPage(driver);
    }

    @Step("Выбран таб Булки?")
    public boolean isBunsDisplayed(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(bunTab));

        return driver.findElement(bunTab).isDisplayed();
    }

    @Step("Выбран таб Соусы?")
    public boolean isSaucesDisplayed(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(saucesTab));

        return driver.findElement(saucesTab).isDisplayed();
    }

    @Step("Выбран таб Начинки?")
    public boolean isFillingsDisplayed(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(fillingsTab));

        return driver.findElement(fillingsTab).isDisplayed();
    }
}
