import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class GoogleSiteTests {

    private WebDriver webDriver;  // create webdriver (interface)
    private AppiumServer appiumServer = new AppiumServer();  // object

    @Before
    public void initDriver() throws MalformedURLException {
        final DesiredCapabilities desiredCapabilities = new DesiredCapabilities();   // settings
        desiredCapabilities.setCapability("platformName","Android");
        desiredCapabilities.setCapability("platformVersion","9");  //9
        desiredCapabilities.setCapability("deviceName","Redmi"); // Redmi
        desiredCapabilities.setCapability("udid","05f9990c0006");  // 05f9990c0006
        desiredCapabilities.setCapability("browserName","Chrome");

        if(!appiumServer.isRunning(4723)) {  // если не запущен appiumServer
            appiumServer.startAppiumService(desiredCapabilities);
        } else {
            System.out.println("The server is running now!");
        }

        webDriver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities); //implement =AndroidDriver
    }

    @Test
    public void verifyGoogleSiteTitle() {
        webDriver.get("https://google.com");
        final String title = webDriver.getTitle();
        Assert.assertEquals("There is incorrect title displayed!","Google", title);

    }
    @After
    public void tearDownDriver() {
        appiumServer.shutDownAppiumService();
    }
}
