package com.capgemini.mrchecker.selenium;

import com.capgemini.mrchecker.PerformAction;
import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.environment.EnvironmentParam;
import com.capgemini.mrchecker.selenium.environment.PageSubURLs;
import com.capgemini.mrchecker.selenium.environment.PageTitles;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CreateApplicationPage extends BasePage {
    private final static By selectorInputsFields = By.cssSelector("textarea");
    private final static By selectorApplyButton = By.cssSelector("input[value='Applay']");
    private final Faker faker = new Faker();

    @Override
    public void load() {
        getDriver().get(EnvironmentParam.BASE_URL.getValue() + PageSubURLs.APPLICATIONS_CREATE_PAGE);
    }

    @Override
    public boolean isLoaded() {
        return isUrlAndPageTitleAsCurrentPage(EnvironmentParam.BASE_URL.getValue() + PageSubURLs.APPLICATIONS_CREATE_PAGE);
    }

    @Override
    public String pageTitle() {
        return PageTitles.APPLICATIONS_CREATE_PAGE.toString();
    }

    @Step("Fill all Fields")
    public void fillAllTexAreas(){
        List<WebElement> textareas = getDriver().findElementDynamics(selectorInputsFields);
        textareas.forEach(t -> {
            String value = faker.friends().quote();
            t.sendKeys(value);
            PerformAction.allureInfo("Fill textArea with value: " + value);
        });
        PerformAction.makeScreenshot();
    }

    @Step("Click apply button")
    public void clickApply(){
        getDriver().elementButton(selectorApplyButton).click();
        PerformAction.allureInfo("Apply button is clicked");
    }
}
