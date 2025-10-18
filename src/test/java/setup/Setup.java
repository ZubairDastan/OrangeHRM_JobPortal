package setup;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.Utils;

import java.time.Duration;

public class Setup {
    public static WebDriver driver; //static variable used to share this webDriver to multiple test suites

    @BeforeSuite
    public void setup() {

        // This will clear the folder before running the suites
        Utils.clearFolder("src/test/resources/screenshots/");

        if (driver == null) { // This conditional statement will prevent reinitialization of the driver if already set.
            driver = new ChromeDriver();
            driver.manage().window().setSize(new Dimension(1920, 1080));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
            driver.get(Utils.getBaseUrl());
        }
    }


    @AfterMethod
    public void screenShot(ITestResult result) { // This method is used for capturing screenshots after a failed testcase
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                Utils.takeScreenShot(driver, result.getName()); // This will send the name of method that failed to used as name of screenshot
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    // This method will quite/close the browser after finishing executing the test case(s)
    @AfterSuite
    public void quitBrowser() {
        try {
            driver.quit();
        } catch (Exception e) {
            driver.close();
        }
    }
}
