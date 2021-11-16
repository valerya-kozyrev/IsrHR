package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ManagerSmenyPageHelper extends PageBase {

    @FindBy(xpath = "//span[contains(text(),'График работы')]")
    WebElement graficRaboty;
    @FindBy(xpath = "//span[contains(text(),'Календарь смен')]")
    WebElement graficSmen;
    @FindBy(xpath = "//div[contains(text(), 'Планирование графика работы')]")
    WebElement planirovanieTitle;
    @FindBy(xpath = "//div[@class = 'fc fc-media-screen fc-direction-ltr fc-theme-standard']")
    WebElement calendar;
    @FindBy(css = "#orgUnitFld")
    WebElement branchField;
    @FindBy(xpath = "//span[@id='orgUnitFld']//span[@class='pi pi-chevron-down p-c']")
    WebElement branchArrow;
    @FindBy(css = "#employeeFld")
    WebElement employeeField;
    @FindBy(xpath = "//span[@id='employeeFld']//span[@class='pi pi-chevron-down p-c']")
    WebElement employeeArrow;
    @FindBy(xpath = "//span[@class='p-dropdown-label p-inputtext p-dropdown-label-empty']")//css = "#shiftFld")//xpath = "//label[contains(text(),'Смена')]")
    WebElement smenaField;
    @FindBy(xpath = "//div[@class='p-dropdown-trigger']")
    WebElement smenaArrow;
    @FindBy(xpath = "//span[@class='pi pi-times p-c']")
    WebElement clearButton;
    @FindBy(xpath = "//button[@class='fc-next-button fc-button fc-button-primary']")
    WebElement dateArrowForward;
    @FindBy(xpath = "//div[@class='fc-daygrid-day-events']")
    WebElement calendarEvent;
    @FindBy(css = "#selectedDatesFld")
    WebElement daty;
    @FindBy(xpath = "//div[@class='p-datepicker-group']")
    WebElement popUpCalendar;
    @FindBy(xpath = "//div[@class='p-autocomplete-panel p-component p-connected-overlay-enter-done']")//css = "#orgUnitFld_list")
    WebElement branchList;

    public ManagerSmenyPageHelper(WebDriver driver) {
        this.driver = driver;
    }

    public ManagerSmenyPageHelper openManagerSmenyPage() {
        log4j.startMethod("ManagerSmenyPageHelper() - openPage()");
        graficRaboty.click();
        graficSmen.click();
        log4j.endMethod("ManagerSmenyPageHelper() - openPage()");
        return this;
    }

    public void waitUntilManagerSmenyPageIsLoaded() {
        log4j.startMethod("SellerSmenyPageHelper() - waitUntilManagerSmenyPageIsLoaded()");
        log4j.info("wait for 'Планирование графика работы' calendar to be visible");
        waitUntilElementIsVisible(calendar, 5);
        log4j.endMethod("SellerSmenyPageHelper() - waitUntilManagerSmenyPageIsLoaded()");
    }

    public boolean isCorrectPage() {
        log4j.startMethod("ManagerSmenyPageHelper() - isCorrectPage");
        log4j.endMethod("ManagerSmenyPageHelper() - isCorrectPage");
        return getPlanirovanieSmenText().equals("Планирование графика работы");
    }

    public String getPlanirovanieSmenText() {
        log4j.startMethod("ManagerSmenyPageHelper() - getPlanirovanieSmenText()");
        waitUntilElementIsVisible(planirovanieTitle, 30);
        log4j.endMethod("ManagerSmenyPageHelper() - getPlanirovanieSmenText()");
        return planirovanieTitle.getText();
    }

    public String getSmenaByField(String branch, String name, String smena, String date){
        log4j.startMethod("ManagerSmenyPageHelper() - getSmenaByField()");
        getDate();
        getBranchByField(branch);
        getEmployeeNameByField(name);
        getSmenyByField(smena);
        log4j.endMethod("ManagerSmenyPageHelper() - getSmenaByField()");
        return getCalendarDayText(date);
    }

    public String getSmenaByArrow(String branch, String name, String smena, String date) {
        log4j.startMethod("ManagerSmenyPageHelper() - getSmenaByArrow()");
        getDate();
        getBranchByArrow(branch);
        getEmployeeNameByArrow(name);
        getSmenyByArrow(smena);
        log4j.endMethod("ManagerSmenyPageHelper() - getSmenaByArrow()");
        return getCalendarDayText(date);
    }

    public void getDate(){
        waitUntilElementIsClickable(dateArrowForward, 30);
        dateArrowForward.click();
    }

    public void getDaty(){
        daty.click();
        waitUntilElementIsVisible(popUpCalendar, 30);
    }

    public void getBranchByArrow(String name) {
        log4j.startMethod("ManagerSmenyPageHelper() - gettBranchByArrow()");
        branchArrow.click();
//        waitUntilElementIsClickable(branchList, 30);
        scrollingToTheBranch(name);
        log4j.endMethod("ManagerSmenyPageHelper() - gettBranchByArrow()");
    }

    public void scrollingToTheBranch(String name) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement branch = driver.findElement(By.xpath("//li[contains(text(),'" + name + "')]"));
        js.executeScript("arguments[0].scrollIntoView();", branch);
        waitUntilElementIsClickable(branch, 30);
        branch.click();
    }

    public void getBranchByField(String name) {
        log4j.startMethod("ManagerSmenyPageHelper() - getBranchByField()");
        branchField.click();
        branchField.clear();
        editField(employeeField, name);
        log4j.endMethod("ManagerSmenyPageHelper() - getBranchByField()");
    }

    public void getEmployeeNameByArrow(String name) {
        log4j.startMethod("ManagerSmenyPageHelper() - getEmployeeNameByArrow()");
        employeeArrow.click();
        scrollingToTheName(name);
        log4j.endMethod("ManagerSmenyPageHelper() - getEmployeeNameByArrow()");
    }

    public void scrollingToTheName(String name) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement sellerName = driver.findElement(By.xpath("//li[contains(text(),'" + name + "')]"));
        js.executeScript("arguments[0].scrollIntoView();", sellerName);
        waitUntilElementIsClickable(sellerName, 30);
        sellerName.click();
    }

    public void getEmployeeNameByField(String name) {
        log4j.startMethod("ManagerSmenyPageHelper() - getEmployeeNameByField()");
        employeeField.click();
        employeeField.clear();
        editField(employeeField, name);
        log4j.endMethod("ManagerSmenyPageHelper() - getEmployeeNameByField()");
    }

    public void getSmenyByArrow(String name) {
        log4j.startMethod("ManagerSmenyPageHelper() - getSmenyByArrow()");
        smenaArrow.click();
        scrollingToTheSmeny(name);
        log4j.endMethod("ManagerSmenyPageHelper() - getSmenyByArrow()");
    }

    public void scrollingToTheSmeny(String name) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement smena = driver.findElement(By.xpath("//li[contains(text(),'" + name + "')]"));
        js.executeScript("arguments[0].scrollIntoView();", smena);
        waitUntilElementIsClickable(smena, 30);
        smena.click();
    }

    public void getSmenyByField(String name) {
        log4j.startMethod("ManagerSmenyPageHelper() - getSmenyByField()");
        smenaField.click();
//        smenaField.clear();
        editField(smenaField, name);
        log4j.endMethod("ManagerSmenyPageHelper() - getSmenyByField()");
    }

    public String getCalendarDayText(String date) {
        WebElement day = driver.findElement(By.xpath("//td[@data-date='"+date+"']"));
        day.click();
        waitUntilElementIsClickable(calendarEvent, 30);
        return calendarEvent.getText();
    }

    public void scrollingTest(By locator, String name) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement line = driver.findElement(locator);//By.xpath("//li[contains(text(),'" + name + "')]"));
        js.executeScript("arguments[0].scrollIntoView();", line);
        waitUntilElementIsClickable(line, 30);
        line.click();
    }
}
