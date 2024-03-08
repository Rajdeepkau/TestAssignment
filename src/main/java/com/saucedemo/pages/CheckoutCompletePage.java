package com.saucedemo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.saucedemo.BaseTest;

public class CheckoutCompletePage extends BaseTest {

	public CheckoutCompletePage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "complete-header")
	private WebElement completeHeader;
	@FindBy(id = "back-to-products")
	private WebElement backHomelink;

	public String getCompleteHeaderText() {
		return completeHeader.getText();
	}

	public ProductsPage clickOnBackHome() {
		backHomelink.click();
		return new ProductsPage();
	}
}
