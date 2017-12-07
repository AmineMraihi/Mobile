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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Amine
 */
@Entity
@Table(name = "review11")
@NamedQueries({
    @NamedQuery(name = "Review11.findAll", query = "SELECT r FROM Review11 r")
    , @NamedQuery(name = "Review11.findByIdReview", query = "SELECT r FROM Review11 r WHERE r.idReview = :idReview")
    , @NamedQuery(name = "Review11.findByEmail", query = "SELECT r FROM Review11 r WHERE r.email = :email")
    , @NamedQuery(name = "Review11.findByContenu", query = "SELECT r FROM Review11 r WHERE r.contenu = :contenu")})
public class Review11 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_review")
    private Integer idReview;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "contenu")
    private String contenu;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne
    private User idUser;

    public Review11() {
    }

    public Review11(Integer idReview) {
        this.idReview = idReview;
    }

    public Review11(Integer idReview, String email, String contenu) {
        this.idReview = idReview;
        this.email = email;
        this.contenu = contenu;
    }

    public Integer getIdReview() {
        return idReview;
    }

    public void setIdReview(Integer idReview) {
        this.idReview = idReview;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReview != null ? idReview.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Review11)) {
            return false;
        }
        Review11 other = (Review11) object;
        if ((this.idReview == null && other.idReview != null) || (this.idReview != null && !this.idReview.equals(other.idReview))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codename1.uikit.entities.Review11[ idReview=" + idReview + " ]";
    }
    
}
