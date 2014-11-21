package com.epam.testapp.service;

public class NavigationHolder {

	private int currentNewsId;
	private int previousNewsId;
	private String currentPageName;
	private String previousPageName;
	
	public NavigationHolder(){}

	public int getCurrentNewsId() {
		return currentNewsId;
	}
	
	public void setCurrentNewsId( int currentNewsId ) {
		this.currentNewsId = currentNewsId;
	}

	public int getPreviousNewsId() {
		return previousNewsId;
	}

	public void setPreviousNewsId( int previousNewsId ) {
		this.previousNewsId = previousNewsId;
	}

	public String getCurrentPageName() {
		return currentPageName;
	}

	public void setCurrentPageName( String currentPageName ) {
		this.currentPageName = currentPageName;
	}

	public String getPreviousPageName() {
		return previousPageName;
	}

	public void setPreviousPageName( String previousPageName ) {
		this.previousPageName = previousPageName;
	}
}
