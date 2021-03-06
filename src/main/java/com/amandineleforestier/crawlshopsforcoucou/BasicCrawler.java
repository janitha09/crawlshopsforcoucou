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

import com.amandineleforestier.model.Brandinfo;
import com.amandineleforestier.model.Emailaddresses;
import java.util.regex.Pattern;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import uk.org.lidalia.slf4jext.LoggerFactory;

/**
 * @author Yasser Ganjisaffar
 */
public class BasicCrawler extends WebCrawler {

    private static final Pattern IMAGE_EXTENSIONS = Pattern.compile(".*\\.(bmp|gif|jpg|png)$");

    EntityManagerFactory emf = null;

    public BasicCrawler() {
        emf = Persistence.createEntityManagerFactory("ShopsPU");
    }
    static String startWithDomain = null;

    public static void setStartWithDomain(String domain) {
        startWithDomain = domain;
    }

    /**
     * You should implement this function to specify whether the given url
     * should be crawled or not (based on your crawling logic).
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        // Ignore the url if it has an extension that matches our defined set of image extensions.
        if (IMAGE_EXTENSIONS.matcher(href).matches()) {
            return false;
        }
        //only pursue if contact about
        // Only accept the url if it is in the "www.ics.uci.edu" domain and protocol is "http".
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
//            scrapeEmailsAndWriteToTable(html, url);
//            logger.info("Number of outgoing links: {}", htmlParseData.getOutgoingUrls().size());
            //ParseDataFromSeek(html, url);
        }
    }

    private void scrapeEmailsAndWriteToTable(String html, String url) {
        Set<String> emails = getEmailFromText(html);
        emails.stream().forEach((email) -> {
            logEmailAddressestoTable(email, url);
        });

    }

    private void ParseDataFromSeek(String html, String url) {
        Document doc = Jsoup.parse(html);
        Elements brandInfo = doc.select("div.designerTitle > p");
        String brandname = brandInfo.first().text();
        String domicile = brandInfo.get(1).text();
        String email = brandInfo.get(2).text();
        String brandurl = brandInfo.get(3).text();

        writeBrandInfoToTable(brandname, domicile, email, brandurl, url);

        logger.info("BrandName: {}", brandname);
        logger.info("Domicile: {}", domicile);
        logger.info("email: {}", email);
        logger.info("brandurl: {}", brandurl);
    }

    public void writeBrandInfoToTable(String brandname, String domicile, String email, String brandurl, String source) {
        EntityManager em = emf.createEntityManager();
        try {
            EntityTransaction entr = em.getTransaction();
            entr.begin();
            Brandinfo usr = new Brandinfo();
            usr.setBrandname(brandname);
            usr.setCountry(domicile);
            usr.setSource(source);
            usr.setBrandemail(email);
            usr.setBrandurl(brandurl);
            em.persist(usr);
            entr.commit();
        } catch (RollbackException e) {
            //logger.log(Level.INFO, "Primary key violation", e.getMessage());
        } finally {
            em.close();
        }
    }

    private Set<String> getEmailFromText(String input) {
        Pattern p = Pattern.compile("\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(input);
        Set<String> emails = new HashSet<>();
        String addr;
        while (matcher.find()) {
            addr = matcher.group();
            System.out.println(addr);
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
            LoggerFactory.getLogger(BasicCrawler.class).info("Primary key violation", e.getMessage());
        } finally {
            em.close();
        }
    }
}
