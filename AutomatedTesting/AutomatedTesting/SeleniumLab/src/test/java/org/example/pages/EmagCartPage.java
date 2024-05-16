package org.example.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@DefaultUrl("https://www.emag.ro/")
public class EmagCartPage extends PageObject {

    @FindBy(id = "searchboxTrigger")
    WebElement searchBox;

    @FindBy(css = "button[type='submit']")
    WebElement searchButton;

    @FindBy(css = ".card-item .card-v2-thumb")
    List<WebElement> productThumbnails;

    @FindBy(xpath = "//button[contains(@class, 'yeahIWantThisProduct')]")
    List<WebElement> addToCartButtons;

    @FindBy(id = "my_cart")
    WebElement cartIcon;

    @FindBy(css = ".cart-products")
    WebElement cartProducts;

    @FindBy(css = ".cart-product-name")
    List<WebElement> cartProductNames;

    @FindBy(css = ".cart-product-quantity")
    List<WebElement> cartProductQuantities;

    @FindBy(css = ".cart-empty-message")
    WebElement emptyCartMessage;

    public void enter_keywords(String keyword) {
        searchBox.clear();
        searchBox.sendKeys(keyword);
    }

    public void lookup_terms() {
        searchBox.sendKeys(Keys.ENTER);
    }

    public void add_to_cart() {
        // Assuming the first product's "Add to Cart" button is the first in the list
        addToCartButtons.get(0).click();
    }

    public void goto_cart() {
        cartIcon.click();
    }

    public void should_see_product_in_cart(String product) {
        boolean productFound = cartProductNames.stream()
                .anyMatch(element -> element.getText().contains(product));
        assertThat("Product is in the cart", productFound, is(true));
    }

    public void should_see_product_quantity_in_cart(String product, int quantity) {
        for (int i = 0; i < cartProductNames.size(); i++) {
            if (cartProductNames.get(i).getText().contains(product)) {
                int actualQuantity = Integer.parseInt(cartProductQuantities.get(i).getText());
                assertThat("Product quantity is correct", actualQuantity, equalTo(quantity));
                break;
            }
        }
    }

    public void remove_from_cart(String product) {
        WebElement productElement = cartProductNames.stream()
                .filter(element -> element.getText().contains(product))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found in cart"));
        productElement.findElement(By.cssSelector(".remove-from-cart")).click();
    }

    public void should_not_see_product_in_cart(String product) {
        boolean productFound = cartProductNames.stream()
                .anyMatch(element -> element.getText().contains(product));
        assertThat("Product is not in the cart", productFound, is(false));
    }

    public void clear_cart() {
        WebElement clearCartButton = find(By.cssSelector(".clear-cart"));
        clearCartButton.click();
    }

    public void should_see_empty_cart() {
        assertThat(emptyCartMessage.isDisplayed(), is(true));
    }
}
