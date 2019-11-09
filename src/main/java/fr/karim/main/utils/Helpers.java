/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.karim.main.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.karim.connexion.DaoSIO;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author karim
 */
public class Helpers {
    
    private static Helpers instances;
    
    public static Color COLOR_VALIDATED = new Color(119, 221, 119);
    public static Color COLOR_NOT_VALIDATED = new Color(236, 25, 25);

    private Helpers() { }
    
    public static Helpers getIntance(){
        return instances != null ? instances : new Helpers();
    }
    
    /**
     * Convertir le ResultSet en une liste de cartes, où chaque carte représente
     * une ligne avec columnNames et columValues
     *
     * @param res
     * @return
     * @throws SQLException
     */
    public List<Map<String, Object>> resultSetToList(ResultSet res) throws SQLException {
        if(res != null){
            ResultSetMetaData md = res.getMetaData();
            int columns = md.getColumnCount();
            List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
            while (res.next()) {
                Map<String, Object> row = new LinkedHashMap<String, Object>(columns);
                for (int i = 1; i <= columns; ++i) {
                    row.put(md.getColumnName(i), res.getObject(i));
                }
                rows.add(row);
            }
            return rows;
        }
        return null;
    }
    
    public List<Map<String, Object>> getCV(Integer id_utilisateur) throws SQLException{
        return this.getCV(null, id_utilisateur);
    }
    
    /**
     * Permet de récuperer toute les informations d'un cv de puis la base de
     * donnée grace à l'id de l'utilisateur
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public List<Map<String, Object>> getCV(Integer id, Integer id_utilisateur) throws SQLException {
        if (DaoSIO.getInstance().connexionActive()) {
            ResultSet res = DaoSIO.getInstance().requeteSelection(""
                    + "SELECT * "
                    + "FROM cv "
                    + "WHERE " + (id != null ? "id = " + id + " AND" : "") 
                    + " id_utilisateur = " + id_utilisateur);

            if (res != null && res.isBeforeFirst()) {
                return resultSetToList(res);
            }
            return null;
        }
        return null;
    }
    /**
     * 
     * @param indentifiable | Peut-être l'email ou l'identifiant
     * @return
     * @throws SQLException 
     */
    public List<Map<String, Object>> getUtilisateur(String indentifiable) throws SQLException{
        return this.getUtilisateur(null, indentifiable);
    }
    
    /**
     * 
     * @param id
     * @param indentifiable | Peut-être l'email ou l'identifiant
     * @return
     * @throws SQLException 
     */
    public List<Map<String, Object>> getUtilisateur(Integer id, String indentifiable) throws SQLException{
        if (DaoSIO.getInstance().connexionActive()) {
            ResultSet res = DaoSIO.getInstance().requeteSelection(""
                    + "SELECT * "
                    + "FROM utilisateurs "
                    + "WHERE " + (id != null ? "id = " + id + " AND" : "") 
                    + "( identifiant = '" + indentifiable + "' OR courriel = '" + indentifiable + "' )");

            if (res != null && res.isBeforeFirst()) {
                return resultSetToList(res);
            }
            return null;
        }
        return null;
    }

    public List<Map<String, Object>> get(String[] table, String[] selected, Map<String,Object> ...args) throws SQLException{

        selected = selected == null ? new String[]{ "*" } : selected;
        StringBuilder r = new StringBuilder();
        
        if(args != null && args.length > 0){
            for(Map<String,Object> element : args){
            	if (element instanceof Map){
                        r
                        .append(" ")
                        .append(element.get("name"))
                        .append(" = ")
                        .append("\"" + element.get("value") + "\" ")
                        .append(element.get("predicat").toString().toUpperCase());
                }
            }
        }

        if (DaoSIO.getInstance().connexionActive() && table != null) {
            
            System.out.println("SELECT " + String.join(",", selected) + " "
                    + "FROM " + String.join(",", table) + " "
                    + ( r != null ? "WHERE " + r.toString() : "" ));
            
            ResultSet res = DaoSIO.getInstance().requeteSelection(""
                    + "SELECT " + String.join(",", selected) + " "
                    + "FROM " + String.join(",", table) + " "
                    + ( r != null ? "WHERE " + r.toString() : "" ));

            if (res != null && res.isBeforeFirst()) {
                return resultSetToList(res);
            }
            return null;
        }
        return null;
    }

    public Map<String,Object> whereElement(String a, Object b, String c){
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("name", a);
        map.put("value", b);
        map.put("predicat", c);
        return map;
    }
    
    @FunctionalInterface
    public interface SimpleDocumentListener extends DocumentListener {
        void update(DocumentEvent e);

        @Override
        default void insertUpdate(DocumentEvent e) { update(e); }
        @Override
        default void removeUpdate(DocumentEvent e) { update(e); }
        @Override
        default void changedUpdate(DocumentEvent e) { update(e); }
    }
    
    public boolean supprimerToutCV(Integer idCV) throws SQLException{
        
        boolean ok = true;
        List<Map<String, Object>> cvs = Helpers.getIntance().get(new String[]{"cv"}, null,
                                Helpers.getIntance().whereElement("id", idCV, ""));
        
        if(cvs != null && cvs.size() > 0){
            for(String element : new String[]{ "formation", "experience_pro" }){
                List<Map<String, Object>> dependents = Helpers.getIntance().get(new String[]{ element }, null,
                                Helpers.getIntance().whereElement("id_cv", cvs.get(0).get("id"), ""));
                if(dependents != null && dependents.size() > 0){
                    for(Map<String, Object> dependent : dependents){
                        
                        if(element.equals("formation")){
                            ok = Helpers.getIntance().deleteFormation((Integer) dependent.get("id"));
                        }else if(element.equals("experience_pro")){
                            ok = Helpers.getIntance().deleteExperiencePro((Integer) dependent.get("id"));
                        }
                        
                    }
                }
            }
            
            return ok =  ok ? Helpers.getIntance().deleteCV(idCV) : false;
        }
        return false;
    }
    
    /**
     * Grâce à l'identifiant de la formation cette méthode est capable de supprimer une formation de la base de donnée.
     * 
     * @param idFormation
     * @return
     * @throws SQLException 
     */
    public boolean deleteFormation(Integer idFormation) throws SQLException {
        Integer res;
        if (idFormation != null) {
            res = DaoSIO.getInstance().requeteAction("DELETE FROM `formation` WHERE id = " + idFormation);
            // Ternaire java à voir sur internet
            return res != null ? true : false;
        }
        return false;
    }

    /**
     * Grâce à l'identifiant de l'expérience professionnelle cette méthode est capable de supprimer une expérience professionnelle de la base de donnée.
     * 
     * @param idExperiencePro
     * @return
     * @throws SQLException 
     */
    public boolean deleteExperiencePro(Integer idExperiencePro) throws SQLException {
        Integer res;
        if (idExperiencePro != null) {
            res = DaoSIO.getInstance().requeteAction("DELETE FROM `experience_pro` WHERE id = " + idExperiencePro);
            return res != null ? true : false;
        }
        return false;
    }

    /**
     * Grâce à l'identifiant d'un CV avec cette méthode on peut supprimer un CV de la base de donnée.
     * 
     * @param idCV
     * @return
     * @throws SQLException 
     */
    public boolean deleteCV(Integer idCV) throws SQLException {
        Integer res;
        if (idCV != null) {
            res = DaoSIO.getInstance().requeteAction("DELETE FROM `cv` WHERE id = " + idCV);
            return res != null ? true : false;
        }
        return false;
    }

    // Prettify JSON Utility
    public static String crunchifyPrettyJSONUtility(String simpleJSON) {
        JsonParser crunhifyParser = new JsonParser();
        JsonObject json = crunhifyParser.parse(simpleJSON).getAsJsonObject();

        Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = prettyGson.toJson(json);

        return prettyJson;
    }

    // Function to convert ArrayList<String> to String[]
    public static String[] GetStringArray(List<String> arr) {

        // declaration and initialise String Array
        String str[] = new String[arr.size()];

        // ArrayList to Array Conversion
        for (int j = 0; j < arr.size(); j++) {

            // Assign each value to String array
            str[j] = arr.get(j);
        }

        return str;
    }

    public static Object[] appendValue(Object[] obj, Object newObj) {

        ArrayList<Object> temp = new ArrayList<Object>(Arrays.asList(obj));
        temp.add(newObj);
        return temp.toArray();

    }
}
