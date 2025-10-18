package page;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavSection {
    WebDriver driver;

    @FindBy(xpath = "//h6[contains(@class, 'oxd-topbar-header-breadcrumb-module')]")
    WebElement pageHeadings;

    @FindBy(xpath = "//span[text()='Recruitment']")
    WebElement menuRecruitment;


    public NavSection(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void assertLogin(String expectedHeading) {

        //Assert admin login by confirming user have landed on dashboard
        String actualHeading = pageHeadings.getText();
        Assert.assertEquals(actualHeading, expectedHeading);
    }

    public void clickOnRecruitment() {
        //Click recruitment menu
        menuRecruitment.click();
    }


}
