package org.example.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

@DefaultUrl("https://www.emag.ro/")
public class EmagFilterPage extends PageObject {
    @FindBy(id="searchboxTrigger")
    private WebElementFacade searchTerms;

    @FindBy(className="btn btn-default searchbox-submit-button")
    private WebElementFacade lookupButton;

    @FindBy(xpath = "//*[@data-id=1]")
    private WebElementFacade laptopsSection;


    @FindBy(css = "//a[@id='my_wishlist']")
    private WebElementFacade wishlistButton;

    public void enter_keywords(String keyword) {
        searchTerms.type(keyword);
    }

    public void lookup_terms() {
        //lookupButton.click();
        searchTerms.sendKeys(Keys.ENTER);
    }

    public void lookup_laptops() {
        laptopsSection.click();
    }

    public void lookup_laptop_subsection(){
        WebElementFacade firstLink = find(By.cssSelector("ul.emg-aside-links li:first-child a"));
        firstLink.click();
    }

    public void check_filter(int optionId){
        WebElementFacade checkBox = find(By.cssSelector("div.filter-body.js-scrollable a[data-option-id='" + optionId + "']"));


        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", checkBox);
        js.executeScript("window.scrollBy(0,-100)");


        checkBox.click();

        try {
            Thread.sleep(1000); // Delay for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void boudary_value_check(int value){

        WebElementFacade minInputField = find(By.cssSelector("input.js-custom-price-min"));

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", minInputField);
        js.executeScript("window.scrollBy(0,-100)");

        minInputField.clear(); // Clear any existing value in the input field
        minInputField.sendKeys(String.valueOf(value)); // Input the desired value

        minInputField.sendKeys(Keys.ENTER);
        try {
            Thread.sleep(500); // Delay for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getFirstText(){
    WebElement itemLinkElement = getDriver().findElement(By.className("card-v2-thumb"));

        if (itemLinkElement != null) {
            WebElement imgElement = itemLinkElement.findElement(By.tagName("img"));
            if (imgElement != null) {
                String alt = imgElement.getAttribute("alt");
                if (alt != null && !alt.isEmpty()) {
                    System.out.println("Alt attribute found successfully");
                    System.out.println("Alt text: " + alt);
                    return alt;
                } else {
                    System.out.println("Alt attribute is empty");
                    return null;
                }
            } else {
                System.out.println("Image element not found within item link element");
                return null;
            }
        } else {
            System.out.println("Item link element not found");
            return null;
        }



    }



}
