package org.example.steps.serenity;

import net.thucydides.core.annotations.Step;
import org.example.pages.EmagProductDetailsPage;

public class EndUserStepsEmagProductDetails {

    EmagProductDetailsPage productDetailsPage;

    @Step
    public void is_the_home_page() {
        productDetailsPage.open();
    }

    @Step
    public void search_for(String product) {
        productDetailsPage.enter_keywords(product);
        productDetailsPage.lookup_terms();
    }

    @Step
    public void view_first_product_details() {
        productDetailsPage.view_first_product_details();
    }

    @Step
    public void should_see_product_title_contains(String keyword) {
        productDetailsPage.should_see_product_title_contains(keyword);
    }

    @Step
    public void should_see_no_products_found_message(String expectedMessage) {
        productDetailsPage.should_see_no_products_found_message(expectedMessage);
    }

    @Step
    public void should_see_product_price(String expectedPrice) {
        productDetailsPage.should_see_product_price(expectedPrice);
    }
}
