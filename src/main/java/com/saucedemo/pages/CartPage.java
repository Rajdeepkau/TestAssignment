package com.saucedemo.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.saucedemo.BaseTest;

public class CartPage extends BaseTest {
    public CartPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "cart_item")
	private List<WebElement> cartItems;
    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;
    @FindBy(id = "checkout")
    private WebElement checkoutButton;
    @FindBy(className = "shopping_cart_badge")
    private WebElement shoppingCartBadge;

    public int getItemCount() {
        return Integer.parseInt(shoppingCartBadge.getText());
    }
    public void clickCartIcon() {
        cartIcon.click();
    }
    public FirstCheckoutPage clickCheckoutButton() {
    	checkoutButton.click();
		return new FirstCheckoutPage() ;
    }

    public List<WebElement> getCartItems() {
        return cartItems;
    }
    public List<String> getCartProductNames() {
        clickCartIcon();
        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
		List<String> actualProductNames = new ArrayList<>();
        for (WebElement item : cartItems) {
            actualProductNames.add(item.findElement(By.className("inventory_item_name")).getText());
        }
        return actualProductNames;
    }
}
