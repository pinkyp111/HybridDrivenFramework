/**
 * 
 */
package com.utilities;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.ITestResult;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.common.base.Function;
import com.jayway.restassured.RestAssured;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.response.Response;

import org.apache.http.client.fluent.Request;

/**
 * @author sreenivasula.reddy
 *
 */
public class CommonFunctions extends StaticVariables {
	public CommonFunctions() {
		ProjectDir = System.getProperty("user.dir");

		File file = new File(ProjectDir + "\\screenshots");
		boolean a = false;
		if (!file.exists()) {
			a = file.mkdir();
		}
		if (a)
			System.out.println("screenshots folder successfully created.");
		else
			System.out.println("screenshots folder already exists.");

		ScreenshotsPath = ProjectDir + "\\screenshots\\";

		

		System.out.println(
				"Current Project Physical Location: " + ProjectDir + "; \n CCFCResultsPath: " + CCFCResultsPath);
		System.out.println("For screenshots path: " + ScreenshotsPath);
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");
	}

	public String testDataPathOf(String TestDataFileName) throws IOException {
		String TestDataPath = ProjectDir + "\\TestData\\" + TestDataFileName;
		System.out.println("TestDataPath: " + TestDataPath);
		return TestDataPath;
	}

	public void getAndOpenTheBrowserFromXML(String browser) throws IOException, InterruptedException {
		try {
			if (browser.equalsIgnoreCase("Firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("IE")) {
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
			} else if (browser.equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			} else if (browser.equalsIgnoreCase("Galaxy J2 5.1.1")) {
				String deviceName = "Galaxy J2 5.1.1";
				// Android Configuration Create object of DesiredCapabilities
				// class and specify android platform
				DesiredCapabilities capabilities = DesiredCapabilities.android();
				// set the capability to execute test in chrome browser
				capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);
				// set the capability to execute our test in Android Platform
				capabilities.setCapability(MobileCapabilityType.PLATFORM, Platform.ANDROID);
				// we need to define platform name
				capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
				// Set the device name as well (you can give any name)
				capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy J2 5.1.1");
				// set the android version as well
				capabilities.setCapability(MobileCapabilityType.VERSION, "5.1.1");
				capabilities.setCapability(CapabilityType.PLATFORM, "WINDOWS");
				capabilities.setCapability("unicodeKeyboard", "True");
				capabilities.setCapability("resetKeyboard", "True");

				// Baskaran
				// capabilities.setCapability("nativeWebTap", false);
				capabilities.setCapability(MobileCapabilityType.SUPPORTS_JAVASCRIPT, true);
				capabilities.setCapability("device", "selendroid");

				// capabilities.setCapability(CapabilityType.BROWSER_NAME,
				// "chrome");
				// capabilities.setCapability("unicodeKeyboard", true);

				capabilities.setCapability(MobileCapabilityType.HAS_TOUCHSCREEN, true);
				// Create object of URL class and specify the appium server
				// address
				URL url = new URL("http://127.0.0.1:4723/wd/hub");
				// Create object of AndroidDriver class and pass the url and
				// capability that we created
				// driver = new AndroidDriver<MobileElement>(url, capabilities);
				driver = new AndroidDriver<MobileElement>(url, capabilities);
				driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
				driver.manage().deleteAllCookies();
			} else if (browser.equalsIgnoreCase("Galaxy Tab E 4.4.4")) {
				// Android Configuration Create object of DesiredCapabilities
				// class and specify android platform
				DesiredCapabilities capabilities = DesiredCapabilities.android();
				// set the capability to execute test in chrome browser
				capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);
				// set the capability to execute our test in Android Platform
				capabilities.setCapability(MobileCapabilityType.PLATFORM, Platform.ANDROID);
				// we need to define platform name
				capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
				// Set the device name as well (you can give any name)
				capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy Tab E");
				// set the android version as well
				capabilities.setCapability(MobileCapabilityType.VERSION, "4.4.4");
				// Create object of URL class and specify the appium server
				// address
				URL url = new URL("http://127.0.0.1:4723/wd/hub");
				// Create object of AndroidDriver class and pass the url and
				// capability that we created
				driver = new AndroidDriver<MobileElement>(url, capabilities);
				driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
				driver.manage().deleteAllCookies();
			} else if (browser.equalsIgnoreCase("andriod 5.1")) {
				// Android Configuration Create object of DesiredCapabilities
				// class and specify android platform
				DesiredCapabilities capabilities = DesiredCapabilities.android();
				// set the capability to execute test in chrome browser
				capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);
				// set the capability to execute our test in Android Platform
				capabilities.setCapability(MobileCapabilityType.PLATFORM, Platform.ANDROID);
				// we need to define platform name
				capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
				// Set the device name as well (you can give any name)
				capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "andriod 5.1");
				// set the android version as well
				capabilities.setCapability(MobileCapabilityType.VERSION, "5.1");
				// Create object of URL class and specify the appium server
				// address
				URL url = new URL("http://127.0.0.1:4723/wd/hub");
				// Create object of AndroidDriver class and pass the url and
				// capability that we created
				driver = new AndroidDriver(url, capabilities);
				driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
				driver.manage().deleteAllCookies();
			}

		} catch (WebDriverException e) {
			System.out.println(e.getMessage());
		}
		// driver.manage().deleteAllCookies();
		Thread.sleep(2000);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public String chromeDriverPath() throws IOException {
		String ChromeDriverPath = ProjectDir + "\\drivers\\" + "chromedriver.exe";
		return ChromeDriverPath;
	}

	public String ieDriverPath() throws IOException {
		String IEDriverPath = ProjectDir + "\\Drivers\\" + "IEDriverServer.exe";
		return IEDriverPath;
	}

	public void launchChromeBrowser(String browser) throws IOException, InterruptedException {
		// SSL certificate
		System.setProperty("webdriver.chrome.driver", chromeDriverPath());
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(CapabilityType.PLATFORM, Platform.getCurrent());
		capabilities.setBrowserName("chrome");

		ChromeOptions options = new ChromeOptions();
		capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		options.addArguments("start-maximized");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(options);
	}

	public void launchIEBrowser(String browser) throws IOException, InterruptedException {
		// SSL certificate
		System.setProperty("webdriver.ie.driver", ieDriverPath());
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		// DesiredCapabilities capabilities =
		// DesiredCapabilities.internetExplorer();
		driver = new InternetExplorerDriver(capabilities);

	}

	public void launchfirefoxBrowser(String browser) throws IOException, InterruptedException {
		System.setProperty("webdriver.firefox.marionette", "./Drivers/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

	/**************** Time Stamp *******************/
	public String timeStampasString() {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		return timeStamp;
	}

	public String TimeStampSQLasString() {
		java.util.Date date = new java.util.Date();
		String timeStamp = new Timestamp(date.getTime()).toString();
		return timeStamp;
	}

	public String TimeStamp2asStringwithAMPM() {
		String timeStamp = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a").format(Calendar.getInstance().getTime());
		return timeStamp;
	}

	public String DateTimeOfUK() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		// Use Europe/London time zone to format the date in
		df.setTimeZone(TimeZone.getTimeZone("Europe/London"));
		String timeStamp = df.format(date);
		return timeStamp;
	}

	public void screenshotOnPassFail(ITestResult testResult) throws IOException {
		StaticVariables.ClassName = testResult.getTestClass().getName().trim();
		StaticVariables.MethodName = testResult.getName().trim();
		String ClsNmMtdNm = StaticVariables.ClassName + "_" + StaticVariables.MethodName + "_";
		if (testResult.getStatus() != ITestResult.FAILURE) {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			System.out.println("ClsNmMtdNm: " + ClsNmMtdNm + "; TimeStampasString" + timeStampasString());
			FileUtils.copyFile(scrFile,
					new File(ScreenshotsPath + "Pass_" + ClsNmMtdNm + timeStampasString() + ".jpg"));
		}
		if (testResult.getStatus() == ITestResult.FAILURE) {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			System.out.println("ClsNmMtdNm: " + ClsNmMtdNm + "; TimeStampasString" + timeStampasString());
			FileUtils.copyFile(scrFile,
					new File(ScreenshotsPath + "Fail_" + ClsNmMtdNm + timeStampasString() + ".jpg"));
		}
	}

	/************** Random number generate *********/
	public int randomNo() {
		int randomInt = 0;
		Random rg = new Random();
		for (int idx = 1; idx <= 8; ++idx) {
			randomInt = rg.nextInt(8);
			if (randomInt == 0) {
				randomInt = 1;
			}
			System.out.println("Generated : " + randomInt);
		}
		return randomInt;
	}

	public int randomNoWithinTheRange() {
		int randomInt = 0;
		Random rg = new Random();
		for (int idx = 1; idx <= 3; ++idx) {
			randomInt = rg.nextInt(3);
			if (randomInt == 0) {
				randomInt = 1;
			}
			System.out.println("Generated : " + randomInt);
		}
		return randomInt;
	}

	public int getRandomNumberInRange(int min, int max) {
		if (min >= max) {
			// throw new IllegalArgumentException("max must be greater than // min");
			return min;
		}
		Random r = new Random();
		return (r.nextInt((max - min) + 1) + min);
	}

	/************** Return Web Element *********/
	public WebElement getWebElementByanyLocator(By elementlocator) {
		WebElement element = driver.findElement(elementlocator);
		return element;
	}

	/*********************************
	 * SendKeysByAnyLocator
	 ************************/

	public void sendkeysByAnyLocator(By locator, String inputData) {
		WebElement element = driver.findElement(locator);
		try {
			if (element.isDisplayed()) {
				if (element.isEnabled()) {
					element.clear();
					element.sendKeys(inputData);
				} else {
					System.out.println("Element is NOT enabled state");
				}

			} else {
				System.out.println("Element is NOT displayed in DOM");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*********************************
	 * ClickAnyLocator
	 ************************/

	public void clickByAnyLocator(By locator) {
		WebElement element = driver.findElement(locator);
		try {
			if (element.isDisplayed()) {
				if (element.isEnabled()) {
					clickUsingJavaScript(element);
				} else {
					System.out.println("Element is NOT enabled state");
				}

			} else {
				System.out.println("Element is NOT displayed in DOM");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**************** ClickUsingJavaScript *****************************/
	public void clickUsingJavaScript(WebElement webelement) throws Exception {
		boolean error = true;
		int counter = 0;
		while (error == true) {
			try {
				if (webelement.isEnabled() || webelement.isDisplayed()) {
					((JavascriptExecutor) driver).executeScript("arguments[0].style.border='6px groove green'",
							webelement);
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", webelement);
					System.out.println(webelement);
					System.out.println("ClickUsingJavaScript Executed");
					Thread.sleep(500);
					error = false;
				} else {
					System.out.println("ClickUsingJavaScript Unable to click on element. webelement.isEnabled() = "
							+ webelement.isEnabled() + "; webelement.isDisplayed() = " + webelement.isDisplayed());
				}
			} catch (Exception ex) {
				System.out.println("Exception in ClickUsingJavaScript Unable to click on element. " + ex);
				Thread.sleep(1000);
				error = true;
			}
			counter++;
			if (counter > 3) {
				break;
			}
		}
	}

	public void checkBoxSelectedOrNot_GetElementByanyLocator(By Locator) {
		String Script = "return document.getElementById('" + Locator + "').checked;";
		System.out.println("JScript: " + ((JavascriptExecutor) driver).executeScript(Script));
	}

	/********************* Frames handling ************************/
	public void switchToDefaultFrame() {
		driver.switchTo().defaultContent();
	}

	public void switchToFrame() {
		driver.switchTo().defaultContent();
		WebElement iFramesElement = driver.findElement(By.xpath("//iframe"));
		driver.switchTo().frame(iFramesElement);
	}

	public void switchToFrameByInt(int i) {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(i);
	}

	public int IframeCount() {
		driver.switchTo().defaultContent();
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		int numberOfFrames = 0;
		numberOfFrames = Integer.parseInt(exe.executeScript("return window.length").toString());
		System.out.println("Number of iframes on the page are: " + numberOfFrames);
		return numberOfFrames;
	}

	public void frameNamesByTagName() {

		System.out.println(
				"***FrameNamesByTagName: Move to default Content explicitly. Otherwise it won't work. 'Call switchToDefaultFrame and don't forget to move to your frame.' ***");
		System.out.println("Iframe Count: " + IframeCount());

		final List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
		for (WebElement iframe : iframes) {
			System.out.println("Iframes: " + iframes.toString());
		}
		System.out.println(
				"***FrameNamesByTagName: Don't forget to switch to your frame. 'Call switchToDefaultFrame and don't forget to move to your frame.' ***");
	}

	public int loopAllframesAndReturnCountofElement(final By locator) throws InterruptedException {
		// WebElement found = fluentWait(By.xpath(xPathElement));
		int ElementpresenceCount = 0;
		int Loop = 0;
		// int maxFramaecount = IframeCount();
		System.out.println("inside Wait for Element in Loop");

		while (ElementpresenceCount < 1) {
			ElementpresenceCount = driver.findElements(locator).size();
			Thread.sleep(500);
			if (Loop == 10) {
				driver.navigate().refresh();
				System.out.println("Page refereshed for el to present;");
			}

			if (Loop > 20) {
				Assert.fail();
			}
			System.out.println("inside Wait for Element in Loop: " + Loop);
			Loop++;
		}
		return ElementpresenceCount;
	}

	public int loopAllframesAndReturnCountofElementOld(final By locator) {
		System.out.println("Inside loopAllframesAndReturnCountofElement");

		int ElementpresenceCount = 0;
		int Loop = 0;
		int maxFramaecount = IframeCount();

		ElementpresenceCount = driver.findElements(locator).size();
		System.out.println("ElementpresenceCount: " + ElementpresenceCount);
		if (Loop == 0 && maxFramaecount > 0 && ElementpresenceCount == 0) {
			driver.switchTo().defaultContent();
			ElementpresenceCount = driver.findElements(locator).size();
		}

		ElementpresenceCount = driver.findElements(locator).size();
		while (ElementpresenceCount == 0 && Loop < maxFramaecount) {
			try {
				switchToFrameByInt(Loop);
				ElementpresenceCount = driver.findElements(locator).size();
				System.out.println("Try LoopAllframesAndReturnWebEL Old: " + Loop + "; ElementpresenceCount: "
						+ ElementpresenceCount);
				if (ElementpresenceCount > 0 || Loop > maxFramaecount) {
					break;
				}
			} catch (Exception ex) {
				System.out.println("Catch LoopAllframesAndReturnWebEL Old: " + Loop + "; " + ex);
			}
			Loop++;
			if (ElementpresenceCount > 0 || Loop > maxFramaecount) {
				break;
			}
		}
		return ElementpresenceCount;
	}

	/******************************** wait for elelemnt *********************/

	public int WaitForElement(final By locator, boolean ContinueTrue_FailFalse) throws InterruptedException {
		int ElementpresenceCount = 0;
		int Loop = 0;
		int looplimit = 5;
		System.out.println("WaitForElement locator: " + locator.toString());
		ElementpresenceCount = driver.findElements(locator).size();
		while (ElementpresenceCount < 1) {
			Thread.sleep(500);
			ElementpresenceCount = driver.findElements(locator).size();
			if (Loop > looplimit && ContinueTrue_FailFalse == false) {
				Assert.fail();
			} else if (Loop > looplimit && ContinueTrue_FailFalse == true) {
				break;
			}
			System.out.println(
					"WaitForElement loop: ElementpresenceCount = " + ElementpresenceCount + "; Loop = " + Loop);
			Loop++;
		}
		ElementpresenceCount = driver.findElements(locator).size();
		System.out.println("WaitForElement - element count = " + ElementpresenceCount);
		return ElementpresenceCount;
	}

	public int WaitForElementWithWaitTime(final By locator, int SecondstoWait, boolean ContinueTrue_FailFalse)
			throws InterruptedException {
		int ElementpresenceCount = 0;
		int Loop = 0;
		int looplimit = SecondstoWait;
		System.out.println("WaitForElementWithWaitTime locator: " + locator.toString());
		ElementpresenceCount = driver.findElements(locator).size();
		while (ElementpresenceCount == 0) {
			Thread.sleep(1000);
			ElementpresenceCount = driver.findElements(locator).size();
			if (Loop >= looplimit && ContinueTrue_FailFalse == false) {
				Assert.fail();
			} else if (Loop >= looplimit && ContinueTrue_FailFalse == true) {
				break;
			}
			System.out.println("WaitForElementWithWaitTime loop: ElementpresenceCount = " + ElementpresenceCount
					+ "; Loop = " + Loop);
			Loop++;
		}
		ElementpresenceCount = driver.findElements(locator).size();
		System.out.println("WaitForElementWithWaitTime - element count = " + ElementpresenceCount);
		return ElementpresenceCount;
	}

	public WebElement fluentWait(final By locator) {
		// http://toolsqa.com/selenium-webdriver/advance-webdriver-waits/
		System.out.println("In fluent wait");
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(60, TimeUnit.SECONDS)
				.pollingEvery(1, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		WebElement elwait = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		return elwait;
	}

	public void waitforElementByXpath(String locater) throws InterruptedException {
		int i = 1;
		while (driver.findElements(By.xpath(locater)).size() < 1) {
			System.out.println("************wait for the element*************");
			Thread.sleep(500);
			if (i > 31) {
				break;
			}
			i++;
		}
	}

	/********************** ScrollToElement *******************/

	public void scrollToElement(WebElement element) {
		System.out.println("***ScrollToElement: ***");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border='6px groove green'", element);
	}

	public void scrollToElementBottom(WebElement element) {
		System.out.println("***ScrollToElementBottom:  ***");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
		System.out.println("***ScrollToElementBottom executed; going to hilight el  ***");
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border='6px groove green'", element);
		System.out.println("***ScrollToElementBottom executed; hilight el  executed***");
	}

	public void highlightElement(WebElement element) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border='6px groove green'", element);
		Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border=''", element);
	}

	public void scrollToXY(int x, int y) {
		System.out.println(
				"***ScrollToXY: Move to default Content explicitly. Otherwise it won't work. 'Call switchToDefaultFrame and don't forget to move to your frame.' ***");
		String script = "window.scrollTo(" + x + "," + y + ");";
		((JavascriptExecutor) driver).executeScript(script);
	}

	public int getXLocationOfElement(WebElement element) {
		System.out.println("***GetXLocationOfElement: Don't forget to move to your frame.***");
		Point point = element.getLocation();
		int x = point.getX();
		System.out.println("X Coordination of the Element: " + x);
		return x;
	}

	public int getYLocationOfElement(WebElement element) {
		System.out.println("***GetYLocationOfElement: Don't forget to move to your frame.***");
		Point point = element.getLocation();
		int y = point.getY();
		System.out.println("Y Coordination of the Element: " + y);
		return y;
	}

	public void scrollToBottom() {
		System.out.println(
				"***ScrollToBottom: Move to default Content explicitly. Otherwise it may not work. 'Call switchToDefaultFrame and don't forget to move to your frame.' ***");

		System.out.println("ScrollToBottom: document.body.scrollHeight: "
				+ ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight;"));
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight);");
	}

	public void ScrollToTop() {
		System.out.println(
				"***ScrollToTop: Move to default Content explicitly. Otherwise it may not work. 'Call switchToDefaultFrame and don't forget to move to your frame.' ***");

		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0);");
	}

	public String removeSpaceAndNonAlphaFromString(String inputString) {
		String Output = inputString.replaceAll("[^a-zA-Z]", "");
		return Output;
	}

	public String removeNonAlphaExcludingSpaceFromString(String inputString) {
		String Output = inputString.replaceAll("[^a-z A-Z]", "");
		return Output;
	}

	public String removeNonAlphaNumericFromString(String inputString) {
		String Output = inputString.replaceAll("[^a-z A-Z0-9]", "");
		return Output;
	}

	public String removeNonNumericFromString(String inputString) {
		String Output = inputString.replaceAll("[^0-9]", "");
		return Output;
	}

	public String RemoveSpaceAndAlpha(String inputString) {
		String Output = inputString.replaceAll("[a-z A-Z]", "");
		return Output;
	}

	public void assertEquals(String receivedMessage, String expectedMessage) throws Exception {
		Thread.sleep(2000);
		Assert.assertEquals(receivedMessage, expectedMessage);
	}

	/**** get the response code from html screen ******************/
	public int getResponseCode(String url) {

		try {
			return Request.Get(url).execute().returnResponse().getStatusLine().getStatusCode();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void verifyURLStatus(String URL) {
		try {
			WebClient webClient = new WebClient();
			HtmlPage htmlPage = webClient.getPage(URL);
			System.out.println("Status Code: " + htmlPage.getWebResponse().getStatusCode() + "; Web Response: "
					+ htmlPage.getWebResponse().getStatusMessage());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getResponseCodeRestAssured(String url) {
		System.out.println("getResponseCodeRestAssured");
		boolean exeption = true;
		int rest = 0;
		int counter = 0;
		while (exeption == true) {
			try {
				rest = RestAssured.get(url).statusCode();
				exeption = false;
				counter++;
				System.out.println("getResponseCodeRestAssured: " + rest + "; Counter: " + counter);
				if (counter > 5)
					break;
			} catch (Exception e) {
				System.out.println("Exception in getResponseCodeRestAssured: " + e);
				// throw new RuntimeException(e);
				exeption = true;
			}
		}
		return rest;
	}

	public int getHTTPStatusCode(String url) {

		boolean exeption = true;
		int loop = 0;
		int code = 0;
		while (exeption == true) {
			try {
				URL siteURL = new URL(url);
				HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
				connection.setRequestMethod("GET");

				connection.setInstanceFollowRedirects(false);
				HttpURLConnection.setFollowRedirects(false);

				connection.connect();
				code = connection.getResponseCode();

				exeption = false;
			} catch (Exception e) {
				System.out.println("Exeption: " + e);
				exeption = true;
			}
			loop++;
			if (loop > 20) {
				break;
			}
		}
		System.out.println(url + "\t\tStatus:" + code);
		return code;
	}

	/*********************** Get Inner Text of element *********************/
	public String getInnerText(final By locator, boolean ContinueTrue_FailFalse) throws InterruptedException {
		String innerText = "";
		int ElementpresenceCount = 0;
		int Loop = 0;
		int looplimit = 5;
		System.out.println("GetInnerText locator: " + locator.toString());
		ElementpresenceCount = driver.findElements(locator).size();
		while (ElementpresenceCount < 1) {
			Thread.sleep(1000);
			ElementpresenceCount = driver.findElements(locator).size();
			if (Loop > looplimit && ContinueTrue_FailFalse == false) {
				Assert.fail();
			} else if (Loop > looplimit && ContinueTrue_FailFalse == true) {
				break;
			}
			System.out
					.println("GetInnerText loop: ElementpresenceCount = " + ElementpresenceCount + "; Loop = " + Loop);
			Loop++;
		}
		ElementpresenceCount = driver.findElements(locator).size();
		try {
			innerText = ReturnWebElementByAnyLocator(locator).getAttribute("innerText").trim();
		} catch (Exception ex) {
			System.out.println("Exception while extracting text GetInnerText: " + ex);
		}
		System.out.println("GetInnerText: ElementpresenceCount = " + ElementpresenceCount);
		System.out.println("GetInnerText: innerText = " + innerText);
		return innerText.replaceAll("\u00A0", "");
	}

	public String getInnerTextByWebElement(WebElement we) throws InterruptedException {
		String innerText = "";
		System.out.println("GetInnerText by WebElement: " + we.toString());
		try {
			if (we.isDisplayed() || we.isEnabled() || we.isSelected()) {
				innerText = we.getAttribute("innerText").trim();
			}
		} catch (Exception ex) {
			System.out.println("Exception while extracting text GetInnerText by WebElement: " + ex);
		}
		System.out.println("GetInnerText by WebElement = " + innerText);
		return innerText;
	}

	public String getInnerTextWithWaitTime(final By locator, int SecondsToWait, boolean ContinueTrue_FailFalse)
			throws InterruptedException {
		String innerText = "";
		int ElementpresenceCount = 0;
		int Loop = 0;
		int looplimit = SecondsToWait;
		System.out.println("GetInnerTextWithWaitTime locator: " + locator.toString());
		ElementpresenceCount = driver.findElements(locator).size();
		while (ElementpresenceCount < 1) {
			Thread.sleep(1000);
			ElementpresenceCount = driver.findElements(locator).size();
			if (Loop >= looplimit && ContinueTrue_FailFalse == false) {
				Assert.fail();
			} else if (Loop >= looplimit && ContinueTrue_FailFalse == true) {
				break;
			}
			System.out.println("GetInnerTextWithWaitTime loop: ElementpresenceCount = " + ElementpresenceCount
					+ "; Loop = " + Loop);
			Loop++;
		}
		ElementpresenceCount = driver.findElements(locator).size();
		try {
			innerText = ReturnWebElementByAnyLocator(locator).getAttribute("innerText").trim();
		} catch (Exception ex) {
			System.out.println("Exception while extracting text GetInnerTextWithWaitTime");
		}
		System.out.println("GetInnerTextWithWaitTime: ElementpresenceCount = " + ElementpresenceCount + "\t\t"
				+ "GetInnerTextWithWaitTime: innerText = " + innerText);
		return innerText.trim();
	}

	public WebElement ReturnWebElementByAnyLocator(final By locator) {
		WebElement RetEl = driver.findElement(locator);
		return RetEl;
	}

	/********* Launch browser based on the weekday ****************/
	public void openBrowserBasedOnTheDayname() {
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_WEEK);
		String browserType = null;

		switch (day) {
		case 1:// sunday no need to test
			break;
		case 2:// Monday will use chrome browser
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			browserType = "Chrome";
			break;
		case 3:// Tuesday will use IE browser
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			browserType = "IE";
			break;
		case 4:// webdnesday will use Firefox browser
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			browserType = "Edge";
			break;
		case 5:// thursday will use edge browser
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			browserType = "Firefox";

			break;
		case 6:// thursday will use edge browser
				// Use
			break;
		case 7:// saturday no need to test
			break;
		default:
			System.out.println("browser : " + browserType + " is invalid, Launching Chrome as browser of choice..");
			driver = new ChromeDriver();
		}
		System.out.println(browserType);
	}

	/***************** Get the Weekday *******************/
	public void weekDayName() {
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// System.out.print("Today is ");
		switch (day) {
		case 1:
			System.out.print("Sunday");
			break;
		case 2:
			System.out.print("Monday");
			break;
		case 3:
			System.out.print("Tuesday");
			break;
		case 4:
			System.out.print("Wednesday");
			break;
		case 5:
			System.out.print("Thursday");
			break;
		case 6:
			System.out.print("Friday");
			break;
		case 7:
			System.out.print("Saturday");
		}
	}

	/*
	 * Opens up a connection with the given URL and returns true if the response
	 * code is 200 (success)
	 */
	public boolean isValidURL(String urlString) {
		try {
			URL url = new URL(urlString);
			url.toURI();
			System.out.println("URL is valid");
			return true;

		} catch (Exception exception) {
			System.out.println("URL is NOT valid");
			return false;
		}
	}

	/* POPUP handle: switch To Child Window */

	public String switchToChildWindow(String mainwindowHandle, int retryCount) throws InterruptedException {
		int retry = 0;
		Set<String> allWindowHandles;
		Iterator<String> iterator;
		String currentWindowHandle = "";

		allWindowHandles = driver.getWindowHandles();
		System.out.println("switchToChildWindow now");
		for (retry = 0; retry <= retryCount; retry++) {
			allWindowHandles = driver.getWindowHandles();
			if (allWindowHandles.size() > 1) {
				break;
			} else {
				Thread.sleep(1000);
			}
		}
		if (driver.getWindowHandles().size() > 1) {
			iterator = allWindowHandles.iterator();
			System.out.println("allWindowHandles.size(); :" + allWindowHandles.size());
			while (iterator.hasNext()) {
				currentWindowHandle = iterator.next();
				if (!currentWindowHandle.equals(mainwindowHandle)) {
					driver.switchTo().window(currentWindowHandle);
					System.out.println("Switched to Child Window");
				}
			}
		} else {
			System.out.println("No child window");
			currentWindowHandle = mainwindowHandle;
		}
		return currentWindowHandle;
	}

	/**** Move to a New Tab in Browser ************/

	public String switchToTab(int Tabindex) throws InterruptedException {

		String Tab1 = driver.getWindowHandle();
		ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
		if (!availableWindows.isEmpty()) {
			driver.switchTo().window(availableWindows.get(Tabindex));
		}
		return Tab1;

	}
	
	
	
	
	
	
	

}
