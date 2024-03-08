package com.saucedemotest;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.saucedemo.BaseTest;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutCompletePage;
import com.saucedemo.pages.FirstCheckoutPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;

public class CheckoutCompletPageTest extends BaseTest {
	private SoftAssert softAssert;
	private LoginPage loginPage;
	private ProductsPage productsPage;
	private FirstCheckoutPage firstCheckoutPage;
	private CheckoutCompletePage checkoutCompletePage;
	private CartPage cartPage;

	@BeforeMethod
	public void initialize() {
		setUp();
		loadProperties();
		softAssert = new SoftAssert();
		loginPage = new LoginPage();
	}

	@Test
	public void validateOrderCompletion() {
		productsPage = loginPage.clickLoginButton(properties.getProperty("username"),
				properties.getProperty("password"));
		productsPage.sortProductsByPriceLowToHigh();
		productsPage.clickOnAddtocartForSeconItem();
		cartPage = productsPage.clickOnShoppingcartLink();
		firstCheckoutPage = cartPage.clickCheckoutButton();
		firstCheckoutPage.fillCheckoutFormWithRandomData();
		checkoutCompletePage = firstCheckoutPage.clickFinishButton();
		softAssert.assertEquals(checkoutCompletePage.getCompleteHeaderText(), properties.getProperty("thankYouText"),
				"Order completion failed");
	}

	@AfterMethod
	public void closeDown() {
		tearDown();
	}
}