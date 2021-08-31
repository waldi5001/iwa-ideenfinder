package de.iwa.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Idee extends AbstractEntiy {

    @ManyToMany
    private Set<Material> material;

    @Temporal(TemporalType.TIMESTAMP)
    private Date erstellungzeitpunkt;

    @Column(length = 30)
    private String bezeichnung;

    @Lob
    private String beschreibung;

    @ManyToMany
    private Set<Kategorie> kategorien;

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public Set<Material> getMaterial() {
        if (material == null) {
            material = new HashSet<>();
        }
        return material;
    }

    public Set<Kategorie> getKategorien() {
        if (kategorien == null) {
            kategorien = new HashSet<>();
        }
        return kategorien;
    }


    public Date getErstellungzeitpunkt() {
        return erstellungzeitpunkt;
    }

    public void setErstellungzeitpunkt(Date erstellungzeitpunkt) {
        this.erstellungzeitpunkt = erstellungzeitpunkt;
    }

}