package com.example.utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.*;

public class ScreenshotUtils {

    private WebDriver driver;

    //Constructor
    public ScreenshotUtils(WebDriver driver) {
        this.driver = driver;
    }

    //Take Screenshot
    public void takeScreenshot(String fileName) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File("screenshots" + File.separator + fileName + ".png");

        try {
            // Ensure the screenshots directory exists
            File dir = dest.getParentFile();
            if (!dir.exists()) {
                dir.mkdirs();
            }

            copyFileUsingStream(src, dest);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save screenshot: " + e.getMessage());
        }
    }

    //Copy File Using Stream
    private void copyFileUsingStream(File source, File dest) throws IOException {
        try (InputStream is = new FileInputStream(source);
             OutputStream os = new FileOutputStream(dest)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        }
    }
}
