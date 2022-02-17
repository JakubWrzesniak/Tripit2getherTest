package com.capgemini.mrchecker.selenium;

import com.capgemini.mrchecker.PerformAction;
import com.capgemini.mrchecker.selenium.Models.User;
import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.Button;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.InputTextElement;
import com.capgemini.mrchecker.selenium.environment.PageSubURLs;
import com.capgemini.mrchecker.selenium.environment.PageTitles;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ManageAccountPage extends BasePage {
    private final static By selectorNameInput = By.cssSelector("#Input_Name");
    private final static By selectorSurnameInput = By.cssSelector("#Input_Surname");
    private final static By selectorDateOfBirthInput = By.cssSelector("#Input_DateOfBirth");
    private final static By selectorAddressInput = By.cssSelector("#Input_Address");
    private final static By selectorPhoneNumberInput = By.cssSelector("#Input_PhoneNumber");
    private final static By selectorSaveButton = By.cssSelector("#update-profile-button");
    private final static By selectorSuccessBanner = By.cssSelector("div[class='alert alert-success alert-dismissible']");
    private final static By selectorPersonalDataButton = By.cssSelector("#personal-data");

    @Override
    public String pageTitle() {
        return PageTitles.MANAGEACCOUNT_PAGE.toString();
    }

    @Override
    public boolean isLoaded() {
        return isUrlAndPageTitleAsCurrentPage(PageSubURLs.MANAGEACCOUNT_PAGE.getValue());
    }

    @Override
    public void load() {
    }

    @Step("Fill user name with value {value}")
    public void fillNameInput(String value){
        InputTextElement input = getDriver().elementInputText(selectorNameInput);
        input.clearInputText();
        input.setInputText(value);
        PerformAction.allureInfo("Fill user name with: " + value);
    }

    @Step("Fill user surname with value {value}")
    public void fillSurnameInput(String value){
        InputTextElement input = getDriver().elementInputText(selectorSurnameInput);
        input.clearInputText();
        input.setInputText(value);
        PerformAction.allureInfo("Fill surname name with: " + value);
    }

    @Step("Fill user Address with value {value}")
    public void fillAddressInput(String value){
        InputTextElement input = getDriver().elementInputText(selectorAddressInput);
        input.clearInputText();
        input.setInputText(value);
        PerformAction.allureInfo("Fill user Address with: " + value);
    }

    @Step("Fill user Phone number with value {value}")
    public void fillPhoneNumberInput(String value){
        InputTextElement input = getDriver().elementInputText(selectorPhoneNumberInput);
        input.clearInputText();
        input.setInputText(value);
        PerformAction.allureInfo("Fill Phone number name with: " + value);
    }

    @Step("Fill user Birthday number with value {value}")
    public void fillBirthDayInput(String value){
        InputTextElement input = getDriver().elementInputText(selectorDateOfBirthInput);;
        input.setInputText(value);
        PerformAction.allureInfo("Fill user Birthday with: " + value);
    }

    @Step("Click Save Button")
    public void clickSaveButton(){
        Button button = getDriver().elementButton(selectorSaveButton);
        button.click();
        PerformAction.allureInfo("Save Button is clicked");
    }

    public boolean isSuccessBannerVisible(){
        return getDriver().findElementDynamic(selectorSuccessBanner).isDisplayed();
    }

    @Step("Fill all user data {user}")
    public boolean fillAllUserDataWithSuccessBanner(User user){
        fillNameInput(user.getName());
        fillSurnameInput(user.getSurname());
        fillAddressInput(user.getAddress());
        fillBirthDayInput(user.getFormattedDateOfBirth());
        fillAddressInput(user.getAddress());
        fillPhoneNumberInput(user.getPhoneNumber());
        PerformAction.allureInfo("User data are filled");
        PerformAction.makeScreenshot();
        clickSaveButton();
        PerformAction.makeScreenshot();
        return isSuccessBannerVisible();
    }

    @Step("Click personal data")
    public void clickPersonalData(){
        getDriver().elementButton(selectorPersonalDataButton).click();
    }
}
