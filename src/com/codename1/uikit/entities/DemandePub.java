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
@Table(name = "demande_pub")
@NamedQueries({
    @NamedQuery(name = "DemandePub.findAll", query = "SELECT d FROM DemandePub d")
    , @NamedQuery(name = "DemandePub.findByIdPub", query = "SELECT d FROM DemandePub d WHERE d.idPub = :idPub")
    , @NamedQuery(name = "DemandePub.findByDateDebut", query = "SELECT d FROM DemandePub d WHERE d.dateDebut = :dateDebut")
    , @NamedQuery(name = "DemandePub.findByDateFin", query = "SELECT d FROM DemandePub d WHERE d.dateFin = :dateFin")
    , @NamedQuery(name = "DemandePub.findByPrix", query = "SELECT d FROM DemandePub d WHERE d.prix = :prix")
    , @NamedQuery(name = "DemandePub.findByPage", query = "SELECT d FROM DemandePub d WHERE d.page = :page")
    , @NamedQuery(name = "DemandePub.findByPath", query = "SELECT d FROM DemandePub d WHERE d.path = :path")
    , @NamedQuery(name = "DemandePub.findByUpdatedAt", query = "SELECT d FROM DemandePub d WHERE d.updatedAt = :updatedAt")})
public class DemandePub implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pub")
    private Integer idPub;
    @Basic(optional = false)
    @Column(name = "date_debut")
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Basic(optional = false)
    @Column(name = "date_fin")
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    @Basic(optional = false)
    @Column(name = "prix")
    private double prix;
    @Basic(optional = false)
    @Column(name = "page")
    private String page;
    @Basic(optional = false)
    @Column(name = "path")
    private String path;
    @Basic(optional = false)
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinColumn(name = "id_boutique", referencedColumnName = "id_boutique")
    @ManyToOne
    private Boutique idBoutique;

    public DemandePub() {
    }

    public DemandePub(Integer idPub) {
        this.idPub = idPub;
    }

    public DemandePub(Integer idPub, Date dateDebut, Date dateFin, double prix, String page, String path, Date updatedAt) {
        this.idPub = idPub;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prix = prix;
        this.page = page;
        this.path = path;
        this.updatedAt = updatedAt;
    }

    public Integer getIdPub() {
        return idPub;
    }

    public void setIdPub(Integer idPub) {
        this.idPub = idPub;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
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

    public Boutique getIdBoutique() {
        return idBoutique;
    }

    public void setIdBoutique(Boutique idBoutique) {
        this.idBoutique = idBoutique;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPub != null ? idPub.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DemandePub)) {
            return false;
        }
        DemandePub other = (DemandePub) object;
        if ((this.idPub == null && other.idPub != null) || (this.idPub != null && !this.idPub.equals(other.idPub))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codename1.uikit.entities.DemandePub[ idPub=" + idPub + " ]";
    }
    
}
