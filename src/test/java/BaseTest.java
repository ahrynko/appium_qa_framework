import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    private WebDriver webDriver;  // create webdriver (interface)

    public WebDriver getWebDriver() {
        return webDriver;
    }

    @Before
    public void initDriver() throws MalformedURLException {
        final DesiredCapabilities desiredCapabilities = new DesiredCapabilities();   // settings
        desiredCapabilities.setCapability("platformName","Android");
        desiredCapabilities.setCapability("platformVersion","7");  //9
        desiredCapabilities.setCapability("deviceName","Xperia XA Dual_62c1"); // Redmi
        desiredCapabilities.setCapability("udid","RQ3005VDRM");  // 05f9990c0006
        desiredCapabilities.setCapability("browserName","Chrome");

        webDriver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities); //implement =AndroidDriver
    }

    @After
    public void tearDownDriver() {
        webDriver.close();
        webDriver.quit();
    }
}


