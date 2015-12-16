/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amandineleforestier.crawlshopsforcoucou;

import com.amandineleforestier.model.Shops;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import se.walkercrou.places.Place;

/**
 *
 * @author janitha
 */
public class ManageGooglePlaces {

    EntityManagerFactory emf = null;
    se.walkercrou.places.GooglePlaces client = null;

    public ManageGooglePlaces() {
        emf = Persistence.createEntityManagerFactory("ShopsPU");
        client = new se.walkercrou.places.GooglePlaces("AIzaSyAbA_omv6dng-yrsMc7RwNVT2Fh9gqoook");
        client.setDebugModeEnabled(true);
    }

    public void getAGooglePlaceFromTheString(String searchText, String sourceUrl, String alternateUrl) {
        List<Place> places = client.getPlacesByQuery(searchText, se.walkercrou.places.GooglePlaces.MAXIMUM_RESULTS);
        if (places.isEmpty()) {
            commitPlaceInfoToShopTable(sourceUrl, searchText, null, alternateUrl);
        }
        Place detailedPlace = null;
        for (Place place : places) {
            detailedPlace = place.getDetails();
            Logger.getLogger(ManageGooglePlaces.class.getName()).log(Level.INFO, "place {0} {1}", new Object[]{places.size(), place.getName()});
            System.out.println("place " + places.size() + " " + place.getName());
            commitPlaceInfoToShopTable(sourceUrl, searchText, detailedPlace, alternateUrl);
        }
    }

    private void commitPlaceInfoToShopTable(String sourceUrl, String searchText, Place detailedPlace, String alternateUrl) {
        EntityManager em = emf.createEntityManager();
        try {
            EntityTransaction entr = em.getTransaction();
            entr.begin();
            Shops shop = new Shops();
            if (detailedPlace != null) {
                shop.setShopname(detailedPlace.getName());
                shop.setAddress(detailedPlace.getAddress());
                shop.setPhone(detailedPlace.getPhoneNumber());
                shop.setGooglemapsurl(detailedPlace.getGoogleUrl());
                shop.setPlacetypes(Arrays.deepToString(detailedPlace.getTypes().toArray()));
                if (detailedPlace.getWebsite() == null) {//which one to trust more
                    shop.setShopurl(alternateUrl);
                } else {
                    shop.setShopurl(detailedPlace.getWebsite());
                }
            }
            shop.setSource(sourceUrl);
            shop.setSearchtext(searchText);
            em.persist(shop);
            entr.commit();
        } catch (NullPointerException ex) {
            Logger.getLogger(ManageGooglePlaces.class.getName()).log(Level.SEVERE, "could not commit", ex.getCause().toString());
        } finally {
            em.close();
        }
    }

}
