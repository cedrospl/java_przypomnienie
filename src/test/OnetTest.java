import main.pages.HomePageOnet;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class OnetTest extends BaseTest {
    @Test
    public void testOpenBrowserWithOnet() throws IOException {
        HomePageOnet homePage = new HomePageOnet(driver);
        assertEquals("Szanowna Użytkowniczko, Szanowny Użytkowniku,", homePage.getCookieDialogHeader());
        assertEquals("Jutro", homePage.acceptCookies2());
        /**Change "ska" to whatever text you want to search it on www.onet.pl site*/
        homePage.getArticleLinksOpenInTabsAndSaveToFile("ska");
    }
}
