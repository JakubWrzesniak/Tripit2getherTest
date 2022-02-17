package com.capgemini.mrchecker.selenium;

import com.capgemini.mrchecker.PerformAction;
import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.environment.EnvironmentParam;
import com.capgemini.mrchecker.selenium.environment.PageSubURLs;
import com.capgemini.mrchecker.selenium.environment.PageTitles;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

public class EditApplicationPage extends BasePage {
    private final static By selectorTripName = By.cssSelector("div.col-9 > div:nth-child(1) > h3");
    private final static By selectorNameInput = By.cssSelector("#Participant_Name");
    private final static By selectorSurnameInput = By.cssSelector("#Participant_Surname");
    private final static By selectorDateOfBirthInput = By.cssSelector("#Participant_DateOfBirth");
    private final static By selectorEmailInput = By.cssSelector("#Participant_Email");
    private final static By selectorPhoneNumberInput = By.cssSelector("#Participant_PhoneNumber");
    private final static By selectorIsVerifiedInput = By.cssSelector("#IsVerified");
    private final static By selectorSubmitButton = By.cssSelector("button[value='Zaakaceptowana']");
    private final static By selectorRejectButton = By.cssSelector("button[value='Anulowana']");
    private final static By selectorErrorBanner = By.cssSelector("div[class='col-8 alert alert-danger alert-dismissible text-center']");

    @Override
    public void load() {
        //getDriver().get(EnvironmentParam.BASE_URL.getValue() + PageSubURLs.APPLICATIONS_SHOWPREVIEWDATA_PAGE);
    }

    @Override
    public boolean isLoaded() {
        return isUrlAndPageTitleAsCurrentPage(EnvironmentParam.BASE_URL.getValue() + PageSubURLs.APPLICATIONS_EDIT_PAGE);
    }

    @Override
    public String pageTitle() {
        return PageTitles.APPLICATIONS_EDIT_PAGE.toString();
    }

    public String getTripName(){
        return getDriver().findElementDynamic(selectorTripName).getText().trim();
    }

    public String getName(){
        return getDriver().elementInputText(selectorNameInput).getValue();
    }

    public String getSurname(){
        return getDriver().elementInputText(selectorSurnameInput).getValue();
    }

    public String getDateOfBirth(){
        return getDriver().elementInputText(selectorDateOfBirthInput).getValue();
    }

    public String getEmail(){
        return getDriver().elementInputText(selectorEmailInput).getValue();
    }

    public String getPhoneNumber(){
        return getDriver().elementInputText(selectorPhoneNumberInput).getValue();
    }

    public boolean isVerified(){
        PerformAction.makeScreenshot();
        return getDriver().elementInputText(selectorPhoneNumberInput).getValue().equals("true");
    }

    @Step("Click is verified checkbox")
    public void clickIsVerifiedCheckBox(){
        getDriver().elementButton(selectorIsVerifiedInput).click();
    }

    @Step("Click Reject button")
    public void clickRejectButton(){
        getDriver().elementButton(selectorRejectButton).click();
    }

    @Step("Click Accept button")
    public void clickSaveButton(){
        getDriver().elementButton(selectorSubmitButton).click();
    }

    public boolean isErrorBannerDisplayed(){
        PerformAction.makeScreenshot();
        return !getDriver().findElementDynamics(selectorErrorBanner).isEmpty();
    }

    public String getErrorBannerText(){
        return getDriver().findElementDynamic(selectorErrorBanner).getText().trim();
    }

    @Step("Accept alert")
    public void acceptAlert(){
        Alert alert = getDriver().switchTo().alert();
        alert.accept();
    }

    @Step("Discard alert")
    public void discardAlert(){
        Alert alert = getDriver().switchTo().alert();
        alert.dismiss();
    }

}
