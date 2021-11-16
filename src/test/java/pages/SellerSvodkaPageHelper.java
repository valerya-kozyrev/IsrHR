package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SellerSvodkaPageHelper extends PageBase{
    @FindBy(xpath = "//div[contains(text(),'Дорош Лариса')]")
    WebElement userName;
    @FindBy(xpath = "//span[contains(text(),'Сводка')]")
    WebElement svodkaIcon;
    @FindBy(xpath = "//input[@role='searchbox']")//css = "#chooseEmployeeFld")
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

//    String year;
//    String month;
//    String name;

    public SellerSvodkaPageHelper(WebDriver driver){
        this.driver = driver;
    }

    public SellerSvodkaPageHelper waitUntilSellerSvodkaPageIsLoaded(){
        log4j.startMethod("SellerSvodkaPageHelper() - waitUntilSellerSvodkaPageIsLoaded()");
        log4j.info("wait until 'Сводка' button is clickable");
        waitUntilElementIsClickable(svodkaIcon, 30);
        log4j.endMethod("SellerSvodkaPageHelper() - waitUntilSellerSvodkaPageIsLoaded()");
        return this;
    }

    public String getSvodkaButtonName(){
        log4j.startMethod("SellerSvodkaPageHelper() - getSvodkaButtonName()");
        log4j.endMethod("SellerSvodkaPageHelper() - getSvodkaButtonName()");
        return svodkaIcon.getText();
    }

    public boolean isCorrectPage() {
        log4j.startMethod("SellerSvodkaPageHelper() - isCorrectPage");
        log4j.endMethod("SellerSvodkaPageHelper() - isCorrectPage");
        return getUserName().equals("Дорош Лариса");
    }

    public String getUserName(){
        log4j.startMethod("SellerSvodkaPageHelper() - getUserName()");
        log4j.endMethod("SellerSvodkaPageHelper() - getUserName()");
        return userName.getText();
    }

    public String getSvodkaByNameField(String month, String year, String name){
        log4j.startMethod("SellerSvodkaPageHelper() - getSvodkaByNameField()");
        getDate(month, year);
        getEmployeeNameByField(name);
        log4j.endMethod("SellerSvodkaPageHelper() - getSvodkaByNameField()");
        return getSvodkaText();
    }

    public String getSvodkaByNameArrow(String month, String year, String name) {
        log4j.startMethod("SellerSvodkaPageHelper() - getSvodkaByNameArrow()");
        getDate(month, year);
        getEmployeeNameByArrow(name);
        log4j.endMethod("SellerSvodkaPageHelper() - getSvodkaByNameArrow()");
        return getSvodkaText();
    }

    public void getDate(String month, String year) {
        log4j.startMethod("SellerSvodkaPageHelper() - getDate()");
        log4j.info("wait until dateField is clickable");
        waitUntilElementIsClickable(dateField,10);
        dateField.click();
        yearArrow.click();
        getYear(year);
        waitUntilElementIsClickable(monthField,10);
        getMonth(month);
        log4j.endMethod("SellerSvodkaPageHelper() - getDate()");
    }

    public void getMonth(String month) {
        log4j.startMethod("SellerSvodkaPageHelper() - getMonth()");
        WebElement m = driver.findElement(By.xpath("//span[contains(text(),'"+month+"')]"));
        m.click();
        log4j.endMethod("SellerSvodkaPageHelper() - getMonth()");
    }

    public void getYear(String year) {
        log4j.startMethod("SellerSvodkaPageHelper() - getYear()");
        WebElement y = driver.findElement(By.xpath("//option[@value='"+year+"']"));
        y.click();
        log4j.endMethod("SellerSvodkaPageHelper() - getYear()");
    }

    public void getEmployeeNameByArrow(String name) {
        log4j.startMethod("SellerSvodkaPageHelper() - getEmployeeNameByArrow()");
        employeeArrow.click();
        scrollingTest(name);
        log4j.endMethod("SellerSvodkaPageHelper() - getEmployeeNameByArrow()");
    }

    public void scrollingTest(String name) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement sellerName = driver.findElement(By.xpath("//li[contains(text(),'"+name+"')]"));
        js.executeScript("arguments[0].scrollIntoView();", sellerName);
        waitUntilElementIsClickable(sellerName,30);
        sellerName.click();
    }

    public void getEmployeeNameByField(String name) {
        log4j.startMethod("SellerSvodkaPageHelper() - getEmployeeNameByField()");
        employeeField.click();
        employeeField.clear();
        editField(employeeField, name);
//        WebElement n = driver.findElement(By.xpath("//li[contains(text(),'"+name+"')]"));
//        n.click();
        log4j.endMethod("SellerSvodkaPageHelper() - getEmployeeNameByField()");
    }

    public String getSvodkaText() {
        log4j.startMethod("SellerSvodkaPageHelper() - getSvodkaText()");
        log4j.endMethod("SellerSvodkaPageHelper() - getSvodkaText()");
        return svodkaText.getText();
    }

    public void openGraficRabotyDropDownList(){
        log4j.startMethod("SellerSvodkaPageHelper() - openGraficRabotyDropDownList()");
        waitUntilElementIsClickable(graficRaboty, 30);
        graficRaboty.click();
        log4j.endMethod("SellerSvodkaPageHelper() - openGraficRabotyDropDownList()");
    }

    public String getGraficSmenText(){
        log4j.startMethod("SellerSvodkaPageHelper() - getGraficSmenText()");
        waitUntilElementIsClickable(graficSmen, 30);
        log4j.endMethod("SellerSvodkaPageHelper() - getGraficSmenText()");
        return graficSmen.getText();
    }

    public String getGraficOtpuskovText(){
        log4j.startMethod("SellerSvodkaPageHelper() - getGraficOtpuskovText()");
        waitUntilElementIsClickable(graficOtpuskov, 30);
        log4j.endMethod("SellerSvodkaPageHelper() - getGraficOtpuskovText()");
        return graficOtpuskov.getText();
    }

}
