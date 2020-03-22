import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidCalculatorTest {

    private AppiumDriver<AndroidElement> appiumDriver;

    @Before
    public void initDriver() throws MalformedURLException {
        final DesiredCapabilities desiredCapabilities = new DesiredCapabilities();   // settings
        desiredCapabilities.setCapability("platformName","Android");
//        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET,"false");
        desiredCapabilities.setCapability("platformVersion","9");  //9
        desiredCapabilities.setCapability("deviceName","Redmi"); // Redmi
        desiredCapabilities.setCapability("udid","05f9990c0006");  // 05f9990c0006
        desiredCapabilities.setCapability("appPackage","com.miui.calculator");  //package
        desiredCapabilities.setCapability("appActivity","com.miui.calculator.cal.CalculatorActivity"); //page

        appiumDriver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities); //implement =AndroidDriver
    }

    @Test
    public void verifyAndroidCalculatorSumTest() {

    }
}
