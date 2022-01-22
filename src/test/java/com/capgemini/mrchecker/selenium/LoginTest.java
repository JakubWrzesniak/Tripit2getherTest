package com.capgemini.mrchecker.selenium;

import com.capgemini.mrchecker.PerformAction;
import com.capgemini.mrchecker.selenium.environment.LoginPage;
import com.capgemini.mrchecker.test.core.BaseTest;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.util.function.BooleanSupplier;
import java.util.function.Function;


public class LoginTest extends BaseTest {
    private final HomePage homePage = new HomePage();
    private final Menu menu = new Menu();
    private final LoginPage loginPage = new LoginPage();
    private final String userEmail = "tripUser2@gmail.com";
    private final String userPassword = "TripUser1!";


    @Test
    void LoginCorrectDataTest(){
        homePage.load();
        menu.clickLoginButton();
        PerformAction.waitForPageLoaded(loginPage);
        loginPage.fillLoginData(userEmail, userPassword);
        Assert.assertEquals(userEmail, loginPage.getLoginText(), "User email value is not expected");
        loginPage.getLoginText();
        loginPage.clickLoginButton();
        PerformAction.waitForPageLoaded(homePage);
    }

    @Override
    public void tearDown() {
        LoginPage.getDriver().close();
    }



}
