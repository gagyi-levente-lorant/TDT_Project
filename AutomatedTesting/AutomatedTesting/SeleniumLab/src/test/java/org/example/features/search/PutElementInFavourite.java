package org.example.features.search;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.example.steps.serenity.EndUserStepsEmagFavourite;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;

@RunWith(SerenityRunner.class)
public class PutElementInFavourite {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public EndUserStepsEmagFavourite user;

    @Issue("#Emag-Favourite")
    @Test
    public void search_and_favourite_s24() {
        user.is_the_home_page();
        user.looks_for("s24");
        user.add_item_to_favourite();
        user.goto_wishlist();
        user.check_item_in_wishlist("s24");
    }

    @Test
    public void search_and_favourite_s24_invalid() {
        user.is_the_home_page();
        user.looks_for("iphone 15");
        user.add_item_to_favourite();
        user.goto_wishlist();
        user.check_item_not_in_wishlist("s24");
    }

    @Test
    public void search_and_favourite_s24_and_iphone_valid() {
        user.is_the_home_page();
        user.looks_for("iphone 13");
        user.add_item_to_favourite();
        user.looks_for("s24");
        user.add_item_to_favourite();
        user.goto_wishlist();
        user.contains_multiple_item(Arrays.asList("S24", "iPhone 13"));
    }

    @Test
    public void search_and_favourite_s24_and_iphone_invalid() {
        user.is_the_home_page();
        user.looks_for("iphone 13");
        user.add_item_to_favourite();
        user.looks_for("s24");
        user.add_item_to_favourite();
        user.goto_wishlist();
        user.contains_multiple_item_invalid(Arrays.asList("S24", "huawei 13"));
    }

    @Test
    public void favourites_are_empty() {
        user.is_the_home_page();
        user.goto_wishlist();
        user.should_be_empty();
    }
}
