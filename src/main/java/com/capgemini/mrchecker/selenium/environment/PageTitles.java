package com.capgemini.mrchecker.selenium.environment;

public enum PageTitles {
	MAIN_PAGE("Demoqa | Just another WordPress site"),
	HOME_PAGE("Home Page - TripIt2Gether"),
	LOGIN_PAGE("Log in - TripIt2Gether"),
	MANAGEACCOUNT_PAGE("Profile - TripIt2Gether"),
	PERSONALDATA_PAGE("Personal Data - TripIt2Gether"),
	DELETEPERSONALDATA_PAGE("Delete Personal Data - TripIt2Gether"),
	REGISTER_PAGE("Register - TripIt2Gether"),
	APPLICATIONS_SHOWPREVIEWDATA_PAGE("Przegląd danych - TripIt2Gether"),
	APPLICATIONS_CREATE_PAGE("Uzupełnij niezbędne informacje - TripIt2Gether"),
	APPLICATIONS_EDIT_PAGE("Szczegóły aplikacji - TripIt2Gether"),
	TRIPS_PAGE("Wycieczki - TripIt2Gether"),
	APPLICATIONS_PAGE("Zgłoszenia - TripIt2Gether");
	
	private final String value;
	
	PageTitles(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}