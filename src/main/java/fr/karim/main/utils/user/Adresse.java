package fr.karim.main.utils.user;

import fr.karim.connexion.DaoSIO;

public class Adresse extends Complementaire {

    private Integer n_rue;
    private String nom_rue;
    private String code_postale;
    private String ville;
    private String pays;
    
    protected String TABLE = "adresse";

    public Adresse() {
        super();
        super.TABLE = this.TABLE;
    }

    public Adresse(Integer id, Integer n_rue, String nom_rue, String code_postale, String ville, String pays) {
        super();
        super.TABLE = this.TABLE;
        super.id = id;
        this.n_rue = n_rue;
        this.nom_rue = nom_rue;
        this.code_postale = code_postale;
        this.ville = ville;
        this.pays = pays;
    }
    
    public Integer sync() {
        if(this.id != null){
            return DaoSIO.getInstance().requeteAction("UPDATE " + super.TABLE + " "
                + "SET "
                    + "n_rue = '" + this.getN_rue()
                    + "', nom_rue = '" + this.getNom_rue()
                    + "', code_postale = '" + this.getCode_postale()
                    + "', ville = '" + this.getVille()
                    + "', pays  = '" + this.getPays() + "' "
                + "WHERE id = " + this.getId());
        }
        return DaoSIO.getInstance().requeteAction("INSERT INTO " + super.TABLE + " (id, n_rue, nom_rue, code_postale, ville, pays, id_utilisateur) "
            + "VALUES ( null, "
                + "'" + this.getN_rue() + "', "
                + "'" + this.getNom_rue() + "', "
                + "'" + this.getCode_postale() + "', "
                + "'" + this.getVille() + "', "
                + "'" + this.getPays() + "', "
                + "'" + this.getId_utilisateur() + "')");
    }

    @Override
    public Adresse setId(Integer id) {
        super.setId(id);
        return this;
    }

    @Override
    public Object setId_utilisateur(Integer id_utilisateur) {
        super.setId_utilisateur(id_utilisateur);
        return this;
    }

    public Integer getN_rue() {
        return n_rue;
    }

    public Adresse setN_rue(Integer n_rue) {
        this.n_rue = n_rue;
        return this;
    }

    public String getNom_rue() {
        return nom_rue;
    }

    public Adresse setNom_rue(String nom_rue) {
        this.nom_rue = nom_rue;
        return this;
    }

    public String getCode_postale() {
        return code_postale;
    }

    public Adresse setCode_postale(String code_postale) {
        this.code_postale = code_postale;
        return this;
    }

    public String getVille() {
        return ville;
    }

    public Adresse setVille(String ville) {
        this.ville = ville;
        return this;
    }

    public String getPays() {
        return pays;
    }

    public Adresse setPays(String pays) {
        this.pays = pays;
        return this;
    }
}
