package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManagerSvodkaPageHelper extends PageBase {

    @FindBy(xpath = "//span[contains(text(),'Отчет')]")
    WebElement ot4etIcon;
    @FindBy(xpath = "//div[contains(text(),'Татьяна')]")
    WebElement userName;
    @FindBy(xpath = "//span[contains(text(),'Сводка')]")
    WebElement svodkaIcon;
    @FindBy(xpath = "//input[@role='searchbox']")//css = "#chooseEmployeeFld")WebElement employeeField;
    WebElement employeeField;
    @FindBy(xpath = "//button[@class='p-button p-component p-autocomplete-dropdown p-button-icon-only']")
    WebElement employeeArrow;
    @FindBy(css = "#pr_id_1")
    WebElement dateField;
    @FindBy(xpath = "//div[@class ='p-monthpicker']")
    WebElement monthField;
    @FindBy(xpath = "//select[@class='p-datepicker-year']")
    WebElement yearArrow;
    @FindBy(xpath = "//div[@class ='p-col-5']")
    WebElement svodkaText;
    @FindBy(xpath = "//span[contains(text(),'График работы')]")
    WebElement graficRaboty;
    @FindBy(xpath = "//span[contains(text(),'Календарь смен')]")
    WebElement graficSmen;
    @FindBy(xpath = "//span[contains(text(),'Календарь отпусков')]")
    WebElement graficOtpuskov;

    public ManagerSvodkaPageHelper(WebDriver driver){
        this.driver = driver;
    }

    public ManagerSvodkaPageHelper waitUntilManagerSvodkaPageIsLoaded(){
        log4j.startMethod("ManagerSvodkaPageHelper() - waitUntilManagerSvodkaPageIsLoaded()");
        log4j.info("wait until 'Отчет' button is clickable and click it");
        waitUntilElementIsClickable(ot4etIcon, 30);
        log4j.endMethod("ManagerSvodkaPageHelper() - waitUntilManagerSvodkaPageIsLoaded()");
        return this;
    }

    public String getOt4etButtonName(){
        log4j.startMethod("ManagerSvodkaPageHelper() - getOt4etButtonName()");
        log4j.endMethod("ManagerSvodkaPageHelper() - getOt4etButtonName()");
        return ot4etIcon.getText();
    }

    public boolean isCorrectPage() {
        log4j.startMethod("ManagerSvodkaPageHelper() - isCorrectPage");
        log4j.endMethod("ManagerSvodkaPageHelper() - isCorrectPage");
        return getUserName().equals("Татьяна");
    }

    public String getUserName(){
        log4j.startMethod("ManagerSvodkaPageHelper() - getUserName()");
        log4j.endMethod("ManagerSvodkaPageHelper() - getUserName()");
        return userName.getText();
    }

    public String getSvodkaButtonName(){
        log4j.startMethod("ManagerSvodkaPageHelper() - getSvodkaButtonName()");
        log4j.endMethod("ManagerSvodkaPageHelper() - getSvodkaButtonName()");
        return svodkaIcon.getText();
    }

    public String getSvodkaByNameField(String month, String year, String name){
        log4j.startMethod("ManagerSvodkaPageHelper() - getSvodka()");
        getDate(month, year);
        getEmployeeNameByField(name);
        log4j.endMethod("ManagerSvodkaPageHelper() - getSvodka()");
        return getSvodkaText();
    }
    public String getSvodkaByNameArrow(String month, String year, String name) {
        log4j.startMethod("ManagerSvodkaPageHelper() - getSvodkaByNameArrow()");
        getDate(month, year);
        getEmployeeNameByArrow(name);
        log4j.endMethod("ManagerSvodkaPageHelper() - getSvodkaByNameArrow()");
        return getSvodkaText();
    }

    public void getDate(String month, String year) {
        log4j.startMethod("ManagerSvodkaPageHelper() - getDate()");
        log4j.info("wait until dateField is clickable");
        waitUntilElementIsClickable(dateField,10);
        dateField.click();
        yearArrow.click();
        getYear(year);
        waitUntilElementIsClickable(monthField,10);
        getMonth(month);
        log4j.endMethod("ManagerSvodkaPageHelper() - getDate()");
    }

    public void getMonth(String month) {
        log4j.startMethod("ManagerSvodkaPageHelper() - getMonth()");
        WebElement m = driver.findElement(By.xpath("//span[contains(text(),'"+month+"')]"));
        m.click();
        log4j.endMethod("ManagerSvodkaPageHelper() - getMonth()");
    }

    public void getYear(String year) {
        log4j.startMethod("ManagerSvodkaPageHelper() - getYear()");
        WebElement y = driver.findElement(By.xpath("//option[@value='"+year+"']"));
        y.click();
        log4j.endMethod("ManagerSvodkaPageHelper() - getYear()");
    }

    public void getEmployeeNameByArrow(String name) {
        log4j.startMethod("ManagerSvodkaPageHelper() - getEmployeeNameByArrow()");
        employeeArrow.click();
        scrollingToTheName(name);
        log4j.endMethod("ManagerSvodkaPageHelper() - getEmployeeNameByArrow()");
    }

    public void scrollingToTheName(String name) {
        log4j.startMethod("ManagerSvodkaPageHelper() - scrollingTest()");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement sellerName = driver.findElement(By.xpath("//li[contains(text(),'"+name+"')]"));
        js.executeScript("arguments[0].scrollIntoView();", sellerName);
        waitUntilElementIsClickable(sellerName,30);
        sellerName.click();
        log4j.endMethod("ManagerSvodkaPageHelper() - scrollingTest()");
    }

    public void getEmployeeNameByField(String name) {
        log4j.startMethod("ManagerSvodkaPageHelper() - getName()");
        employeeField.click();
        employeeField.clear();
        editField(employeeField, name);
//        WebElement n = driver.findElement(By.xpath("//li[contains(text(),'"+name+"')]"));
//        n.click();
        log4j.endMethod("ManagerSvodkaPageHelper() - getName()");
    }

    public String getSvodkaText() {
        log4j.startMethod("ManagerSvodkaPageHelper() - getSvodkaText()");
        log4j.endMethod("ManagerSvodkaPageHelper() - getSvodkaText()");
        return svodkaText.getText();
    }

    public void openGraficRabotyDropDownList(){
        log4j.startMethod("ManagerSvodkaPageHelper() - openGraficRabotyDropDownList()");
        waitUntilElementIsClickable(graficRaboty, 30);
        graficRaboty.click();
        log4j.endMethod("ManagerSvodkaPageHelper() - openGraficRabotyDropDownList()");
    }

    public String getGraficSmenText(){
        log4j.startMethod("ManagerSvodkaPageHelper() - getGraficSmenText()");
        waitUntilElementIsClickable(graficSmen, 30);
        log4j.endMethod("ManagerSvodkaPageHelper() - getGraficSmenText()");
        return graficSmen.getText();
    }

    public String getGraficOtpuskovText(){
        log4j.startMethod("ManagerSvodkaPageHelper() - getGraficOtpuskovText()");
        waitUntilElementIsClickable(graficOtpuskov, 30);
        log4j.endMethod("ManagerSvodkaPageHelper() - getGraficOtpuskovText()");
        return graficOtpuskov.getText();
    }

}
