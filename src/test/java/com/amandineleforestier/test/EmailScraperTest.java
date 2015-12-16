/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amandineleforestier.test;

import com.amandineleforestier.crawlshopsforcoucou.EmailScraper;
import com.amandineleforestier.model.Shops;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author janitha
 */
public class EmailScraperTest {
    
    public EmailScraperTest() {
    }
       @Test
    @Ignore
    public void preFetchShopUrlsFromTable() {
        int offset = 0;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ShopsPU");
        EntityManager em = emf.createEntityManager();
        List<Shops> models = null;//where sh.placetypes like '%clothing%' and sh.shopurl is not null and sh.shopurl <> ''
//        EmailScraper emailFinder = new EmailScraper();
        while ((models = getIterableModels(em, offset, Shops.class)).size() > 0) {
            em.getTransaction().begin();
            for (Shops model : models) {
                Logger.getLogger(NewEmptyJUnitTest.class.getName()).log(Level.INFO, "Url: " + model.getShopurl());
                crawlForEmailAddresses(model.getShopurl());
            }
            em.flush();
            em.clear();
            em.getTransaction().commit();
            offset += models.size();
        }
    }

    private List<Shops> getIterableModels(EntityManager em, int offset, Class<Shops> aClass) {
        return em.createQuery("FROM Shops sh where sh.placetypes like '%clothing%' and sh.shopurl is not null and sh.shopurl <> ''", aClass).
                setFirstResult(offset).
                setMaxResults(100).
                getResultList();
    }

    private void crawlForEmailAddresses(String urlSeedToBeScraped) {
        //        SELECT shopurl FROM shops.shops where placetypes like '%clothing%' and shopurl is not null and shopurl <> '';
        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder("C:\\Users\\janitha\\Documents\\NetBeansProjects\\crawlshopsforcoucou\\target\\interim");
        config.setPolitenessDelay(1000);
        config.setMaxDepthOfCrawling(2);
        config.setIncludeBinaryContentInCrawling(false);
        config.setResumableCrawling(false);
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = null;
        String shopUrl = urlSeedToBeScraped;
        try {
            controller = new CrawlController(config, pageFetcher, robotstxtServer);
            controller.addSeed(shopUrl);
            EmailScraper.setStartWithDomain(shopUrl);
            controller.start(EmailScraper.class, 1);
        } catch (Exception ex) {
            Logger.getLogger(EmailScraperTest.class.getName()).log(Level.SEVERE, "Something went wrong" + ex.getMessage());
        }
    }
}
