
package pages;

import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ProductTest {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {

            // Load login credentials
            Dotenv dotenv = Dotenv.configure()
                    .directory(System.getProperty("user.dir"))
                    .load();

            String username = dotenv.get("GOBUILD_USERNAME");
            String password = dotenv.get("GOBUILD_PASSWORD");

            // STEP 1: LOGIN
            LoginPage login = new LoginPage(driver);

            login.launch();
            login.entersLoginCredentials(username, password);
            login.clickLoginButton();
            login.selectOrganization("Vyper cloths");

            // STEP 2: CREATE PRODUCT
            ProductPage product = new ProductPage(driver);

            product.clickProductsMenu();
            product.clickAddProduct();

            product.enterProductName("Ashmi");
            product.selectCategory("Smart phone");
            product.enterProductPrice("550");
            product.selectPackageType("small");

            // STEP 3: PUBLISH PRODUCT
            product.clickPublishProduct();

            // View product
            product.clickViewProduct();

        } finally {
            driver.quit();
        }
    }
}
