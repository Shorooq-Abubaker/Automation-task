package com.example.pages;

import com.example.utilities.ScreenshotUtils;
import com.example.utilities.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    protected static WaitUtils waitUtils;
    protected static ScreenshotUtils screenshotUtils;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        this.screenshotUtils = new ScreenshotUtils(driver);
        PageFactory.initElements(driver, this);
    }

    protected void click(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    protected void sendKeys(WebElement element, String text) {
        waitUtils.waitForVisibility(element).clear();
        element.sendKeys(text);
    }

    public void waitForPageToLoad() {
        waitUtils.waitForVisibility(getPageLoadIndicator());
        System.out.println(getPageLoadIndicator()+ "Is visible.");
    }

    protected abstract By getPageLoadIndicator();
}