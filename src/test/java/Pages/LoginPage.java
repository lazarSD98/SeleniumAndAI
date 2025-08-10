package Pages;

import Base.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.UUID;

import static Base.BaseTest.driver;

public class LoginPage {
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

/***WebElements***/

    @FindBy(id = "user-name")
    public WebElement usernameField;
    @FindBy(id = "password")
    public WebElement passwordField;
    @FindBy(id = "login-button")
    public WebElement loginButton;
    @FindBy(css = ".error-message-container.error")
    private WebElement errorMessageContainer;


    /***Methods***/


    public static String getValidUsername() {
        return "standard_user";
    }

    public static String getValidPassword() {
        return "secret_sauce";
    }

    public String getInvalidPassword() {
        return "invalid_" + UUID.randomUUID().toString().substring(0, 8);
    }

    public String getInvalidUsername() {
        return "invalidUser_" + UUID.randomUUID().toString().substring(0, 8);
    }

    public String getErrorMessageText() {
        return errorMessageContainer.getText();
    }

    public String expectedErrorMsgEmptyFields() {
        return "Epic sadface: Username is required";
    }

    public String expectedErrorMsgNoPassword() {
        return "Epic sadface: Password is required";
    }

    public String expectedErrorMsgWrongCredentials() {
        return "Epic sadface: Username and password do not match any user in this service";
    }

    public void insertValidUsername() {
        usernameField.clear();
        usernameField.sendKeys(getValidUsername());
    }

    public void inserValidPassword() {
        passwordField.clear();
        passwordField.sendKeys(getValidPassword());
    }

    public void loginWithValidCredentials() {

        insertValidUsername();
        inserValidPassword();
        clickLoginButton();

    }

    public void inserInvalidUsername() {
        usernameField.clear();
        usernameField.sendKeys(getInvalidUsername());
    }

    public void inserInvalidPassword() {
        passwordField.clear();
        passwordField.sendKeys(getInvalidPassword());
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void loginWithInvalidUsernameValidPassword() {
        inserInvalidUsername();
        inserValidPassword();
        clickLoginButton();
    }

    public void loginWithValidUsernameInvalidPassword() {
        insertValidUsername();
        inserInvalidPassword();
        clickLoginButton();
    }

    public boolean isLoginButtonVisible() {
        try {
            return loginButton.isDisplayed();  // returns true if visible
        } catch (Exception e) {
            return false;
        }
    }


}






