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
@Table(name = "jardin_enfant")
@NamedQueries({
    @NamedQuery(name = "JardinEnfant.findAll", query = "SELECT j FROM JardinEnfant j")
    , @NamedQuery(name = "JardinEnfant.findByIdJardin", query = "SELECT j FROM JardinEnfant j WHERE j.idJardin = :idJardin")
    , @NamedQuery(name = "JardinEnfant.findByNbPlaceTotal", query = "SELECT j FROM JardinEnfant j WHERE j.nbPlaceTotal = :nbPlaceTotal")
    , @NamedQuery(name = "JardinEnfant.findByNbPlaceLibre", query = "SELECT j FROM JardinEnfant j WHERE j.nbPlaceLibre = :nbPlaceLibre")
    , @NamedQuery(name = "JardinEnfant.findByDateReservation", query = "SELECT j FROM JardinEnfant j WHERE j.dateReservation = :dateReservation")
    , @NamedQuery(name = "JardinEnfant.findByIdUser", query = "SELECT j FROM JardinEnfant j WHERE j.idUser = :idUser")})
public class JardinEnfant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_jardin")
    private Integer idJardin;
    @Basic(optional = false)
    @Column(name = "nb_place_total")
    private int nbPlaceTotal;
    @Basic(optional = false)
    @Column(name = "nb_place_libre")
    private int nbPlaceLibre;
    @Basic(optional = false)
    @Column(name = "date_reservation")
    @Temporal(TemporalType.DATE)
    private Date dateReservation;
    @Basic(optional = false)
    @Column(name = "id_user")
    private int idUser;

    public JardinEnfant() {
    }

    public JardinEnfant(Integer idJardin) {
        this.idJardin = idJardin;
    }

    public JardinEnfant(Integer idJardin, int nbPlaceTotal, int nbPlaceLibre, Date dateReservation, int idUser) {
        this.idJardin = idJardin;
        this.nbPlaceTotal = nbPlaceTotal;
        this.nbPlaceLibre = nbPlaceLibre;
        this.dateReservation = dateReservation;
        this.idUser = idUser;
    }

    public Integer getIdJardin() {
        return idJardin;
    }

    public void setIdJardin(Integer idJardin) {
        this.idJardin = idJardin;
    }

    public int getNbPlaceTotal() {
        return nbPlaceTotal;
    }

    public void setNbPlaceTotal(int nbPlaceTotal) {
        this.nbPlaceTotal = nbPlaceTotal;
    }

    public int getNbPlaceLibre() {
        return nbPlaceLibre;
    }

    public void setNbPlaceLibre(int nbPlaceLibre) {
        this.nbPlaceLibre = nbPlaceLibre;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJardin != null ? idJardin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JardinEnfant)) {
            return false;
        }
        JardinEnfant other = (JardinEnfant) object;
        if ((this.idJardin == null && other.idJardin != null) || (this.idJardin != null && !this.idJardin.equals(other.idJardin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codename1.uikit.entities.JardinEnfant[ idJardin=" + idJardin + " ]";
    }
    
}
