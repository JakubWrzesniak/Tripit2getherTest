package com.capgemini.mrchecker.selenium;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.environment.EnvironmentParam;
import com.capgemini.mrchecker.selenium.environment.PageSubURLs;
import com.capgemini.mrchecker.selenium.environment.PageTitles;

public class HomePage extends BasePage {
    @Override
    public void load() {
        getDriver().get(EnvironmentParam.BASE_URL.getValue());
    }

    @Override
    public boolean isLoaded() {
        return isUrlAndPageTitleAsCurrentPage(EnvironmentParam.BASE_URL.getValue());
    }

    @Override
    public String pageTitle() {
        return PageTitles.HOME_PAGE.toString();
    }
}
