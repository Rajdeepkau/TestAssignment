package com.saucedemotest;

import java.util.Collections;
import java.util.List;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.saucedemo.BaseTest;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;

public class ProductsPageTest extends BaseTest {
	private SoftAssert softAssert;
	private LoginPage loginPage;
	private ProductsPage productsPage;

	@BeforeMethod
	public void initialize() {
		setUp();
		loadProperties();
		softAssert = new SoftAssert();
		loginPage = new LoginPage();

	}

	@Test
	public void validatePriceLowToHighSorting() {
		productsPage = loginPage.clickLoginButton(properties.getProperty("username"),
				properties.getProperty("password"));
		productsPage.sortProductsByPriceLowToHigh();
		// Retrieve product prices after sorting
		List<Double> prices = productsPage.getProductPrices();
		// Check if prices are sorted in ascending order
		for (int i = 0; i < prices.size() - 1; i++) {
			softAssert.assertTrue(prices.get(i) <= prices.get(i + 1), "Products are not sorted by price (low to high)");
		}
	}

	@Test
	public void validateInvalidPriceLowToHighSorting() {
		// Login and sort products by price low to high
		productsPage = loginPage.clickLoginButton(properties.getProperty("username"),
				properties.getProperty("password"));
		productsPage.sortProductsByPriceLowToHigh();
		// Shuffle product prices to disrupt sorting
		List<Double> prices = productsPage.getProductPrices();
		Collections.shuffle(prices);
		// Assert that products are not sorted by price (low to high)
		softAssert.assertFalse(productsPage.isSorted(prices), "Products should not be sorted by price (low to high)");
	}

	@AfterMethod
	public void closeDown() {
		softAssert.assertAll();
		tearDown();
	}
}
