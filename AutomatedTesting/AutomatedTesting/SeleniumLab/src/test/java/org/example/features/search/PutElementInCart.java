package org.example.features.search;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.example.steps.serenity.EndUserStepsEmagCart;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.example.pages.EmagCartPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.util.Arrays;
import org.openqa.selenium.By;


@RunWith(SerenityRunner.class)
public class PutElementInCart {

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\andre\\Desktop\\tdt\\chromedriver.exe");
    }

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public EndUserStepsEmagCart user;
    @Steps
    public EmagCartPage emagCartPage;
    @Issue("#Emag-Cart")
    @Test
    public void search_and_cart_s24() {
        user.is_the_home_page();
        user.looks_for("s24");
        user.add_item_to_cart();
        // Wait for the cart button to be clickable before clicking on it
//        WebDriverWait wait = new WebDriverWait(webdriver, 20); // Increase the wait time
//        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(emagCartPage.cartButton));
//        element.click();
        user.goto_cart();
        user.check_item_in_cart("s24");
    }

    @Test
    public void search_and_cart_s24_invalid() {
        user.is_the_home_page();
        user.looks_for("iphone 15");
        user.add_item_to_cart();
        user.goto_cart();
        user.check_item_not_in_cart("s24");
    }

    @Test
    public void search_and_cart_s24_and_iphone_valid() {
        user.is_the_home_page();
        user.looks_for("iphone 13");
        user.add_item_to_cart();
        user.looks_for("s24");
        user.add_item_to_cart();
        user.goto_cart();
        user.contains_multiple_item(Arrays.asList("S24", "iPhone 13"));
    }

    @Test
    public void search_and_cart_s24_and_iphone_invalid() {
        user.is_the_home_page();
        user.looks_for("iphone 13");
        user.add_item_to_cart();
        user.looks_for("s24");
        user.add_item_to_cart();
        user.goto_cart();
        user.contains_multiple_item_invalid(Arrays.asList("S24", "huawei 13"));
    }

    @Test
    public void cart_is_empty() {
        user.is_the_home_page();
        user.goto_cart();
        user.should_be_empty();
    }
}
