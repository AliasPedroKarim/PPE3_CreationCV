/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.karim.main.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author karim
 */
public class Utilisateur extends com.karimandco.auth.Utilisateur {
    private static ArrayList<CV> cv = new ArrayList<CV>();
    
    private String genre;

    protected Utilisateur() { }

    public static Utilisateur getInstance() {
        if (Utilisateur.monUtilisateur == null) {
            Utilisateur.monUtilisateur = new Utilisateur();
        }
        return (Utilisateur) Utilisateur.monUtilisateur;
    }

    public static ArrayList<CV> getCv() {
        return cv;
    }
    
    public Boolean loadDataUser(Map<String, Object> data){
        if(data!= null && data.size() > 0){
            Utilisateur.setIdentifiant((String) data.get("identifiant"));
            
            this.setCourriel((String) data.get("courriel"));
            
            String dateString = new SimpleDateFormat("dd/MM/yyyy").format( (java.sql.Date) data.get("date_de_naissance") );
            
            this.setDateNaissance(!dateString.toString().equals("") ? dateString.toString() : null);
            this.setId((Integer) data.get("id"));
            this.setNom((String) data.get("nom"));
            this.setPrenom((String) data.get("prenom"));
            this.setStatut((Integer) data.get("status"));
            this.setNumeroTelephone((String) data.get("num_telephone"));
            this.setGenre((String) data.get("genre"));
            
            return !data.get("identifiant").equals("") ? true : false;
        }
        return null;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
}
