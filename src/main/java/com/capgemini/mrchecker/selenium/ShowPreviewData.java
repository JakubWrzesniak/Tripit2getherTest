package com.capgemini.mrchecker.selenium;

import com.capgemini.mrchecker.PerformAction;
import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.environment.EnvironmentParam;
import com.capgemini.mrchecker.selenium.environment.PageSubURLs;
import com.capgemini.mrchecker.selenium.environment.PageTitles;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ShowPreviewData extends BasePage {
    private final static By selectorEditYourData = By.cssSelector("a[class*='btn-warning']");
    private final static By selectorSave = By.cssSelector("div.row > div >button[type='submit']");
    private final static By selectorTripName = By.cssSelector("body > div > main > div > div:nth-child(3) > div.col-9 > div:nth-child(1) > h3");
    private final static By selectorNameFiled = By.cssSelector("#Name");
    private final static By selectorSurnameField = By.cssSelector("#Surname");
    private final static By selectorAddressField = By.cssSelector("#Address");
    private final static By selectorDateOfBirthField = By.cssSelector("#DateOfBirth");
    private final static By selectorEmailField = By.cssSelector("#Email");
    private final static By selectorPhoneNumberField = By.cssSelector("#PhoneNumber");

    private final static By selectorNameFiledError = By.cssSelector("form > div:nth-child(3) > div:nth-child(1) > span");
    private final static By selectorSurnameFieldError = By.cssSelector("form > div:nth-child(3) > div:nth-child(2) > span");
    private final static By selectorAddressFieldError = By.cssSelector("form > div:nth-child(4) > div:nth-child(1) > span");
    private final static By selectorDateOfBirthFieldError = By.cssSelector("form > div:nth-child(4) > div:nth-child(2) > span");
    private final static By selectorEmailFieldError = By.cssSelector("form > div:nth-child(5) > div:nth-child(2) > span");
    private final static By selectorPhoneNumberFieldError = By.cssSelector("form > div:nth-child(5) > div:nth-child(2) > span");

    @Override
    public void load() {
        //getDriver().get(EnvironmentParam.BASE_URL.getValue() + PageSubURLs.APPLICATIONS_SHOWPREVIEWDATA_PAGE);
    }

    @Override
    public boolean isLoaded() {
        return isUrlAndPageTitleAsCurrentPage(EnvironmentParam.BASE_URL.getValue() + PageSubURLs.APPLICATIONS_SHOWPREVIEWDATA_PAGE);
    }

    @Override
    public String pageTitle() {
        return PageTitles.APPLICATIONS_SHOWPREVIEWDATA_PAGE.toString();
    }

    @Step("Click edit ypur data button")
    public void clickEditYourData(){
        getDriver().elementButton(selectorEditYourData).click();
    }

    @Step("Click Save")
    public void clickSave(){
        getDriver().elementButton(selectorSave).click();
        PerformAction.allureInfo("Save button is clicked");
    }

    public String getTripName(){
        return getDriver().findElementDynamic(selectorTripName).getText().trim();
    }

    public String getName(){
        return getDriver().elementInputText(selectorNameFiled).getValue();
    }

    public String getSurname(){
        return getDriver().elementInputText(selectorSurnameField).getValue();
    }

    public String getAddress(){
        return getDriver().elementInputText(selectorAddressField).getValue();
    }

    public String getDateOfBirth(){
        return getDriver().elementInputText(selectorDateOfBirthField).getValue();
    }

    public String getEmail(){
        return getDriver().elementInputText(selectorEmailField).getValue();
    }

    public String getPhoneNumber(){
        return getDriver().elementInputText(selectorPhoneNumberField).getValue();
    }

    public boolean isNameErrorVisible(){
        return getDriver().waitForElementVisible(selectorNameFiled).isDisplayed();
    }

    public boolean isSurnameErrorVisible(){
        return getDriver().waitForElementVisible(selectorSurnameField).isDisplayed();
    }

    public boolean isAddressErrorVisible(){
        return getDriver().waitForElementVisible(selectorSurnameField).isDisplayed();
    }

    public boolean isPhoneNumberErrorVisible(){
        return getDriver().waitForElementVisible(selectorSurnameField).isDisplayed();
    }
}
