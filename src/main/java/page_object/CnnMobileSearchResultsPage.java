package page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class CnnMobileSearchResultsPage {

    private WebDriver webDriver;

    public CnnMobileSearchResultsPage(final WebDriver webDriver) {  //протащили webDriver в конструктор
        this.webDriver = webDriver; // скопировали себе
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        PageFactory.initElements(webDriver, this);
    }

    public String getPageHtmlSourceCode() {
        return webDriver.getPageSource();  // возврат Html
    }
}
