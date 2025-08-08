package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static Base.BaseTest.driver;

public class InventoryPage {
    public InventoryPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));


    //***WebElements***//

    @FindBy(className = "shopping_cart_link")
    public WebElement shoppingCartButton;
    @FindBy(id = "react-burger-menu-btn")
    public WebElement burgerMenuButton;
    public List<WebElement> menuItems(){
        return driver.findElements(By.cssSelector(".bm-item.menu-item"));
    }

    ///*Methods***//

    public boolean isShoppingCartButtonDisplayed() {
        try {
            return shoppingCartButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isBurgerMenuButtonVisible() {
        try {
            return burgerMenuButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void openBurgerMenu() {
        burgerMenuButton.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(menuItems()));
    }

    public void clickLogoutFromMenu() throws InterruptedException {
        openBurgerMenu();
        for (WebElement item : menuItems()) {
            if (item.getText().trim().equalsIgnoreCase("Logout")) {
                item.click();
                break;
            }
        }
    }

}