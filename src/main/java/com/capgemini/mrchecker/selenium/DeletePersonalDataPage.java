package com.capgemini.mrchecker.selenium;

import com.capgemini.mrchecker.PerformAction;
import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.environment.PageSubURLs;
import com.capgemini.mrchecker.selenium.environment.PageTitles;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class DeletePersonalDataPage extends BasePage {
    private final static By selectorPasswordInput = By.cssSelector("#Input_Password");
    private final static By selectorDeleteButton = By.cssSelector("#delete-user > button");

    @Override
    public String pageTitle() {
        return PageTitles.DELETEPERSONALDATA_PAGE.toString();
    }

    @Override
    public boolean isLoaded() {
        return isUrlAndPageTitleAsCurrentPage(PageSubURLs.DELETEPERSONALDATA_PAGE.getValue());
    }

    @Override
    public void load() {
    }

    @Step("Fill Password")
    public void fillPassword(String password){
        getDriver().elementInputText(selectorPasswordInput).setInputText(password);
        PerformAction.makeScreenshot();
    }

    @Step("Click Delete Button")
    public void clickDeleteButton(){
        getDriver().elementButton(selectorDeleteButton).click();
    }
}
