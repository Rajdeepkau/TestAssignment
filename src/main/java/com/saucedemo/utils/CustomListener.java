package com.saucedemo.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.saucedemo.BaseTest;

public class CustomListener extends BaseTest implements ITestListener{
	@Override
	public void onTestStart(ITestResult result) {
		logger.info("**********************Test Case started: " + result.getMethod().getMethodName()
				+ "****************************");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		logger.info("**********************Test Case Passed: " + result.getMethod().getMethodName()
				+ "****************************");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		logger.info("!!!!!!!!!!!!!!Test Case Fail: " + result.getMethod().getMethodName()
				+ " Taking Screenshot!!!!!!!!!!!!!!!!!!!!");
		Utils.failedTestScreenShot(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		logger.info("??????????????????Test Case skipped: " + result.getMethod().getMethodName() + "????????????????");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		onTestFailure(result);
	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {

	}
}


