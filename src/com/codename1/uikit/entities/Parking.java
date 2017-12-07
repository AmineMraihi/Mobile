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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Amine
 */
@Entity
@Table(name = "parking")
@NamedQueries({
    @NamedQuery(name = "Parking.findAll", query = "SELECT p FROM Parking p")
    , @NamedQuery(name = "Parking.findByIdParking", query = "SELECT p FROM Parking p WHERE p.idParking = :idParking")
    , @NamedQuery(name = "Parking.findByNomParking", query = "SELECT p FROM Parking p WHERE p.nomParking = :nomParking")
    , @NamedQuery(name = "Parking.findByNbPlaceTotal", query = "SELECT p FROM Parking p WHERE p.nbPlaceTotal = :nbPlaceTotal")
    , @NamedQuery(name = "Parking.findByNbPlaceLibre", query = "SELECT p FROM Parking p WHERE p.nbPlaceLibre = :nbPlaceLibre")})
public class Parking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_parking")
    private Integer idParking;
    @Basic(optional = false)
    @Column(name = "nom_parking")
    private String nomParking;
    @Basic(optional = false)
    @Column(name = "nb_place_total")
    private int nbPlaceTotal;
    @Basic(optional = false)
    @Column(name = "nb_place_libre")
    private int nbPlaceLibre;

    public Parking() {
    }

    public Parking(Integer idParking) {
        this.idParking = idParking;
    }

    public Parking(Integer idParking, String nomParking, int nbPlaceTotal, int nbPlaceLibre) {
        this.idParking = idParking;
        this.nomParking = nomParking;
        this.nbPlaceTotal = nbPlaceTotal;
        this.nbPlaceLibre = nbPlaceLibre;
    }

    public Integer getIdParking() {
        return idParking;
    }

    public void setIdParking(Integer idParking) {
        this.idParking = idParking;
    }

    public String getNomParking() {
        return nomParking;
    }

    public void setNomParking(String nomParking) {
        this.nomParking = nomParking;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParking != null ? idParking.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parking)) {
            return false;
        }
        Parking other = (Parking) object;
        if ((this.idParking == null && other.idParking != null) || (this.idParking != null && !this.idParking.equals(other.idParking))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codename1.uikit.entities.Parking[ idParking=" + idParking + " ]";
    }
    
}
