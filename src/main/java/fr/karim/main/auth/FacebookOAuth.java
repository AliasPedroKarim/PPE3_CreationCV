/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.karim.main.auth;

import fr.karim.main.auth.facebook.ConstantsExample;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.json.JsonObject;
import fr.karim.main.auth.facebook.CustomUser;

/**
 *
 * @author karim
 */
 
public class FacebookOAuth {
 
   public static void main(String[] args) {
   
       FacebookClient facebookClient = new DefaultFacebookClient(ConstantsExample.MY_ACCESS_TOKEN, ConstantsExample.MY_APP_SECRET, Version.VERSION_4_0);
 
       CustomUser user = facebookClient.fetchObject("me", CustomUser.class,
               Parameter.with("fields", "id, name, email, first_name, last_name"));
 
       System.out.println("First Name= " + user.getFirstName());
       System.out.println("Last Name= " + user.getLastName());
       System.out.println("Full Name= " + user.getFullName());
       System.out.println("Email= " + user.getEmail());
       
       JsonObject userData = facebookClient.fetchObject("me",
                JsonObject.class, Parameter.with("fields", "name, first_name"));
 
        System.out.println("userData=" + userData);
 
        System.out.println(userData.getString("FirstName", "first_name"));
        System.out.println(userData.getString("Name", "name"));
   }
}