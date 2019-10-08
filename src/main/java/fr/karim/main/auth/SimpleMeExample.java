package fr.karim.main.auth;
 
import fr.karim.main.auth.facebook.ConstantsExample;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.types.User;
 
public class SimpleMeExample {
 
    public static void main(String[] args) {
        
        // DefaultFacebookClient is the FacebookClient implementation
        // that ships with RestFB. You can customize it by passing in
        // custom JsonMapper and WebRequestor implementations, or simply
        // write your own FacebookClient instead for maximum control.

        // FacebookClient facebookClientViaToken = new DefaultFacebookClient(ConstantsExample.MY_ACCESS_TOKEN);

        // It's also possible to create a client that can only access
        // publicly-visible data - no access token required.
        // Note that many of the examples below will not work unless you supply an access token!

        // FacebookClient publicOnlyFacebookClient = new DefaultFacebookClient();

        // Get added security by using your app secret:

        FacebookClient facebookClient = new DefaultFacebookClient(ConstantsExample.MY_ACCESS_TOKEN, ConstantsExample.MY_APP_SECRET, Version.LATEST);
         
        User user = facebookClient.fetchObject("me", User.class);
         
        System.out.println("User="+ user);
        System.out.println("UserName= "+ user.getUsername());
        System.out.println("Birthday= "+ user.getBirthday());
 
    }
 
}