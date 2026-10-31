
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {

    WebDriver driver;
    WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators

    private By productsMenu =
            By.xpath("//a[@href='/inventory/product']");

    private By addProductButton =
            By.xpath("//button[normalize-space()='Add Product']");

    private By productName = By.name("name");

    private By categoryDropdown =
            By.id("react-select-product_category_inline_search-input");

    private By productPrice = By.name("price");

    private By publishProductButton =
            By.xpath("//button[@type='submit' and normalize-space()='Publish product']");

    private By viewProductButton =
            By.xpath("//button[.//*[name()='svg' and contains(@class,'lucide-eye')]]");
    private By productNameError =
            By.xpath("//p[normalize-space()='Product name is required']");


    // Actions

    public void clickProductsMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(productsMenu)).click();
    }

    public void clickAddProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(addProductButton)).click();
    }

    public void enterProductName(String name) {

        WebElement nameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(productName)
        );

        nameField.clear();
        nameField.sendKeys(name);
    }

    public void selectCategory(String category) {

        WebElement categoryField = wait.until(
                ExpectedConditions.elementToBeClickable(categoryDropdown)
        );

        categoryField.click();
        categoryField.sendKeys(category);

        By categoryOption = By.xpath(
                "//*[@role='option' and normalize-space()='" + category + "']"
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(categoryOption)
        ).click();
    }

    public void enterProductPrice(String price) {

        WebElement priceField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(productPrice)
        );

        priceField.clear();
        priceField.sendKeys(price);
    }

    public void selectPackageType(String packageType) {

        By packageButton = By.xpath(
                "//button[.//div[normalize-space()='" + packageType + "']]"
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(packageButton)
        ).click();
    }

    public void clickPublishProduct() {
        wait.until(
                ExpectedConditions.elementToBeClickable(publishProductButton)
        ).click();
    }

    public void clickViewProduct() {
        wait.until(
                ExpectedConditions.elementToBeClickable(viewProductButton)
        ).click();
    }
    public boolean isProductNameErrorDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(productNameError)
        ).isDisplayed();
    }
}
