package com.capgemini.mrchecker.selenium;

import com.capgemini.mrchecker.PerformAction;
import com.capgemini.mrchecker.selenium.Models.User;
import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.test.core.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ApplicationManagementTest extends BaseTest {
    private final TripsPage tripsPage = new TripsPage();
    private final ShowPreviewData showPreviewData = new ShowPreviewData();
    private final CreateApplicationPage createApplicationPage = new CreateApplicationPage();
    private static final RegisterPage registerPage = new RegisterPage();
    private final ManageAccountPage manageAccountPage = new ManageAccountPage();
    private final PersonalDataPage personalDataPage = new PersonalDataPage();
    private final DeletePersonalDataPage deletePersonalDataPage = new DeletePersonalDataPage();
    private final LoginPage loginPage = new LoginPage();
    private final EditApplicationPage editApplicationPage = new EditApplicationPage();
    private final ApplicationsPage applicationsPage = new ApplicationsPage();
    private final User tripOperator = new User("TripOperator@localhost", "tripOper1!");
    private final Menu menu = new Menu();
    private static User user = new User();
    private String selectedTrip;

    @AfterAll
    static void closeDriver(){
        BasePage.getDriver().close();
    }

    @Step("[PRE] register user and create application for a trip")
    public void setUp() {
        user = new User();
        registerPage.load();
        registerPage.fillRegisterData(user.getEmail(), user.getPassword());
        registerPage.clickRegisterButton();
        menu.clickManageAccount();
        manageAccountPage.fillAllUserDataWithSuccessBanner(user);
        tripsPage.load();
        List<String> availableTrips = tripsPage.getAvailableTrips();
        if(availableTrips.isEmpty()){
            Assertions.fail("Cannot assign to any trip");
        }
        selectedTrip = availableTrips.get(0);
        tripsPage.clickApplyToATrip(selectedTrip);
        showPreviewData.clickSave();
        createApplicationPage.fillAllTexAreas();
        createApplicationPage.clickApply();
        menu.clickLogoutButton();
    }

    @Step("[POST] Logout")
    public void tearDown() {
        if(menu.ifUserIsLogIn()) {
            menu.clickLogoutButton();
        }
        menu.clickLoginButton();
        loginPage.fillLoginData(user.getEmail(), user.getPassword());
        loginPage.clickLoginButton();
        menu.clickManageAccount();
        manageAccountPage.clickPersonalData();
        personalDataPage.clickDeleteButton();
        deletePersonalDataPage.fillPassword(user.getPassword());
        deletePersonalDataPage.clickDeleteButton();
    }

    @Test
    @Description("Check if new application status is \"OczekującaPotiwerdzenia\"")
    @TmsLink("T-1")
    void AcceptUserApplicationCheckNewApplicationStatusTest(){
        menu.clickLoginButton();
        loginPage.fillLoginData(tripOperator.getEmail(), tripOperator.getPassword());
        loginPage.clickLoginButton();
        applicationsPage.load();
        PerformAction.waitForPageLoaded(applicationsPage);
        String applicationStatus = applicationsPage.getApplicationStatus(selectedTrip, user.getEmail());
        Assertions.assertEquals("OczekujacaPotiwerdzenia", applicationStatus, "Application status shuld be \"OczekującaPotwierdzenia\"");
    }

    @Test
    @Description("Accept application and check if status is changed to \"Zaakaceptowana\"")
    @TmsLink("T-2")
    void AcceptUserApplicationHappyPathTest(){
        menu.clickLoginButton();
        loginPage.fillLoginData(tripOperator.getEmail(), tripOperator.getPassword());
        loginPage.clickLoginButton();
        applicationsPage.load();
        PerformAction.waitForPageLoaded(applicationsPage);
        applicationsPage.openApplication(selectedTrip, user.getEmail());
        verifyUserData();
        if(!editApplicationPage.isVerified()){
            editApplicationPage.clickIsVerifiedCheckBox();
        }
        editApplicationPage.clickSaveButton();
        Assertions.assertTrue(applicationsPage.isLoaded(), "Applications apage is not loaded");
        String applicationStatus = applicationsPage.getApplicationStatus(selectedTrip, user.getEmail());
        Assertions.assertEquals("Zaakaceptowana", applicationStatus, "Application status should be \"Zaakaceptowana\"");
        PerformAction.makeScreenshot();
    }

    @Test
    @Description("Accept application and check if status is not changed")
    @TmsLink("T-3")
    void AcceptUserApplicationNotVerifiedUserTest(){
        menu.clickLoginButton();
        loginPage.fillLoginData(tripOperator.getEmail(), tripOperator.getPassword());
        loginPage.clickLoginButton();
        applicationsPage.load();
        PerformAction.waitForPageLoaded(applicationsPage);
        applicationsPage.openApplication(selectedTrip, user.getEmail());
        verifyUserData();
        editApplicationPage.clickSaveButton();
        PerformAction.waitForPageLoaded(editApplicationPage);
        Assertions.assertTrue(editApplicationPage.isErrorBannerDisplayed(), "Error banner is not displayed");
        Assertions.assertEquals("Użytkownik nie został Zweryfikowany", editApplicationPage.getErrorBannerText(), "Incorrect Error banner");
        PerformAction.makeScreenshot();
        applicationsPage.load();
        String applicationStatus = applicationsPage.getApplicationStatus(selectedTrip, user.getEmail());
        Assertions.assertEquals("OczekujacaPotiwerdzenia", applicationStatus, "Application status should be \"OczekujacaPotiwerdzenia\"");
        PerformAction.makeScreenshot();
    }

    @Test
    @Description("Reject application and check if status is changed to \"Anulowana\"")
    @TmsLink("T-4")
    void RejectUserApplicationHappyPathTest(){
        menu.clickLoginButton();
        loginPage.fillLoginData(tripOperator.getEmail(), tripOperator.getPassword());
        loginPage.clickLoginButton();
        applicationsPage.load();
        PerformAction.waitForPageLoaded(applicationsPage);
        applicationsPage.openApplication(selectedTrip, user.getEmail());
        verifyUserData();
        editApplicationPage.clickRejectButton();
        editApplicationPage.acceptAlert();
        Assertions.assertTrue(applicationsPage.isLoaded(), "Applications apage is not loaded");
        String applicationStatus = applicationsPage.getApplicationStatus(selectedTrip, user.getEmail());
        Assertions.assertEquals("Anulowana", applicationStatus, "Application status should be \"Anluowana\"");
        PerformAction.makeScreenshot();
    }

    @Test
    @Description("Discard Reject application and check if status is not changed \"Anulowana\"")
    @TmsLink("T-5")
    void DiscardRejectingUserApplicationHappyPathTest(){
        menu.clickLoginButton();
        loginPage.fillLoginData(tripOperator.getEmail(), tripOperator.getPassword());
        loginPage.clickLoginButton();
        applicationsPage.load();
        PerformAction.waitForPageLoaded(applicationsPage);
        String appStatus = applicationsPage.getApplicationStatus(selectedTrip, user.getEmail());
        applicationsPage.openApplication(selectedTrip, user.getEmail());
        verifyUserData();
        editApplicationPage.clickRejectButton();
        editApplicationPage.discardAlert();
        Assertions.assertTrue(editApplicationPage.isLoaded(), "Edit Applications apage is not loaded");
        applicationsPage.load();
        String applicationStatus = applicationsPage.getApplicationStatus(selectedTrip, user.getEmail());
        Assertions.assertEquals(appStatus, applicationStatus, "Application status should be \""+ appStatus +"\"");
        PerformAction.makeScreenshot();
    }

    @Step("Verify user Data on Edit Application page")
    private void verifyUserData(){
        PerformAction.makeScreenshot();
        Assertions.assertEquals(user.getName(), editApplicationPage.getName(), "User Name is incorrect");
        Assertions.assertEquals(user.getSurname(), editApplicationPage.getSurname(), "User Surname is incorrect");
        Assertions.assertTrue(editApplicationPage.getDateOfBirth().contains(user.getDateOfBirth().toString()), "User Date of birth is incorrect");
        Assertions.assertEquals(user.getEmail(), editApplicationPage.getEmail(), "User Name is incorrect");
        Assertions.assertEquals(user.getPhoneNumber(), editApplicationPage.getPhoneNumber(), "User Phone Number is incorrect");
    }


}
