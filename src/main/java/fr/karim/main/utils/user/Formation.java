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
public class Formation {

    private Integer id;
    private String nom;
    private String lieu;
    private String description;
    private Date annee_debut;
    private Date annee_fin;
    private Integer id_cv;
    private Date created_at;
    private Date modified_at;

    public Formation() {
    }

    public Formation(Integer id, String nom, String lieu, String description, Date annee_debut, Date annee_fin, Integer id_cv, Date created_at, Date modified_at) {
        this.id = id;
        this.nom = nom;
        this.lieu = lieu;
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

    public Formation setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getNom() {
        return nom;
    }

    public Formation setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public String getLieu() {
        return lieu;
    }

    public Formation setLieu(String lieu) {
        this.lieu = lieu;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Formation setDescription(String description) {
        this.description = description;
        return this;
    }

    public Date getAnnee_debut() {
        return annee_debut;
    }

    public Formation setAnnee_debut(Date annee_debut) {
        this.annee_debut = annee_debut;
        return this;
    }

    public Date getAnnee_fin() {
        return annee_fin;
    }

    public Formation setAnnee_fin(Date annee_fin) {
        this.annee_fin = annee_fin;
        return this;
    }

    public Integer getId_cv() {
        return id_cv;
    }

    public Formation setId_cv(Integer id_cv) {
        this.id_cv = id_cv;
        return this;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Formation setCreated_at(Date created_at) {
        this.created_at = created_at;
        return this;
    }

    public Date getModified_at() {
        return modified_at;
    }

    public Formation setModified_at(Date modified_at) {
        this.modified_at = modified_at;
        return this;
    }
}
