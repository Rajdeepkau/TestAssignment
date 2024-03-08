package com.saucedemo.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.saucedemo.BaseTest;

public class ProductsPage extends BaseTest {
	public ProductsPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "inventory_item_price")
	private List<WebElement> productPrices;
	@FindBy(xpath = "//div[@class='header_secondary_container']/span")
	private WebElement productsTitle;
	@FindBy(className = "inventory_item_price")
	private WebElement productPrice;
	@FindBy(xpath = "//select[@class='product_sort_container']")
	private WebElement sortSelect;
	@FindBy(id = "add-to-cart-sauce-labs-bolt-t-shirt")
	private WebElement addCartFirst;
	@FindBy(id = "add-to-cart-test.allthethings()-t-shirt-(red)")
	private WebElement addcartSecond;
	@FindBy(className = "shopping_cart_link")
	private WebElement shoppingCartLink;
	@FindBy(id = "logout_sidebar_link")
	private WebElement logoutButton;
	@FindBy(id = "react-burger-menu-btn")
	private WebElement sideBarButton;

	public String getProductPrice() {
		return productPrice.getText();
	}

	public void sortProductsByPriceLowToHigh() {
		Select sortDropdown = new Select(sortSelect);
		sortDropdown.selectByValue("lohi");
	}

	public String getTextProductsPageHeading() {
		return productsTitle.getText();
	}

	public void clickOnAddtocartforFirstItem() {
		addCartFirst.click();
	}

	private void clickOnSideBarButton() {
		sideBarButton.click();
	}

	public LoginPage clickOnlogout() {
		clickOnSideBarButton();
		logoutButton.click();
		return new LoginPage();
	}

	public CartPage clickOnShoppingcartLink() {
		shoppingCartLink.click();
		return new CartPage();
	}

	public void clickOnAddtocartForSeconItem() {
		clickOnAddtocartforFirstItem();
		addcartSecond.click();

	}

	public List<Double> getProductPrices() {
		List<Double> prices = new ArrayList<>();
		for (WebElement element : productPrices) {
			String priceText = element.getText().replace("$", "");
			prices.add(Double.parseDouble(priceText));
		}
		return prices;
	}

	public boolean isSorted(List<Double> prices) {
		for (int i = 0; i < prices.size() - 1; i++) {
			if (prices.get(i) > prices.get(i + 1)) {
				return false;
			}
		}
		return true;
	}

}
