package com.saucedemo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.saucedemo.BaseTest;

public class LoginPage extends BaseTest {
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "user-name")
	private WebElement usernameInput;
	@FindBy(css = "h3[data-test='error']")
	private WebElement errorMessage;

	@FindBy(id = "password")
	private WebElement passwordInput;

	@FindBy(id = "login-button")
	private WebElement loginButton;

	public void enterUsername(String username) {
		usernameInput.sendKeys(username);
	}

	public void enterPassword(String password) {
		passwordInput.sendKeys(password);
	}

	public String getTextError() {
		return errorMessage.getText();
	}

	public ProductsPage clickLoginButton(String username, String Password) {
		enterUsername(username);
		enterPassword(Password);
		loginButton.click();
		return new ProductsPage();
	}

	public boolean isButtonDisplayed() {
		return loginButton.isDisplayed();

	}
}