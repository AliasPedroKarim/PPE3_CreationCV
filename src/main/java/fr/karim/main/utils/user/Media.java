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
public class Media {
    private Integer id;
    private String filename;
    private byte[] image;
    private String extension;
    private String size;
    // id_utilisateur
    private Date created_at;
    private Date modified_at;

    public Media() {
    }
    
    public Media(Integer id, String filename, byte[] image, String extension, String size, Date created_at, Date modified_at) {
        this.id = id;
        this.filename = filename;
        this.image = image;
        this.extension = extension;
        this.size = size;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }
    
    public Integer getId() {
        return id;
    }

    public Media setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getFilename() {
        return filename;
    }

    public Media setFilename(String filename) {
        this.filename = filename;
        return this;
    }

    public byte[] getImage() {
        return image;
    }

    public Media setImage(byte[] image) {
        this.image = image;
        return this;
    }

    public String getExtension() {
        return extension;
    }

    public Media setExtension(String extension) {
        this.extension = extension;
        return this;
    }

    public String getSize() {
        return size;
    }

    public Media setSize(String size) {
        this.size = size;
        return this;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Media setCreated_at(Date created_at) {
        this.created_at = created_at;
        return this;
    }

    public Date getModified_at() {
        return modified_at;
    }

    public Media setModified_at(Date modified_at) {
        this.modified_at = modified_at;
        return this;
    }
    
    
}
