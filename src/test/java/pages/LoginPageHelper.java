package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageHelper extends PageBase {

    @FindBy(id = "username")
    WebElement userNameField;
    @FindBy(id = "password")
    WebElement passwordField;
    @FindBy(xpath = "//button[@class='p-button p-component']")
    WebElement loginButton;
    @FindBy(xpath = "//h1[contains(text(),'ДОСТУП ЗАПРЕЩЕН!')]")
    WebElement errorMessage;

    public LoginPageHelper(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPageHelper waitUntilLoginPageIsLoaded() {
        log4j.startMethod("LoginPageHelper() - waitUntilLoginPageIsLoaded()");
        log4j.info("wait for 'Log in' button to be clickable");
        waitUntilElementIsClickable(loginButton, 30);
        log4j.endMethod("LoginPageHelper() - waitUntilLoginPageIsLoaded()");
        return this;
    }

    public void login(String login, String password) {
        log4j.startMethod("LoginPageHelper() - login()");
        fillInUserNameField(login);
        fillInPasswordField(password);
        submitLogin();
        log4j.endMethod("LoginPageHelper() - login()");
    }

    public void fillInUserNameField(String value) {
        log4j.startMethod("LoginPageHelper() - fillInUserNameField()");
        log4j.info("wait for 'Log in' button to be clickable");
        waitUntilElementIsClickable(userNameField, 20);
        editField(userNameField, value);
        log4j.endMethod("LoginPageHelper() - fillInUserNameField()");
    }

    public void fillInPasswordField(String value) {
        log4j.startMethod("LoginPageHelper() - fillInPasswordField()");
        log4j.info("wait for 'password' field to be clickable");
        waitUntilElementIsClickable(passwordField, 20);
        editField(passwordField, value);
        log4j.endMethod("LoginPageHelper() - fillInPasswordField()");
    }

    public void submitLogin() {
        log4j.startMethod("LoginPageHelper() - submitLogin()");
        log4j.info("wait for 'Log in' button to be clickable");
        waitUntilElementIsClickable(loginButton, 20);
        loginButton.click();
        log4j.endMethod("LoginPageHelper() - submitLogin()");
    }

    public String getErrorMessage() {
        log4j.startMethod("LoginPageHelper - getErrorMessage()");
        log4j.info("wait for the error message to be visible");
        waitUntilElementIsVisible(errorMessage, 10);
        log4j.info("return error message");
        log4j.endMethod("LoginPageHelper() - getErrorMessage()");
        return errorMessage.getText();
    }

}
