
package stepdefinition;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import static org.testng.AssertJUnit.assertTrue;

public class LoginStep {

    WebDriver driver;
    LoginPage login;

    @Given("user navigates to login page")
    public void user_navigates_to_login_page() {
        driver = new ChromeDriver();
        login = new LoginPage(driver);
        login.launch();
    }

    @Given("user enters valid login credentials")
    public void user_enters_valid_login_credentials() {

        Dotenv dotenv = Dotenv.configure()
                .directory(System.getProperty("user.dir"))
                .load();

        String username = dotenv.get("GOBUILD_USERNAME");
        String password = dotenv.get("GOBUILD_PASSWORD");

        if (username == null || password == null) {
            throw new IllegalStateException(
                    "Credentials not found. Check your .env file."
            );
        }

        login.entersLoginCredentials(username, password);
    }

    @Given("user enters username {string} and password {string}")
    public void user_enters_credentials(String username, String password) {
        login.entersLoginCredentials(username, password);
    }

    @When("user clicks on Login button")
    public void user_clicks_login_button() {
        login.clickLoginButton();
    }

    @When("user selects organization {string}")
    public void user_selects_organization(String organization) {
        login.selectOrganization(organization);
    }

    @Then("verify user is on dashboard")
    public void verify_user_is_on_dashboard() {
        assertTrue(
                "Dashboard should be visible",
                login.isDashboardDisplayed()
        );
    }

    @Then("user is able to see login page")
    public void verify_login_page() {
        assertTrue(
                "Login page should be visible",
                login.isLoginPageDisplayed()
        );
    }

    @Then("verify error message is displayed")
    public void verify_error_message() {
        assertTrue(
                "Login error message should be visible",
                login.isErrorMessageDisplayed()
        );
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
