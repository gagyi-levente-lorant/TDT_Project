package org.example.features.search;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.example.steps.serenity.EndUserStepsEmagFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class FilterElements {


    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public EndUserStepsEmagFilter mark;


    @Issue("#EMAG-FILTER")
    @Test
    public void search_and_filter_min_below() {
        mark.is_the_home_page();
        mark.select_laptops();
        mark.select_laptops_subsection();
        mark.select_laptops_subsection();
        mark.filter(25844);
        mark.price_filter(1997);
        mark.check_item_valid("Laptop ultraportabil Microsoft Surface Go 2 cu procesor Intel Pentium Gold 4425Y, 10.5\", Full HD, 4GB, 64GB eMMC, UHD Graphics 615, Windows 10 Home S, Platinum");
    }

    @Test
    public void search_and_filter_min() {
        mark.is_the_home_page();
        mark.select_laptops();
        mark.select_laptops_subsection();
        mark.select_laptops_subsection();
        mark.filter(25844);
        mark.price_filter(1998);
        mark.check_item_valid("Laptop ultraportabil Microsoft Surface Go 2 cu procesor Intel Pentium Gold 4425Y, 10.5\", Full HD, 4GB, 64GB eMMC, UHD Graphics 615, Windows 10 Home S, Platinum");
    }

    @Test
    public void search_and_filter_min_above() {
        mark.is_the_home_page();
        mark.select_laptops();
        mark.select_laptops_subsection();
        mark.select_laptops_subsection();
        mark.filter(25844);
        mark.price_filter(1999);
        mark.check_item_valid("Laptop Microsoft Surface Go 3 cu procesor Intel® Pentium® Gold 6500Y pana la 3.40 GHz, 10.5\", WUXGA+, 4GB, 64GB SSD, Intel® UHD Graphics 615, Windows 11 Home in S mode, Platinum");
    }


    @Test
    public void search_and_filter_max_below() {
        mark.is_the_home_page();
        mark.select_laptops();
        mark.select_laptops_subsection();
        mark.select_laptops_subsection();
        mark.filter(25844);
        mark.price_filter(2699);
        mark.check_item_valid("Laptop Microsoft Surface Go 3 cu procesor Intel® Pentium® Gold 6500Y pana la 3.40 GHz, 10.5\", WUXGA+, 4GB, 64GB SSD, Intel® UHD Graphics 615, Windows 11 Home in S mode, Platinum");
    }


    @Test
    public void search_and_filter_max() {
        mark.is_the_home_page();
        mark.select_laptops();
        mark.select_laptops_subsection();
        mark.select_laptops_subsection();
        mark.filter(25844);
        mark.price_filter(2700);
        mark.check_item_valid("Laptop ultraportabil Microsoft Surface Go 2 cu procesor Intel Pentium Gold 4425Y, 10.5\", Full HD, 4GB, 64GB eMMC, UHD Graphics 615, Windows 10 Home S, Platinum");
    }


    @Test
    public void search_and_filter_max_above() {
        mark.is_the_home_page();
        mark.select_laptops();
        mark.select_laptops_subsection();
        mark.select_laptops_subsection();
        mark.filter(25844);
        mark.price_filter(2701);
        mark.check_item_valid("Laptop ultraportabil Microsoft Surface Go 2 cu procesor Intel Pentium Gold 4425Y, 10.5\", Full HD, 4GB, 64GB eMMC, UHD Graphics 615, Windows 10 Home S, Platinum");
    }
}
