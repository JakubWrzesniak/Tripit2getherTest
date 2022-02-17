package com.capgemini.mrchecker.selenium;

import com.capgemini.mrchecker.PerformAction;
import com.capgemini.mrchecker.selenium.Models.User;
import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import io.qameta.allure.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SignUpForATripTest extends BaseTest {
    private final TripsPage tripsPage = new TripsPage();
    private final ShowPreviewData showPreviewData = new ShowPreviewData();
    private final CreateApplicationPage createApplicationPage = new CreateApplicationPage();
    private final RegisterPage registerPage = new RegisterPage();
    private final PersonalDataPage personalDataPage = new PersonalDataPage();
    private final DeletePersonalDataPage deletePersonalDataPage = new DeletePersonalDataPage();
    private final ManageAccountPage manageAccountPage = new ManageAccountPage();
    private final Menu menu = new Menu();
    private User user;

    @AfterAll
    static void closeDriver(){
        BasePage.getDriver().close();
    }

    @Step("[PRE] register user")
    public void setUp() {
        user = new User();
        BFLogger.logInfo(user.getPassword());
        registerPage.load();
        registerPage.fillRegisterData(user.getEmail(), user.getPassword());
        registerPage.clickRegisterButton();
    }

    @Step("[POST] Logout")
    public void tearDown() {
        if(menu.ifUserIsLogIn()){
            menu.clickManageAccount();
            manageAccountPage.clickPersonalData();
            personalDataPage.clickDeleteButton();
            deletePersonalDataPage.fillPassword(user.getPassword());
            deletePersonalDataPage.clickDeleteButton();
        }
    }

    @Test
    @Description("Celem testu jest pomyślne zapisanie się użytkownika na wycieczkę")
    @TmsLink("T-6")
    void SignUpForATripSuccess_test(){
        menu.clickManageAccount();
        fillUserDataInAccountManagement();
        String tripName = "Warsow City Tour";
        PerformAction.allureInfo("Trip Name: " + tripName);
        tripsPage.load();
        tripsPage.clickApplyToATrip(tripName);
        Assertions.assertTrue(showPreviewData.isLoaded(), "Prewiev data page is not loaded");
        PerformAction.allureInfo("Show preview Data page is opened");
        Assertions.assertEquals(tripName, showPreviewData.getTripName(), "Trip name is incorrect");
        verifyUserDataOnShowPreviewDataPage();
        showPreviewData.clickSave();
        PerformAction.waitForPageLoaded(createApplicationPage);
        Assertions.assertTrue(createApplicationPage.isLoaded(), "Create application page is not loaded");
        createApplicationPage.fillAllTexAreas();
        createApplicationPage.clickApply();
        PerformAction.waitForPageLoaded(tripsPage);
        Assertions.assertTrue(tripsPage.isLoaded(), "Trips page is not loaded");
    }

    @Test
    @TmsLink("T-7")
    @Description("Sign up for test - Missing Data")
    void SignUpForATripMissingUserData_test(){
        String tripName = "Warsow City Tour";
        tripsPage.load();
        tripsPage.clickApplyToATrip(tripName);
        Assertions.assertTrue(showPreviewData.isLoaded(), "Prewiev data page is not loaded");
        Assertions.assertEquals(tripName, showPreviewData.getTripName(), "Trip name is incorrect");
        showPreviewData.clickSave();
        PerformAction.waitForPageLoaded(showPreviewData);
        verifyUserMissingDataField();
    }

    @Test
    @TmsLink("T-8")
    @Description("Sign up for test - Missing Data")
    void SignUpForATripMissingUserData_FillUserCorrectData_test(){
        menu.clickManageAccount();
        String tripName = "Warsow City Tour";
        tripsPage.load();
        tripsPage.clickApplyToATrip(tripName);
        Assertions.assertTrue(showPreviewData.isLoaded(), "Prewiev data page is not loaded");
        Assertions.assertEquals(tripName, showPreviewData.getTripName(), "Trip name is incorrect");
        showPreviewData.clickSave();
        PerformAction.waitForPageLoaded(showPreviewData);
        verifyUserMissingDataField();
        menu.clickManageAccount();
        menu.clickManageAccount();
        manageAccountPage.fillAllUserDataWithSuccessBanner(user);
        tripsPage.load();
        tripsPage.clickApplyToATrip(tripName);
        Assertions.assertTrue(showPreviewData.isLoaded(), "Prewiev data page is not loaded");
        Assertions.assertEquals(tripName, showPreviewData.getTripName(), "Trip name is incorrect");
        verifyUserDataOnShowPreviewDataPage();
        showPreviewData.clickSave();
        PerformAction.waitForPageLoaded(createApplicationPage);
        Assertions.assertTrue(createApplicationPage.isLoaded(), "Create application page is not loaded");
        createApplicationPage.fillAllTexAreas();
        createApplicationPage.clickApply();
        PerformAction.waitForPageLoaded(tripsPage);
        Assertions.assertTrue(tripsPage.isLoaded(), "Trips page is not loaded");
    }

    @Step("Verify user data on Shoe preview Page")
    public void verifyUserDataOnShowPreviewDataPage(){
        PerformAction.makeScreenshot();
        Assertions.assertEquals(user.getName() ,showPreviewData.getName(), "User Name is incorrect");
        PerformAction.allureInfo("User name is correct: " + showPreviewData.getName());
        Assertions.assertEquals(user.getSurname() ,showPreviewData.getSurname(), "User Surname is incorrect");
        PerformAction.allureInfo("User surname is correct: " + showPreviewData.getSurname());
        Assertions.assertEquals(user.getAddress() ,showPreviewData.getAddress(), "User Address is incorrect");
        PerformAction.allureInfo("User Address is correct: " + showPreviewData.getAddress());
        Assertions.assertTrue(showPreviewData.getDateOfBirth().contains(user.getDateOfBirth().toString()), "User Date of Birth is incorrect");
        PerformAction.allureInfo("User DateOfBirth is correct: " + showPreviewData.getDateOfBirth());
        Assertions.assertEquals(user.getEmail() ,showPreviewData.getEmail(), "User Email is incorrect");
        PerformAction.allureInfo("User Email is correct: " + showPreviewData.getEmail());
        Assertions.assertEquals(user.getPhoneNumber() ,showPreviewData.getPhoneNumber(), "User Email is incorrect");
        PerformAction.allureInfo("User PhoneNumber is correct: " + showPreviewData.getPhoneNumber());
    }

    @Step("Veriy User missing data field")
    public void verifyUserMissingDataField(){
        PerformAction.makeScreenshot();
        Assertions.assertTrue(showPreviewData.getName().isEmpty(), "Input name is not empty");
        Assertions.assertTrue(showPreviewData.isNameErrorVisible(), "Error input name not displayed");
        Assertions.assertTrue(showPreviewData.getSurname().isEmpty(), "Input surname is not empty");
        Assertions.assertTrue(showPreviewData.isSurnameErrorVisible(), "Error input surname not displayed");
        Assertions.assertTrue(showPreviewData.getAddress().isEmpty(), "Input Address is not empty");
        Assertions.assertTrue(showPreviewData.isAddressErrorVisible(), "Error input Address not displayed");
        Assertions.assertTrue(showPreviewData.getPhoneNumber().isEmpty(), "Input phone number is not empty");
        Assertions.assertTrue(showPreviewData.isPhoneNumberErrorVisible(), "Error input phone number not displayed");
    }

    @Step("Fill user data in account management")
    public void fillUserDataInAccountManagement(){
        Assertions.assertTrue(manageAccountPage.fillAllUserDataWithSuccessBanner(user), "Success banner after change data is not visible");
        PerformAction.allureInfo("Success banner after change user data displayed");
    }
}
