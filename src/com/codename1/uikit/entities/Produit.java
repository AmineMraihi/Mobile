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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "produit")
@NamedQueries({
    @NamedQuery(name = "Produit.findAll", query = "SELECT p FROM Produit p")
    , @NamedQuery(name = "Produit.findByIdProduit", query = "SELECT p FROM Produit p WHERE p.idProduit = :idProduit")
    , @NamedQuery(name = "Produit.findByNom", query = "SELECT p FROM Produit p WHERE p.nom = :nom")
    , @NamedQuery(name = "Produit.findByType", query = "SELECT p FROM Produit p WHERE p.type = :type")
    , @NamedQuery(name = "Produit.findByPrix", query = "SELECT p FROM Produit p WHERE p.prix = :prix")
    , @NamedQuery(name = "Produit.findByQuantite", query = "SELECT p FROM Produit p WHERE p.quantite = :quantite")
    , @NamedQuery(name = "Produit.findByPrixAchatGros", query = "SELECT p FROM Produit p WHERE p.prixAchatGros = :prixAchatGros")
    , @NamedQuery(name = "Produit.findByNbVente", query = "SELECT p FROM Produit p WHERE p.nbVente = :nbVente")
    , @NamedQuery(name = "Produit.findByPath", query = "SELECT p FROM Produit p WHERE p.path = :path")
    , @NamedQuery(name = "Produit.findByUpdatedAt", query = "SELECT p FROM Produit p WHERE p.updatedAt = :updatedAt")})
public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_produit")
    private Integer idProduit;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @Column(name = "prix")
    private Double prix;
    @Basic(optional = false)
    @Column(name = "quantite")
    private int quantite;
    @Basic(optional = false)
    @Column(name = "prix_achat_gros")
    private double prixAchatGros;
    @Column(name = "nb_vente")
    private Integer nbVente;
    @Basic(optional = false)
    @Column(name = "path")
    private String path;
    @Basic(optional = false)
    @Lob
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    
    
     @Lob
    @Column(name = "id_boutique")
    private int id_boutique;
    @Basic(optional = false)
    
   
    @OneToMany(mappedBy = "idProduit")
    private Collection<Promotion> promotionCollection;

    public Produit() {
    }

    public Produit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    public Produit(Integer idProduit, String nom, String type, Double prix, int quantite, double prixAchatGros, String path, String description, Date updatedAt) {
        this.idProduit = idProduit;
        this.nom = nom;
        this.type = type;
        this.prix = prix;
        this.quantite = quantite;
        this.prixAchatGros = prixAchatGros;
        this.path = path;
        this.description = description;
        this.updatedAt = updatedAt;
    }

    public Produit(Integer idProduit, String nom, String type, Double prix, String description) {
        this.idProduit = idProduit;
        this.nom = nom;
        this.type = type;
        this.prix = prix;
        this.description = description;
    }

    public Produit(String nom, Double prix) {
        this.nom = nom;
        this.prix = prix;
    }
    

    public Integer getIdProduit() {
        return idProduit;
    }

    public Produit(String nom, String type, Double prix) {
        this.nom = nom;
        this.type = type;
        this.prix = prix;
    }

    public void setIdProduit(Integer idProduit) {
        this.idProduit = idProduit;
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

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrixAchatGros() {
        return prixAchatGros;
    }

    public void setPrixAchatGros(double prixAchatGros) {
        this.prixAchatGros = prixAchatGros;
    }

    public Integer getNbVente() {
        return nbVente;
    }

    public void setNbVente(Integer nbVente) {
        this.nbVente = nbVente;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getIdBoutique() {
        return id_boutique;
    }

    public void setIdBoutique(int idBoutique) {
        this.id_boutique = idBoutique;
    }

    public Collection<Promotion> getPromotionCollection() {
        return promotionCollection;
    }

    public void setPromotionCollection(Collection<Promotion> promotionCollection) {
        this.promotionCollection = promotionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProduit != null ? idProduit.hashCode() : 0);
        return hash;
    }

    public Produit(String nom, String type, Double prix, String path) {
        this.nom = nom;
        this.type = type;
        this.prix = prix;
        this.path = path;
    }

    public Produit(String nom, String type) {
        this.nom = nom;
        this.type = type;
    }

    public Produit(Integer idProduit, String nom, String type, Double prix, String path, String description) {
        this.idProduit = idProduit;
        this.nom = nom;
        this.type = type;
        this.prix = prix;
        this.path = path;
        this.description = description;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produit)) {
            return false;
        }
        Produit other = (Produit) object;
        if ((this.idProduit == null && other.idProduit != null) || (this.idProduit != null && !this.idProduit.equals(other.idProduit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codename1.uikit.entities.Produit[ idProduit=" + idProduit + " ]";
    }
    
}
