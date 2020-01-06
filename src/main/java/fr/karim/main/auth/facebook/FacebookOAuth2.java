/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.karim.main.auth.facebook;

import fr.karim.connexion.Constants;
import fr.karim.main.auth.google.GoogleOAuth2;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author karim
 */

public class FacebookOAuth2 {

    private String redirectUri = null;
    private Map userResponseData;
    private LocalServerReceiveFB localServerReceiveFB;
    GoogleClientSecrets clientSecrets;

    private CustomUserFacebook userOAuth = null;

    public CustomUserFacebook getUserOAuth() {
        return userOAuth;
    }

    private void setUserOAuth(CustomUserFacebook userOAuth) {
        this.userOAuth = userOAuth;
    }

	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    public FacebookOAuth2() {

	    try {
	        clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
				    new InputStreamReader(GoogleOAuth2.class.getResourceAsStream("/client_secrets.facebook.json")));
		    System.out.println(clientSecrets.getInstalled().getClientId());
		    System.out.println(clientSecrets.getInstalled().getClientSecret());
	    } catch (IOException e) {
		    e.printStackTrace();
	    }

	    this.localServerReceiveFB = new LocalServerReceiveFB();

        URI uri = null;
        try {
            uri = buildURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        if (uri != null){
            try {
                this.OpenDesktopBroswer(uri);

                try {
                    if (this.StuffData()){
                        FacebookClient facebookClient = new DefaultFacebookClient((String) this.userResponseData.get("access_token"), clientSecrets.getInstalled().getClientSecret(), Version.VERSION_4_0);

                        CustomUserFacebook user = facebookClient.fetchObject("me", CustomUserFacebook.class,
                                Parameter.with("fields", "id, name, email, first_name, last_name, birthday, gender, short_name"));

                        this.setUserOAuth(user);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private URI buildURI() throws URISyntaxException {
        return new URIBuilder()
                .setScheme("https")
                .setHost("www.facebook.com")
                .setPath("/v4.0/dialog/oauth")
                .setParameter("client_id", clientSecrets.getInstalled().getClientId())
                .setParameter("display", "popup")
                .setParameter("response_type", "token")
                .setParameter("redirect_uri", Constants.REDIRECT_URI)
                .setParameter("state", UUID.randomUUID().toString())
                .build();
    }

    private void OpenDesktopBroswer(URI uri) throws IOException {
        HttpGet httpget = new HttpGet(uri);
        System.out.println("URL Utilisé ➜ " + httpget.getURI());

        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(httpget.getURI());
        }else{
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("xdg-open " + httpget.getURI());
        }
    }

    private boolean StuffData() throws IOException {
        try {
            this.redirectUri = this.localServerReceiveFB.getRedirectUri();
            this.userResponseData = this.localServerReceiveFB.waitForData();
            this.userResponseData = this.localServerReceiveFB.waitForData();
        } finally {
            if (this.userResponseData != null){
                this.localServerReceiveFB.stop();
            }
        }

        return this.userResponseData != null;
    }

    public static void main(String[] args) {

        new FacebookOAuth2();

    }
}