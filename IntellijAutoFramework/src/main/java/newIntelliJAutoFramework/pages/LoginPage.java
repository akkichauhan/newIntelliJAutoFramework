package newIntelliJAutoFramework.pages;

import newIntelliJAutoFramework.core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class LoginPage extends BaseTest {

    protected WebDriver driver;

    @FindBy(name = "user-name")
    WebElement swagUsername;

    @FindBy(name = "password")
    WebElement swagPassword;

    @FindBy(css = "input[value='Login']")
    WebElement swagLoginButton;

    @FindBy(css = "div[class*='products-list grid'] div[class='product-item-info']")
    List<WebElement> productItemsInfo;

    @FindBy(css = "button[title='Add to Cart']")
    WebElement addToCartButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void loginToSwagLabApp(String username, String password) throws Exception {
        String url = readValueFromConfigFile("sauceLabURL");
        String user = readValueFromConfigFile(username);
        String pass = readValueFromConfigFile(password);
        driver.navigate().to(url);
        inputValue(swagUsername, user);
        normalWait(2000);
        inputValue(swagPassword, pass);
        normalWait(2000);
        clickOnElement(swagLoginButton);
        normalWait(2500);
    }

    public void loginToLumaApp() throws Exception {
        String url = readValueFromConfigFile("lumaECommerceApp");
        driver.navigate().to(url);
        normalWait(2500);
    }

    public void verifyProductItemsInfoOnLumaApp() {
        for (WebElement ele : this.productItemsInfo) {
            normalWait(250);
            mouseHoverOnElement(ele);
            normalWait(350);
            Assert.assertTrue(this.addToCartButton.isDisplayed(), "Add to cart button is not displaying. Please try again");
        }
    }
}
