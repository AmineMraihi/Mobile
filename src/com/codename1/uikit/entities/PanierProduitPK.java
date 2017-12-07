/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Amine
 */
@Embeddable
public class PanierProduitPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_produit")
    private int idProduit;
    @Basic(optional = false)
    @Column(name = "id_panier")
    private int idPanier;

    public PanierProduitPK() {
    }

    public PanierProduitPK(int idProduit, int idPanier) {
        this.idProduit = idProduit;
        this.idPanier = idPanier;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getIdPanier() {
        return idPanier;
    }

    public void setIdPanier(int idPanier) {
        this.idPanier = idPanier;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idProduit;
        hash += (int) idPanier;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PanierProduitPK)) {
            return false;
        }
        PanierProduitPK other = (PanierProduitPK) object;
        if (this.idProduit != other.idProduit) {
            return false;
        }
        if (this.idPanier != other.idPanier) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codename1.uikit.entities.PanierProduitPK[ idProduit=" + idProduit + ", idPanier=" + idPanier + " ]";
    }
    
}
