/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.karim.main.auth.facebook;

import com.restfb.Facebook;

/**
 *
 * @author karim
 */
 
public class CustomUserFacebook {

    @Facebook("id")
    private String id;

    @Facebook("name")
    private String fullName;

    @Facebook("first_name")
    private String firstName;
 
    @Facebook("last_name")
    private String lastName;

    @Facebook("short_name")
    private String shortName;
 
    @Facebook("email")
    private String email;

    @Facebook("birthday")
    private String birthday;

    @Facebook("gender")
    private String gender;

    // Getter and Setter

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getFullName() { return fullName; }

    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getShortName() { return shortName; }

    public void setShortName(String shortName) { this.shortName = shortName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getBirthday() { return birthday; }

    public void setBirthday(String birthday) { this.birthday = birthday; }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }
}
