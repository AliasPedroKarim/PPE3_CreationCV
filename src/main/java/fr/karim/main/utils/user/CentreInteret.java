package fr.karim.main.utils.user;

import fr.karim.connexion.DaoSIO;
import fr.karim.references.Reference;
import java.util.Date;

public class CentreInteret extends Complementaire {

    private String label;
    private String description;
    private Date created_at;
    private Date modified_at;

    protected String TABLE = "centre_interet";

    public CentreInteret() {
        super();
        super.TABLE = this.TABLE;
    }

    public CentreInteret(Integer id, String label, String description, Date created_at, Date modified_at) {
        super();
        super.TABLE = this.TABLE;
        super.id = id;
        this.label = label;
        this.description = description;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }
    
    public Integer sync(){
        
        if(this.id != null){
            return DaoSIO.getInstance().requeteAction("UPDATE " + this.TABLE + " "
                + "SET "
                    + "label = '" + this.getLabel()
                    + "', description = '" + this.getDescription()
                    + "', modified_at = '" + Reference.simpleDateDashes3.format(new Date())
                + "WHERE id = " + this.getId());
        }
        
        return DaoSIO.getInstance().requeteAction("INSERT INTO " + this.TABLE + " (id, label, description, id_utilisateur, created_at, modified_at) "
            + "VALUES ( null, "
                + "'" + this.getLabel()+ "', "
                + "'" + this.getDescription()+ "', "
                + "'" + this.getId_utilisateur() + "', "
                + "'" + Reference.simpleDateDashes3.format(new Date()) + "', "
                + "'" + Reference.simpleDateDashes3.format(new Date()) + "')");
    }

    @Override
    public CentreInteret setId(Integer id) {
        super.setId(id);
        return this;
    }

    @Override
    public CentreInteret setId_utilisateur(Integer id_utilisateur) {
        super.setId_utilisateur(id_utilisateur);
        return this;
    }

    public String getLabel() {
        return label;
    }

    public CentreInteret setLabel(String label) {
        this.label = label;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CentreInteret setDescription(String description) {
        this.description = description;
        return this;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public CentreInteret setCreated_at(Date created_at) {
        this.created_at = created_at;
        return this;
    }

    public Date getModified_at() {
        return modified_at;
    }

    public CentreInteret setModified_at(Date modified_at) {
        this.modified_at = modified_at;
        return this;
    }
}
