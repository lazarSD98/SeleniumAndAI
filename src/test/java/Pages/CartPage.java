package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {
    public CartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    /***Webelements***/
    @FindBy(id = "continue-shopping")
    public WebElement continueShopping;
    @FindBy(className = "cart_item_label")
    public List<WebElement> itemsInsideTheCart;
    @FindBy(css = ".btn.btn_secondary.btn_small.cart_button")
    public List<WebElement> removeButtons;
    @FindBy (id = "shopping_cart_container")
    public WebElement cartButton;

    public void clickContinueShopping(){
        continueShopping.click();
    }

    public void removeAllItemsFromCart() {
        for (WebElement button : removeButtons) {
            button.click();
        }
    }

    public boolean isCartEmpty() {
        cartButton.click();
        return itemsInsideTheCart.isEmpty();
    }

    public void ensureCartIsEmpty() {
        cartButton.click();
        while (!isCartEmpty()) {
            removeAllItemsFromCart();

        }
    }


}
