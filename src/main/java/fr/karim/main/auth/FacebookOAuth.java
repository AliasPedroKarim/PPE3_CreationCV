/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.karim.main.auth;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.json.JsonObject;
import fr.karim.main.auth.facebook.Constants;
import fr.karim.main.auth.facebook.CustomUser;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;

import java.awt.*;
import java.io.IOException;
import java.net.*;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author karim
 */

public class FacebookOAuth {

    private String redirectUri = null;
    private Map data;
    private LocalServerReceiveFB localServerReceiveFB;

   public FacebookOAuth() {

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
                       FacebookClient facebookClient = new DefaultFacebookClient((String) this.data.get("access_token"), Constants.MY_APP_SECRET, Version.VERSION_4_0);

                       CustomUser user = facebookClient.fetchObject("me", CustomUser.class,
                               Parameter.with("fields", "id, name, email, first_name, last_name"));

                       System.out.println("First Name= " + user.getFirstName());
                       System.out.println("Last Name= " + user.getLastName());
                       System.out.println("Full Name= " + user.getFullName());
                       System.out.println("Email= " + user.getEmail());

                       JsonObject userData = facebookClient.fetchObject("me",
                               JsonObject.class, Parameter.with("fields", "name, first_name"));

                       System.out.println("userData=" + userData);

                       System.out.println(userData.getString("first_name", "first_name"));
                       System.out.println(userData.getString("name", "name"));
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
           .setParameter("client_id", Constants.MY_APP_ID)
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
           this.data = this.localServerReceiveFB.waitForData();
           this.data = this.localServerReceiveFB.waitForData();
       } finally {
           if (this.data != null){
               this.localServerReceiveFB.stop();
           }
       }

       return this.data != null;
   }

    public static void main(String[] args) {
        new FacebookOAuth();
    }
}