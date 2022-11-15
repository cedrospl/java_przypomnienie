import org.junit.Test;
import pages.HomePage;

import static org.junit.Assert.assertEquals;

public class WpTest extends BaseTest {
    @Test
    public void testOpenBrowserWithWP() {

        HomePage homePage = new HomePage(driver);

        assertEquals("Cenimy Twoją prywatność", homePage.getCookieDialogHeader());
        assertEquals("TYM ŻYJE POLSKA", homePage.acceptCookies());
    }
}
