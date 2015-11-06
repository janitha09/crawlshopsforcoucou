/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amandineleforestier.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 222252
 */
@Entity
@Table(name = "brandinfo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Brandinfo.findAll", query = "SELECT b FROM Brandinfo b"),
    @NamedQuery(name = "Brandinfo.findBySource", query = "SELECT b FROM Brandinfo b WHERE b.source = :source"),
    @NamedQuery(name = "Brandinfo.findByBrandname", query = "SELECT b FROM Brandinfo b WHERE b.brandname = :brandname"),
    @NamedQuery(name = "Brandinfo.findByCountry", query = "SELECT b FROM Brandinfo b WHERE b.country = :country"),
    @NamedQuery(name = "Brandinfo.findByBrandemail", query = "SELECT b FROM Brandinfo b WHERE b.brandemail = :brandemail"),
    @NamedQuery(name = "Brandinfo.findByBrandurl", query = "SELECT b FROM Brandinfo b WHERE b.brandurl = :brandurl"),
    @NamedQuery(name = "Brandinfo.findById", query = "SELECT b FROM Brandinfo b WHERE b.id = :id")})
public class Brandinfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "source")
    private String source;
    @Column(name = "brandname")
    private String brandname;
    @Column(name = "country")
    private String country;
    @Column(name = "brandemail")
    private String brandemail;
    @Column(name = "brandurl")
    private String brandurl;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    public Brandinfo() {
    }

    public Brandinfo(Integer id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBrandemail() {
        return brandemail;
    }

    public void setBrandemail(String brandemail) {
        this.brandemail = brandemail;
    }

    public String getBrandurl() {
        return brandurl;
    }

    public void setBrandurl(String brandurl) {
        this.brandurl = brandurl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        if (!(object instanceof Brandinfo)) {
            return false;
        }
        Brandinfo other = (Brandinfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.amandineleforestier.model.Brandinfo[ id=" + id + " ]";
    }
    
}
