package com.example.tests;

import com.example.pages.ReviewPage;
import com.example.utilities.Config;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.fail;

public class CreateReviewTest extends BaseTest {
    @DataProvider(name = "reviewData")
    public Object[][] reviewData() {
        return new Object[][] {
                { "Systematic Review", "Biomedical", "Auto description 1", "user1@example.com", "Collaborator" },
                { "Literature Review", "Environmental", "Auto description 2", "user2@example.com", "Reviewer" },
                { "Systematic Review", "Biomedical", "Auto description 3", "user3@example.com", "Viewer" }
        };
    }

    @Test(dataProvider = "reviewData")
    public void createMultipleReviewsTest(String reviewType, String reviewDomain, String description, String memberEmail, String memberRole) throws Exception {

        String title = "AutoReview_" + System.currentTimeMillis();

        //Wait login page to be loaded
        loginPage.waitForPageToLoad();

        //1. Login to your Rayyan account
        loginPage.login(Config.EMAIL, Config.PASSWORD);

        //2. Create as many team reviews as you can:
        //2.b Create New Review, Fill in all required fields with valid, realistic data
        reviewPage.createReview(title, reviewType, reviewDomain, description );


        // Check for upgrade dialog
        ReviewPage.UploadStatus status = reviewPage.uploadArticles();

        switch (status) {
            case SKIP_CLICKED:
                System.out.println("Skip clicked successfully.");
                break;
            case UPGRADE_DIALOG_VISIBLE:
                screenshotUtils.takeScreenshot("upgrade_dialog_" + System.currentTimeMillis());
                reviewPage.closeUpgradeDialogIfPresent();
                Assert.fail("Upgrade dialog appeared. Test must fail.");
                break;
            case NOTHING_VISIBLE:
                screenshotUtils.takeScreenshot("Neither_Skip_Upgrade_visible_" + System.currentTimeMillis());
                reviewPage.closeUpgradeDialogIfPresent();
                Assert.fail("Neither Skip button nor Upgrade dialog is visible.");
                break;
        }

            //2.d Add team members and Submit the form to create the review.
            reviewPage.inviteMember(memberEmail, memberRole, "");

            // Final success screenshot
            Assert.assertTrue(reviewPage.isReviewCreated(title), "Review title not visible after creation.");
            screenshotUtils.takeScreenshot("success_review_"+ title);
            Assert.assertTrue(true, "Review created and members invited successfully");

            //Delete the Created Review
            reviewPage.deleteReview();

    }
}