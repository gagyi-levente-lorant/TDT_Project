package org.example.steps.serenity;

import net.thucydides.core.annotations.Step;
import org.example.pages.EmagCartPage;

import java.text.Normalizer;
import java.util.List;


import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.CoreMatchers.equalTo;
public class EndUserStepsEmagCart {

    EmagCartPage dictionaryPage;

    @Step
    public void enters(String keyword) {
        dictionaryPage.enter_keywords(keyword);
    }

    @Step
    public void starts_search() {
        dictionaryPage.lookup_terms();
    }

    @Step
    public void add_item_to_cart(){dictionaryPage.add_to_cart();}


    @Step
    public void goto_cart(){dictionaryPage.click_on_cart();}

    @Step
    public void check_item_in_cart(String itemName){
        assertThat(dictionaryPage.getItemLinkText(), containsString(itemName));
    }
    @Step
    public void check_item_not_in_cart(String itemName){
        assertThat(dictionaryPage.getItemLinkText(), not(containsString(itemName)));
    }

    @Step
    public void contains_multiple_item(List<String> nameList) {

        List<String> listItems = dictionaryPage.getCartItems();
        for (String name: nameList) {
            assertThat(listItems, hasItem(containsString(name)));
        }

    }

    @Step
    public void contains_multiple_item_invalid(List<String> nameList) {

        List<String> listItems = dictionaryPage.getCartItems();
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
        String expectedText = "Cosul meu\nCosul tau de cumparaturi nu contine produse. Pentru a adauga produse in cos te rugam sa te intorci in magazin.";
        String emptyText = dictionaryPage.get_empty_cart();

        // Remove all non-alphanumeric characters and convert to lowercase
        expectedText = removeNonAlphanumeric(expectedText).toLowerCase();
        emptyText = removeNonAlphanumeric(emptyText).toLowerCase();

        assertThat(emptyText, equalTo(expectedText));
    }

    // Method to remove all non-alphanumeric characters from a string
    private String removeNonAlphanumeric(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("[^a-zA-Z0-9\\s]", "");
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
