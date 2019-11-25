package fr.karim.main.utils.user;

import fr.karim.connexion.DaoSIO;
import fr.karim.references.Reference;
import java.util.Date;

public class Langue extends Complementaire {

    private String label;
    private String niveau;
    private Integer percentage;
    private Date created_at;
    private Date modified_at;
    
    protected String TABLE = "langue";
    
    public Langue() {
        super();
        super.TABLE = this.TABLE;
    }

    public Langue(Integer id, String label, String niveau, Integer percentage, Date created_at, Date modified_at) {
        super();
        super.TABLE = this.TABLE;
        super.id = id;
        this.label = label;
        this.niveau = niveau;
        this.percentage = percentage;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }

    public Integer sync() {

        if (this.id != null) {
            return DaoSIO.getInstance().requeteAction("UPDATE " + this.TABLE + " "
                + "SET "
                    + "label = '" + this.getLabel()
                    + "', niveau = '" + this.getNiveau()
                    + "', percentage = '" + this.getPercentage()
                    + "', modified_at = '" + Reference.simpleDateDashes3.format(new Date())
                + "WHERE id = " + this.getId());
        }

        return DaoSIO.getInstance().requeteAction("INSERT INTO " + this.TABLE + " (id, label, niveau, percentage, id_utilisateur, created_at, modified_at) "
            + "VALUES (null, "
                + "'" + this.getLabel() + "', "
                + "'" + this.getNiveau() + "', "
                + "'" + this.getPercentage() + "', "
                + "'" + this.getId_utilisateur() + "', "
                + "'" + Reference.simpleDateDashes3.format(new Date()) + "', "
                + "'" + Reference.simpleDateDashes3.format(new Date()) + "')");
    }

    @Override
    public Langue setId(Integer id) {
        super.setId(id);
        return this;
    }

    @Override
    public Langue setId_utilisateur(Integer id_utilisateur) {
        super.setId_utilisateur(id_utilisateur);
        return this;
    }

    public String getLabel() {
        return label;
    }

    public Langue setLabel(String label) {
        this.label = label;
        return this;
    }

    public String getNiveau() {
        return niveau;
    }

    public Langue setNiveau(String niveau) {
        this.niveau = niveau;
        return this;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public Langue setPercentage(Integer percentage) {
        this.percentage = percentage;
        return this;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Langue setCreated_at(Date created_at) {
        this.created_at = created_at;
        return this;
    }

    public Date getModified_at() {
        return modified_at;
    }

    public Langue setModified_at(Date modified_at) {
        this.modified_at = modified_at;
        return this;
    }
}
