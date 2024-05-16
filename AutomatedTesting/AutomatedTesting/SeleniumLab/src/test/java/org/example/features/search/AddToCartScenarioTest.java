package org.example.features.search;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.example.steps.serenity.EndUserStepsEmagCart;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class AddToCartScenarioTest {

    @Managed
    WebDriver driver;

    @Steps
    EndUserStepsEmagCart endUser;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\stefa\\Desktop\\ss\\chromedriver.exe");
    }

    @Test
    public void add_single_product_to_cart() {
        endUser.is_the_home_page();
        endUser.search_for("Laptop");
        endUser.add_to_cart();
        endUser.goto_cart();
        endUser.should_see_product_in_cart("Laptop");
    }

    @Test
    public void add_multiple_products_to_cart() {
        endUser.is_the_home_page();
        endUser.search_for("Laptop");
        endUser.add_to_cart();
        endUser.search_for("Mouse");
        endUser.add_to_cart();
        endUser.goto_cart();
        endUser.should_see_products_in_cart("Laptop", "Mouse");
    }

    @Test
    public void add_same_product_multiple_times() {
        endUser.is_the_home_page();
        endUser.search_for("Laptop");
        endUser.add_to_cart();
        endUser.add_to_cart();
        endUser.goto_cart();
        endUser.should_see_product_quantity_in_cart("Laptop", 2);
    }

    @Test
    public void remove_product_from_cart() {
        endUser.is_the_home_page();
        endUser.search_for("Laptop");
        endUser.add_to_cart();
        endUser.goto_cart();
        endUser.remove_from_cart("Laptop");
        endUser.should_not_see_product_in_cart("Laptop");
    }

    @Test
    public void clear_cart() {
        endUser.is_the_home_page();
        endUser.search_for("Laptop");
        endUser.add_to_cart();
        endUser.search_for("Mouse");
        endUser.add_to_cart();
        endUser.goto_cart();
        endUser.clear_cart();
        endUser.should_see_empty_cart();
    }
}
