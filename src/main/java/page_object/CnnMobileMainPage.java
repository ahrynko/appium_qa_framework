package page_object;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

public class CnnMobileMainPage {

    @FindBy(xpath = "//button[@data-test='menuButton']")
    private WebElement burgerMenuButton;

    @FindBy(xpath = "//input[@id='header-search-bar']")
    private WebElement searchField;

    private WebDriver webDriver;
    private WebDriverWait webDriverWait;

    public CnnMobileMainPage(final WebDriver webDriver) { //протащили webDriver в конструктор
        this.webDriver = webDriver; // скопировали себе
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        PageFactory.initElements(webDriver, this);
        this.webDriverWait = new WebDriverWait(webDriver, Duration.of(30, ChronoUnit.SECONDS).getSeconds());
    }

    public void clickBurgerMenuButton() {
        burgerMenuButton.click();
        webDriverWait.until(ExpectedConditions.visibilityOf(searchField)); // подождали когда появиться search
    }

    public CnnMobileSearchResultsPage searchFor(final String text) {
        searchField.sendKeys(text, Keys.ENTER);
        return new CnnMobileSearchResultsPage(webDriver);
    }
}
