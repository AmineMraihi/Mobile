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
import javax.persistence.Lob;
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
@Table(name = "topic1")
@NamedQueries({
    @NamedQuery(name = "Topic1.findAll", query = "SELECT t FROM Topic1 t")
    , @NamedQuery(name = "Topic1.findByIdTopic1", query = "SELECT t FROM Topic1 t WHERE t.idTopic1 = :idTopic1")
    , @NamedQuery(name = "Topic1.findByCategorie", query = "SELECT t FROM Topic1 t WHERE t.categorie = :categorie")
    , @NamedQuery(name = "Topic1.findByDateTopic", query = "SELECT t FROM Topic1 t WHERE t.dateTopic = :dateTopic")
    , @NamedQuery(name = "Topic1.findByLoginUser", query = "SELECT t FROM Topic1 t WHERE t.loginUser = :loginUser")})
public class Topic1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_topic1")
    private Integer idTopic1;
    @Basic(optional = false)
    @Column(name = "categorie")
    private String categorie;
    @Basic(optional = false)
    @Lob
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "date_topic")
    @Temporal(TemporalType.DATE)
    private Date dateTopic;
    @Basic(optional = false)
    @Column(name = "login_user")
    private String loginUser;

    public Topic1() {
    }

    public Topic1(Integer idTopic1) {
        this.idTopic1 = idTopic1;
    }

    public Topic1(Integer idTopic1, String categorie, String description, Date dateTopic, String loginUser) {
        this.idTopic1 = idTopic1;
        this.categorie = categorie;
        this.description = description;
        this.dateTopic = dateTopic;
        this.loginUser = loginUser;
    }

    public Integer getIdTopic1() {
        return idTopic1;
    }

    public void setIdTopic1(Integer idTopic1) {
        this.idTopic1 = idTopic1;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateTopic() {
        return dateTopic;
    }

    public void setDateTopic(Date dateTopic) {
        this.dateTopic = dateTopic;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTopic1 != null ? idTopic1.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Topic1)) {
            return false;
        }
        Topic1 other = (Topic1) object;
        if ((this.idTopic1 == null && other.idTopic1 != null) || (this.idTopic1 != null && !this.idTopic1.equals(other.idTopic1))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codename1.uikit.entities.Topic1[ idTopic1=" + idTopic1 + " ]";
    }
    
}
