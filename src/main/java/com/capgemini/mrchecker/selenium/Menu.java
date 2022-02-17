package com.capgemini.mrchecker.selenium;

import com.capgemini.mrchecker.PerformAction;
import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.Button;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.function.Function;

public class Menu extends BasePage {
    private final static By selectorLoginButton = By.cssSelector("body > header > nav > div > div > ul:nth-child(1) > li:nth-child(2) > a");
    private final static By selectorLogoutButton = By.cssSelector("form[action*='Logout'] button");
    private final static By selectorRegisterButton = By.cssSelector("body > header > nav > div > div > ul:nth-child(1) > li:nth-child(1) > a");
    private final static By selectorManageAccount = By.cssSelector("a[title='Manage']");
    private final static By selectorApplications = By.cssSelector(" ul.navbar-nav.flex-grow-1 > li:nth-child(2) > a");

    @Override
    public String pageTitle() {
        return null;
    }

    @Override
    public boolean isLoaded() {
        Button loginButton = getDriver().elementButton(selectorLoginButton);
        return loginButton.isDisplayed();
    }

    @Override
    public void load() {
    }

    @Step("Click Logout Button")
    public void clickLoginButton(){
         Button loginButton = getDriver().elementButton(selectorLoginButton);
         loginButton.click();
    }

    @Step("Click Logout Button")
    public void clickLogoutButton(){
        Button loginButton = getDriver().elementButton(selectorLogoutButton);
        loginButton.click();
    }

    @Step("Click Register Button")
    public void clickRegisterButton(){
        Button loginButton = getDriver().elementButton(selectorRegisterButton);
        loginButton.click();
    }

    @Step("Click Management Account")
    public void clickManageAccount(){
        getDriver().elementButton(selectorManageAccount).click();
        PerformAction.allureInfo("Click Management button is cliekd");
    }

    public void clickApplications(){
        Button loginButton = getDriver().elementButton(selectorManageAccount);
        loginButton.click();
    }

    public boolean ifUserIsLogIn(){
        return !getDriver().findElementDynamics(selectorManageAccount).isEmpty();
    }
}
