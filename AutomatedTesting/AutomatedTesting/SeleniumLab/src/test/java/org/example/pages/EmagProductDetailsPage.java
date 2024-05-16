package org.example.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

@DefaultUrl("https://www.emag.ro/")
public class EmagProductDetailsPage extends PageObject {

    @FindBy(id = "searchboxTrigger")
    WebElement searchBox;

    @FindBy(css = "button[type='submit']")
    WebElement searchButton;

    @FindBy(css = ".card-item .card-v2-thumb")
    List<WebElement> productThumbnails;

    @FindBy(css = ".page-title")
    WebElement productTitle;

    @FindBy(css = ".title-phrasing.title-phrasing-sm.text-danger")
    WebElement noProductsFoundMessage;

    @FindBy(xpath = "//div[@class='highlight-box']//p[@class='product-new-price has-deal']")
    WebElement productPrice;

    public void enter_keywords(String keyword) {
        searchBox.clear();
        searchBox.sendKeys(keyword);
    }

    public void lookup_terms() {
        searchBox.sendKeys(Keys.ENTER);
    }

    public void view_first_product_details() {
        productThumbnails.get(0).click();
    }

    public void should_see_product_title_contains(String keyword) {
        assertThat(productTitle.getText(), containsString(keyword));
    }

    public void should_see_no_products_found_message(String expectedMessage) {
        assertThat(noProductsFoundMessage.getText(), is(expectedMessage));
    }

    public void should_see_product_price(String expectedPrice) {
        assertThat(productPrice.getText(), containsString(expectedPrice));
    }
}
