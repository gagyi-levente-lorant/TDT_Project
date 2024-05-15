package org.example.steps.serenity;

import net.thucydides.core.annotations.Step;
import org.example.pages.EmagFavouritePage;

import java.util.List;


import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class EndUserStepsEmagFavourite {

    EmagFavouritePage dictionaryPage;

    @Step
    public void enters(String keyword) {
        dictionaryPage.enter_keywords(keyword);
    }

    @Step
    public void starts_search() {
        dictionaryPage.lookup_terms();
    }

    @Step
    public void add_item_to_favourite(){dictionaryPage.add_to_favourite();}


    @Step
    public void goto_wishlist(){dictionaryPage.click_on_wishlist();}

    @Step
    public void check_item_in_wishlist(String itemName){
        assertThat(dictionaryPage.getItemLinkText(), containsString(itemName));
    }
    @Step
    public void check_item_not_in_wishlist(String itemName){
        assertThat(dictionaryPage.getItemLinkText(), not(containsString(itemName)));
    }

    @Step
    public void contains_multiple_item(List<String> nameList) {

        List<String> listItems = dictionaryPage.getFavouriteItems();
        for (String name: nameList) {
            assertThat(listItems, hasItem(containsString(name)));
        }

    }

    @Step
    public void contains_multiple_item_invalid(List<String> nameList) {

        List<String> listItems = dictionaryPage.getFavouriteItems();
        boolean atLeastOneNotFound = false;

        for (String name : nameList) {
            if (!listItems.contains(name)) {
                atLeastOneNotFound = true;
                break;
            }
        }
        assertThat("At least one item from the name list is not found in the items list", atLeastOneNotFound);
    }

    @Step
    public void should_be_empty() {
        String expectedText = "Hmm, niciun produs in lista ta.";
        String emptyText = dictionaryPage.get_empty_favourites();
        assertThat(emptyText, equalTo(expectedText));
    }

    @Step
    public void is_the_home_page() {
        dictionaryPage.open();
    }

    @Step
    public void looks_for(String term) {
        enters(term);
        starts_search();
    }


}
