/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.karim.main.utils.user;

import java.util.Date;

/**
 *
 * @author karim
 */
public class ExperiencePro {

    private Integer id;
    private String entreprise;
    private String adresse;
    private String description;
    private Date annee_debut;
    private Date annee_fin;
    private Integer id_cv;
    private Date created_at;
    private Date modified_at;

    public ExperiencePro() {
    }

    public ExperiencePro(Integer id, String entreprise, String adresse, String description, Date annee_debut, Date annee_fin, Integer id_cv, Date created_at, Date modified_at) {
        this.id = id;
        this.entreprise = entreprise;
        this.adresse = adresse;
        this.description = description;
        this.annee_debut = annee_debut;
        this.annee_fin = annee_fin;
        this.id_cv = id_cv;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }

    public Integer getId() {
        return id;
    }

    public ExperiencePro setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public ExperiencePro setEntreprise(String entreprise) {
        this.entreprise = entreprise;
        return this;
    }

    public String getAdresse() {
        return adresse;
    }

    public ExperiencePro setAdresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ExperiencePro setDescription(String description) {
        this.description = description;
        return this;
    }

    public Date getAnnee_debut() {
        return annee_debut;
    }

    public ExperiencePro setAnnee_debut(Date annee_debut) {
        this.annee_debut = annee_debut;
        return this;
    }

    public Date getAnnee_fin() {
        return annee_fin;
    }

    public ExperiencePro setAnnee_fin(Date annee_fin) {
        this.annee_fin = annee_fin;
        return this;
    }

    public Integer getId_cv() {
        return id_cv;
    }

    public ExperiencePro setId_cv(Integer id_cv) {
        this.id_cv = id_cv;
        return this;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public ExperiencePro setCreated_at(Date created_at) {
        this.created_at = created_at;
        return this;
    }

    public Date getModified_at() {
        return modified_at;
    }

    public ExperiencePro setModified_at(Date modified_at) {
        this.modified_at = modified_at;
        return this;
    }
}
