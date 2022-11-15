package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    protected static WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h3[text()='Cenimy Twoją prywatność']")
    private WebElement homeCookieDialogHeader;

    @FindBy(xpath = "//button[text()='AKCEPTUJĘ I PRZECHODZĘ DO SERWISU']")
    private WebElement homeCookieButtonAcceptText;

    @FindBy(xpath = "//a[text()='TYM ŻYJE POLSKA']")
    private WebElement homeWPNewsHeader;

    public String getCookieDialogHeader() {
        System.out.println("Getting Cookie Dialog Header Text");
        return homeCookieDialogHeader.getText();
    }

    public String getLogoText() {
        System.out.println("Getting WP News Header Text");
        return homeWPNewsHeader.getText();
    }

    public String acceptCookies() {
        homeCookieButtonAcceptText.click();
        return getLogoText();
    }

}
