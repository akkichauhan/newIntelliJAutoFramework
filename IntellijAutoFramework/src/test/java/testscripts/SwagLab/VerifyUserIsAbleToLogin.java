package testscripts.SwagLab;

import newIntelliJAutoFramework.core.BaseTest;
import newIntelliJAutoFramework.pages.LoginPage;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;


public class VerifyUserIsAbleToLogin extends BaseTest{

    private static final Logger log = LoggerFactory.getLogger(VerifyUserIsAbleToLogin.class);
    LoginPage loginPage;

    public LoginPage setLoginPageObject(){
        return PageFactory.initElements(driver, LoginPage.class);
    }

    @Test(description = "loginToSwagLabApplication", priority = 2)
    public void loginToSwagLabApplication() throws Exception {
        loginPage = setLoginPageObject();
        loginPage.loginToSwagLabApp("sauceUsername", "saucePassword");
    }

    @Test(description = "loginToLumaApplication", priority = 1)
    public void loginToLumaApplication() throws Exception {
        loginPage = setLoginPageObject();
        loginPage.loginToLumaApp();
        loginPage.verifyProductItemsInfoOnLumaApp();
    }
}
