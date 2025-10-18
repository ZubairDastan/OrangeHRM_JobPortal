package testrunner;

import org.testng.annotations.Test;
import page.Login;
import page.NavSection;
import setup.Setup;

public class LoginTestRunner extends Setup {
    Login login;
    NavSection navSection;

    @Test(priority = 1, description = "User can login with valid credential")
    public void doLogin() {
        login = new Login(driver);
        navSection = new NavSection(driver);
        login.adminLogin();
        navSection.assertLogin("Dashboard");
    }
}
