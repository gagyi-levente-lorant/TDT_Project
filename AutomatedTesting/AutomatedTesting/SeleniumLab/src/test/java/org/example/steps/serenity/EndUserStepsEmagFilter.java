package org.example.steps.serenity;

import net.thucydides.core.annotations.Step;
import org.example.pages.EmagFilterPage;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class EndUserStepsEmagFilter {


    EmagFilterPage dictionaryPage;

    @Step
    public void enters(String keyword) {
        dictionaryPage.enter_keywords(keyword);
    }

    @Step
    public void starts_search() {
        dictionaryPage.lookup_terms();
    }

    @Step
    public void select_laptops(){
        dictionaryPage.lookup_laptops();
    }

    @Step
    public void select_laptops_subsection(){
        dictionaryPage.lookup_laptop_subsection();
    }

    @Step
    public void is_the_home_page() {
        dictionaryPage.open();
    }

    @Step
    public void filter(int optionId) {
        dictionaryPage.check_filter(optionId);

    }


    public void price_filter(int number){
        dictionaryPage.boudary_value_check(number);
    }


    public void check_item_valid(String itemName){
        assertThat(dictionaryPage.getFirstText(), containsString(itemName));
    }

}
