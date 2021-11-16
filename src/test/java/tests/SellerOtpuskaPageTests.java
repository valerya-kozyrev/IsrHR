package tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SellerSvodkaPageHelper;
import pages.LoginPageHelper;
import pages.SellerOtpuskaPageHelper;

public class SellerOtpuskaPageTests extends TestBase {
    LoginPageHelper loginPage;
    SellerSvodkaPageHelper sellerSvodkaPage;
    SellerOtpuskaPageHelper sellerOtpuskaPage;

    @BeforeMethod
    public void initTest() {
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        sellerSvodkaPage = PageFactory.initElements(driver, SellerSvodkaPageHelper.class);
        sellerOtpuskaPage = PageFactory.initElements(driver, SellerOtpuskaPageHelper.class);

        log4j.startMethod("SellerOtpuskaPage - initTest()");
        loginPage
                .waitUntilLoginPageIsLoaded()
                .login(LOGIN_SELLER, PASSWORD);
        sellerSvodkaPage
                .waitUntilSellerSvodkaPageIsLoaded();
        sellerOtpuskaPage
                .openOtpuskaPage()
                .waitUntilOtpuskaPageIsLoaded();
        log4j.endMethod("SellerOtpuskaPage - initTest()");
    }

    @Test
    public void planirovanieOtpuskovExistsTest() {
        log4j.startTestCase("SellerOtpuskaPage - planirovanieOtpuskovExistsTest()");
        Assert.assertEquals(sellerOtpuskaPage.getPlanirovanieOtpuskovText(), "Планирование отпусков и больничных");
        log4j.endTestCase("SellerOtpuskaPage - planirovanieOtpuskovExistsTest()");
    }

    @Test
    public void verifyClearButton() {
        log4j.startTestCase("sellerOtpuskaPage - verifyClearButton()");
        Assert.assertTrue(sellerOtpuskaPage.getCalendar("Дорош Лариса"));
        log4j.endTestCase("sellerOtpuskaPage - verifyClearButton()");
    }

//    @Test
//    public void verifyClearButton() {
//        log4j.startTestCase("sellerOtpuskaPage - verifyClearButton()");
//        Assert.assertTrue(sellerOtpuskaPage.clearButtonIsPresent("Дорош Лариса"));
//        log4j.endTestCase("sellerOtpuskaPage - verifyClearButton()");
//    }

//    @Test
//    public boolean clearButtonIsPresent(String name) {
//        log4j.startMethod("SellerOtpuskaPageHelper() - getEmployeeNameByField()");
//        sellerOtpuskaPage.getEmployeeName(name);
//        log4j.endMethod("SellerOtpuskaPageHelper() - getEmployeeNameByField()");
//        return sellerOtpuskaPage.clearButtonIsPresent("Дорош Лариса");
//    }

//    @Test
//    public void verifySmenyByFieldsTest() {
//        log4j.startTestCase("sellerOtpuskaPage - verifySmenyByFieldsTest()");
//        Assert.assertTrue(sellerOtpuskaPage.getCalendar("Герцлия", "Дорош Лариса", "2", "2021-11-11")
//                .contains("09 - 18"));
//        log4j.endTestCase("sellerOtpuskaPage - verifySmenyByFieldsTest()");
//    }

}
