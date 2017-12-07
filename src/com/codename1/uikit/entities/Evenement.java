/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Amine
 */
@Entity
@Table(name = "evenement")
@NamedQueries({
    @NamedQuery(name = "Evenement.findAll", query = "SELECT e FROM Evenement e")
    , @NamedQuery(name = "Evenement.findByIdEvenement", query = "SELECT e FROM Evenement e WHERE e.idEvenement = :idEvenement")
    , @NamedQuery(name = "Evenement.findByNom", query = "SELECT e FROM Evenement e WHERE e.nom = :nom")
    , @NamedQuery(name = "Evenement.findByDate", query = "SELECT e FROM Evenement e WHERE e.date = :date")
    , @NamedQuery(name = "Evenement.findByPath", query = "SELECT e FROM Evenement e WHERE e.path = :path")
    , @NamedQuery(name = "Evenement.findByUpdatedAt", query = "SELECT e FROM Evenement e WHERE e.updatedAt = :updatedAt")})
public class Evenement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_evenement")
    private Integer idEvenement;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @Lob
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @Column(name = "path")
    private String path;
    @Basic(optional = false)
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne
    private User idUser;

    public Evenement() {
    }

    public Evenement(Integer idEvenement) {
        this.idEvenement = idEvenement;
    }

    public Evenement(Integer idEvenement, String nom, String description, Date date, String path, Date updatedAt) {
        this.idEvenement = idEvenement;
        this.nom = nom;
        this.description = description;
        this.date = date;
        this.path = path;
        this.updatedAt = updatedAt;
    }

    public Evenement(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    
    
    public Integer getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(Integer idEvenement) {
        this.idEvenement = idEvenement;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEvenement != null ? idEvenement.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evenement)) {
            return false;
        }
        Evenement other = (Evenement) object;
        if ((this.idEvenement == null && other.idEvenement != null) || (this.idEvenement != null && !this.idEvenement.equals(other.idEvenement))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codename1.uikit.entities.Evenement[ idEvenement=" + idEvenement + " ]";
    }
    
}
