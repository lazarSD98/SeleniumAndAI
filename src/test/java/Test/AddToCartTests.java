package Test;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddToCartTests extends BaseTest {
    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");

    }

    @Test
    public void addItemsToCart() {
        loginPage.loginWithValidCredentials();
        cartPage.ensureCartIsEmpty();
        cartPage.continueShopping.click();
        inventoryPage.addRandomItemsToCart(); //Method generates a random number up to 6 and adds equivalent number of randomly chosen items to the cart
        Assert.assertEquals(inventoryPage.numberOfAddedItems(), inventoryPage.actualNumberOfAddedItems()); // Method checks if the number that
        // indicates number of items inside the car corresponds to the number of added item
        Assert.assertTrue(inventoryPage.areListsEqualIgnoreOrder(inventoryPage.getNamesOfAddedItems(), inventoryPage.getNamesOfCartItems())); // Method check whether
        // the items which were previously added to the cart correspond to the numbers that are actually inside the cart
    }
    @Test(priority = 1)
    public void emptyCart(){
        loginPage.loginWithValidCredentials();
        cartPage.ensureCartIsEmpty();
        cartPage.continueShopping.click();
        inventoryPage.addRandomItemsToCart();
        inventoryPage.goToCartPage();
        Assert.assertFalse(cartPage.isCartEmpty());
        cartPage.removeAllItemsFromCart();
        Assert.assertTrue(cartPage.isCartEmpty());
        Assert.assertFalse(inventoryPage.isCartBadgeDisplayed());


    }

}
