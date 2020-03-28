package com.testscenarios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Function;
import com.objectrepository.Locators;
import com.utilities.CommonFunctions;
import com.utilities.StaticVariables;

public class ClassTemplate extends StaticVariables {
	
	public String SurName, titleName, carName;
	public String TestDataPath = "";
	public String BaseURL, intmodelNo;
	int randomInt;

	CommonFunctions cfn = new CommonFunctions();

	@BeforeClass
	@Parameters("browser")
	public void beforeclass(@Optional("chrome") String browser) throws IOException, InterruptedException {
		TestDataPath = cfn.testDataPathOf("testdata.properties");
		Browser = browser;
		cfn.getAndOpenTheBrowserFromXML(Browser);
		System.out.println("ScreenshotsPath: " + ScreenshotsPath);
	}

	@AfterClass
	public void End() throws IOException {
		driver.quit();
	}

	@AfterMethod
	public void statusScreenShots(ITestResult testResult) throws IOException, InterruptedException {
		cfn.screenshotOnPassFail(testResult);
		Thread.sleep(1000);
	}

	@Test
	public void testCase_001() throws Exception {

		File file = new File(TestDataPath);
		Properties prop = new Properties();

		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Navigate to URL
		BaseURL = prop.getProperty("URL");
		driver.navigate().to(BaseURL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// impliment actula sript from here....
		
		
	}

}
