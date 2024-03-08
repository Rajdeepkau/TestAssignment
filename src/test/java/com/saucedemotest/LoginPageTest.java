package com.saucedemotest;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.saucedemo.BaseTest;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;

public class LoginPageTest extends BaseTest {
	private SoftAssert softAssert;
	private LoginPage loginPage;
	private ProductsPage produtspage;

	@BeforeMethod
	public void initialize() {
		setUp();
		loadProperties();
		softAssert = new SoftAssert();
		loginPage = new LoginPage();
	}

	@Test
	public void verifyValidCredentialsLogin() {
		produtspage = loginPage.clickLoginButton(properties.getProperty("username"),
				properties.getProperty("password"));
		softAssert.assertEquals(produtspage.getTextProductsPageHeading(), properties.getProperty("productsPageHeading"),
				"Login failed. Products not shown on page.");
	}

	@Test
	public void verifyInvalidValidCredentialsLogin() {
		loginPage.clickLoginButton(RandomStringUtils.randomAlphabetic(8), RandomStringUtils.randomAlphanumeric(8));
		softAssert.assertEquals(loginPage.getTextError(), properties.getProperty("invalidUserNameError"),
				"Error message is not displayed for invalid credentials");
	}

	@AfterMethod
	public void closeDown() {
		softAssert.assertAll();
		tearDown();
	}
}
