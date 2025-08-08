package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class inventoryPage {
    public inventoryPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    //***WebElements***//

    @FindBy(className = "shopping_cart_link")
    public WebElement shoppingCartButton;

    ///*Methods***//

    public boolean isShoppingCartButtonDisplayed() {
        try {
            return shoppingCartButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
