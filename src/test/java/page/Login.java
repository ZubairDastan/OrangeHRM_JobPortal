package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;

public class Login {
    public WebDriver driver;

    @FindBy(xpath = "//input[@name='username']")
    WebElement userName;

    @FindBy(xpath = "//input[@name='password']")
    WebElement password;

    @FindBy(xpath = "//button[contains(@type, 'submit')]")
    WebElement btnLogin;

    public Login(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void adminLogin() {
        String username = Utils.getUserName();
        String passwrd = Utils.getPassword();
        userName.sendKeys(username);
        password.sendKeys(passwrd);
        btnLogin.click();
    }
}
