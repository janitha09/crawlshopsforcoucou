/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.amandineleforestier.crawlshopsforcoucou;

import com.amandineleforestier.model.Emailaddresses;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import java.util.regex.Pattern;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import edu.uci.ics.crawler4j.url.WebURL;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import uk.org.lidalia.slf4jext.LoggerFactory;

/**
 * @author Yasser Ganjisaffar
 */
public class EmailScraper extends WebCrawler {

    private static final Pattern IMAGE_EXTENSIONS = Pattern.compile(".*\\.(bmp|gif|jpg|png)$");

    EntityManagerFactory emf = null;

    public EmailScraper() {
        emf = Persistence.createEntityManagerFactory("ShopsPU");
    }
    static String startWithDomain = null;

    public static void setStartWithDomain(String domain) {
        startWithDomain = domain;
    }

    /**
     * You should implement this function to specify whether the given url
     * should be crawled or not (based on your crawling logic).
     * @return true or false
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        // Ignore the url if it has an extension that matches our defined set of image extensions.
        if (IMAGE_EXTENSIONS.matcher(href).matches()) {
            return false;
        }
        //only pursue if contact about
        return href.startsWith(startWithDomain);
    }

    /**
     * This function is called when a page is fetched and ready to be processed
     * by your program.
     */
    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        logger.info("URL: {}", url);

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String html = htmlParseData.getHtml();
            scrapeEmailsAndWriteToTable(html, url);
        }
    }

    private void scrapeEmailsAndWriteToTable(String html, String url) {
        Set<String> emails = getEmailFromText(html);
        emails.stream().forEach((email) -> {
            logEmailAddressestoTable(email, url);
        });
    }

    private Set<String> getEmailFromText(String input) {
        Pattern p = Pattern.compile("\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(input);
        Set<String> emails = new HashSet<>();
        String addr;
        while (matcher.find()) {
            addr = matcher.group();
            logger.info( addr);
            emails.add(addr);
        }
        return emails;
    }

    public void logEmailAddressestoTable(String email, String sourceUrl) {
        EntityManager em = emf.createEntityManager();
        try {
            EntityTransaction entr = em.getTransaction();
            entr.begin();
            Emailaddresses table = new Emailaddresses();
            table.setEmail(email);
            table.setSourceurl(sourceUrl);
            em.persist(table);
            entr.commit();
        } catch (RollbackException e) {
            LoggerFactory.getLogger(EmailScraper.class).info("Primary key violation", e.getMessage());
        } finally {
            em.close();
        }
    }
}
