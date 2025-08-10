package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static Base.BaseTest.driver;

public class InventoryPage {
    public InventoryPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));


    /***WebElements***/

    @FindBy(className = "shopping_cart_link")
    public WebElement shoppingCartButton;
    @FindBy(id = "react-burger-menu-btn")
    public WebElement burgerMenuButton;

    public List<WebElement> menuItems() {
        return driver.findElements(By.cssSelector(".bm-item.menu-item"));
    }

    @FindBy(className = "inventory_item")
    public List<WebElement> inventoryItems;

    private static final By ITEM_NAME_BY = By.className("inventory_item_name");
    private static final By ADD_TO_CART_BTN_BY = By.cssSelector(".btn_primary.btn_inventory");
    private static final By REMOVE_BTN_BY = By.cssSelector(".btn_secondary.btn_inventory");

    @FindBy(className = "shopping_cart_badge")
    public WebElement shoppingCartBadge;
    @FindBy(className = "inventory_item_name")
    public List<WebElement>  itemsInsideTheCart;

    /***Methods***/

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

    public void clickLogoutFromMenu() {
        openBurgerMenu();
        for (WebElement item : menuItems()) {
            if (item.getText().trim().equalsIgnoreCase("Logout")) {
                item.click();
                break;
            }
        }
    }
    public void goToCartPage(){
        shoppingCartButton.click();
    }

    public Integer fixedRandomCount = null;

    public int generateRandomCount() {
        if (fixedRandomCount == null) {
            int max = inventoryItems == null ? 0 : inventoryItems.size();
            if (max == 0) throw new IllegalStateException("No inventory items found!");
            fixedRandomCount = Math.min(new Random().nextInt(6) + 1, max);
        }
        return fixedRandomCount;
    }



    public void addRandomItemsToCart() {
        if (inventoryItems == null || inventoryItems.isEmpty()) {
            throw new IllegalStateException("No inventory items found!");
        }

        int countToAdd = generateRandomCount();
        System.out.println(countToAdd);

        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < inventoryItems.size(); i++) {
            indices.add(i);
        }
        Collections.shuffle(indices);

        List<String> addedItemNames = new ArrayList<>();
        int itemsAdded = 0;

        for (int index : indices) {
            if (itemsAdded >= countToAdd) break;

            WebElement item = inventoryItems.get(index);

            boolean alreadyAdded = !item.findElements(REMOVE_BTN_BY).isEmpty();

            if (!alreadyAdded) {
                WebElement addButton = item.findElement(ADD_TO_CART_BTN_BY);
                addButton.click();

                String name = item.findElement(ITEM_NAME_BY).getText().trim();
                addedItemNames.add(name);

                itemsAdded++;
            }
        }


    }

    public List<String> getNamesOfAddedItems() {
        List<String> addedNames = new ArrayList<>();

        for (WebElement item : inventoryItems) {
            List<WebElement> removeButtons = item.findElements(By.cssSelector(".btn_secondary.btn_inventory"));
            if (!removeButtons.isEmpty()) {
                String name = item.findElement(By.className("inventory_item_name")).getText().trim();
                addedNames.add(name);
            }
        }


        return addedNames;
    }
    public List<String> getNamesOfCartItems() {
        shoppingCartButton.click();
        List<String> itemNames = new ArrayList<>();
        for (WebElement item : itemsInsideTheCart) {
            itemNames.add(item.getText());
        }
        return itemNames;
    }
    public boolean areListsEqualIgnoreOrder(List<String> list1, List<String> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }
        List<String> sortedList1 = new ArrayList<>(list1);
        List<String> sortedList2 = new ArrayList<>(list2);

        Collections.sort(sortedList1);
        Collections.sort(sortedList2);

        return sortedList1.equals(sortedList2);
    }
    public boolean isCartBadgeDisplayed() {
        try {
            return shoppingCartBadge.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String numberOfAddedItems(){
        return fixedRandomCount.toString();
    }
    public String actualNumberOfAddedItems(){
       return shoppingCartBadge.getText();
    }

}




