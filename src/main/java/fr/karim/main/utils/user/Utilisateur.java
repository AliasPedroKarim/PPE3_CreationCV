/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.karim.main.utils.user;

import fr.karim.main.utils.Helpers;
import fr.karim.references.Reference;

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
public class Utilisateur extends com.karimandco.auth.Utilisateur {
    private ArrayList<CV> cv = new ArrayList<CV>();

    private String genre;
    private String url_site;
    private Media media;

    private List<CentreInteret> centreInterets = new ArrayList<CentreInteret>();
    private List<Informatique> informatiques = new ArrayList<Informatique>();
    private List<Langue> langues = new ArrayList<Langue>();
    private Adresse adresse;

    private Date created_at;
    private Date modified_at;

    protected Utilisateur() { }

    public static Utilisateur getInstance() {
        if (Utilisateur.monUtilisateur == null) {
            Utilisateur.monUtilisateur = new Utilisateur();
            return (Utilisateur) Utilisateur.monUtilisateur;
        }
        return (Utilisateur) Utilisateur.monUtilisateur;
    }

    public static Boolean logout(){
        Utilisateur.getInstance().monUtilisateur = null;
        Utilisateur.setIdentifiant(null);
        return Utilisateur.getInstance().getEstConnecte();
    }
    
    public Boolean loadDataUser(Map<String, Object> data){
        if(data!= null && data.size() > 0){
            Utilisateur.setIdentifiant((String) data.get("identifiant"));
            
            this.setCourriel((String) data.get("courriel"));

            String dateString = Reference.simpleDateSlashes2.format( (java.sql.Date) data.get("date_de_naissance") );
            
            this.setDateNaissance(!dateString.toString().equals("") ? dateString.toString() : null);
            this.setId((Integer) data.get("id"));
            this.setNom((String) data.get("nom"));
            this.setPrenom((String) data.get("prenom"));
            this.setStatut((Integer) data.get("statut"));
            this.setNumeroTelephone((String) data.get("num_telephone"));

            this.setGenre((String) data.get("genre"));
            this.setCreated_at((java.sql.Date) data.get("created_at"));
            this.setModified_at((java.sql.Date) data.get("modified_at"));

            this.setModified_at((java.sql.Date) data.get("modified_at"));

            loadMediaUser((Integer) data.get("id"));
            loadCentreInteretUser((Integer) data.get("id"));
            loadInformatiqueUser((Integer) data.get("id"));
            loadLangueUser((Integer) data.get("id"));
			loadAdresseUser((Integer) data.get("id"));

            loadCVUser((Integer) data.get("id"));

            
            return !data.get("identifiant").equals("") ? true : false;
        }
        return null;
    }
    
    public void loadMediaUser(Integer id){
        List<Map<String,Object>> req = null;
        try {
            req = Helpers.getIntance().get(new String[]{ "media" }, null, Helpers.getIntance().whereElement("id_utilisateur", id, ""));
        } catch (SQLException ex) {
            Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        if(req != null && req.size() > 0){
            Map<String, Object> item = req.get(0);
            if(item != null){
                media = new Media()
                    .setId((Integer) item.get("id"))
                    .setFilename((String) item.get("filename"))
                    .setImage((byte[]) item.get("image"))
                    .setExtension((String) item.get("extension"))
                    .setSize((String) item.get("size"))
                    .setCreated_at((java.sql.Date) item.get("created_at"))
                    .setModified_at((java.sql.Date) item.get("modified_at"));
            }
        }
    }

    public void loadCentreInteretUser(Integer id){
        List<Map<String,Object>> req = null;
        try {
            req = Helpers.getIntance().get(new String[]{ "centre_interet" }, null, Helpers.getIntance().whereElement("id_utilisateur", id, ""));
        } catch (SQLException ex) {
            Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(req != null && req.size() > 0){
            for (Map<String,Object> item : req) {
                centreInterets.add(
                    new CentreInteret()
                        .setId((Integer) item.get("id"))
                        .setLabel((String) item.get("label"))
                        .setDescription((String) item.get("description"))
                        .setCreated_at((java.sql.Date) item.get("created_at"))
                        .setModified_at((java.sql.Date) item.get("modified_at"))
                );
            }
        }
    }

    public void loadInformatiqueUser(Integer id){
        List<Map<String,Object>> req = null;
        try {
            req = Helpers.getIntance().get(new String[]{ "informatique" }, null, Helpers.getIntance().whereElement("id_utilisateur", id, ""));
        } catch (SQLException ex) {
            Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(req != null && req.size() > 0){
            for (Map<String,Object> item : req) {
                informatiques.add(
                    new Informatique()
                        .setId((Integer) item.get("id"))
                        .setLabel((String) item.get("label"))
                        .setDescription((String) item.get("description"))
                        .setCreated_at((java.sql.Date) item.get("created_at"))
                        .setModified_at((java.sql.Date) item.get("modified_at"))
                );
            }
        }
    }

    public void loadLangueUser(Integer id){
        List<Map<String,Object>> req = null;
        try {
            req = Helpers.getIntance().get(new String[]{ "langue" }, null, Helpers.getIntance().whereElement("id_utilisateur", id, ""));
        } catch (SQLException ex) {
            Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(req != null && req.size() > 0){
            for (Map<String,Object> item : req) {
                langues.add(
                    new Langue()
                        .setId((Integer) item.get("id"))
                        .setLabel((String) item.get("label"))
                        .setNiveau((String) item.get("niveau"))
                        .setPercentage((Integer) item.get("percentage"))
                        .setCreated_at((java.sql.Date) item.get("created_at"))
                        .setModified_at((java.sql.Date) item.get("modified_at"))
                );
            }
        }
    }

	public void loadAdresseUser(Integer id){
		List<Map<String,Object>> req = null;
		try {
			req = Helpers.getIntance().get(new String[]{ "adresse" }, null, Helpers.getIntance().whereElement("id_utilisateur", id, ""));
		} catch (SQLException ex) {
			Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
		}

		if(req != null && req.size() > 0){
			Map<String, Object> item = req.get(0);
			if(item != null){
				adresse = new Adresse()
					.setId((Integer) item.get("id"))
					.setN_rue((Integer) item.get("n_rue"))
					.setNom_rue((String) item.get("nom_rue"))
					.setCode_postale((String) item.get("code_postale"))
					.setVille((String) item.get("ville"))
					.setPays((String) item.get("pays"));
			}
		}
	}

    public void loadCVUser(Integer id){
        List<Map<String,Object>> req = null;
        try {
            req = Helpers.getIntance().get(new String[]{ "cv" }, null, Helpers.getIntance().whereElement("id_utilisateur", id, ""));
        } catch (SQLException ex) {
            Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(req != null && req.size() > 0){
            for (Map<String,Object> item : req) {
                cv.add(
                    new CV((Integer) item.get("id"))
                        .setTitre((String) item.get("titre"))
                        .setDescription((String) item.get("description"))
                        .setSignature((String) item.get("signature"))
                        .setNom_maitrise((String) item.get("nom_maitrise"))
                        .setMaitrise((String) item.get("maitrise"))
                        .setId_utilisateur((Integer) item.get("id_utilisateur"))
                        .setCreated_at((java.sql.Date) item.get("created_at"))
                        .setModified_at((java.sql.Date) item.get("modified_at"))
                );
            }
        }
    }

    public void reloadCV(){
        cv = new ArrayList<CV>();
        loadCVUser(this.getId());
    }

    public CV returnSingleCV(Integer cvID){
        if (cv != null && cv.size() > 0){
            for (CV i : cv){
                if (i.getId() != null && i.getId().equals(cvID)){
                    return i;
                }
            }
        }
        return null;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public ArrayList<CV> getCv() {
        return cv;
    }

    public List<CentreInteret> getCentreInterets() {
        return centreInterets;
    }

    public List<Informatique> getInformatiques() {
        return informatiques;
    }

    public List<Langue> getLangues() {
        return langues;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getModified_at() {
        return modified_at;
    }

    public void setModified_at(Date modified_at) {
        this.modified_at = modified_at;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

	public String getUrl_site() {
		return url_site;
	}

	public void setUrl_site(String url_site) {
		this.url_site = url_site;
	}

	public boolean isClient(){
        return this.getStatut() == 0;
    }
    
    public boolean isAdmin(){
        return this.getStatut() == 1;
    }
    
}
