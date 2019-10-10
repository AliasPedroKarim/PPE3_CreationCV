/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.karim.connexion;

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
public class DaoSIOExample {

    /**
     * Membres static (de classe)
     *
     */
    private static String NOM_SERVEUR = "< HOST >";
    private static String PORT = "3306";
    private static String NOM_BDD = "< NAME DB >";
    private static String NOM_UTILISATEUR = "< NAME USER >";
    private static String MOT_DE_PASSE = "< PASS >";

    private static String chaineConnexion;

    //propriété non statique
    private Connection connexion;

    private static DaoSIOExample monDao = null;

    /**
     * Constructeur privé ! pour construire un objet, il faut utiliser la
     * méthode publique statique getDaoSIO
     *
     */
    private DaoSIOExample() {
        try {
            //Définition de l'emplacement de la BDD
            DaoSIOExample.chaineConnexion = "jdbc:mysql://" + DaoSIOExample.NOM_SERVEUR + "/" + DaoSIOExample.NOM_BDD;

            //Création de la connexion à la BDD
            this.connexion = (Connection) DriverManager.getConnection(DaoSIOExample.chaineConnexion, DaoSIOExample.NOM_UTILISATEUR, DaoSIOExample.MOT_DE_PASSE);

        } catch (SQLException ex) {
            Logger.getLogger(DaoSIOExample.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Permet d'obtenir l'objet instancié
     *
     * @return un Objet DaoSIO avec connexion active ... pour une certaine durée
     */
    public static DaoSIOExample getInstance() {

        if (DaoSIOExample.monDao == null) {
            DaoSIOExample.monDao = new DaoSIOExample();
        } else {
            if (!DaoSIOExample.monDao.connexionActive()) {
                DaoSIOExample.monDao = new DaoSIOExample();
            }
        }
        return DaoSIOExample.monDao;
    }

    public Boolean connexionActive() {
        Boolean connexionActive = true;
        try {
            if (this.connexion != null && this.connexion.isClosed()) {
                connexionActive = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoSIOExample.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connexionActive;
    }

    /**
     *
     * @param sql, comportera un ordre selec
     * @return
     */
    public ResultSet requeteSelection(String sql) {
        if(sql != null && sql.equals("")){
            try {
                Statement requete = new DaoSIOExample().connexion.createStatement();
                return requete.executeQuery(sql);

            } catch (SQLException ex) {
                Logger.getLogger(DaoSIOExample.class.getName()).log(Level.SEVERE, null, ex);
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
            Statement requete = new DaoSIOExample().connexion.createStatement();
            return requete.executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(DaoSIOExample.class.getName()).log(Level.SEVERE, null, ex);
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
