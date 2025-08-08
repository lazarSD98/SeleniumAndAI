package Test;

import Base.BaseTest;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class loginTests extends BaseTest {
    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");

    }

    @Test
    public void loginValidCredentials() {
        loginPage.loginWithValidCredentials();

        Assert.assertFalse(loginPage.isLoginButtonVisible());
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        Assert.assertTrue(inventoryPage.isShoppingCartButtonDisplayed());
    }
    @Test(priority = 1)
    public void loginInvalidUsername(){
        loginPage.loginWithInvalidUsernameValidPassword();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertEquals(loginPage.getErrorMessageText(), loginPage.expectedErrorMsgWrongCredentials());
        Assert.assertTrue(loginPage.isLoginButtonVisible());
    }
    @Test(priority = 2)
    public void loginInvalidPassword(){
        loginPage.loginWithValidUsernameInvalidPassword();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertEquals(loginPage.getErrorMessageText(), loginPage.expectedErrorMsgWrongCredentials());
        Assert.assertTrue(loginPage.isLoginButtonVisible());
    }
    @Test(priority = 3)
    public void loginEmptyFields(){
        loginPage.clickLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertEquals(loginPage.getErrorMessageText(), loginPage.expectedErrorMsgEmptyFields());
        Assert.assertTrue(loginPage.isLoginButtonVisible());
    }
    @Test(priority = 4)
    public void loginEmptyPassword(){
        loginPage.insertValidUsername();
        loginPage.clickLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertEquals(loginPage.getErrorMessageText(), loginPage.expectedErrorMsgNoPassword());
        Assert.assertTrue(loginPage.isLoginButtonVisible());
    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }

}



