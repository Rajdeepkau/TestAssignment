package com.saucedemo;

public enum BrowserOptions {
	CHROME("Chrome"), 
	EDGE("Edge"), 
	FIREFOX("Firefox");

	private final String value;

	BrowserOptions(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
