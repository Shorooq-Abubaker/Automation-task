package com.example.pages;

import com.example.locators.ReviewLoc;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class ReviewPage extends BasePage {

    private ReviewLoc locators;


    public ReviewPage(WebDriver driver) {
        super(driver);
        locators = new ReviewLoc();
        PageFactory.initElements(driver, locators);
    }

    @Override
    protected By getPageLoadIndicator() {
        return By.name("review[title]");
    }

    // Method to check if the "Create Review" button is visible
    private boolean isCreateReviewButtonVisible() {
        try {
            waitUtils.waitForVisibility(locators.createReviewButton);
            return locators.createReviewButton.isDisplayed();
        } catch (Exception e) {
            System.err.println("[Error] Create Review button not found or not visible: " + e.getMessage());
            return false;
        }
    }

    //Method to Click on Create Review Button
    private void clickCreateReview() {
        click(locators.createReviewButton);
        System.out.println("[INFO] Create review button clicked");

    }

    //Method to Enter Title Field
    private void enterTitle(String title) {
        sendKeys(locators.reviewTitleInput, title);
        System.out.println("[INFO] Creating review: " + title);
    }

    //Method to Click on Review Type Button
    private void clickReviewTypeDropdownButton() {
        click(locators.reviewTypeDropdownButton);
        System.out.println("[INFO] Review type dropdown button clicked");
    }

    //Method to select Review Type from The List
    private void selectReviewType(String reviewTypeName) throws NoSuchFieldException {

        //Click on Review Type button
        clickReviewTypeDropdownButton();


        //Wait the list to be visible
        waitUtils.waitForVisibilityOfAll(locators.ReviewTypeOptions);

        for (WebElement option : locators.ReviewTypeOptions) {
            if (option.getText().trim().equalsIgnoreCase(reviewTypeName)) {
                option.click();
                System.out.println("[INFO] Review type: "+ reviewTypeName +" clicked");
                return;
            }
        }

        throw new NoSuchFieldException("[ERROR] Review type option not found: " + reviewTypeName);
    }

    //Method to Click on Review Domain Button
    private void clickReviewDomainDropdownButton() {
        click(locators.reviewDomainDropdownButton);
        System.out.println("[INFO] Review domain dropdown button clicked");
    }

    //Method to Select Review Domain from the List
    private void selectReviewDomain(String reviewDomainName) throws NoSuchFieldException, InterruptedException {

        //Click on Review Domain Button
        clickReviewDomainDropdownButton();

        //Wait the list be visible
        waitUtils.waitForVisibilityOfAll(locators.DomainOptions);

        //Search for the selected Domain
        Thread.sleep(300);
        for (WebElement option : locators.DomainOptions) {
            if (option.getText().trim().equalsIgnoreCase(reviewDomainName)) {
                option.click();
                System.out.println("[INFO] Review Domain: " + reviewDomainName + " clicked");
                return;
            }
        }

        throw new NoSuchFieldException("[ERROR] Review Domain option not found: " + reviewDomainName);
    }

    //Method to set Description Text Field
    private void setReviewDescription(String reviewDescription) {
        sendKeys(locators.reviewDescriptionTextArea, reviewDescription);
        System.out.println("[INFO] Review description: " + reviewDescription + " entered");
    }

    //Method to Click on "Create New Review" Button in the Dialog
    private void createNewReview() {
        // Check if the "Create Review" button is visible before clicking
        if (isCreateReviewButtonVisible()) {
            click(locators.createNewReviewButton);
            System.out.println("[INFO] Create New Review button clicked successfully.");
        } else {
            //Take a screenshot for debugging
            screenshotUtils.takeScreenshot("create_review_button_not_visible_" + System.currentTimeMillis());
            throw new IllegalStateException("[ERROR] Create New Review button is not visible. Review creation aborted.");
        }
    }

    //Method to Fill and Create the Form Create Review
    public void createReview(String title, String reviewTypeName, String reviewDomainName, String reviewDescription) throws NoSuchFieldException, InterruptedException {

        //Click create review button in dashboard page
        clickCreateReview();

        //Fill the Form
        //1. Review title
        enterTitle(title);

        //2. Select Review Type
        selectReviewType(reviewTypeName);

        //3. Select Review Domain
        selectReviewDomain(reviewDomainName);

        //4. Add description
        setReviewDescription(reviewDescription);

        //5. Click on Create New Review Button
        createNewReview();


    }

    //Method to check If the Review Created
    public boolean isReviewCreated(String title) {
        return driver.getPageSource().contains(title);
    }

    //Add Articles
    private void clickSkipButton() {
        click(locators.skipButton);
        System.out.println("[INFO] Upload Article Skip button clicked");
    }

    //Define enum Values to Handle the displayed Dialog
    public enum UploadStatus {
        SKIP_CLICKED,
        UPGRADE_DIALOG_VISIBLE,
        NOTHING_VISIBLE
    }

    //Method to Avoid the "Upload Articles" and "Upgrade Dialog"
    public UploadStatus uploadArticles() {
        if (isElementVisible(locators.skipButtonBy, 5)) {
            click(locators.skipButton);
            return UploadStatus.SKIP_CLICKED;
        } else if (isElementVisible(locators.UpgradeDialog, 5)) {
            return UploadStatus.UPGRADE_DIALOG_VISIBLE;
        } else {
            return UploadStatus.NOTHING_VISIBLE;
        }
    }

    // Invite Members
    //Method to Click on "Invite Member" Button in the dashboard
    private void clickInviteMembersButton() {
        click(locators.inviteMemberButton);
        System.out.println("[INFO] Invite members button clicked");
    }

    //Method to enter the Invite Email
    private void enterInviteEmail(String email) {
        sendKeys(locators.inviteEmailInput, email);
        System.out.println("[INFO] Invite email: " + email + " entered");
    }

    //Method to Click on User Role Button
    private void clickUserRoleButton() {
        click(locators.memberRoleDropdownButton);
        System.out.println("[INFO] Member role button clicked");
    }

    //Method to Select User Role from the List
    private void selectUserRole(String roleName) throws NoSuchFieldException {
        //Click on User Role Button to show the dropdown list
        clickUserRoleButton();

        //Wait the list be visible
        waitUtils.waitForVisibilityOfAll(locators.memberRoleOptions);

        //Search for the selected Domain
        for (WebElement option : locators.memberRoleOptions) {
            if (option.getText().trim().equalsIgnoreCase(roleName)) {
                option.click();
                System.out.println("[INFO] Member role: " + roleName + " clicked");
                return;
            }
        }

        throw new NoSuchFieldException("[ERROR] Review Domain option not found: " + roleName);

    }

    //Method to Enter Reason/Message Text Field
    private void enterReason(String reason) {
        sendKeys(locators.invitationReasonTextArea, reason);
        System.out.println("[INFO] Invitation reason: " + reason + " entered");
    }

    //Method to Click on invite Button inside the Dialog
    private void clickInviteButton() {
            click(locators.inviteButton);
            System.out.println("[INFO] Invite button clicked successfully.");
    }

    //Method to Fill the form of Invite Member
    public void inviteMember(String email, String roleName, String reason) throws NoSuchFieldException {
        //1. Fill the Invite Members Form
        //1.1. Enter User Email
        enterInviteEmail(email);

        //1.2 Select User Role
        selectUserRole(roleName);

        //1.3 Add a reason
        enterReason(reason);

        //1.4 Click Invite
        clickInviteButton();

    }

    //Method to Close the "Upgrade Dialog"
    public void closeUpgradeDialogIfPresent() {
        if (isWizardPresent()) {
            click(locators.closeButton);
            System.out.println("Close button clicked");
            waitUtils.waitForElementToDisappear(locators.UpgradeDialog);
        }
    }


    // Check for upgrade dialog
    public boolean isWizardPresent(){
        try {
            return isElementFullyVisible(locators.UpgradeDialog);
        }catch (Exception e){
            return false;
        }
    }

    private void clickThreeDotsButton() {
        waitUtils.waitForClickability(locators.threeDotsButton).click();
    }

    private void clickDeleteReviewButton() {
        click(locators.deleteReviewButton);
    }

    private void clickConfirmDeleteButton() {
        click(locators.confirmDeleteButton);
    }

    public void clickTopReviewButton() {
        click(locators.topReviewButton);
    }

    //Method to Delete Review
    public void deleteReview() throws InterruptedException {
        // Click on More options
        waitUtils.waitForVisibility(locators.threeDotsButton);
        Thread.sleep(300);
        clickThreeDotsButton();

        // Wait until the dropdown appears
        waitUtils.waitForVisibility(locators.deleteReviewButton);

        // Click Delete Review
        clickDeleteReviewButton();

        //Wait until Confirm dialog appear
        waitUtils.waitForVisibility(locators.confirmDeleteButton);
        clickConfirmDeleteButton();
    }


}
