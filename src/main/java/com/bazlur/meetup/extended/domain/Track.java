package com.bazlur.meetup.extended.domain;

/**
 * @author Bazlur Rahman Rokon
 * @since 1/31/17.
 */
public enum Track {
	JAVA_SCRIPT("JavaScript"),
	CLOUD("Cloud"),
	SERVER_SIDE_JAVA("Server side Java"),
	ANDROID("Android"),
	WEB("Web");

	private String displayName;

	Track(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public String getId() {
		return name();
	}


}
