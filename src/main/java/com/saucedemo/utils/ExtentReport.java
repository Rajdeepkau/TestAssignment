package com.saucedemo.utils;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport extends TestListenerAdapter {

    private ExtentHtmlReporter htmlReporter;
    private ExtentReports extent;
    private ExtentTest test;

    @Override
    public void onStart(ITestContext testContext) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String repName = "MyAccountPortal_" + timeStamp + ".html";
        htmlReporter = new ExtentHtmlReporter("C:\\Users\\user\\eclipse-workspace-second\\ui-test-automation\\Extent Reports" + repName);
		htmlReporter.loadXMLConfig("C:\\Users\\user\\eclipse-workspace-second\\ui-test-automation\\extent.xml");
        htmlReporter.config().setDocumentTitle("Extent Report");
        htmlReporter.config().setReportName("My Account Portal Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        test = extent.createTest(tr.getName());
        test.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        test = extent.createTest(tr.getName());
        test.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        test = extent.createTest(tr.getName());
        test.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
    }

    @Override
    public void onFinish(ITestContext testContext) {
        extent.flush();
    }
}
