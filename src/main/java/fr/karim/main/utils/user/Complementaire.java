package fr.karim.main.utils.user;

import com.karimandco.cv.DaoSIO;

public class Complementaire {

    protected Integer id;
    protected Integer id_utilisateur;
    protected String TABLE = null;

    public Complementaire() {
    }

    public Integer delete(){
        if(this.id != null){
            return DaoSIO.getInstance().requeteAction("DELETE FROM " + this.TABLE + " WHERE id = " + this.getId());
        }
        return null;
    }

    public Integer getId() {
        return id;
    }

    public Object setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getId_utilisateur() {
        return id_utilisateur;
    }

    public Object setId_utilisateur(Integer id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
        return this;
    }

    public String getTABLE() {
        return TABLE;
    }

    public Object setTABLE(String TABLE) {
        this.TABLE = TABLE;
        return this;
    }
}
