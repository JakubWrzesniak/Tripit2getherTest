package com.capgemini.mrchecker;

import com.capgemini.mrchecker.selenium.core.BasePage;

public class PerformAction {

    public static void waitForPageLoaded(BasePage page) {
        BasePage.getWebDriverWait().until(driver -> page.isLoaded());
    }
}
