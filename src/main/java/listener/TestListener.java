package listener;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import base.BaseTest;

public class TestListener implements ITestListener, ISuiteListener {
	
	
	private static ExtentReports extent;
    private static ExtentTest test;
	
	  /**
	   * This method is invoked before the SuiteRunner starts.
	   *
	   * @param suite The suite
	   */
	  public void onStart(ISuite suite) {
		  
		  extent = new ExtentReports();
		  ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark.html");
		  extent.attachReporter(spark);
	  }

	  /**
	   * This method is invoked after the SuiteRunner has run all the tests in the suite.
	   *
	   * @param suite The suite
	   */
	  public void onFinish(ISuite suite) {
	    extent.flush();
	  }
	
	
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
	  }

	  /**
	   * Invoked each time a test succeeds.
	   *
	   * @param result <code>ITestResult</code> containing information about the run test
	   * @see ITestResult#SUCCESS
	   */
	  public void onTestSuccess(ITestResult result) {
		 if(result.getStatus() == ITestResult.SUCCESS) {
			 System.out.println(result.getName() + " is successfully passed/");
		 }
	    
	  }

	  /**
	   * Invoked each time a test fails.
	   *
	   * @param result <code>ITestResult</code> containing information about the run test
	   * @see ITestResult#FAILURE
	   */
	  public void onTestFailure(ITestResult result) {
		  if(result.getStatus() == ITestResult.FAILURE)
	    System.out.println(result.getName() + " test is failed.");
	  }

	  /**
	   * Invoked each time a test is skipped.
	   *
	   * @param result <code>ITestResult</code> containing information about the run test
	   * @see ITestResult#SKIP
	   */
	  public void onTestSkipped(ITestResult result) {
	    // not implemented
	  }

	  

	
	

}
