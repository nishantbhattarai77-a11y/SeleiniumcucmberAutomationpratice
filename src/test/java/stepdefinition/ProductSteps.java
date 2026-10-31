
package stepdefinition;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.ProductPage;

import static org.junit.Assert.assertTrue;

public class ProductSteps {

    WebDriver driver;
    LoginPage login;
    ProductPage product;

    // Background
    @Given("user is logged in and navigates to Products page")
    public void userIsLoggedInAndNavigatesToProductsPage() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        login = new LoginPage(driver);
        login.launch();

        Dotenv dotenv = Dotenv.configure()
                .directory(System.getProperty("user.dir"))
                .load();

        String username = dotenv.get("GOBUILD_USERNAME");
        String password = dotenv.get("GOBUILD_PASSWORD");

        if (username == null || password == null) {
            throw new RuntimeException("GoBuild credentials not found in .env file");
        }

        login.entersLoginCredentials(username, password);
        login.clickLoginButton();
        login.selectOrganization("Vyper cloths");

        product = new ProductPage(driver);
        product.clickProductsMenu();
    }

    // Product page visibility
    @Then("user is able to see Products page")
    public void userIsAbleToSeeProductsPage() {
        assertTrue(
                "Products page is not displayed",
                driver.getCurrentUrl().contains("/inventory/product")
        );
    }

    // Add Product
    @Given("user clicks on Add Product button")
    public void userClicksOnAddProductButton() {
        product.clickAddProduct();
    }

    // Product name
    @When("user enters product name {string}")
    public void userEntersProductName(String name) {
        product.enterProductName(name);
    }

    // Category
    @When("user selects category {string}")
    public void userSelectsCategory(String category) {
        product.selectCategory(category);
    }

    // Price
    @When("user enters product price {string}")
    public void userEntersProductPrice(String price) {
        product.enterProductPrice(price);
    }

    // Package type
    @When("user selects package type {string}")
    public void userSelectsPackageType(String packageType) {
        product.selectPackageType(packageType);
    }

    // Publish
    @When("user clicks on Publish Product button")
    public void userClicksOnPublishProductButton() {
        product.clickPublishProduct();
    }

    // Successful product creation
    @Then("verify product is created successfully")
    public void verifyProductIsCreatedSuccessfully() {
        // Add a real success message or product-list assertion here.
    }

    // Negative product creation
    @Then("verify product creation is rejected")
    public void verifyProductCreationIsRejected() {
        assertTrue(
                "Product name validation message was not displayed",
                product.isProductNameErrorDisplayed()
        );
    }

    // Close browser after every scenario
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    @After
    public void tearDown(Scenario scenario) {

        if (driver != null) {

            if (scenario.isFailed()) {

                System.out.println("FAILED SCENARIO: " + scenario.getName());

                byte[] screenshot = ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.BYTES);

                scenario.attach(
                        screenshot,
                        "image/png",
                        "Failure Screenshot"
                );
            }

            driver.quit();
        }
    }
}
