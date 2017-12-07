/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Amine
 */
@Entity
@Table(name = "panier_produit")
@NamedQueries({
    @NamedQuery(name = "PanierProduit.findAll", query = "SELECT p FROM PanierProduit p")
    , @NamedQuery(name = "PanierProduit.findByIdProduit", query = "SELECT p FROM PanierProduit p WHERE p.panierProduitPK.idProduit = :idProduit")
    , @NamedQuery(name = "PanierProduit.findByIdPanier", query = "SELECT p FROM PanierProduit p WHERE p.panierProduitPK.idPanier = :idPanier")})
public class PanierProduit implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PanierProduitPK panierProduitPK;

    public PanierProduit() {
    }

    public PanierProduit(PanierProduitPK panierProduitPK) {
        this.panierProduitPK = panierProduitPK;
    }

    public PanierProduit(int idProduit, int idPanier) {
        this.panierProduitPK = new PanierProduitPK(idProduit, idPanier);
    }

    public PanierProduitPK getPanierProduitPK() {
        return panierProduitPK;
    }

    public void setPanierProduitPK(PanierProduitPK panierProduitPK) {
        this.panierProduitPK = panierProduitPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (panierProduitPK != null ? panierProduitPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PanierProduit)) {
            return false;
        }
        PanierProduit other = (PanierProduit) object;
        if ((this.panierProduitPK == null && other.panierProduitPK != null) || (this.panierProduitPK != null && !this.panierProduitPK.equals(other.panierProduitPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codename1.uikit.entities.PanierProduit[ panierProduitPK=" + panierProduitPK + " ]";
    }
    
}
