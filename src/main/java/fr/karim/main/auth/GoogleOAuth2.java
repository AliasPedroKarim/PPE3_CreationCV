/*
 * Copyright (c) 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package fr.karim.main.auth;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Tokeninfo;
import com.google.api.services.oauth2.model.Userinfoplus;
import com.google.gson.Gson;
import fr.karim.main.auth.google.CustomUserGoogle;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 * Command-line sample for the Google OAuth2 API described at <a
 * href="http://code.google.com/apis/accounts/docs/OAuth2Login.html">Using OAuth 2.0 for Login
 * (Experimental)</a>.
 *
 * @author Yaniv Inbar
 */
public class GoogleOAuth2 {

	/**
	 * Be sure to specify the name of your application. If the application name is {@code null} or
	 * blank, the application will log a warning. Suggested format is "MyCompany-ProductName/1.0".
	 */
	private static final String APPLICATION_NAME = "";

	/** Directory to store user credentials. */
	private static final java.io.File DATA_STORE_DIR =
			new java.io.File(System.getProperty("user.home"), ".store/oauth2_sample");

	/**
	 * Global instance of the {@link DataStoreFactory}. The best practice is to make it a single
	 * globally shared instance across your application.
	 */
	private static FileDataStoreFactory dataStoreFactory;

	/** Global instance of the HTTP transport. */
	private static HttpTransport httpTransport;

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	/** OAuth 2.0 scopes. */
	private static final List<String> SCOPES = Arrays.asList(
			"https://www.googleapis.com/auth/userinfo.profile",
			"https://www.googleapis.com/auth/userinfo.email");

	private static Oauth2 oauth2;
	private static GoogleClientSecrets clientSecrets;

	private Userinfoplus user = null;

	private CustomUserGoogle userOAuth = null;

	public CustomUserGoogle getUserOAuth() {
		return userOAuth;
	}

	public void setUserOAuth(CustomUserGoogle userOAuth) {
		this.userOAuth = userOAuth;
	}

	public Userinfoplus getUser() {
		return user;
	}

	public void setUser(Userinfoplus user) {
		this.user = user;
	}

	public GoogleOAuth2() {
		try {
			httpTransport = GoogleNetHttpTransport.newTrustedTransport();

			// Pour stocker les données d'authentification
			/*dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);*/

			// authorization
			Credential credential = this.authorize();
			// set up global Oauth2 instance
			oauth2 = new Oauth2.Builder(httpTransport, JSON_FACTORY, credential).setApplicationName(
					APPLICATION_NAME).build();
			// run commands
			this.tokenInfo(credential.getAccessToken());
			this.userInfo();
			// success!
			return;
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	/** Authorizes the installed application to access user's protected data. */
	private Credential authorize() throws Exception {
		// load client secrets
		clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
				new InputStreamReader(GoogleOAuth2.class.getResourceAsStream("/client_secrets.google.json")));
		if (clientSecrets.getDetails().getClientId().startsWith("Enter")
				|| clientSecrets.getDetails().getClientSecret().startsWith("Enter ")) {
			System.out.println("Enter Client ID and Secret from https://code.google.com/apis/console/ "
					+ "into oauth2-cmdline-sample/src/main/resources/client_secrets.json");
			System.exit(1);
		}
		// set up authorization code flow
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
				httpTransport, JSON_FACTORY, clientSecrets, SCOPES).build();


		// Pour stocker les données d'authentification
    /*GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
            httpTransport, JSON_FACTORY, clientSecrets, SCOPES).setDataStoreFactory(
            dataStoreFactory).build();*/

		// authorize
		return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
	}

	private void tokenInfo(String accessToken) throws IOException {
		header("Validating a token");
		Tokeninfo tokeninfo = oauth2.tokeninfo().setAccessToken(accessToken).execute();
		System.out.println(tokeninfo.toPrettyString());
		if (!tokeninfo.getAudience().equals(clientSecrets.getDetails().getClientId())) {
			System.err.println("ERROR: audience does not match our client ID!");
		}
	}

	private void userInfo() throws IOException, ParseException {
		header("Obtaining User Profile Information");
		Userinfoplus userinfo = oauth2.userinfo().get().execute();
		CustomUserGoogle user = new Gson().fromJson(userinfo.toPrettyString(), CustomUserGoogle.class);

		this.setUser(userinfo);
		this.setUserOAuth(user);

		System.out.println(userinfo.toPrettyString());

	}

	public void header(String name) {
		System.out.println();
		System.out.println("================== " + name + " ==================");
		System.out.println();
	}

	public static void main(String[] args) {
		new GoogleOAuth2();
	}
}