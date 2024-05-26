package newIntelliJAutoFramework.core;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.List;
import java.util.Properties;


public class BaseTest {

    protected static WebDriver driver;

    @BeforeMethod
    public void startChromeBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    public void waitForElementVisible(WebElement ele, int time) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public void waitForElementToBecomeInVisible(WebElement ele, int time) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.invisibilityOf(ele));
    }

    public void clickOnElement(WebElement ele) {
        waitForElementVisible(ele, 30);
        try {
            ele.click();
        } catch (Exception ex) {
            System.out.println("click through javascript");
            JavascriptExecutor javaexe = (JavascriptExecutor) driver;
            javaexe.executeScript("arguments[0].click();", ele);
        }
    }

    public void inputValue(WebElement ele, String value) {
        waitForElementVisible(ele, 30);
        ele.clear();
        ele.sendKeys(value);
    }

    public String readValueFromConfigFile(String key) throws Exception {
        String value;
        try {
            Properties prop = new Properties();
            File f = new File(System.getProperty("user.dir") + "//config.properties");
            if (f.exists()) {
                prop.load(new FileInputStream(f));
                value = prop.getProperty(key);
            } else {
                throw new Exception("File not found");
            }
        } catch (Exception ex) {
            System.out.println(
                    "Failed to read from application.properties file.");
            throw ex;
        }
        if (value == null) {
            throw new Exception("Key not found in properties file");
        }
        return value;
    }

    public void normalWait(int waitTimeInMilliSeconds){
        try {
            Thread.sleep(waitTimeInMilliSeconds);
        } catch (Exception e) {
        }
    }

    public void mouseHoverOnElement(WebElement ele){
        Actions action =  new Actions(driver);
        action.moveToElement(ele).build().perform();
    }

    @AfterMethod
    public void tearDown(){
        //driver.quit();
    }

}
