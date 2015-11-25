/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amandineleforestier.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author janitha
 */
@Entity
@Table(name = "emailaddresses")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Emailaddresses.findAll", query = "SELECT e FROM Emailaddresses e"),
    @NamedQuery(name = "Emailaddresses.findById", query = "SELECT e FROM Emailaddresses e WHERE e.id = :id"),
    @NamedQuery(name = "Emailaddresses.findByEmail", query = "SELECT e FROM Emailaddresses e WHERE e.email = :email"),
    @NamedQuery(name = "Emailaddresses.findBySourceurl", query = "SELECT e FROM Emailaddresses e WHERE e.sourceurl = :sourceurl"),
    @NamedQuery(name = "Emailaddresses.findByTimeposted", query = "SELECT e FROM Emailaddresses e WHERE e.timeposted = :timeposted")})
public class Emailaddresses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "email")
    private String email;
    @Size(max = 255)
    @Column(name = "sourceurl")
    private String sourceurl;
    @Column(name = "timeposted")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeposted;

    public Emailaddresses() {
    }

    public Emailaddresses(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSourceurl() {
        return sourceurl;
    }

    public void setSourceurl(String sourceurl) {
        this.sourceurl = sourceurl;
    }

    public Date getTimeposted() {
        return timeposted;
    }

    public void setTimeposted(Date timeposted) {
        this.timeposted = timeposted;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emailaddresses)) {
            return false;
        }
        Emailaddresses other = (Emailaddresses) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.amandineleforestier.model.Emailaddresses[ id=" + id + " ]";
    }
    
}
