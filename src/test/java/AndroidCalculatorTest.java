import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidCalculatorTest {

    private AppiumDriver<AndroidElement> appiumDriver;

    @Before
    public void initDriver() throws MalformedURLException {

        final DesiredCapabilities desiredCapabilities = new DesiredCapabilities();   // settings
        desiredCapabilities.setCapability("platformName","Android");
//        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET,"false");
        desiredCapabilities.setCapability("platformVersion","9");  //9  //7
        desiredCapabilities.setCapability("deviceName","Redmi"); // Redmi  //Xperia XA Dual_62c1
        desiredCapabilities.setCapability("udid","05f9990c0006");  // 05f9990c0006  //RQ3005VDRM

        desiredCapabilities.setCapability("appPackage","com.miui.calculator");  //package
        desiredCapabilities.setCapability("appActivity","com.miui.calculator.cal.CalculatorActivity"); //page
//        desiredCapabilities.setCapability("automationName","UiAutomator1");
        desiredCapabilities.setCapability("autoGrantPermissions","true");

        appiumDriver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities); //implement = AndroidDriver
    }

    @Test
    public void verifyAndroidCalculatorSumTest() {
        WebDriverWait driverWait = new WebDriverWait(appiumDriver, 20);

        WebElement acceptButton  = driverWait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//android.widget.Button[2]")));

        Assert.assertTrue(acceptButton.isDisplayed());
        acceptButton.click();

        WebElement digitFive  = driverWait.until(ExpectedConditions.presenceOfElementLocated(
                By.id("com.miui.calculator:id/btn_5_s")));
        digitFive.click();

        WebElement signPlus  = driverWait.until(ExpectedConditions.presenceOfElementLocated(
                By.id("com.miui.calculator:id/btn_plus_s")));
        signPlus.click();

        WebElement digitSeven  = driverWait.until(ExpectedConditions.presenceOfElementLocated(
                By.id("com.miui.calculator:id/btn_7_s")));
        digitSeven.click();

        WebElement equals  = driverWait.until(ExpectedConditions.presenceOfElementLocated(
                By.id("com.miui.calculator:id/btn_equal_s")));
        equals.click();

        WebElement actualSumResult = driverWait.until(ExpectedConditions.presenceOfElementLocated(
                By.id("com.miui.calculator:id/result")));
        String expectedSumResult = "= 12";

        Assert.assertEquals("Wrong amount in calculator", expectedSumResult, actualSumResult.getText());
    }

    @After
    public void tearDownDriver() {
        appiumDriver.quit();
    }

}


