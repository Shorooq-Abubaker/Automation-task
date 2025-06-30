package com.example.pages;

import com.example.locators.LoginLoc;
import com.example.utilities.ScreenshotUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class RayyanLoginPage extends BasePage {

    private LoginLoc locators;

    public RayyanLoginPage(WebDriver driver) {
        super(driver);
        locators = new LoginLoc();
        PageFactory.initElements(driver, locators);
    }

    @Override
    protected By getPageLoadIndicator() {
        return By.id("user_email");
    }

    //Method to Enter Login Email
    public void enterSigninEmail(String username) {
        sendKeys(locators.userEmailField, username);
        System.out.println("[INFO] Login Email: " + username + " is entered");
    }

    //Method to Enter Password in the Field
    public void enterPassword(String password) {
        sendKeys(locators.userPasswordField, password);
        System.out.println("[INFO] Password: " + password + " is entered");
    }

    //Method to Click on Login Button
    public void clickLogin() {
        click(locators.submitSigninButton);
        System.out.println("[INFO] Log in Button is clicked");
    }

    //Method to do Login Using Email/Password Fields
    public void login(String email, String password) {
        System.out.println("[INFO] Log in method is called");
        enterSigninEmail(email);
        enterPassword(password);
        clickLogin();

        screenshotUtils.takeScreenshot("after_login_" + System.currentTimeMillis());

    }
}