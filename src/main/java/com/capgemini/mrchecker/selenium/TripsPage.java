package com.capgemini.mrchecker.selenium;

import com.capgemini.mrchecker.PerformAction;
import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.environment.EnvironmentParam;
import com.capgemini.mrchecker.selenium.environment.PageSubURLs;
import com.capgemini.mrchecker.selenium.environment.PageTitles;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class TripsPage extends BasePage {
    private final static By selectorTrips = By.cssSelector("div.row");
    private final static By selectorApplyOnTrip = By.cssSelector("a[id*='apply']");
    private final static By selectorTripName = By.cssSelector("div.row div:nth-child(2)");

    @Override
    @Step("Open Trips Page")
    public void load() {
        getDriver().get(EnvironmentParam.BASE_URL.getValue() + PageSubURLs.TRIPS_PAGE);
    }

    @Override
    public boolean isLoaded() {
        return isUrlAndPageTitleAsCurrentPage(EnvironmentParam.BASE_URL.getValue() + PageSubURLs.TRIPS_PAGE);
    }

    @Override
    public String pageTitle() {
        return PageTitles.TRIPS_PAGE.toString();
    }

    @Step("Click Apply to a trip {tripName}")
    public void clickApplyToATrip(String tripName) {
        List<WebElement> trips = getDriver().findElementDynamics(selectorTrips);
        for (WebElement trip : trips) {
            String tripN = trip.findElement(selectorTripName).getText();
            if (tripN.trim().contains(tripName.trim())) {
                PerformAction.allureInfo("Found \"" + tripName + "\" in table");
                WebElement applyButton = trip.findElement(selectorApplyOnTrip);
                applyButton.click();
                PerformAction.allureInfo("Clicked apply button");
                return;
            }
        }
    }

    public List<String> getAvailableTrips(){
        List<WebElement> trips = getDriver().findElementDynamics(selectorTrips);
        List<String> results = new ArrayList<>();
        for(WebElement trip : trips){
            if(!trip.findElements(selectorApplyOnTrip).isEmpty()){
                results.add(trip.findElement(selectorTripName).getText().trim());
            }
        }
        return results;
    }
}
