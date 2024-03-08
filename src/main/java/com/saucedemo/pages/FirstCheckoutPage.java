package com.saucedemo.pages;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.saucedemo.BaseTest;
public class FirstCheckoutPage extends BaseTest {
	public FirstCheckoutPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "checkout")
	private WebElement checkoutButton;

	@FindBy(id = "first-name")
	private WebElement firstNameInput;

	@FindBy(id = "last-name")
	private WebElement lastNameInput;
	@FindBy(id = "postal-code")
	private WebElement postalCodeInput;
	@FindBy(id = "continue")
	private WebElement continueButton;
	@FindBy(className = "summary_info")
	private WebElement summaryTitle;
	@FindBy(xpath = "//div[@class='item_total']")
	private WebElement itemTotalElement;

	@FindBy(xpath = "//div[@class='tax_amount']")
	private WebElement taxAmountElement;

	@FindBy(xpath = "//div[@class='total_price']")
	private WebElement totalPriceElement;
	@FindBy(id = "finish")
	private WebElement finishButton;

	public void clickCheckoutButton() {
		checkoutButton.click();
	}

	public double getItemTotal() {
		return extractPrice(itemTotalElement.getText());
	}
	
	public double getTaxAmount() {
		return extractPrice(taxAmountElement.getText());
	}

	public double getTotalPrice() {
		return extractPrice(totalPriceElement.getText());
	}

	private double extractPrice(String priceText) {
		return 0.0;
	}
	public CheckoutCompletePage clickFinishButton() {
		finishButton.click();
		return new CheckoutCompletePage() ;
	}

	public void fillCheckoutFormWithRandomData() {
		firstNameInput.sendKeys(RandomStringUtils.randomAlphabetic(8));
		lastNameInput.sendKeys(RandomStringUtils.randomAlphabetic(8));
		postalCodeInput.sendKeys(RandomStringUtils.randomNumeric(5));
		continueButton.click();
	}

	public String getSummaryTitleText() {
		return summaryTitle.getText();
	}
}
