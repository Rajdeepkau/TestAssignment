package com.saucedemo.utils;

import org.apache.commons.lang3.RandomStringUtils;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.saucedemo.BaseTest;



public class Utils extends BaseTest {
	public static void failedTestScreenShot(String testMethodName) {
		if (driver != null) {
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

			File screenShotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(screenShotFile,
						new File("./FailedTestCasesScreenShot/" + "_" + testMethodName + "_" + timeStamp + ".jpg"));
			} catch (IOException e) {
				System.out.println("The IO Exception is: " + e);
				e.printStackTrace();
			}
		} else {
			System.out.println("WebDriver is not initialized. Unable to take screenshot.");
		}
	}

	public static String generateRandomString(int chCnt) {
		return RandomStringUtils.randomAlphabetic(chCnt);
	}

	public static String generateRandomAlphaNumeric(int chCnt) {
		return RandomStringUtils.randomAlphanumeric(chCnt);
	}

	public static String generateRandomNumber(int cnt) {
		return RandomStringUtils.randomNumeric(cnt);
	}

	public static String getCurrentDateTimeStamp() {
		return new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	}
	

	
}
