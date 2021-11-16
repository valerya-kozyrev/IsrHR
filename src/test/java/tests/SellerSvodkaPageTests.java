package tests;

import example.util.DataProviders;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SellerSvodkaPageHelper;
import pages.LoginPageHelper;

public class SellerSvodkaPageTests extends TestBase {
    LoginPageHelper loginPage;
    SellerSvodkaPageHelper sellerSvodkaPage;

    @BeforeMethod
    public void initTest() {
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        sellerSvodkaPage = PageFactory.initElements(driver, SellerSvodkaPageHelper.class);

        log4j.startMethod("sellerSvodkaPage - initTest()");
        loginPage
                .waitUntilLoginPageIsLoaded()
                .login(LOGIN_SELLER, PASSWORD);
        sellerSvodkaPage
                .waitUntilSellerSvodkaPageIsLoaded();
        log4j.endMethod("sellerSvodkaPage - initTest()");
    }

    @Test
    public  void verifyPageTest(){
        log4j.startTestCase("sellerSvodkaPage - verifyApplTest");
        Assert.assertTrue(sellerSvodkaPage.isCorrectPage());
        log4j.endTestCase("sellerSvodkaPage - verifyApplTest");
    }

    @Test
    public void verifySvodkaEmployeeFieldTest() {
        log4j.startTestCase("sellerSvodkaPage - verifySvodkaTest()");
        Assert.assertEquals(sellerSvodkaPage.getSvodkaByNameField("Ноя","2021", "Дорош Лариса"),
                sellerSvodkaPage.getSvodkaText());
        log4j.endTestCase("sellerSvodkaPage - verifySvodkaTest()");
    }

    @Test
    public void verifySvodkaEmployeeArrowTest() {
        log4j.startTestCase("sellerSvodkaPage - verifySvodkaTest()");
        Assert.assertEquals(sellerSvodkaPage.getSvodkaByNameArrow("Ноя","2021", "Дорош Лариса"),
                sellerSvodkaPage.getSvodkaText());
        log4j.endTestCase("sellerSvodkaPage - verifySvodkaTest()");
    }

    @Test
    public void verifyGraficRabotyDropDownList(){
        log4j.startTestCase("sellerSvodkaPage - verifyGraficRabotyDropDownList()");
        sellerSvodkaPage.openGraficRabotyDropDownList();
        Assert.assertEquals(sellerSvodkaPage.getGraficSmenText(), "Календарь смен");
        Assert.assertEquals(sellerSvodkaPage.getGraficOtpuskovText(), "Календарь отпусков");
        log4j.endTestCase("sellerSvodkaPage - verifyGraficRabotyDropDownList()");
    }

}

