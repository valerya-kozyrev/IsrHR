package tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPageHelper;
import pages.ManagerSvodkaPageHelper;

public class ManagerSvodkaPageTests extends TestBase{
    LoginPageHelper loginPage;
    ManagerSvodkaPageHelper managerSvodkaPage;

    @BeforeMethod
    public void initTest() {
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        managerSvodkaPage = PageFactory.initElements(driver, ManagerSvodkaPageHelper.class);

        log4j.startMethod("managerSvodkaPage - initTest()");
        loginPage
                .waitUntilLoginPageIsLoaded()
                .login(LOGIN_MANAGER, PASSWORD);
        managerSvodkaPage
                .waitUntilManagerSvodkaPageIsLoaded();
        log4j.endMethod("managerSvodkaPage - initTest()");
    }

    @Test
    public  void verifyManagerSvodkaPageTest(){
        log4j.startTestCase("managerSvodkaPage - verifyManagerSvodkaPageTest()");
        Assert.assertTrue(managerSvodkaPage.isCorrectPage());
        Assert.assertTrue(managerSvodkaPage.getOt4etButtonName().equals("Отчет"));
        log4j.endTestCase("managerSvodkaPage - verifyManagerSvodkaPageTest()");
    }

    @Test
    public void verifySvodkaByEmployeeFieldTest() {
        log4j.startTestCase("managerSvodkaPage - verifySvodkaByEmployeeFieldTest()");
        Assert.assertEquals(managerSvodkaPage.getSvodkaByNameField("Ноя","2021", "Дорош Лариса"),
                managerSvodkaPage.getSvodkaText());
        log4j.endTestCase("managerSvodkaPage - verifySvodkaByEmployeeFieldTest()");
    }

    @Test
    public void verifySvodkaByEmployeeArrowTest() {
        log4j.startTestCase("managerSvodkaPage - verifySvodkaByEmployeeArrowTest()");
        Assert.assertEquals(managerSvodkaPage.getSvodkaByNameArrow("Ноя","2021", "Дорош Лариса"),
                managerSvodkaPage.getSvodkaText());
        log4j.endTestCase("managerSvodkaPage - verifySvodkaByEmployeeArrowTest()");
    }

    @Test
    public void verifyGraficRabotyDropDownList(){
        log4j.startTestCase("managerSvodkaPage - verifyGraficRabotyDropDownList()");
        managerSvodkaPage.openGraficRabotyDropDownList();
        Assert.assertEquals(managerSvodkaPage.getGraficSmenText(), "Календарь смен");
        Assert.assertEquals(managerSvodkaPage.getGraficOtpuskovText(), "Календарь отпусков");
        log4j.endTestCase("managerSvodkaPage - verifyGraficRabotyDropDownList()");
    }
}
