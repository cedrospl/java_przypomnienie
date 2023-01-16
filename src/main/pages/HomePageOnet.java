package main.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    public void getArticleLinksOpenThemInDifferentTabsAndSaveLinkstoFile() throws IOException {
        System.out.println("Saving article links to the list");
        List<WebElement> phraseSearch2 = driver.findElements(By.partialLinkText("ska"));
        WebElement q;
        String k;
        List<String> articleLinks = new ArrayList<>();
        for (WebElement webElement : phraseSearch2) {
            q = webElement;
            k = q.getAttribute("href");
            articleLinks.add(k);
        }
        System.out.println("Opening a new tab with the article link from the list");
        for (String articleLink : articleLinks) {
            driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
            driver.get(String.valueOf(articleLink));

        }
        System.out.println("Saving Article Links ");
        Path dest = Paths.get("SavedArticleLinks.java"); // można też podać patha do innego folderu w projekcie
        try {
            Path p = Files.write(dest, articleLinks, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
            System.out.println("Saved file" + p.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
