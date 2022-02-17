package com.capgemini.mrchecker.selenium.environment;

public enum PageSubURLs {
	TRIPS_PAGE(""),
	LOGIN_PAGE("Identity/Account/Login"),
	MANAGEACCOUNT_PAGE("Identity/Account/Manage"),
	PERSONALDATA_PAGE("Identity/Account/Manage/PersonalData"),
	DELETEPERSONALDATA_PAGE("Identity/Account/Manage/DeletePersonalData"),
	REGISTER_PAGE("Identity/Account/Register"),
	APPLICATIONS_SHOWPREVIEWDATA_PAGE("Applications/ShowPreviewData/"),
	APPLICATIONS_CREATE_PAGE("Applications/Create/"),
	APPLICATIONS_EDIT_PAGE("Applications/Edit"),
	APPLICATIONS_PAGE("Applications");

	/*
	 * Sub urls are used as real locations in test environment
	 */
	private final String subURL;
	
	PageSubURLs(String subURL) {
		this.subURL = subURL;
	}
	
	@Override
	public String toString() {
		return getValue();
	}
	
	public String getValue() {
		return subURL;
	}
}