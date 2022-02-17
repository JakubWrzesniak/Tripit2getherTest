package com.capgemini.mrchecker.selenium;

import com.capgemini.mrchecker.PerformAction;
import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.Button;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.InputTextElement;
import com.capgemini.mrchecker.selenium.environment.EnvironmentParam;
import com.capgemini.mrchecker.selenium.environment.PageSubURLs;
import com.capgemini.mrchecker.selenium.environment.PageTitles;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class RegisterPage extends BasePage {
    private final static By selectorEmailInput = By.cssSelector("input[type='email']");
    private final static By selectorPasswordInput = By.cssSelector("input[name='Input.Password']");
    private final static By selectorConfirmPasswordInput = By.cssSelector("input[name='Input.ConfirmPassword']");
    private final static By selectorRegisterButton = By.cssSelector("button[type='submit']");

    @Override
    public String pageTitle() {
        return PageTitles.REGISTER_PAGE.toString();
    }

    @Override
    public boolean isLoaded() {
        return isUrlAndPageTitleAsCurrentPage(PageSubURLs.REGISTER_PAGE.getValue());
    }

    @Override
    public void load() {
        getDriver().get(EnvironmentParam.BASE_URL.getValue() + PageSubURLs.REGISTER_PAGE);
    }

    @Step("Fill register data: email: {userEmail}")
    public void fillRegisterData(String userEmail, String userPassword){
        InputTextElement inputEmail = getDriver().elementInputText(selectorEmailInput);
        InputTextElement inputPassword = getDriver().elementInputText(selectorPasswordInput);
        InputTextElement inputConfirmationPassword = getDriver().elementInputText(selectorConfirmPasswordInput);
        inputEmail.setInputText(userEmail);
        inputPassword.setInputText(userPassword);
        inputConfirmationPassword.setInputText(userPassword);
        PerformAction.makeScreenshot();
    }

    @Step("Click Register Button")
    public void clickRegisterButton(){
        Button loginButton = getDriver().elementButton(selectorRegisterButton);
        loginButton.click();
    }

}
