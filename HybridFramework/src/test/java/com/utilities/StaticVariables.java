/**
 * 
 */
package com.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.Calendar;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

/**
 * @author sreenivasula.reddy
 *
 */
public class StaticVariables {

	public static String Browser = "";
	public static String appUrl = "https://www.toyota.co.uk/";
	public static WebDriver driver; 

	public static String ProjectDir = "";
	public static String ScreenshotsPath = "";

	public static String CCFCResultsPath = "";

	public static String ClassName = "";
	public static String MethodName = "";

}
