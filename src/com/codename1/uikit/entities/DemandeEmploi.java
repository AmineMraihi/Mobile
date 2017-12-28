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
@Table(name = "demande_emploi")
@NamedQueries({
    @NamedQuery(name = "DemandeEmploi.findAll", query = "SELECT d FROM DemandeEmploi d")
    , @NamedQuery(name = "DemandeEmploi.findByIdDemande", query = "SELECT d FROM DemandeEmploi d WHERE d.idDemande = :idDemande")
    , @NamedQuery(name = "DemandeEmploi.findByIdUserFk", query = "SELECT d FROM DemandeEmploi d WHERE d.idUserFk = :idUserFk")
    , @NamedQuery(name = "DemandeEmploi.findByNomEmp", query = "SELECT d FROM DemandeEmploi d WHERE d.nomEmp = :nomEmp")
    , @NamedQuery(name = "DemandeEmploi.findByPrenomEmp", query = "SELECT d FROM DemandeEmploi d WHERE d.prenomEmp = :prenomEmp")
    , @NamedQuery(name = "DemandeEmploi.findByDateNaissance", query = "SELECT d FROM DemandeEmploi d WHERE d.dateNaissance = :dateNaissance")
    , @NamedQuery(name = "DemandeEmploi.findByAdresse", query = "SELECT d FROM DemandeEmploi d WHERE d.adresse = :adresse")
    , @NamedQuery(name = "DemandeEmploi.findBySexe", query = "SELECT d FROM DemandeEmploi d WHERE d.sexe = :sexe")
    , @NamedQuery(name = "DemandeEmploi.findByEmail", query = "SELECT d FROM DemandeEmploi d WHERE d.email = :email")
    , @NamedQuery(name = "DemandeEmploi.findByNumTel", query = "SELECT d FROM DemandeEmploi d WHERE d.numTel = :numTel")
    , @NamedQuery(name = "DemandeEmploi.findByQualification", query = "SELECT d FROM DemandeEmploi d WHERE d.qualification = :qualification")
    , @NamedQuery(name = "DemandeEmploi.findByExperience", query = "SELECT d FROM DemandeEmploi d WHERE d.experience = :experience")})
public class DemandeEmploi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_demande")
    private Integer idDemande;
    @Basic(optional = false)
    @Column(name = "id_user_fk")
    private int idUserFk;
    @Basic(optional = false)
    @Column(name = "nom_emp")
    private String nomEmp;
    @Basic(optional = false)
    @Column(name = "prenom_emp")
    private String prenomEmp;
    @Basic(optional = false)
    @Column(name = "date_naissance")
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    @Column(name = "adresse")
    private String adresse;
    @Column(name = "sexe")
    private String sexe;
    @Column(name = "email")
    private String email;
    @Column(name = "num_tel")
    private String numTel;
    @Column(name = "qualification")
    private String qualification;
    @Column(name = "experience")
    private Integer experience;
    @Basic(optional = false)
    @Column(name = "id_offre_fk")
    private int idOffreFk;

    public DemandeEmploi() {
    }

    public DemandeEmploi(Integer idDemande) {
        this.idDemande = idDemande;
    }

    public DemandeEmploi(Integer idDemande, int idUserFk, String nomEmp, String prenomEmp, Date dateNaissance) {
        this.idDemande = idDemande;
        this.idUserFk = idUserFk;
        this.nomEmp = nomEmp;
        this.prenomEmp = prenomEmp;
        this.dateNaissance = dateNaissance;
    }

    public Integer getIdDemande() {
        return idDemande;
    }

    public void setIdDemande(Integer idDemande) {
        this.idDemande = idDemande;
    }

    public int getIdUserFk() {
        return idUserFk;
    }

    public void setIdUserFk(int idUserFk) {
        this.idUserFk = idUserFk;
    }

    public String getNomEmp() {
        return nomEmp;
    }

    public void setNomEmp(String nomEmp) {
        this.nomEmp = nomEmp;
    }

    public String getPrenomEmp() {
        return prenomEmp;
    }

    public void setPrenomEmp(String prenomEmp) {
        this.prenomEmp = prenomEmp;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public int getIdOffreFk() {
        return idOffreFk;
    }

    public void setIdOffreFk(int idOffreFk) {
        this.idOffreFk = idOffreFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDemande != null ? idDemande.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DemandeEmploi)) {
            return false;
        }
        DemandeEmploi other = (DemandeEmploi) object;
        if ((this.idDemande == null && other.idDemande != null) || (this.idDemande != null && !this.idDemande.equals(other.idDemande))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DemandeEmploi{" + "idDemande=" + idDemande + ", idUserFk=" + idUserFk + ", nomEmp=" + nomEmp + ", prenomEmp=" + prenomEmp + ", dateNaissance=" + dateNaissance + ", adresse=" + adresse + ", sexe=" + sexe + ", email=" + email + ", numTel=" + numTel + ", qualification=" + qualification + ", experience=" + experience + ", idOffreFk=" + idOffreFk + '}';
    }

    
    
}
