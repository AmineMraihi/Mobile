/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Amine
 */
@Entity
@Table(name = "boutique")
@NamedQueries({
    @NamedQuery(name = "Boutique.findAll", query = "SELECT b FROM Boutique b")
    , @NamedQuery(name = "Boutique.findByIdBoutique", query = "SELECT b FROM Boutique b WHERE b.idBoutique = :idBoutique")
    , @NamedQuery(name = "Boutique.findByNom", query = "SELECT b FROM Boutique b WHERE b.nom = :nom")
    , @NamedQuery(name = "Boutique.findByType", query = "SELECT b FROM Boutique b WHERE b.type = :type")
    , @NamedQuery(name = "Boutique.findByPosition", query = "SELECT b FROM Boutique b WHERE b.position = :position")
    , @NamedQuery(name = "Boutique.findByDateCreation", query = "SELECT b FROM Boutique b WHERE b.dateCreation = :dateCreation")
    , @NamedQuery(name = "Boutique.findByDateExpiration", query = "SELECT b FROM Boutique b WHERE b.dateExpiration = :dateExpiration")})
public class Boutique implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_boutique")
    private Integer idBoutique;
    @Column(name = "nom")
    private String nom;
    @Column(name = "type")
    private String type;
    @Column(name = "position")
    private String position;
    @Column(name = "date_creation")
    @Temporal(TemporalType.DATE)
    private Date dateCreation;
    @Column(name = "date_expiration")
    @Temporal(TemporalType.DATE)
    private Date dateExpiration;
    @OneToMany(mappedBy = "idBoutique")
    private Collection<Produit> produitCollection;
    @OneToMany(mappedBy = "idBoutique")
    private Collection<DemandePub> demandePubCollection;
    @OneToMany(mappedBy = "idBoutiqueFk")
    private Collection<OffreEmploi> offreEmploiCollection;
    @OneToMany(mappedBy = "idBoutique")
    private Collection<Publicite> publiciteCollection;
    @OneToMany(mappedBy = "idBoutique")
    private Collection<User> userCollection;

    public Boutique() {
    }

    public Boutique(Integer idBoutique) {
        this.idBoutique = idBoutique;
    }

    public Integer getIdBoutique() {
        return idBoutique;
    }

    public void setIdBoutique(Integer idBoutique) {
        this.idBoutique = idBoutique;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public Collection<Produit> getProduitCollection() {
        return produitCollection;
    }

    public void setProduitCollection(Collection<Produit> produitCollection) {
        this.produitCollection = produitCollection;
    }

    public Collection<DemandePub> getDemandePubCollection() {
        return demandePubCollection;
    }

    public void setDemandePubCollection(Collection<DemandePub> demandePubCollection) {
        this.demandePubCollection = demandePubCollection;
    }

    public Collection<OffreEmploi> getOffreEmploiCollection() {
        return offreEmploiCollection;
    }

    public void setOffreEmploiCollection(Collection<OffreEmploi> offreEmploiCollection) {
        this.offreEmploiCollection = offreEmploiCollection;
    }

    public Collection<Publicite> getPubliciteCollection() {
        return publiciteCollection;
    }

    public void setPubliciteCollection(Collection<Publicite> publiciteCollection) {
        this.publiciteCollection = publiciteCollection;
    }

    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBoutique != null ? idBoutique.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Boutique)) {
            return false;
        }
        Boutique other = (Boutique) object;
        if ((this.idBoutique == null && other.idBoutique != null) || (this.idBoutique != null && !this.idBoutique.equals(other.idBoutique))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codename1.uikit.entities.Boutique[ idBoutique=" + idBoutique + " ]";
    }
    
}
