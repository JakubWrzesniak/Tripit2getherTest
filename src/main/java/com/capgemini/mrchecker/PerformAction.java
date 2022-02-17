package com.capgemini.mrchecker;

import com.capgemini.mrchecker.selenium.core.BasePage;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class PerformAction {

    public static void waitForPageLoaded(BasePage page) {
        BasePage.getWebDriverWait().until(driver -> page.isLoaded());
        PerformAction.allureInfo("Page is loaded: " + page.pageTitle());
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] makeScreenshot() {
        return ((TakesScreenshot) BasePage.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public static void allureInfo(String message){
        Allure.step("[INFO] " + message);
    }
}
