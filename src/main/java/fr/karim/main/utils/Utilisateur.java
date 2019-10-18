/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.karim.main.utils;

import java.util.ArrayList;

/**
 *
 * @author karim
 */
public class Utilisateur extends com.karimandco.auth.Utilisateur {
    private static ArrayList<CV> cv = new ArrayList<CV>();

    public Utilisateur getIntance(){
        if (Utilisateur.monUtilisateur == null) {
            Utilisateur.monUtilisateur = new Utilisateur();
        }
        return (Utilisateur) Utilisateur.monUtilisateur;
    }

    public static ArrayList<CV> getCv() {
        return cv;
    }
    
}
