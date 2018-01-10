/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Amine
 */
@Entity
@Table(name = "reclamation")
@NamedQueries({
    @NamedQuery(name = "Reclamation.findAll", query = "SELECT r FROM Reclamation r")
    , @NamedQuery(name = "Reclamation.findByIdReclamation", query = "SELECT r FROM Reclamation r WHERE r.idReclamation = :idReclamation")
    , @NamedQuery(name = "Reclamation.findByType", query = "SELECT r FROM Reclamation r WHERE r.type = :type")})
public class Reclamation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_reclamation")
    private Integer idReclamation;
    @Basic(optional = false)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @Lob
    @Column(name = "text")
    private String text;
    @JoinColumn(name = "id_reclamant", referencedColumnName = "id_user")
    @ManyToOne
    private User idReclamant;
    @JoinColumn(name = "id_P_reclame", referencedColumnName = "id_user")
    @ManyToOne
    private User idPreclame;

    public Reclamation() {
    }

    public Reclamation(String type, String text) {
        this.type = type;
        this.text = text;
    }

    public Reclamation(Integer idReclamation) {
        this.idReclamation = idReclamation;
    }

    public Reclamation(Integer idReclamation, String type, String text) {
        this.idReclamation = idReclamation;
        this.type = type;
        this.text = text;
    }

    public Integer getIdReclamation() {
        return idReclamation;
    }

    public void setIdReclamation(Integer idReclamation) {
        this.idReclamation = idReclamation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getIdReclamant() {
        return idReclamant;
    }

    public void setIdReclamant(User idReclamant) {
        this.idReclamant = idReclamant;
    }

    public User getIdPreclame() {
        return idPreclame;
    }

    public void setIdPreclame(User idPreclame) {
        this.idPreclame = idPreclame;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReclamation != null ? idReclamation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reclamation)) {
            return false;
        }
        Reclamation other = (Reclamation) object;
        if ((this.idReclamation == null && other.idReclamation != null) || (this.idReclamation != null && !this.idReclamation.equals(other.idReclamation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codename1.uikit.entities.Reclamation[ idReclamation=" + idReclamation + " ]";
    }
    
}
