
import org.junit.Assert;
import org.junit.Test;

public class GoogleSiteTests extends BaseTest{

    @Test
    public void verifyGoogleSiteTitle() {
        getWebDriver().get("https://google.com");
        final String title = getWebDriver().getTitle();
        Assert.assertEquals("There is incorrect title displayed!","Google", title);
    }
}
