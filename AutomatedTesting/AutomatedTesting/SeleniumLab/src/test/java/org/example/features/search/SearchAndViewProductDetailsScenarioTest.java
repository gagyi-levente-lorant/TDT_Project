package org.example.features.search;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.example.steps.serenity.EndUserStepsEmagProductDetails;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class SearchAndViewProductDetailsScenarioTest {

    @Managed
    WebDriver driver;

    @Steps
    EndUserStepsEmagProductDetails endUser;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\stefa\\Desktop\\ss\\chromedriver.exe");
    }

    @Test
    public void search_and_view_laptop_details() {
        endUser.is_the_home_page();
        endUser.search_for("Laptop");
        endUser.view_first_product_details();
        endUser.should_see_product_title_contains("Laptop");
    }

    @Test
    public void search_and_view_mouse_details() {
        endUser.is_the_home_page();
        endUser.search_for("Mouse");
        endUser.view_first_product_details();
        endUser.should_see_product_title_contains("Mouse");
    }

    @Test
    public void search_and_view_non_existent_product() {
        endUser.is_the_home_page();
        endUser.search_for("NonExistentProduct");
        endUser.should_see_no_products_found_message("0 rezultate pentru:");
    }

    @Test
    public void search_and_check_product_price() {
        endUser.is_the_home_page();
        endUser.search_for("Laptop");
        endUser.view_first_product_details();
        endUser.should_see_product_price("Lei");
    }
}
