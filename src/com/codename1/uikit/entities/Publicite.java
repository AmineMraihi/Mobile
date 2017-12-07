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
@Table(name = "publicite")
@NamedQueries({
    @NamedQuery(name = "Publicite.findAll", query = "SELECT p FROM Publicite p")
    , @NamedQuery(name = "Publicite.findByIdPub", query = "SELECT p FROM Publicite p WHERE p.idPub = :idPub")
    , @NamedQuery(name = "Publicite.findByDateDebut", query = "SELECT p FROM Publicite p WHERE p.dateDebut = :dateDebut")
    , @NamedQuery(name = "Publicite.findByDateFin", query = "SELECT p FROM Publicite p WHERE p.dateFin = :dateFin")
    , @NamedQuery(name = "Publicite.findByPrix", query = "SELECT p FROM Publicite p WHERE p.prix = :prix")
    , @NamedQuery(name = "Publicite.findByPage", query = "SELECT p FROM Publicite p WHERE p.page = :page")
    , @NamedQuery(name = "Publicite.findByPath", query = "SELECT p FROM Publicite p WHERE p.path = :path")
    , @NamedQuery(name = "Publicite.findByUpdatedAt", query = "SELECT p FROM Publicite p WHERE p.updatedAt = :updatedAt")})
public class Publicite implements Serializable {

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

    public Publicite() {
    }

    public Publicite(Integer idPub) {
        this.idPub = idPub;
    }

    public Publicite(Integer idPub, Date dateDebut, Date dateFin, double prix, String page, String path, Date updatedAt) {
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
        if (!(object instanceof Publicite)) {
            return false;
        }
        Publicite other = (Publicite) object;
        if ((this.idPub == null && other.idPub != null) || (this.idPub != null && !this.idPub.equals(other.idPub))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codename1.uikit.entities.Publicite[ idPub=" + idPub + " ]";
    }
    
}
