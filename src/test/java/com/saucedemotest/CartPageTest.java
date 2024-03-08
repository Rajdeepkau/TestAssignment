package com.saucedemotest;

import java.util.Arrays;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.saucedemo.BaseTest;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;

public class CartPageTest extends BaseTest {
	private SoftAssert softAssert;
	private CartPage cartPage;
	private LoginPage loginPage;
	private ProductsPage productsPage;

	@BeforeMethod
	public void initialize() {
		setUp();
		loadProperties();
		softAssert = new SoftAssert();
		loginPage = new LoginPage();
		cartPage = new CartPage();

	}

	@Test
	public void validateAddToCartFuntionality() {
		productsPage = loginPage.clickLoginButton(properties.getProperty("username"),
				properties.getProperty("password"));
		productsPage.sortProductsByPriceLowToHigh();
		productsPage.clickOnAddtocartForSeconItem();
		softAssert.assertEquals(cartPage.getItemCount(), Integer.parseInt(properties.getProperty("expectedItemCount")),
				"The number of items in the cart is not correct.");
	}

	@Test
	public void VerifyAddToCartWithIncorrectItemCount() {

		productsPage = loginPage.clickLoginButton(properties.getProperty("username"),
				properties.getProperty("password"));
		productsPage.sortProductsByPriceLowToHigh();
		productsPage.clickOnAddtocartForSeconItem();
		softAssert.assertNotEquals(cartPage.getItemCount(),Integer.parseInt(properties.getProperty("expectedItemCount")) + 1,"Test failed: Incorrect item count was used for testing.");
	}

	@Test
	public void validateCartContents() {
		productsPage = loginPage.clickLoginButton(properties.getProperty("username"),
				properties.getProperty("password"));
		productsPage.sortProductsByPriceLowToHigh();
		productsPage.clickOnAddtocartForSeconItem();
		softAssert.assertEquals(cartPage.getCartProductNames(),Arrays.asList(properties.getProperty("expectedProductNames").split(", ")),"The products in the cart do not match the expected products.");
	}

	@AfterMethod
	public void closeDown() {
		softAssert.assertAll();
		tearDown();
	}
}