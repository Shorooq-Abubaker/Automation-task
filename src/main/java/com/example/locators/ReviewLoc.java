package com.example.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ReviewLoc {

    public static final By skipButtonBy = By.xpath("//button[span[text()='Skip']]");
    public static final By UpgradeDialog = By.xpath("//div[@role='dialog' and contains(., 'Upgrade')]");

    //---------------------------------------------------------------------------------
    // Create Review Locators

    //Create Review Button in Dashboard
    @FindBy(xpath = "//button[normalize-space()='Create Review']")
    public WebElement createReviewButton;

    //Review Title Input Field
    @FindBy(name = "review[title]")
    public WebElement reviewTitleInput;

    //Review Type Button
    @FindBy(xpath = "//button[.//div[normalize-space()='Choose review type']]")
    public WebElement reviewTypeDropdownButton;

    //Review Type List
    @FindBy(css = "button[data-testid='dropdown-option']")
    public List<WebElement> ReviewTypeOptions;

    //Review Domain Button
    @FindBy(xpath = "//button[.//div[normalize-space()='Choose review domain']]")
    public WebElement reviewDomainDropdownButton;

    //Review Domain List
    @FindBy(css = "button[data-testid='dropdown-option']")
    public List<WebElement> DomainOptions;

    //Review Description Text Area
    @FindBy(name = "review[description]")
    public WebElement reviewDescriptionTextArea;

    //Create New Review Wizard Button
    @FindBy(css = "button[aria-label='Create New Review']")
    public WebElement createNewReviewButton;

    //---------------------------------------------------------------------------------
    //Upload Articles wizard
    //Skip Wizard Button

    @FindBy(xpath = "//button[span[text()='Skip']]")
    public WebElement skipButton;

    //---------------------------------------------------------------------------------
    //Add Team Members Locators
    //Invite Member Button Dashboard
    @FindBy(css ="button[aria-label='Invite Member']")
    public WebElement inviteMemberButton;

    //Invited Member Email Field
    @FindBy(name = "invite[emails]")
    public WebElement inviteEmailInput;

    //User Role Button
    @FindBy(xpath = "//button[.//div[normalize-space()='Select member role']]")
    public WebElement memberRoleDropdownButton;

    //User Role List
    @FindBy(css = "button[data-testid='dropdown-option']")
    public List<WebElement> memberRoleOptions;

    //Invite Reason/Message Text Area
    @FindBy(name = "invite[reason]")
    public WebElement invitationReasonTextArea;

    //Invite Wizard Button
    @FindBy(css = "button[aria-label='Invite']")
    public WebElement inviteButton;

    //---------------------------------------------------------------------------------
    //Upgrade Dialog
    //Close Upgrade Dialog Button
    @FindBy(xpath = "//div[@role='dialog']//button[normalize-space()='Close']")
    public WebElement closeButton;

    //---------------------------------------------------------------------------------
    // Three dots button inside actions bar
    @FindBy(xpath = "/html[1]/body[1]/div[2]/div[1]/div[1]/main[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/button[1]/*[name()='svg'][1]")
    public WebElement threeDotsButton;

    //Delete Review Button
    @FindBy(xpath = "(//button[@class='flex gap-2 items-center text-left !text-3xs h-9 rounded-default py-1 px-2.5 enabled:hover:bg-selection01 disabled:text-disabledText z-10'])[2]")
    public WebElement deleteReviewButton;

    @FindBy(xpath = "(//button[@class='flex items-center justify-center gap-2 rounded-default p-2 !text-xs font-medium active:scale-95 transition-all flex-[0.4] text-error bg-error/10 hover:bg-error/20'])[1]")
    public WebElement confirmDeleteButton;

    @FindBy(xpath = "/html[1]/body[1]/div[2]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/a[1]/div[1]")
    public WebElement topReviewButton;



    // "Delete Review" option in dropdown
   // @FindBy(xpath = "//ul[contains(@class,'shadow-menu')]//span[text()='Delete Review']/ancestor::button")
    //public WebElement deleteReviewButton;

    // Confirmation modal Delete button
   // @FindBy(xpath = "//div[@role='dialog']//button[.//span[text()='Delete']]")
    //public WebElement confirmDeleteButton;
}
