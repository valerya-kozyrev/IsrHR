package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ManagerOtpuskaPageHelper extends PageBase {

    @FindBy(xpath = "//span[contains(text(),'График работы')]")
    WebElement graficRaboty;
    @FindBy(xpath = "//span[contains(text(),'Календарь отпусков')]")
    WebElement graficOtpuskov;
    @FindBy(xpath = "//div[contains(text(), 'Планирование отпусков и больничных')]")
    WebElement planirovanieTitle;
    @FindBy(xpath = "//div[@class = 'fc fc-media-screen fc-direction-ltr fc-theme-standard']")
    WebElement calendar;
    @FindBy(xpath = "//div[@class='p-col-2']")
    WebElement branch;
    @FindBy(xpath = "//ul[@class='p-dropdown-items']")
    WebElement branchList;
    @FindBy(css = "#pr_id_2")
    WebElement employee;
    @FindBy(xpath = "//button[@class='p-button p-component p-button-rounded p-button-info p-button-icon-only']")
    WebElement clearButton;
    @FindBy(css = "checkFilterFld")//xpath = "//span[@class='p-checkbox-icon p-c pi pi-check']")
    WebElement checkbox;
    @FindBy(xpath = "//span[@class='fc-icon fc-icon-chevron-right']")
    WebElement dateArrow;
    @FindBy(xpath = "//div[@class='fc-daygrid-day-events']")
    WebElement calendarEvent;

    public ManagerOtpuskaPageHelper(WebDriver driver) {
        this.driver = driver;
    }

    public ManagerOtpuskaPageHelper openManagerOtpuskaPage() {
        log4j.startMethod("ManagerOtpuskaPageHelper() - openOtpuskaPage()");
        graficRaboty.click();
        graficOtpuskov.click();
        log4j.endMethod("ManagerOtpuskaPageHelper() - openOtpuskaPage()");
        return this;
    }

    public void waitUntilManagerOtpuskaPageIsLoaded() {
        log4j.startMethod("ManagerOtpuskaPageHelper() - waitUntilOtpuskaPageIsLoaded()");
        log4j.info("wait for 'Планирование графика работы' calendar to be visible");
        waitUntilElementIsVisible(calendar,5);
        log4j.endMethod("ManagerOtpuskaPageHelper() - waitUntilOtpuskaPageIsLoaded()");
    }

    public String getPlanirovanieOtpuskovText(){
        log4j.startMethod("ManagerOtpuskaPageHelper() - getPlanirovanieOtpuskovText()");
        waitUntilElementIsVisible(planirovanieTitle, 30);
        log4j.endMethod("ManagerOtpuskaPageHelper() - getPlanirovanieOtpuskovText()");
        return planirovanieTitle.getText();
    }

    public String getCalendar(String branch, String name, String smena, String date) {
        log4j.startMethod("ManagerOtpuskaPageHelper() - getSmenaByArrow()");
//        getDate();
//        getBranchByArrow(branch);
        getEmployeeName(name);
        clearButton.isDisplayed();
        log4j.endMethod("ManagerOtpuskaPageHelper() - getSmenaByArrow()");
        return getCalendarDayText(date);
    }

    public void getEmployeeName(String name) {
        log4j.startMethod("ManagerOtpuskaPageHelper() - getEmployeeName()");
        employee.click();
        scrollingToTheName(name);
        log4j.endMethod("ManagerOtpuskaPageHelper() - getEmployeeName()");
    }

    public void scrollingToTheName(String name) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement sellerName = driver.findElement(By.xpath("//li[contains(text(),'" + name + "')]"));
        js.executeScript("arguments[0].scrollIntoView();", sellerName);
        waitUntilElementIsClickable(sellerName, 30);
        sellerName.click();
    }

    public boolean clearButtonIsPresent(String name) {
        log4j.startMethod("ManagerOtpuskaPageHelper() - getEmployeeNameByField()");
        getEmployeeName(name);
        clearButton.click();
        log4j.endMethod("ManagerOtpuskaPageHelper() - getEmployeeNameByField()");
        return clearButton.isDisplayed();
    }

    public String getCalendarDayText(String date) {
        WebElement day = driver.findElement(By.xpath("//td[@data-date='"+date+"']"));
        day.click();
        waitUntilElementIsClickable(calendarEvent, 30);
        return calendarEvent.getText();
    }
}
