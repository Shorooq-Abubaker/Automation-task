package com.example.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginLoc {

    // Login Wizard Elements

    //Email Field
    @FindBy(id = "user_email")
    public WebElement userEmailField;

    //Password Field
    @FindBy(id = "user_password")
    public WebElement userPasswordField;

    //Login Button
    @FindBy(xpath = "//button[span[normalize-space()='Sign In']]")
    public WebElement submitSigninButton;
}
