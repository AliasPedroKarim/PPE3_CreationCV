/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karimandco.cv;

/**
 *
 * @author c.nadal
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe d'accès aux données contenant des membres statiques afin de manipuler
 * la BDD ON implémente ici le Design Pattern Singleton
 *
 * @author nc
 */
public class DaoSIO {

    /**
     * Membres static (de classe)
     *
     */
    
    private static String NOM_SERVEUR = (String) fr.karim.connexion.DaoSIO.getDAOData().get("NOM_SERVEUR");
    private static String PORT = (String) fr.karim.connexion.DaoSIO.getDAOData().get("PORT");
    private static String NOM_BDD = (String) fr.karim.connexion.DaoSIO.getDAOData().get("NOM_BDD");
    private static String NOM_UTILISATEUR = (String) fr.karim.connexion.DaoSIO.getDAOData().get("NOM_UTILISATEUR");
    private static String MOT_DE_PASSE = (String) fr.karim.connexion.DaoSIO.getDAOData().get("MOT_DE_PASSE");

    private static String chaineConnexion;

    //propriété non statique
    private Connection connexion;

    private static DaoSIO monDao = null;

    /**
     * Constructeur privé ! pour construire un objet, il faut utiliser la
     * méthode publique statique getDaoSIO
     *
     */
    private DaoSIO() {
        try {
            //Définition de l'emplacement de la BDD
            DaoSIO.chaineConnexion = "jdbc:mysql://" + DaoSIO.NOM_SERVEUR + "/" + DaoSIO.NOM_BDD;

            //Création de la connexion à la BDD
            this.connexion = (Connection) DriverManager.getConnection(DaoSIO.chaineConnexion, DaoSIO.NOM_UTILISATEUR, DaoSIO.MOT_DE_PASSE);

        } catch (SQLException ex) {
            Logger.getLogger(DaoSIO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Permet d'obtenir l'objet instancié
     *
     * @return un Objet DaoSIO avec connexion active ... pour une certaine durée
     */
    public static DaoSIO getInstance() {

        if (DaoSIO.monDao == null) {
            DaoSIO.monDao = new DaoSIO();
        } else {
            if (!DaoSIO.monDao.connexionActive()) {
                DaoSIO.monDao = new DaoSIO();
            }
        }
        return DaoSIO.monDao;
    }

    public Boolean connexionActive() {
        Boolean connexionActive = true;
        try {
            if (this.connexion != null && !this.connexion.isValid(0)) {
                connexionActive = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoSIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connexionActive;
    }

    /**
     *
     * @param sql, comportera un ordre selec
     * @return
     */
    public ResultSet requeteSelection(String sql) {
        if(sql != null && !sql.equals("")){
            try {
                if(DaoSIO.getInstance().connexion != null){
                    Statement requete =  DaoSIO.getInstance().connexion.createStatement();
                    return requete.executeQuery(sql);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DaoSIO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    /**
     *
     * @param sql, comportera un ordre insert, update, select, alter, etc.
     * @return le nombre de lignes impactées par la requête action
     *
     */
    public Integer requeteAction(String sql) {
        try {
            if(DaoSIO.getInstance().connexion != null){
                Statement requete = DaoSIO.getInstance().connexion.createStatement();
                return requete.executeUpdate(sql);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DaoSIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    /**
     *
     * @param table
     * @param args
     * @return
     * @throws java.sql.SQLException
     */
    public Integer getLastID(String table, String ...args) throws SQLException{
        
        ResultSet result = this.requeteSelection("SELECT " + (!args.equals("") ? String.join(",", args) : "*" ) 
                + " FROM " + table + " ORDER BY id DESC LIMIT 0, 1");
        
        if(result.next()){
            return Integer.parseInt(result.getString("id"));
        }
        
        return null;
    }
}
