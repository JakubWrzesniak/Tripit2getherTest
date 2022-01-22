package com.capgemini.mrchecker.selenium.environment;

public enum PageTitles {
	MAIN_PAGE("Demoqa | Just another WordPress site"),
	HOME_PAGE("Home Page - TripIt2Gether"),
	LOGIN_PAGE("Log in - TripIt2Gether");
	
	private final String value;
	
	PageTitles(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}