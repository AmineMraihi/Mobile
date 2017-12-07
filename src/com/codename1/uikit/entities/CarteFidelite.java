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
@Table(name = "carte_fidelite")
@NamedQueries({
    @NamedQuery(name = "CarteFidelite.findAll", query = "SELECT c FROM CarteFidelite c")
    , @NamedQuery(name = "CarteFidelite.findByIdCarteFidelite", query = "SELECT c FROM CarteFidelite c WHERE c.idCarteFidelite = :idCarteFidelite")
    , @NamedQuery(name = "CarteFidelite.findByNbPoint", query = "SELECT c FROM CarteFidelite c WHERE c.nbPoint = :nbPoint")
    , @NamedQuery(name = "CarteFidelite.findByDateCreation", query = "SELECT c FROM CarteFidelite c WHERE c.dateCreation = :dateCreation")})
public class CarteFidelite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_carte_fidelite")
    private Integer idCarteFidelite;
    @Basic(optional = false)
    @Column(name = "nb_point")
    private int nbPoint;
    @Basic(optional = false)
    @Column(name = "date_creation")
    @Temporal(TemporalType.DATE)
    private Date dateCreation;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne
    private User idUser;

    public CarteFidelite() {
    }

    public CarteFidelite(Integer idCarteFidelite) {
        this.idCarteFidelite = idCarteFidelite;
    }

    public CarteFidelite(Integer idCarteFidelite, int nbPoint, Date dateCreation) {
        this.idCarteFidelite = idCarteFidelite;
        this.nbPoint = nbPoint;
        this.dateCreation = dateCreation;
    }

    public Integer getIdCarteFidelite() {
        return idCarteFidelite;
    }

    public void setIdCarteFidelite(Integer idCarteFidelite) {
        this.idCarteFidelite = idCarteFidelite;
    }

    public int getNbPoint() {
        return nbPoint;
    }

    public void setNbPoint(int nbPoint) {
        this.nbPoint = nbPoint;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
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
        hash += (idCarteFidelite != null ? idCarteFidelite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CarteFidelite)) {
            return false;
        }
        CarteFidelite other = (CarteFidelite) object;
        if ((this.idCarteFidelite == null && other.idCarteFidelite != null) || (this.idCarteFidelite != null && !this.idCarteFidelite.equals(other.idCarteFidelite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codename1.uikit.entities.CarteFidelite[ idCarteFidelite=" + idCarteFidelite + " ]";
    }
    
}
