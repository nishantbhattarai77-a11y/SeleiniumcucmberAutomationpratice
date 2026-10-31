
package pages;

import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginTest {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {
            // Create LoginPage object
            LoginPage login = new LoginPage(driver);

            // Open GoBuild
            login.launch();

            Dotenv dotenv = Dotenv.configure()
                    .directory(System.getProperty("user.dir"))
                    .load();

            String username = dotenv.get("GOBUILD_USERNAME");
            String password = dotenv.get("GOBUILD_PASSWORD");

            if (username == null || password == null) {
                throw new IllegalStateException(
                        "Check GOBUILD_USERNAME and GOBUILD_PASSWORD in .env"
                );
            }

            // Enter Login Credentials
            login.entersLoginCredentials(username, password);

            // Click Sign In
            login.clickLoginButton();

            // Wait for organization selection
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(d -> d.getPageSource().contains("Vyper cloths"));

            // Select Organization
            login.selectOrganization("Vyper cloths");

            // Wait for dashboard to load
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(d -> d.getPageSource().contains("Dashboard"));

        } finally {
            // Close Browser
            driver.quit();
        }
    }
}
