package com.example.utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.List;

public class WaitUtils {

    private WebDriver driver;
    private static WebDriverWait wait;

    public WaitUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Default wait time of 10 seconds
    }

    //Method to Wait All WebElements to be Visible - Used for the dropdown list
    public void waitForVisibilityOfAll(List<WebElement> elements) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    // Wait for an element to disappear
    public boolean waitForElementToDisappear(By locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // Wait for visibility using By locator
    public WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait for visibility using WebElement
    public WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForClickability(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement waitForClickability(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }


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