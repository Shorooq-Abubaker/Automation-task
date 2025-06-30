package com.example.pages;

import com.example.utilities.ScreenshotUtils;
import com.example.utilities.WaitUtils;
import org.openqa.selenium.*;
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
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        waitUtils.waitForClickability(element).click();
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

    //Method to Verify if the Element is Visible or not
    public boolean isElementVisible(By locator, int timeoutSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    //Method to Verify if the locator is Fully Visible or not
    public boolean isElementFullyVisible(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

            // Use JavaScript to check full visibility
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Boolean isVisible = (Boolean) js.executeScript(
                    "const elem = arguments[0];" +
                            "const style = window.getComputedStyle(elem);" +
                            "const rect = elem.getBoundingClientRect();" +
                            "return style && style.display !== 'none' && " +
                            "style.visibility !== 'hidden' && " +
                            "parseFloat(style.opacity) > 0 && " +
                            "rect.width > 0 && rect.height > 0 && " +
                            "rect.top >= 0 && rect.left >= 0 && " +
                            "rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) && " +
                            "rect.right <= (window.innerWidth || document.documentElement.clientWidth);",
                    element);

            return isVisible != null && isVisible;
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }
}