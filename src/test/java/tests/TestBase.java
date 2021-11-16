package tests;

import com.google.common.io.Files;
import example.SuiteConfiguration;
import example.util.LogLog4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pages.LoginPageHelper;
import ru.stqa.selenium.factory.WebDriverPool;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Base class for TestNG-based test classes
 */
public class TestBase {

    protected static URL gridHubUrl = null;
    protected static String baseUrl;
    protected static Capabilities capabilities;

    public static final String PASSWORD = "123";
    public static final String LOGIN_SELLER = "lara";
    public static final String LOGIN_MANAGER = "tanya";

    public static LogLog4j log4j = new LogLog4j();

    LoginPageHelper loginPage;

    //  protected WebDriver driver;
    protected EventFiringWebDriver driver;

    public class MyListener extends AbstractWebDriverEventListener {
        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            log4j.info("Element has to be found: " + by);
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            log4j.info("Element was found: " + by);
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            log4j.error("Error - " + throwable);
            getScreenshot((TakesScreenshot) driver);
        }
    }

    private void getScreenshot(TakesScreenshot driver) {
        File tmp = driver.getScreenshotAs(OutputType.FILE);
        File screen = new File("screen - " + System.currentTimeMillis() + ".png");
        try {
            Files.copy(tmp, screen);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log4j.info("See screens, " + screen);
    }

    @AfterMethod(alwaysRun = true)
    public void finishTest(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            log4j.error("Test failed ");
            getScreenshot(driver);
        }
    }

    @BeforeSuite(alwaysRun = true)
    public void initTestSuite() throws IOException {
        SuiteConfiguration config = new SuiteConfiguration();
        baseUrl = config.getProperty("site.url");
        if (config.hasProperty("grid.url") && !"".equals(config.getProperty("grid.url"))) {
            gridHubUrl = new URL(config.getProperty("grid.url"));
        }
        capabilities = config.getCapabilities();
    }

    @BeforeMethod(alwaysRun = true)
    public void initWebDriver() {
        driver = new EventFiringWebDriver(WebDriverPool.DEFAULT.getDriver(gridHubUrl, capabilities));
        driver.register(new MyListener());
//    driver = new ChromeDriver();
        driver.get(baseUrl);

        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        loginPage.waitUntilLoginPageIsLoaded();
    }


    //  @AfterSuite(alwaysRun = true)
//  public void tearDown() {
//    WebDriverPool.DEFAULT.dismissAll();
//  }
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
