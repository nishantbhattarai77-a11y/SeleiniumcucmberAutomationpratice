
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    private By username = By.name("username");
    private By password = By.name("password");

    private By signinButton = By.xpath(
            "//button[@type='submit' and normalize-space()='Sign in']"
    );

    private By errorMessage = By.xpath(
            "//div[normalize-space()='Login failed. Please check your credentials and try again.']"
    );

    private By dashboard = By.xpath(
            "//*[normalize-space()='Dashboard']"
    );

    // Launch Website
    public void launch() {
        driver.manage().window().maximize();
        driver.get("https://app.gobuild.live/");
    }

    // Enter Credentials
    public void entersLoginCredentials(String user, String pass) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(username))
                .sendKeys(user);

        wait.until(ExpectedConditions.visibilityOfElementLocated(password))
                .sendKeys(pass);
    }

    // Click Sign In
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(signinButton))
                .click();
    }

    // Select Organization
    public void selectOrganization(String organizationName) {
        By organizationButton = By.xpath(
                "//button[.//span[normalize-space()='" + organizationName + "']]"
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(organizationButton)
        ).click();
    }

    // Verify Dashboard
    public boolean isDashboardDisplayed() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(dashboard)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Verify Login Page
    public boolean isLoginPageDisplayed() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(username)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Verify Invalid Login Error
    public boolean isErrorMessageDisplayed() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(errorMessage)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Close Browser
    public void closeBrowser() {
        driver.quit();
    }
}
