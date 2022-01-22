package com.capgemini.mrchecker.selenium;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.function.Function;

public class Menu extends BasePage {
    private final static By selectorLoginButton = By.cssSelector("body > header > nav > div > div > ul:nth-child(1) > li:nth-child(2) > a");

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

    public void clickLoginButton(){
         Button loginButton = getDriver().elementButton(selectorLoginButton);
         loginButton.click();
    }
}
