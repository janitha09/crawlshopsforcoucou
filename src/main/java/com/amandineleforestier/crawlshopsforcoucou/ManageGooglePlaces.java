/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amandineleforestier.crawlshopsforcoucou;

import com.amandineleforestier.model.Shops;
import java.util.Arrays;
//import com.amandineleforestier.test.NewEmptyJUnitTest;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.jsoup.nodes.Element;
import se.walkercrou.places.Place;

/**
 *
 * @author janitha
 */
public class ManageGooglePlaces {
    EntityManagerFactory emf = null;
    se.walkercrou.places.GooglePlaces client = null;
    public ManageGooglePlaces(){
        emf = Persistence.createEntityManagerFactory("ShopsPU");
        client = new se.walkercrou.places.GooglePlaces("AIzaSyAbA_omv6dng-yrsMc7RwNVT2Fh9gqoook");
        client.setDebugModeEnabled(true);
    }

    public void getAGooglePlaceFromTheString(String searchText, String sourceUrl) {
        List<Place> places = client.getPlacesByQuery(searchText, se.walkercrou.places.GooglePlaces.MAXIMUM_RESULTS);
        Place detailedPlace = null;
        for (Place place : places) {
            detailedPlace = place.getDetails();
            System.out.println("place " + places.size() + " " + place.getName());
            //assertEquals("http://www.1972-conceptstore.com/", detailedPlace.getWebsite());
            commitPlaceInfoToShopTable(sourceUrl, searchText, detailedPlace);
        }
    }

    private void commitPlaceInfoToShopTable(String sourceUrl, String searchText, Place detailedPlace) {
        String placeTypes = "";
        EntityManager em = emf.createEntityManager();
        try {
            EntityTransaction entr = em.getTransaction();
            entr.begin();
            Shops shop = new Shops();
            shop.setShopname(detailedPlace.getName());
            shop.setAddress(detailedPlace.getAddress());
            shop.setPhone(detailedPlace.getPhoneNumber());
            shop.setShopurl(detailedPlace.getWebsite());
            //Logger.getLogger(NewEmptyJUnitTest.class.getName()).log(Level.INFO, "shop website: ", detailedPlace.getWebsite());
            shop.setSource(sourceUrl);
            shop.setSearchtext(searchText);
            shop.setGooglemapsurl(detailedPlace.getGoogleUrl());
            for (String t : detailedPlace.getTypes()) {
                placeTypes += "," + t;
            }
            shop.setPlacetypes(Arrays.deepToString(detailedPlace.getTypes().toArray()));
            em.persist(shop);
            entr.commit();
        } catch (Exception ex) {
            //Logger.getLogger(NewEmptyJUnitTest.class.getName()).log(Level.INFO, "could not commit", ex.getMessage());
        } finally {
            em.close();
        }
    }
    
}
