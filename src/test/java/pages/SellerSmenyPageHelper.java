package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class SellerSmenyPageHelper extends PageBase {

    @FindBy(xpath = "//span[contains(text(),'График работы')]")
    WebElement graficRaboty;
    @FindBy(xpath = "//span[contains(text(),'Календарь смен')]")
    WebElement graficSmen;
    @FindBy(xpath = "//div[contains(text(), 'Планирование графика работы')]")
    WebElement planirovanieTitle;
    @FindBy(xpath = "//div[@class = 'fc fc-media-screen fc-direction-ltr fc-theme-standard']")
    WebElement calendar;
    @FindBy(xpath = "//span[@id='orgUnitFld']/input[@type='text']")//css = "#orgUnitFld")
    WebElement branchField;
    @FindBy(xpath = "//span[@id='orgUnitFld']/input[@type='text']")
    WebElement branchText;
    @FindBy(xpath = "//span[@id='orgUnitFld']//span[@class='pi pi-chevron-down p-c']")
    WebElement branchArrow;
    @FindBy(xpath = "//input[@role='searchbox']")//xpath = "//span[@id='employeeFld']/input[@type='text']")//css = "#employeeFld")
    WebElement employeeField;
    @FindBy(xpath = "//button[@class='p-button p-component p-autocomplete-dropdown p-button-icon-only']")//xpath = "//span[@id='employeeFld']//span[@class='pi pi-chevron-down p-c']")
    WebElement employeeArrow;
    @FindBy(css = "#shiftFld")
    WebElement smenaField;
    @FindBy(xpath = "//span[@class='p-dropdown-label p-inputtext']")
    WebElement smenaFieldWriting;
    @FindBy(xpath = "//div[@class='p-dropdown-trigger']")//div[@id='shiftFld']//span[@class='p-dropdown-trigger-icon pi pi-chevron-down p-clickable']")
    WebElement smenaArrow;
    @FindBy(xpath = "//span[@class='pi pi-times p-c']")
    WebElement clearButton;
    @FindBy(xpath = "//button[@class='fc-next-button fc-button fc-button-primary']")
    WebElement dateArrowForward;
    @FindBy(xpath = "//div[@class='fc-daygrid-day-events']")
    WebElement calendarEvent;

    public SellerSmenyPageHelper(WebDriver driver) {
        this.driver = driver;
    }

    public SellerSmenyPageHelper openSellerSmenyPage() {
        log4j.startMethod("SellerSmenyPageHelper() - openPage()");
        graficRaboty.click();
        graficSmen.click();
        log4j.endMethod("SellerSmenyPageHelper() - openPage()");
        return this;
    }

    public void waitUntilSellerSmenyPageIsLoaded() {
        log4j.startMethod("SellerSmenyPageHelper() - waitUntilSmenyPageIsLoaded()");
        log4j.info("wait for 'Планирование графика работы' calendar to be visible");
        waitUntilElementIsVisible(calendar, 5);
        log4j.endMethod("SellerSmenyPageHelper() - waitUntilSmenyPageIsLoaded()");
    }

    public boolean isCorrectPage() {
        log4j.startMethod("SellerSmenyPageHelper() - isCorrectPage");
        log4j.endMethod("SellerSmenyPageHelper() - isCorrectPage");
        return getPlanirovanieSmenText().equals("Планирование графика работы");
    }

    public String getPlanirovanieSmenText() {
        log4j.startMethod("SellerSmenyPageHelper() - getPlanirovanieSmenText()");
        waitUntilElementIsVisible(planirovanieTitle, 30);
        log4j.endMethod("SellerSmenyPageHelper() - getPlanirovanieSmenText()");
        return planirovanieTitle.getText();
    }

    public String getSmenaByField(String branch, String name, String smena, String date){
        log4j.startMethod("SellerSmenyPageHelper() - getSmenaByField()");
        getDate();
        getBranchByField(branch);
        getEmployeeNameByField(name);
        getSmenyByField(smena);
        log4j.endMethod("SellerSmenyPageHelper() - getSmenaByField()");
        return getCalendarDayText(date);
    }

    public String getSmenaByArrow(String branch, String name, String smena, String date) {
        log4j.startMethod("SellerSmenyPageHelper() - getSmenaByArrow()");
        getDate();
        getBranchByArrow(branch);
        getEmployeeNameByArrow(name);
        getSmenyByArrow(smena);
        log4j.endMethod("SellerSmenyPageHelper() - getSmenaByArrow()");
        return getCalendarDayText(date);
    }

    public void getDate(){
        log4j.startMethod("SellerSmenyPageHelper() - getDate()");
        waitUntilElementIsClickable(dateArrowForward, 30);
        dateArrowForward.click();
        log4j.endMethod("SellerSmenyPageHelper() - getDate()");
    }
    public void getBranchByArrow(String name) {
        log4j.startMethod("SellerSmenyPageHelper() - gettBranchByArrow()");
        branchArrow.click();
        scrollingToTheBranch(name);
        log4j.endMethod("SellerSmenyPageHelper() - gettBranchByArrow()");
    }

    public void scrollingToTheBranch(String name) {
        log4j.startMethod("SellerSmenyPageHelper() - scrollingToTheBranch()");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement branch = driver.findElement(By.xpath("//li[contains(text(),'" + name + "')]"));//div[@class='p-autocomplete-panel p-component p-connected-overlay-enter-done']" +
                //"//li[contains(text(),'" + name + "')]"));
        js.executeScript("arguments[0].scrollIntoView();", branch);
        waitUntilElementIsClickable(branch, 30);
        branch.click();
        log4j.endMethod("SellerSmenyPageHelper() - scrollingToTheBranch()");
    }

    public void getBranchByField(String name) {
        log4j.startMethod("SellerSmenyPageHelper() - getBranchByField()");
        branchField.click();
//        branchField.clear();
        editField(branchField, name);
//        waitUntilElementTextIs(branchText, name, 30);
        log4j.endMethod("SellerSmenyPageHelper() - getBranchByField()");
    }

    public void getEmployeeNameByArrow(String name) {
        log4j.startMethod("SellerSmenyPageHelper() - getEmployeeNameByArrow()");
        employeeArrow.click();
        scrollingToTheName(name);
        log4j.endMethod("SellerSmenyPageHelper() - getEmployeeNameByArrow()");
    }

    public void scrollingToTheName(String name) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement sellerName = driver.findElement(By.xpath("//li[contains(text(),'" + name + "')]"));
        js.executeScript("arguments[0].scrollIntoView();", sellerName);
        waitUntilElementIsClickable(sellerName, 30);
        sellerName.click();
    }

    public void getEmployeeNameByField(String name) {
        log4j.startMethod("SellerSmenyPageHelper() - getEmployeeNameByField()");
        employeeField.click();
//        employeeField.clear();
        editField(employeeField, name);
//        waitUntilElementTextIs(employeeField, name, 30);
        log4j.endMethod("SellerSmenyPageHelper() - getEmployeeNameByField()");
    }

    public void getSmenyByArrow(String name) {
        log4j.startMethod("SellerSmenyPageHelper() - getSmenyByArrow()");
        smenaArrow.click();
        scrollingToTheSmeny(name);
        log4j.endMethod("SellerSmenyPageHelper() - getSmenyByArrow()");
    }

    public void scrollingToTheSmeny(String name) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement smena = driver.findElement(By.xpath("//li[contains(text(),'" + name + "')]"));
        js.executeScript("arguments[0].scrollIntoView();", smena);
        waitUntilElementIsClickable(smena, 30);
        smena.click();
    }

    public void getSmenyByField(String name) {
        log4j.startMethod("SellerSmenyPageHelper() - getSmenyByField()");
//        smenaField.getLocation();
//        smenaField.click();
//        smenaField.clear();
//        smenaFieldWriting.click();
        editField(smenaField, name);
        log4j.endMethod("SellerSmenyPageHelper() - getSmenyByField()");
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
