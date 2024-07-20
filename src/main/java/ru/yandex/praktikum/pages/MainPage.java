package ru.yandex.praktikum.pages;

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

    //Открыть «URL»
    public MainPage open() {
        driver.get(BASE_URI);
        return this;
    }

    public LoginPage clickPersonalAreaHeader(){
        driver.findElement(personalAreaHeader).click();
        return new LoginPage(driver);
    }

    public LoginPage clickSingInAccountButton(){
        driver.findElement(singInAccountButton).click();
        return new LoginPage(driver);
    }

    public MainPage clickBunButton(){
        driver.findElement(bunButton).click();
        return new MainPage(driver);
    }

    public MainPage clickSaucesButton(){
        driver.findElement(saucesButton).click();
        return new MainPage(driver);
    }

    public MainPage clickFillingsButton(){
        driver.findElement(fillingsButton).click();
        return new MainPage(driver);
    }

    public boolean isBunsDisplayed(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(bunTab));

        return driver.findElement(bunTab).isDisplayed();
    }

    public boolean isSaucesDisplayed(){
        return driver.findElement(saucesTab).isDisplayed();
    }

    public boolean isFillingsDisplayed(){
        return driver.findElement(fillingsTab).isDisplayed();
    }
}
