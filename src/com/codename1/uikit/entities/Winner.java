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
@Table(name = "winner")
@NamedQueries({
    @NamedQuery(name = "Winner.findAll", query = "SELECT w FROM Winner w")
    , @NamedQuery(name = "Winner.findByIdTabWinner", query = "SELECT w FROM Winner w WHERE w.idTabWinner = :idTabWinner")
    , @NamedQuery(name = "Winner.findByWinnerDate", query = "SELECT w FROM Winner w WHERE w.winnerDate = :winnerDate")
    , @NamedQuery(name = "Winner.findByIdWinner", query = "SELECT w FROM Winner w WHERE w.idWinner = :idWinner")})
public class Winner implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tab_winner")
    private Integer idTabWinner;
    @Basic(optional = false)
    @Column(name = "winner_date")
    @Temporal(TemporalType.DATE)
    private Date winnerDate;
    @Basic(optional = false)
    @Column(name = "id_winner")
    private int idWinner;

    public Winner() {
    }

    public Winner(Integer idTabWinner) {
        this.idTabWinner = idTabWinner;
    }

    public Winner(Integer idTabWinner, Date winnerDate, int idWinner) {
        this.idTabWinner = idTabWinner;
        this.winnerDate = winnerDate;
        this.idWinner = idWinner;
    }

    public Integer getIdTabWinner() {
        return idTabWinner;
    }

    public void setIdTabWinner(Integer idTabWinner) {
        this.idTabWinner = idTabWinner;
    }

    public Date getWinnerDate() {
        return winnerDate;
    }

    public void setWinnerDate(Date winnerDate) {
        this.winnerDate = winnerDate;
    }

    public int getIdWinner() {
        return idWinner;
    }

    public void setIdWinner(int idWinner) {
        this.idWinner = idWinner;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTabWinner != null ? idTabWinner.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Winner)) {
            return false;
        }
        Winner other = (Winner) object;
        if ((this.idTabWinner == null && other.idTabWinner != null) || (this.idTabWinner != null && !this.idTabWinner.equals(other.idTabWinner))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codename1.uikit.entities.Winner[ idTabWinner=" + idTabWinner + " ]";
    }
    
}
