package tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPageHelper;
import pages.ManagerOtpuskaPageHelper;
import pages.ManagerSvodkaPageHelper;

public class ManagerOtpuskaPageTests extends TestBase{
    LoginPageHelper loginPage;
    ManagerSvodkaPageHelper managerSvodkaPage;
    ManagerOtpuskaPageHelper managerOtpuskaPage;

    @BeforeMethod
    public void initTest() {
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        managerSvodkaPage = PageFactory.initElements(driver, ManagerSvodkaPageHelper.class);
        managerOtpuskaPage = PageFactory.initElements(driver, ManagerOtpuskaPageHelper.class);

        log4j.startMethod("ManagerOtpuskaPage - initTest()");
        loginPage
                .waitUntilLoginPageIsLoaded()
                .login(LOGIN_MANAGER, PASSWORD);
        managerSvodkaPage
                .waitUntilManagerSvodkaPageIsLoaded();
        managerOtpuskaPage
                .openManagerOtpuskaPage()
                .waitUntilManagerOtpuskaPageIsLoaded();
        log4j.endMethod("ManagerOtpuskaPage - initTest()");
    }

    @Test
    public void planirovanieOtpuskovExistsTest() {
        log4j.startTestCase("ManagerOtpuskaPage - planirovanieOtpuskovExistsTest()");
        Assert.assertEquals(managerOtpuskaPage.getPlanirovanieOtpuskovText(), "Планирование отпусков и больничных");
        log4j.endTestCase("ManagerOtpuskaPage - planirovanieOtpuskovExistsTest()");
    }

//    @Test
//    public void verifyClearButton() {
//        log4j.startTestCase("managerOtpuskaPage - verifyClearButton()");
//        Assert.assertTrue(managerOtpuskaPage.clearButtonIsPresent("Дорош Лариса"));
//        log4j.endTestCase("managerOtpuskaPage - verifyClearButton()");
//    }

//    @Test
//    public boolean clearButtonIsPresent(String name) {
//        log4j.startMethod("managerOtpuskaPage - getEmployeeNameByField()");
//        managerOtpuskaPage.getEmployeeName(name);
//        log4j.endMethod("managerOtpuskaPage - getEmployeeNameByField()");
//        return managerOtpuskaPage.clearButtonIsPresent("Дорош Лариса");
//    }

//    @Test
//    public void verifySmenyByFieldsTest() {
//        log4j.startTestCase("managerOtpuskaPage - verifySmenyByFieldsTest()");
//        Assert.assertTrue(managerOtpuskaPage.getCalendar("Герцлия", "Дорош Лариса", "2", "2021-11-11")
//                .contains("09 - 18"));
//        log4j.endTestCase("managerOtpuskaPage - verifySmenyByFieldsTest()");
//    }

}
