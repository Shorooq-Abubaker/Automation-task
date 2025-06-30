package com.example.tests;
import com.example.pages.RayyanLoginPage;
import com.example.pages.ReviewPage;
import com.example.utilities.Config;
import com.example.utilities.ScreenshotUtils;
import com.example.utilities.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class BaseTest {
    protected WebDriver driver;
    protected WaitUtils wait;
    protected RayyanLoginPage loginPage;
    protected ReviewPage reviewPage;
    public ScreenshotUtils screenshotUtils;

    @BeforeMethod
    public void setUp() {
        // Initialize WebDriver based on configuration
        initializeDriver();

        // Maximize the browser window
        driver.manage().window().maximize();

        // Navigate to the base URL
        driver.get(Config.BASE_URL);

        // Initialize WaitUtils
        wait = new WaitUtils(driver);

        //Initialize Screenshots
        screenshotUtils = new ScreenshotUtils(driver);

        // Initialize the LoginPage
        loginPage = new RayyanLoginPage(driver);
        reviewPage = new ReviewPage(driver);

    }

    @AfterMethod
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }

    private void initializeDriver() {
        String browser = System.getProperty("browser", "chrome").toLowerCase(); // Default to Chrome

        if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", Config.CHROME_DRIVER_PATH);
            driver = new ChromeDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

}