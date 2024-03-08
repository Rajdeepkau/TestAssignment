package com.saucedemotest;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.saucedemo.BaseTest;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.FirstCheckoutPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;

public class FirstCheckoutPageTest extends BaseTest {
	private SoftAssert softAssert;
	private CartPage cartpage;
	private LoginPage loginPage;
	private ProductsPage productsPage;
	private FirstCheckoutPage firstcheckoutpage;

	@BeforeMethod
	public void initialize() {
		setUp();
		loadProperties();
		softAssert = new SoftAssert();
		loginPage = new LoginPage();
	}

	@Test
	public void verifyValidDataCheckout() {
		// Login
		productsPage = loginPage.clickLoginButton(properties.getProperty("username"),
				properties.getProperty("password"));

		// Sort products and add to cart
		productsPage.sortProductsByPriceLowToHigh();
		productsPage.clickOnAddtocartForSeconItem();

		// Proceed to checkout
		cartpage = productsPage.clickOnShoppingcartLink();
		firstcheckoutpage = cartpage.clickCheckoutButton();
		firstcheckoutpage.fillCheckoutFormWithRandomData();

		// Verify checkout success
		softAssert.assertTrue(
				firstcheckoutpage.getSummaryTitleText().contains(properties.getProperty("checkoutOverviewText")),
				"Checkout failed");
	}

	@Test
	public void verifyItemTotalValidation() {
		productsPage = loginPage.clickLoginButton(properties.getProperty("username"),
				properties.getProperty("password"));
		productsPage.sortProductsByPriceLowToHigh();
		productsPage.clickOnAddtocartForSeconItem();
		cartpage = productsPage.clickOnShoppingcartLink();
		firstcheckoutpage = cartpage.clickCheckoutButton();
		firstcheckoutpage.fillCheckoutFormWithRandomData();
		double itemTotal = firstcheckoutpage.getItemTotal();
		softAssert.assertEquals(itemTotal, Double.parseDouble(properties.getProperty("expectedItemTotal")),
				"Item total does not match expected.");
	}

	@AfterMethod
	public void closeDown() {
		tearDown();
	}

}