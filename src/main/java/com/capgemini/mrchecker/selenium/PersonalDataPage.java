package com.capgemini.mrchecker.selenium;

import com.capgemini.mrchecker.PerformAction;
import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.environment.PageSubURLs;
import com.capgemini.mrchecker.selenium.environment.PageTitles;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class PersonalDataPage extends BasePage {
    private final static By selectorDeleteButton = By.cssSelector("#delete");
    @Override
    public String pageTitle() {
        return PageTitles.PERSONALDATA_PAGE.toString();
    }

    @Override
    public boolean isLoaded() {
        return isUrlAndPageTitleAsCurrentPage(PageSubURLs.PERSONALDATA_PAGE.getValue());
    }

    @Override
    public void load() {
    }

    @Step("Click Delete button")
    public void clickDeleteButton(){
        getDriver().elementButton(selectorDeleteButton).click();
    }
}
