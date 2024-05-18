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
public class EmagCartPage extends PageObject {
    @FindBy(id="searchboxTrigger")
    private WebElementFacade searchTerms;

    @FindBy(className="btn btn-default searchbox-submit-button")
    private WebElementFacade lookupButton;

    @FindBy(xpath = "//button[@class='btn btn-sm btn-primary btn-emag btn-block yeahIWantThisProduct']")
    public WebElementFacade cartButton;


    @FindBy(xpath = "//a[@id='my_cart']")
    private WebElementFacade allcartButton;

    @FindBy(className="main-product-title-container")
    private WebElementFacade itemLink;

    public void enter_keywords(String keyword) {
        searchTerms.type(keyword);
    }

    public void lookup_terms() {
        searchTerms.sendKeys(Keys.ENTER);
    }

    public void add_to_cart(){
        if (cartButton != null) {
            System.out.println("Element found successfully");
            cartButton.click();
        } else {
            System.out.println("Element not found");
        }
    }

    public void click_on_cart(){
        if (allcartButton != null) {
            System.out.println("Cart button found successfully");
            allcartButton.click();
        } else {
            System.out.println("Cart button not found");
        }
    }

    public String getItemLinkText() {
        WebElement itemLinkElement = getDriver().findElement(By.xpath("//a[@class='cart-widget cart-line']"));
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

    public List<String> getCartItems(){
        WebElement itemList = getDriver().findElement(By.id("cart-products"));
        return itemList.findElements(By.tagName("a")).stream().map(element -> element.getText()).collect(Collectors.toList());
    }

    public String get_empty_cart(){
        WebElement noProductElement = getDriver().findElement(By.id("cart-products"));
        return noProductElement.getText();
    }

}
