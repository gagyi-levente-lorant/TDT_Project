package org.example.steps.serenity;

import net.thucydides.core.annotations.Step;
import org.example.pages.EmagCartPage;

public class EndUserStepsEmagCart {

    EmagCartPage cartPage;

    @Step
    public void is_the_home_page() {
        cartPage.open();
    }

    @Step
    public void search_for(String product) {
        cartPage.enter_keywords(product);
        cartPage.lookup_terms();
    }

    @Step
    public void add_to_cart() {
        cartPage.add_to_cart();
    }

    @Step
    public void goto_cart() {
        cartPage.goto_cart();
    }

    @Step
    public void should_see_product_in_cart(String product) {
        cartPage.should_see_product_in_cart(product);
    }

    @Step
    public void should_see_products_in_cart(String... products) {
        for (String product : products) {
            cartPage.should_see_product_in_cart(product);
        }
    }

    @Step
    public void should_see_product_quantity_in_cart(String product, int quantity) {
        cartPage.should_see_product_quantity_in_cart(product, quantity);
    }

    @Step
    public void remove_from_cart(String product) {
        cartPage.remove_from_cart(product);
    }

    @Step
    public void should_not_see_product_in_cart(String product) {
        cartPage.should_not_see_product_in_cart(product);
    }

    @Step
    public void clear_cart() {
        cartPage.clear_cart();
    }

    @Step
    public void should_see_empty_cart() {
        cartPage.should_see_empty_cart();
    }
}
