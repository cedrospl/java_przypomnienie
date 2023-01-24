package main.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class HomePageOnet {

    protected static WebDriver driver;

    public HomePageOnet(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[text()='Szanowna Użytkowniczko, Szanowny Użytkowniku,']")
    private WebElement homeCookieDialogHeader;

    @FindBy(xpath = "//span[text()='Ustawienia zaawansowane']")
    private WebElement homeCookieFirstStepAcceptButton;

    @FindBy(xpath = "//span[text()='Nie wyrażam zgody']")
    private WebElement homeCookieSecondStepAcceptButton;

    @FindBy(xpath = "//span[text()='Przejdź do serwisu']")
    private WebElement homeCookieButtonAcceptText;

    @FindBy(xpath = "//span[text()='Jutro']")
    private WebElement homeWeatherText;

    public String getCookieDialogHeader() {
        System.out.println("Getting Cookie Dialog Header Text");
        return homeCookieDialogHeader.getText();
    }

    public String getLogoText() {
        System.out.println("Getting Onet Weather Info Tomorrow Text");
        return homeWeatherText.getText();
    }

    public String acceptCookies2() {
        homeCookieFirstStepAcceptButton.click();
        homeCookieSecondStepAcceptButton.click();
        return getLogoText();
    }

    public void getArticleLinksOpenInTabsAndSavetoFile() throws IOException {
        System.out.println("Saving article links to the list");
        /**
         Change "ska" to whatever text you want to search it on www.onet.pl site
         */
        List<WebElement> searchByPhrase = driver.findElements(By.partialLinkText("ska"));
        WebElement phraseSearch;
        String singleArticle;
        List<String> articleLinks = new ArrayList<>();
        for (WebElement webElement : searchByPhrase) {
            phraseSearch = webElement;
            singleArticle = phraseSearch.getAttribute("href");
            articleLinks.add(singleArticle);
        }
        System.out.println("Opening a new tab with the article link from the list");
        for (String articleLink : articleLinks) {
            driver.switchTo().newWindow(WindowType.TAB);
            driver.navigate().to(articleLink);
        }
        System.out.println("Saving Article Links");
        /**
         You can also put a path to other folder in the project to save a file
         */
        Path dest = Paths.get("SavedArticleLinks.csv");
        try {
            Path path = Files.write(dest, articleLinks, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
            System.out.println("Saved file" + path.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}