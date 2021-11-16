package tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPageHelper;
import pages.ManagerSmenyPageHelper;
import pages.ManagerSvodkaPageHelper;

public class ManagerSmenyPageTests extends TestBase {

    LoginPageHelper loginPage;
    ManagerSvodkaPageHelper managerSvodkaPage;
    ManagerSmenyPageHelper managerSmenyPage;

    @BeforeMethod
    public void initTest() {
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        managerSvodkaPage = PageFactory.initElements(driver, ManagerSvodkaPageHelper.class);
        managerSmenyPage = PageFactory.initElements(driver, ManagerSmenyPageHelper.class);

        log4j.startMethod("managerSmenyPage - initTest()");
        loginPage
                .waitUntilLoginPageIsLoaded()
                .login(LOGIN_MANAGER, PASSWORD);
        managerSvodkaPage
                .waitUntilManagerSvodkaPageIsLoaded();
        managerSmenyPage
                .openManagerSmenyPage()
                .waitUntilManagerSmenyPageIsLoaded();
        log4j.endMethod("managerSmenyPage - initTest()");
    }

    @Test
    public void planirovanieSmenExistsTest() {
        log4j.startTestCase("managerSmenyPage - planirovanieSmenExistsTest()");
        Assert.assertTrue(managerSmenyPage.isCorrectPage());
        log4j.endTestCase("managerSmenyPage - planirovanieSmenExistsTest()");
    }


//    @Test
//    public void verifySmenyByFieldsTest() {
//        log4j.startTestCase("managerSmenyPage - verifySmenyByFieldsTest()");
//        Assert.assertTrue(managerSmenyPage.getSmenaByField("Герцлия", "Дорош Лариса", "2", "2021-11-11")
//                .contains("09 - 18"));
//        log4j.endTestCase("managerSmenyPage - verifySmenyByFieldsTest()");
//    }

//    @Test
//    public void verifySmenyByArrowsTest() {
//        log4j.startTestCase("managerSmenyPage - verifySmenyByArrowsTest()");
//        Assert.assertTrue(managerSmenyPage.getSmenaByArrow("Герцлия", "Дорош Лариса", "2", "2021-11-11")
//                .contains("09 - 18"));
//        log4j.endTestCase("managerSmenyPage - verifySmenyByArrowsTest()");
//    }

}


