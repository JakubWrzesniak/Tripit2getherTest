package com.capgemini.mrchecker.selenium;

import com.capgemini.mrchecker.PerformAction;
import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.environment.EnvironmentParam;
import com.capgemini.mrchecker.selenium.environment.PageSubURLs;
import com.capgemini.mrchecker.selenium.environment.PageTitles;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ApplicationsPage extends BasePage {
    private final By selectorApplication = By.cssSelector("div[class='row border border-info p-3']");
    private final By selectorApplicationInfoButton = By.cssSelector("a");
    private final By selectorTripStatus = By.cssSelector("div> div:nth-child(3)");

    @Override
    public void load() {
        getDriver().get(EnvironmentParam.BASE_URL.getValue() + PageSubURLs.APPLICATIONS_PAGE);
    }

    @Override
    public boolean isLoaded() {
        return isUrlAndPageTitleAsCurrentPage(EnvironmentParam.BASE_URL.getValue() + PageSubURLs.APPLICATIONS_PAGE);
    }

    @Override
    public String pageTitle() {
        return PageTitles.APPLICATIONS_PAGE.toString();
    }

    public WebElement getApplicationRow(String tripName, String userEmail){
        List<WebElement> rows = getDriver().findElementDynamics(selectorApplication);
        for(WebElement row : rows){
            String rowText = row.getText();
            if(rowText.contains(tripName) && rowText.contains(userEmail)){
                return row;
            }
        }
        return null;
    }

    @Step("Open application")
    public void openApplication(WebElement row){
        PerformAction.makeScreenshot();
        row.findElement(selectorApplicationInfoButton).click();
    }

    @Step("Open application for: {tripName}, {userEmail}")
    public void openApplication(String tripName, String userEmail){
        WebElement row = getApplicationRow(tripName, userEmail);
        if(row != null){
            openApplication(row);
        } else{
            Assertions.fail("Cannot find application with data: " + userEmail + "; " + tripName);
        }
    }

    public String getApplicationStatus(String tripName, String userEmail){
        WebElement row = getApplicationRow(tripName, userEmail);
        Assertions.assertNotNull(row);
        PerformAction.makeScreenshot();
        return row.findElement(selectorTripStatus).getText();

    }
}
