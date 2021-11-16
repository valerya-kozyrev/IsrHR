package tests;

import example.util.DataProviders;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SellerSvodkaPageHelper;
import pages.ManagerSvodkaPageHelper;
import pages.LoginPageHelper;


public class LoginTests extends TestBase {
    LoginPageHelper loginPage;
    ManagerSvodkaPageHelper managerSvodkaPage;
    SellerSvodkaPageHelper sellerSvodkaPage;

    @BeforeMethod(alwaysRun = true)
    public void initTest() {
        loginPage = PageFactory.initElements(driver,LoginPageHelper.class);
        sellerSvodkaPage = PageFactory.initElements(driver,SellerSvodkaPageHelper.class);
        managerSvodkaPage = PageFactory.initElements(driver,ManagerSvodkaPageHelper.class);

        log4j.startMethod("loginPage - initTest()");
        loginPage
                .waitUntilLoginPageIsLoaded();
        log4j.endMethod("loginPage - initTest()");
    }


    //negative
    @Test
    public void loginNegativeTest() {
        log4j.startTestCase("loginNegativeTest()");
        loginPage.login("email","password");
        Assert.assertEquals(loginPage.getErrorMessage(),
                "ДОСТУП ЗАПРЕЩЕН!");
        log4j.endTestCase("loginNegativeTest()");
    }

    //positive
    @Test
    public void loginManagerPositiveTest() {
        log4j.startTestCase("loginPage - loginManagerPositiveTest()");
        loginPage.login(LOGIN_MANAGER, PASSWORD);
        managerSvodkaPage.waitUntilManagerSvodkaPageIsLoaded();
        Assert.assertEquals(managerSvodkaPage.getOt4etButtonName(),
                "Отчет", "Name of the button is not 'Отчет'");
        log4j.endTestCase("loginPage - loginManagerPositiveTest()");
    }

    @Test
    public void loginSellerPositiveTest() {
        log4j.startTestCase("loginPage - loginSellerPositiveTest()");
        loginPage.login(LOGIN_SELLER, PASSWORD);
        sellerSvodkaPage.waitUntilSellerSvodkaPageIsLoaded();
        Assert.assertEquals(sellerSvodkaPage.getSvodkaButtonName(),
                "Сводка", "Name of the button is not 'Сводка'");
        log4j.endTestCase("loginPage - loginSellerPositiveTest()");
    }

}