package tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SellerSvodkaPageHelper;
import pages.LoginPageHelper;
import pages.SellerSmenyPageHelper;

public class SellerSmenyPageTests extends TestBase {
    LoginPageHelper loginPage;
    SellerSvodkaPageHelper sellerSvodkaPage;
    SellerSmenyPageHelper sellerSmenyPage;

    @BeforeMethod
    public void initTest() {
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        sellerSvodkaPage = PageFactory.initElements(driver, SellerSvodkaPageHelper.class);
        sellerSmenyPage = PageFactory.initElements(driver, SellerSmenyPageHelper.class);

        log4j.startMethod("SellerSmenyPage - initTest()");
        loginPage
                .waitUntilLoginPageIsLoaded()
                .login(LOGIN_SELLER, PASSWORD);
        sellerSvodkaPage
                .waitUntilSellerSvodkaPageIsLoaded();
        sellerSmenyPage
                .openSellerSmenyPage()
                .waitUntilSellerSmenyPageIsLoaded();
        log4j.endMethod("SellerSmenyPage - initTest()");
    }

    @Test
    public void planirovanieSmenExistsTest() {
        log4j.startTestCase("SellerSmenyPage - planirovanieSmenExistsTest()");
        Assert.assertTrue(sellerSmenyPage.isCorrectPage());
        log4j.endTestCase("SellerSmenyPage - planirovanieSmenExistsTest()");
    }

//    @Test
//    public void verifySmenyByFieldsTest() {
//        log4j.startTestCase("sellerSmenyPage - verifySmenyByFieldsTest()");
//        Assert.assertTrue(sellerSmenyPage.getSmenaByField("Герцлия", "Дорош Лариса", "2", "2021-11-11")
//                .contains("09 - 18"));
//        log4j.endTestCase("sellerSmenyPage - verifySmenyByFieldsTest()");
//    }

//    @Test
//    public void verifySmenyByArrowsTest() {
//        log4j.startTestCase("sellerSmenyPage - verifySSmenyByArrowsTest()");
//        Assert.assertTrue(sellerSmenyPage.getSmenaByArrow("Герцлия", "Дорош Лариса", "2", "2021-11-11")
//                .contains("09 - 18"));
//        log4j.endTestCase("sellerSmenyPage - verifySmenyByArrowsTest()");
//    }

}
