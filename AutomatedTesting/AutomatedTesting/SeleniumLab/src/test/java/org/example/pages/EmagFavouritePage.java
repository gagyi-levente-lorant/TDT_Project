package org.example.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

@DefaultUrl("https://www.emag.ro/")
public class EmagFavouritePage extends PageObject {
    @FindBy(id="searchboxTrigger")
    private WebElementFacade searchTerms;

    @FindBy(className="btn btn-default searchbox-submit-button")
    private WebElementFacade lookupButton;

    @FindBy(xpath = "//button[@class='add-to-favorites btn']")
    private WebElementFacade favouriteButton;


    @FindBy(xpath = "//a[@id='my_wishlist']")
    private WebElementFacade wishlistButton;

    @FindBy(className="card-item card-standard js-product-data card-favorites w-100")
    private WebElementFacade itemLink;

    public void enter_keywords(String keyword) {
        searchTerms.type(keyword);
    }

    public void lookup_terms() {
        searchTerms.sendKeys(Keys.ENTER);
    }

    public void add_to_favourite(){
        if (favouriteButton != null) {
            System.out.println("Element found successfully");
            favouriteButton.click();
        } else {
            System.out.println("Element not found");
        }
    }

    public void click_on_wishlist(){
        if (wishlistButton != null) {
            System.out.println("Wishlist button found successfully");
            wishlistButton.click();
        } else {
            System.out.println("Wishlist button not found");
        }
    }

    public String getItemLinkText() {
        WebElement itemLinkElement = getDriver().findElement(By.xpath("//a[@class='card-v2-title semibold mrg-btm-xs js-product-url ']"));
        if (itemLinkElement != null) {
            String href = itemLinkElement.getAttribute("href");
            if (href != null && !href.isEmpty()) {
                System.out.println("Item link found successfully");
                System.out.println("Href: " + href);
                return href;
            } else {
                System.out.println("Href attribute is empty");
                return null;
            }
        } else {
            System.out.println("Item link element not found");
            return null;
        }
    }

    public List<String> getFavouriteItems(){
        WebElement itemList = getDriver().findElement(By.id("list-of-favorites"));
        return itemList.findElements(By.tagName("a")).stream().map(element -> element.getText()).collect(Collectors.toList());
    }

    public String get_empty_favourites(){
        WebElement noProductElement = getDriver().findElement(By.xpath("//h3[@class='mrg-btm-none']"));
        return noProductElement.getText();
    }

}
