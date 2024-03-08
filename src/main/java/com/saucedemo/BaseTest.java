package com.saucedemo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.testng.annotations.BeforeClass;
import com.saucedemo.utils.WebdriverEvents;

public class BaseTest {
	public static WebDriver driver;
	public static Logger logger;
	private final String URL = "https://www.saucedemo.com/";
	private final BrowserOptions DEFAULT_BROWSER = BrowserOptions.CHROME;
	public Properties properties;

	@BeforeClass
	public void loggerSteup() {

		logger = Logger.getLogger(BaseTest.class);
		PropertyConfigurator.configure("log4j.properties");
		BasicConfigurator.configure();
		logger.setLevel(Level.INFO);
	}

	public void setUp() {
		switch (DEFAULT_BROWSER) {
		case FIREFOX:
			driver = new FirefoxDriver();
			break;
		case EDGE:
			driver = new SafariDriver();
			break;
		case CHROME:
			driver = new ChromeDriver();
			break;
		default:
			throw new IllegalArgumentException("Invalid browser type specified: ");
		}

		WebdriverEvents events = new WebdriverEvents();
		// Create an instance of EventFiringDecorator and decorate your WebDriver
		EventFiringDecorator<WebDriver> decorator = new EventFiringDecorator<>(events);
		driver = decorator.decorate(driver);
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	public void loadProperties() {
		properties = new Properties();
		try (InputStream input = new FileInputStream("testData.properties")) {
			properties.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void tearDown() {
		driver.quit();

	}
}
