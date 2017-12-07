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
@Table(name = "scheduled_command")
@NamedQueries({
    @NamedQuery(name = "ScheduledCommand.findAll", query = "SELECT s FROM ScheduledCommand s")
    , @NamedQuery(name = "ScheduledCommand.findByIdScheduledCommand", query = "SELECT s FROM ScheduledCommand s WHERE s.idScheduledCommand = :idScheduledCommand")
    , @NamedQuery(name = "ScheduledCommand.findByName", query = "SELECT s FROM ScheduledCommand s WHERE s.name = :name")
    , @NamedQuery(name = "ScheduledCommand.findByCommand", query = "SELECT s FROM ScheduledCommand s WHERE s.command = :command")
    , @NamedQuery(name = "ScheduledCommand.findByArguments", query = "SELECT s FROM ScheduledCommand s WHERE s.arguments = :arguments")
    , @NamedQuery(name = "ScheduledCommand.findByCronExpression", query = "SELECT s FROM ScheduledCommand s WHERE s.cronExpression = :cronExpression")
    , @NamedQuery(name = "ScheduledCommand.findByDhLastExecution", query = "SELECT s FROM ScheduledCommand s WHERE s.dhLastExecution = :dhLastExecution")
    , @NamedQuery(name = "ScheduledCommand.findByLastReturnCode", query = "SELECT s FROM ScheduledCommand s WHERE s.lastReturnCode = :lastReturnCode")
    , @NamedQuery(name = "ScheduledCommand.findByLogFile", query = "SELECT s FROM ScheduledCommand s WHERE s.logFile = :logFile")
    , @NamedQuery(name = "ScheduledCommand.findByPriority", query = "SELECT s FROM ScheduledCommand s WHERE s.priority = :priority")
    , @NamedQuery(name = "ScheduledCommand.findByBExecuteImmediately", query = "SELECT s FROM ScheduledCommand s WHERE s.bExecuteImmediately = :bExecuteImmediately")
    , @NamedQuery(name = "ScheduledCommand.findByBDisabled", query = "SELECT s FROM ScheduledCommand s WHERE s.bDisabled = :bDisabled")
    , @NamedQuery(name = "ScheduledCommand.findByBLocked", query = "SELECT s FROM ScheduledCommand s WHERE s.bLocked = :bLocked")})
public class ScheduledCommand implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_SCHEDULED_COMMAND")
    private Integer idScheduledCommand;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @Column(name = "COMMAND")
    private String command;
    @Column(name = "ARGUMENTS")
    private String arguments;
    @Column(name = "CRON_EXPRESSION")
    private String cronExpression;
    @Basic(optional = false)
    @Column(name = "DH_LAST_EXECUTION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dhLastExecution;
    @Column(name = "LAST_RETURN_CODE")
    private Integer lastReturnCode;
    @Column(name = "LOG_FILE")
    private String logFile;
    @Basic(optional = false)
    @Column(name = "PRIORITY")
    private int priority;
    @Basic(optional = false)
    @Column(name = "B_EXECUTE_IMMEDIATELY")
    private boolean bExecuteImmediately;
    @Basic(optional = false)
    @Column(name = "B_DISABLED")
    private boolean bDisabled;
    @Basic(optional = false)
    @Column(name = "B_LOCKED")
    private boolean bLocked;

    public ScheduledCommand() {
    }

    public ScheduledCommand(Integer idScheduledCommand) {
        this.idScheduledCommand = idScheduledCommand;
    }

    public ScheduledCommand(Integer idScheduledCommand, String name, String command, Date dhLastExecution, int priority, boolean bExecuteImmediately, boolean bDisabled, boolean bLocked) {
        this.idScheduledCommand = idScheduledCommand;
        this.name = name;
        this.command = command;
        this.dhLastExecution = dhLastExecution;
        this.priority = priority;
        this.bExecuteImmediately = bExecuteImmediately;
        this.bDisabled = bDisabled;
        this.bLocked = bLocked;
    }

    public Integer getIdScheduledCommand() {
        return idScheduledCommand;
    }

    public void setIdScheduledCommand(Integer idScheduledCommand) {
        this.idScheduledCommand = idScheduledCommand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getArguments() {
        return arguments;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public Date getDhLastExecution() {
        return dhLastExecution;
    }

    public void setDhLastExecution(Date dhLastExecution) {
        this.dhLastExecution = dhLastExecution;
    }

    public Integer getLastReturnCode() {
        return lastReturnCode;
    }

    public void setLastReturnCode(Integer lastReturnCode) {
        this.lastReturnCode = lastReturnCode;
    }

    public String getLogFile() {
        return logFile;
    }

    public void setLogFile(String logFile) {
        this.logFile = logFile;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean getBExecuteImmediately() {
        return bExecuteImmediately;
    }

    public void setBExecuteImmediately(boolean bExecuteImmediately) {
        this.bExecuteImmediately = bExecuteImmediately;
    }

    public boolean getBDisabled() {
        return bDisabled;
    }

    public void setBDisabled(boolean bDisabled) {
        this.bDisabled = bDisabled;
    }

    public boolean getBLocked() {
        return bLocked;
    }

    public void setBLocked(boolean bLocked) {
        this.bLocked = bLocked;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idScheduledCommand != null ? idScheduledCommand.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScheduledCommand)) {
            return false;
        }
        ScheduledCommand other = (ScheduledCommand) object;
        if ((this.idScheduledCommand == null && other.idScheduledCommand != null) || (this.idScheduledCommand != null && !this.idScheduledCommand.equals(other.idScheduledCommand))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codename1.uikit.entities.ScheduledCommand[ idScheduledCommand=" + idScheduledCommand + " ]";
    }
    
}
