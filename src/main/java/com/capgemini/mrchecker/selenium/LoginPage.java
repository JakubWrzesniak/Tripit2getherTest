package com.capgemini.mrchecker.selenium;

import com.capgemini.mrchecker.PerformAction;
import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.Button;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.InputTextElement;
import com.capgemini.mrchecker.selenium.environment.PageSubURLs;
import com.capgemini.mrchecker.selenium.environment.PageTitles;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    private final static By selectorEmailField = By.cssSelector("#Input_Email");
    private final static By selectorPasswordField = By.cssSelector("#Input_Password");
    private final static By selectorLoginButton = By.cssSelector("#account > div:nth-child(7) > button");

    @Override
    public String pageTitle() {
        return PageTitles.LOGIN_PAGE.toString();
    }

    @Override
    public boolean isLoaded() {
        return isUrlAndPageTitleAsCurrentPage(PageSubURLs.LOGIN_PAGE.getValue());
    }

    @Override
    public void load() {
    }

    @Step("Fill login data: emial {userEmail}")
    public void fillLoginData(String userEmail, String userPassword){
        InputTextElement inputEmail = getDriver().elementInputText(selectorEmailField);
        InputTextElement inputPassword = getDriver().elementInputText(selectorPasswordField);
        inputEmail.setInputText(userEmail);
        inputPassword.setInputText(userPassword);
        PerformAction.makeScreenshot();
    }

    @Step("ClickLoginButton")
    public void clickLoginButton(){
        Button loginButton = getDriver().elementButton(selectorLoginButton);
        loginButton.click();
    }

    public String getLoginText(){
        InputTextElement inputEmail = getDriver().elementInputText(selectorEmailField);
        return inputEmail.getValue();
    }
}
