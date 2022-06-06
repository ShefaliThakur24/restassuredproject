package com.employeeapi.utilities;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter {
////https://learn-automation.com/extent-report-with-selenium-webdriver/
    public ExtentSparkReporter spark;
    public ExtentReports extent;
    public ExtentTest test;

    public void onStart(ITestContext testContext) {
        spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/Reports/myReport.html");//specify location of the report
        extent = new ExtentReports();
        extent.attachReporter(spark);

        spark.config().setDocumentTitle("Automation Report"); // Tile of report
        spark.config().setReportName("Rest API Testing Report"); // name of the report
        //htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); //location of the chart
        spark.config().setTheme(Theme.DARK);


        extent.setSystemInfo("Project Name","Employee Database API");
        extent.setSystemInfo("Host name","localhost");
        extent.setSystemInfo("Environemnt","QA");
        extent.setSystemInfo("user","shefali");
        extent.setReportUsesManualConfiguration(true);
    }

    public void onTestSuccess(ITestResult result) {
        //test=extent.createTest(result.getClass().getName());
        //test.createNode(result.getName());
        test = extent.createTest(result.getName()); // create new entry in th report

        test.log(Status.PASS, "Test Case PASSED IS " + result.getName());
    }

    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getName()); // create new entry in th report

        test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
        test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to add error/exception in extent report

    }

    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getName()); // create new entry in th report
        test.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
    }

    public void onFinish(ITestContext testContext) {
        extent.flush();
    }

}
