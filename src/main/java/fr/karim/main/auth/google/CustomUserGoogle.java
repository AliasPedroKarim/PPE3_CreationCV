package fr.karim.main.auth.google;

import fr.karim.main.utils.GoogleParseJSON;

public class CustomUserGoogle {

	@GoogleParseJSON("email")
	private String email;

	@GoogleParseJSON("family_name")
	private String family_name;

	@GoogleParseJSON("given_name")
	private String given_name;

	@GoogleParseJSON("id")
	private String id;

	@GoogleParseJSON("locale")
	private String locale;

	@GoogleParseJSON("name")
	private String name;

	@GoogleParseJSON("picture")
	private String picture;

	@GoogleParseJSON("verified_email")
	private String verified_email;

	public String getEmail() { return email; }

	public void setEmail(String email) { this.email = email; }

	public String getFamily_name() { return family_name; }

	public void setFamily_name(String family_name) { this.family_name = family_name; }

	public String getGiven_name() { return given_name; }

	public void setGiven_name(String given_name) { this.given_name = given_name; }

	public String getId() { return id; }

	public void setId(String id) { this.id = id; }

	public String getLocale() { return locale; }

	public void setLocale(String locale) { this.locale = locale; }

	public String getName() { return name; }

	public void setName(String name) { this.name = name; }

	public String getPicture() { return picture; }

	public void setPicture(String picture) { this.picture = picture; }

	public String getVerified_email() { return verified_email; }

	public void setVerified_email(String verified_email) { this.verified_email = verified_email; }
}
