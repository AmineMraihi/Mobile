/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.entities;

import com.codename1.ui.util.Resources;
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
@Table(name = "offre_emploi")
@NamedQueries({
    @NamedQuery(name = "OffreEmploi.findAll", query = "SELECT o FROM OffreEmploi o")
    , @NamedQuery(name = "OffreEmploi.findByIdOffre", query = "SELECT o FROM OffreEmploi o WHERE o.idOffre = :idOffre")
    , @NamedQuery(name = "OffreEmploi.findByIdUserFk", query = "SELECT o FROM OffreEmploi o WHERE o.idUserFk = :idUserFk")
    , @NamedQuery(name = "OffreEmploi.findByPoste", query = "SELECT o FROM OffreEmploi o WHERE o.poste = :poste")
    , @NamedQuery(name = "OffreEmploi.findBySpecialite", query = "SELECT o FROM OffreEmploi o WHERE o.specialite = :specialite")
    , @NamedQuery(name = "OffreEmploi.findBySalaire", query = "SELECT o FROM OffreEmploi o WHERE o.salaire = :salaire")
    , @NamedQuery(name = "OffreEmploi.findByNbrDemand\u00e9", query = "SELECT o FROM OffreEmploi o WHERE o.nbrDemand\u00e9 = :nbrDemand\u00e9")
    , @NamedQuery(name = "OffreEmploi.findByDateExpiration", query = "SELECT o FROM OffreEmploi o WHERE o.dateExpiration = :dateExpiration")})
public class OffreEmploi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_offre")
    private Integer idOffre;
    @Basic(optional = false)
    @Column(name = "id_user_fk")
    private int idUserFk;
    @Column(name = "poste")
    private String poste;
    @Column(name = "specialite")
    private String specialite;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "salaire")
    private Double salaire;
    @Column(name = "nbr_demande")
    private Integer nbrDemande;
    @Column(name = "date_expiration")
    @Temporal(TemporalType.DATE)
    private Date dateExpiration;
    @JoinColumn(name = "id_boutique_fk", referencedColumnName = "id_boutique")
    @ManyToOne
    private Boutique idBoutiqueFk;
    @OneToMany(mappedBy = "idOffreFk")
    private Collection<DemandeEmploi> demandeEmploiCollection;

    public OffreEmploi() {
    }

    public OffreEmploi(Integer idOffre) {
        this.idOffre = idOffre;
    }

    public OffreEmploi(Integer idOffre, int idUserFk) {
        this.idOffre = idOffre;
        this.idUserFk = idUserFk;
    }

    public OffreEmploi(Integer idOffre, int idUserFk, String poste, String specialite, Integer nbrDemande) {
        this.idOffre = idOffre;
        this.idUserFk = idUserFk;
        this.poste = poste;
        this.specialite = specialite;
        this.nbrDemande = nbrDemande;
    }

    
    
    public OffreEmploi(String poste, String specialite, Double salaire, Integer nbrDemande) {
        this.poste = poste;
        this.specialite = specialite;
        this.salaire = salaire;
        this.nbrDemande = nbrDemande;
     }

        public OffreEmploi(String poste, String specialite,Integer nbrDemande) {
        this.poste = poste;
        this.specialite = specialite;
       
        this.nbrDemande = nbrDemande;
     }

        public OffreEmploi(String poste, String specialite) {
        this.poste = poste;
        this.specialite = specialite;
       
      
     }
        
    public Integer getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(Integer idOffre) {
        this.idOffre = idOffre;
    }

    public int getIdUserFk() {
        return idUserFk;
    }

    public void setIdUserFk(int idUserFk) {
        this.idUserFk = idUserFk;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public Double getSalaire() {
        return salaire;
    }

    public void setSalaire(Double salaire) {
        this.salaire = salaire;
    }

    public Integer getNbrDemande() {
        return nbrDemande;
    }

    public void setNbrDemande(Integer nbrDemande) {
        this.nbrDemande = nbrDemande;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public Boutique getIdBoutiqueFk() {
        return idBoutiqueFk;
    }

    public void setIdBoutiqueFk(Boutique idBoutiqueFk) {
        this.idBoutiqueFk = idBoutiqueFk;
    }

    public Collection<DemandeEmploi> getDemandeEmploiCollection() {
        return demandeEmploiCollection;
    }

    public void setDemandeEmploiCollection(Collection<DemandeEmploi> demandeEmploiCollection) {
        this.demandeEmploiCollection = demandeEmploiCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOffre != null ? idOffre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OffreEmploi)) {
            return false;
        }
        OffreEmploi other = (OffreEmploi) object;
        if ((this.idOffre == null && other.idOffre != null) || (this.idOffre != null && !this.idOffre.equals(other.idOffre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OffreEmploi{" + "idOffre=" + idOffre + ", idUserFk=" + idUserFk + ", poste=" + poste + ", specialite=" + specialite + ", salaire=" + salaire + ", nbrDemande=" + nbrDemande + ", dateExpiration=" + dateExpiration + ", idBoutiqueFk=" + idBoutiqueFk + ", demandeEmploiCollection=" + demandeEmploiCollection + '}';
    }

  
    
}
