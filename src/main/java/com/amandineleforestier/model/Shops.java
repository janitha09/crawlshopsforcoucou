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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author janitha
 */
@Entity
@Table(name = "shops")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Shops.findAll", query = "SELECT s FROM Shops s"),
    @NamedQuery(name = "Shops.findBySource", query = "SELECT s FROM Shops s WHERE s.source = :source"),
    @NamedQuery(name = "Shops.findBySearchtext", query = "SELECT s FROM Shops s WHERE s.searchtext = :searchtext"),
    @NamedQuery(name = "Shops.findByShopname", query = "SELECT s FROM Shops s WHERE s.shopname = :shopname"),
    @NamedQuery(name = "Shops.findByShopurl", query = "SELECT s FROM Shops s WHERE s.shopurl = :shopurl"),
    @NamedQuery(name = "Shops.findByAddress", query = "SELECT s FROM Shops s WHERE s.address = :address"),
    @NamedQuery(name = "Shops.findByPhone", query = "SELECT s FROM Shops s WHERE s.phone = :phone"),
    @NamedQuery(name = "Shops.findByGooglemapsurl", query = "SELECT s FROM Shops s WHERE s.googlemapsurl = :googlemapsurl"),
    @NamedQuery(name = "Shops.findByPlacetypes", query = "SELECT s FROM Shops s WHERE s.placetypes = :placetypes"),
    @NamedQuery(name = "Shops.findById", query = "SELECT s FROM Shops s WHERE s.id = :id")})
public class Shops implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 255)
    @Column(name = "source")
    private String source;
    @Size(max = 45)
    @Column(name = "searchtext")
    private String searchtext;
    @Size(max = 45)
    @Column(name = "shopname")
    private String shopname;
    @Size(max = 255)
    @Column(name = "shopurl")
    private String shopurl;
    @Size(max = 255)
    @Column(name = "address")
    private String address;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 20)
    @Column(name = "phone")
    private String phone;
    @Size(max = 255)
    @Column(name = "googlemapsurl")
    private String googlemapsurl;
    @Size(max = 255)
    @Column(name = "placetypes")
    private String placetypes;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    public Shops() {
    }

    public Shops(Integer id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSearchtext() {
        return searchtext;
    }

    public void setSearchtext(String searchtext) {
        this.searchtext = searchtext;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getShopurl() {
        return shopurl;
    }

    public void setShopurl(String shopurl) {
        this.shopurl = shopurl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGooglemapsurl() {
        return googlemapsurl;
    }

    public void setGooglemapsurl(String googlemapsurl) {
        this.googlemapsurl = googlemapsurl;
    }

    public String getPlacetypes() {
        return placetypes;
    }

    public void setPlacetypes(String placetypes) {
        this.placetypes = placetypes;
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
        if (!(object instanceof Shops)) {
            return false;
        }
        Shops other = (Shops) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.amandineleforestier.model.Shops[ id=" + id + " ]";
    }
    
}
