package de.iwa.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Material extends AbstractEntiy{
    @Column(length = 15)
    private String bezeichnung;

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }
}
