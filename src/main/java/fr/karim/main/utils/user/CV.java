/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.karim.main.utils.user;

import fr.karim.main.utils.Helpers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author karim
 */
public class CV {
    private List<Formation> formations = new ArrayList<Formation>();
    private List<ExperiencePro> experiencePros = new ArrayList<ExperiencePro>();

    private Integer id;
    private String titre;
    private String description;
    private String signature;
    private String nom_maitrise;
    private String maitrise;
    private Integer id_utilisateur;
    private Date created_at;
    private Date modified_at;

    public CV() { }

    public CV(Integer id) {
        this.id = id;

        loadFormation();
        loadExperiencePro();
    }

    public CV(List<Formation> formations, List<ExperiencePro> experiencePros) {
        this.formations = formations;
        this.experiencePros = experiencePros;
    }

    public List<Formation> getFormations() {
        return formations;
    }

    public CV setFormations(ArrayList<Formation> formations) {
        this.formations = formations;
        return this;
    }

    public List<ExperiencePro> getExperiencePros() {
        return experiencePros;
    }

    public CV setExperiencePros(ArrayList<ExperiencePro> experiencePros) {
        this.experiencePros = experiencePros;
        return this;
    }

    private void loadExperiencePro() {
        if(this.id != null){
            List<Map<String,Object>> req = null;
            try {
                req = Helpers.getIntance().get(new String[]{ "experience_pro" }, null, Helpers.getIntance().whereElement("id_cv", this.id, ""));
            } catch (SQLException ex) {
                Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
            }

            if(req != null && req.size() > 0){
                for (Map<String,Object> item : req) {
                    experiencePros.add(
                            new ExperiencePro()
                                    .setId((Integer) item.get("id"))
                                    .setEntreprise((String) item.get("entreprise"))
                                    .setAdresse((String) item.get("adresse"))
                                    .setDescription((String) item.get("description"))
                                    .setAnnee_debut((java.sql.Date) item.get("annee_debut"))
                                    .setAnnee_fin((java.sql.Date) item.get("annee_fin"))
                                    .setId_cv((Integer) item.get("id_cv"))
                                    .setCreated_at((java.sql.Date) item.get("created_at"))
                                    .setModified_at((java.sql.Date) item.get("modified_at"))
                    );
                }
            }
        }
    }

    private void loadFormation() {
        if(this.id != null){
            List<Map<String,Object>> req = null;
            try {
                req = Helpers.getIntance().get(new String[]{ "formation" }, null, Helpers.getIntance().whereElement("id_cv", this.id, ""));
            } catch (SQLException ex) {
                Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
            }

            if(req != null && req.size() > 0){
                for (Map<String,Object> item : req) {
                    formations.add(
                            new Formation()
                                    .setId((Integer) item.get("id"))
                                    .setNom((String) item.get("nom"))
                                    .setLieu((String) item.get("lieu"))
                                    .setDescription((String) item.get("description"))
                                    .setAnnee_debut((java.sql.Date) item.get("annee_debut"))
                                    .setAnnee_fin((java.sql.Date) item.get("annee_fin"))
                                    .setId_cv((Integer) item.get("id_cv"))
                                    .setCreated_at((java.sql.Date) item.get("created_at"))
                                    .setModified_at((java.sql.Date) item.get("modified_at"))
                    );
                }
            }
        }
    }

    public void reloadExperiencePro(){
        experiencePros = new ArrayList<ExperiencePro>();
        loadExperiencePro();
    }

    public void reloadFormation(){
        formations = new ArrayList<Formation>();
        loadFormation();
    }

    /**
     * Attention ! cette methode permet de recharger que les elements inclu dans le class cv donc ici (formations et experience_pros)
     */
    public void reloadAllElement(){
        reloadExperiencePro();
        reloadFormation();
    }

    public Integer getId() {
        return id;
    }

    public CV setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getTitre() {
        return titre;
    }

    public CV setTitre(String titre) {
        this.titre = titre;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CV setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSignature() {
        return signature;
    }

    public CV setSignature(String signature) {
        this.signature = signature;
        return this;
    }

    public String getNom_maitrise() {
        return nom_maitrise;
    }

    public CV setNom_maitrise(String nom_maitrise) {
        this.nom_maitrise = nom_maitrise;
        return this;
    }

    public String getMaitrise() {
        return maitrise;
    }

    public CV setMaitrise(String maitrise) {
        this.maitrise = maitrise;
        return this;
    }

    public Integer getId_utilisateur() {
        return id_utilisateur;
    }

    public CV setId_utilisateur(Integer id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
        return this;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public CV setCreated_at(Date created_at) {
        this.created_at = created_at;
        return this;
    }

    public Date getModified_at() {
        return modified_at;
    }

    public CV setModified_at(Date modified_at) {
        this.modified_at = modified_at;
        return this;
    }
}
