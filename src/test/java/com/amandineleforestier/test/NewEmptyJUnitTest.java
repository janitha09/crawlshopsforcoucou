package com.amandineleforestier.test;
//Henry-christ.com
//Raw-fashion.comstores.get(s).select
//Kirobykim.com
//Decadent need website
//Modstrom not stockists on website they have sales agents concepth stores http://www.modstrom.com/en/stores
//Marcopolo need website
//Charlieway need website
//Majestic filatures

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.amandineleforestier.crawlshopsforcoucou.BasicCrawler;
import com.amandineleforestier.crawlshopsforcoucou.ManageGooglePlaces;
import com.amandineleforestier.model.Brandinfo;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Place;

/**
 *
 * @author 222252
 */
public class NewEmptyJUnitTest {

    @Test
    @Ignore
    public void hello() {
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
        String shopUrl = "http://seekexhibitions.com/";
        try {
            controller = new CrawlController(config, pageFetcher, robotstxtServer);
            controller.addSeed(shopUrl);
            BasicCrawler.setStartWithDomain(shopUrl);

            controller.start(BasicCrawler.class, 1);
        } catch (Exception ex) {
            Logger.getLogger(NewEmptyJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    @Ignore
    public void jsoupstringhtml() {
        String html = "<html><head><title>First parse</title></head>"
                + "<body><p>Parsed HTML into a doc.</p></body></html>";
        Document doc = Jsoup.parse(html);
//        System.out.println(doc.body());
        assertEquals("<body>\n" + " <p>Parsed HTML into a doc.</p>\n" + "</body>", doc.body().toString());
//        http://seekexhibitions.com/theindex/designers/view/735
//        http://seekexhibitions.com/theindex/designers/view/856
//        http://seekexhibitions.com/theindex/designers/view/736
    }

    @Test
    @Ignore
    public void getDesignerInfoFromSeek() throws IOException {
        //Document doc = Jsoup.connect("http://example.com/").get();
        String html
                = "<body>\n"
                + "    <div class=\"designerProfile\">\n"
                + "        <div class=\"rightProfile\">\n"
                + "            <div class=\"rightProfileInner\">\n"
                + "                <div class=\"designerLogo\">\n"
                + "                    <a href='http://www.alfredogonzales.com' target='_blank'>\n"
                + "                        <img src=\"/theindex/img/loader.gif\" data-original=\"/theindex/app/webroot/slir/w170-h50/theindex/app/webroot/files/2015/06/09/_web-AlfredoGonzales-logo-2.jpg\" style=\"max-height:50px; max-width:100%;\" class=\"lazy\" alt=\"\" />\n"
                + "                    </a>\n"
                + "                </div>\n"
                + "                <div class=\"designerTitle  \">\n"
                + "                    <p>ALFREDO GONZALES</p><p>THE NETHERLANDS</p><p><a href='mailto:djordy@alfredogonzales.com'>DJORDY@ALFREDOGONZALES.COM</a></p><p><a target='_blank' href='http://www.alfredogonzales.com'>WWW.ALFREDOGONZALES.COM</a></p>\n"
                + "                </div>\n"
                + "\n"
                + "                <div class=\"designerContacts \">\n"
                + "                    <p><b>DESIGNER</b> HUGO HAPPEL, DENNI VAN HUIS, DENNIS EBELI, MARTIN HOOKE</p>\n"
                + "                </div>\n"
                + "                <div id='middleBreak'><a href='#' class='mailme'>SEND ME PDF</a></div>\n"
                + "                <div style=\"z-index:1\" class=\"designerInfo \">\n"
                + "\n"
                + "                    At Alfredo Gonzales, we believe that life is about appreciating the little things - they make the big picture. We celebrate a lifestyle in which all the little things matter, because these little things make us enjoy life, every day. <br>\n"
                + "                    An early morning surf in clear waters, a daily shot of espresso after having breakfast, the smile of your grandmother when you pay her a visit. Just a few moments that blend into your life and contribute to your happiness. It is the sum of countless elements that paints the full picture. An eye for detail is required if you want to get the most out of it.<br>\n"
                + "                    Our socks are a striking example of this philosophy. We are dedicated to making socks for those discerning men and women that care about the little things, understand that details make a difference, and love quality & design.\n"
                + "                </div>\n"
                + "\n"
                + "            </div>\n"
                + "        </div>\n"
                + "        <div class=\"leftProfile\">\n"
                + "            <div data-original=\"/theindex/app/webroot/slir/w1200-h1200/theindex/app/webroot/files/2015/05/12/_Alfredo_21.jpg\"\n"
                + "                 class=\"DesignerImgs coverBg lazy\">\n"
                + "\n"
                + "            </div>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "</body>";
        Document doc = Jsoup.parse(html);
        Elements designerTitleClass = doc.select("div.designerTitle");
        assertEquals(1, designerTitleClass.size());
//        System.out.println(designerTitleClass.first().toString());
        assertEquals("<div class=\"designerTitle  \"> \n"
                + " <p>ALFREDO GONZALES</p>\n"
                + " <p>THE NETHERLANDS</p>\n"
                + " <p><a href=\"mailto:djordy@alfredogonzales.com\">DJORDY@ALFREDOGONZALES.COM</a></p>\n"
                + " <p><a target=\"_blank\" href=\"http://www.alfredogonzales.com\">WWW.ALFREDOGONZALES.COM</a></p> \n"
                + "</div>",
                designerTitleClass.first().toString());
        Elements designerName = doc.select("div.designerTitle > p:eq(0)");
//        System.out.println(designerName.first().toString());
        assertEquals("ALFREDO GONZALES", designerName.first().text());

        Elements country = doc.select("div.designerTitle > p:eq(1)");
//        System.out.println(country.first().toString());
        assertEquals("THE NETHERLANDS", country.first().text());

        Elements emailUrl = doc.select("div.designerTitle > p > a[href]");
        assertEquals(2, emailUrl.size());
        //System.out.println(emailUrl.first().toString());
        assertEquals("<a href=\"mailto:djordy@alfredogonzales.com\">DJORDY@ALFREDOGONZALES.COM</a>", emailUrl.first().toString());
        assertEquals("mailto:djordy@alfredogonzales.com", emailUrl.first().attr("href"));
        assertEquals("http://www.alfredogonzales.com", emailUrl.get(1).attr("href"));
    }

    @Test
    @Ignore
    public void writeDesignerInformationToTable() {
        //Document doc = Jsoup.connect("http://example.com/").get();
        String html
                = "<body>\n"
                + "    <div class=\"designerProfile\">\n"
                + "        <div class=\"rightProfile\">\n"
                + "            <div class=\"rightProfileInner\">\n"
                + "                <div class=\"designerLogo\">\n"
                + "                    <a href='http://www.alfredogonzales.com' target='_blank'>\n"
                + "                        <img src=\"/theindex/img/loader.gif\" data-original=\"/theindex/app/webroot/slir/w170-h50/theindex/app/webroot/files/2015/06/09/_web-AlfredoGonzales-logo-2.jpg\" style=\"max-height:50px; max-width:100%;\" class=\"lazy\" alt=\"\" />\n"
                + "                    </a>\n"
                + "                </div>\n"
                + "                <div class=\"designerTitle  \">\n"
                + "                    <p>ALFREDO GONZALES</p><p>THE NETHERLANDS</p><p><a href='mailto:djordy@alfredogonzales.com'>DJORDY@ALFREDOGONZALES.COM</a></p><p><a target='_blank' href='http://www.alfredogonzales.com'>WWW.ALFREDOGONZALES.COM</a></p>\n"
                + "                </div>\n"
                + "\n"
                + "                <div class=\"designerContacts \">\n"
                + "                    <p><b>DESIGNER</b> HUGO HAPPEL, DENNI VAN HUIS, DENNIS EBELI, MARTIN HOOKE</p>\n"
                + "                </div>\n"
                + "                <div id='middleBreak'><a href='#' class='mailme'>SEND ME PDF</a></div>\n"
                + "                <div style=\"z-index:1\" class=\"designerInfo \">\n"
                + "\n"
                + "                    At Alfredo Gonzales, we believe that life is about appreciating the little things - they make the big picture. We celebrate a lifestyle in which all the little things matter, because these little things make us enjoy life, every day. <br>\n"
                + "                    An early morning surf in clear waters, a daily shot of espresso after having breakfast, the smile of your grandmother when you pay her a visit. Just a few moments that blend into your life and contribute to your happiness. It is the sum of countless elements that paints the full picture. An eye for detail is required if you want to get the most out of it.<br>\n"
                + "                    Our socks are a striking example of this philosophy. We are dedicated to making socks for those discerning men and women that care about the little things, understand that details make a difference, and love quality & design.\n"
                + "                </div>\n"
                + "\n"
                + "            </div>\n"
                + "        </div>\n"
                + "        <div class=\"leftProfile\">\n"
                + "            <div data-original=\"/theindex/app/webroot/slir/w1200-h1200/theindex/app/webroot/files/2015/05/12/_Alfredo_21.jpg\"\n"
                + "                 class=\"DesignerImgs coverBg lazy\">\n"
                + "\n"
                + "            </div>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "</body>";
        Document doc = Jsoup.parse(html);
        Elements brandInfo = doc.select("div.designerTitle > p");
        String brandname = brandInfo.first().text();
        String domicile = brandInfo.get(1).text();
        String email = brandInfo.get(2).text();
        String brandurl = brandInfo.get(3).text();

        BasicCrawler bc = new BasicCrawler();
        bc.writeBrandInfoToTable(brandname, domicile, email, brandurl, "sourceurl");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ShopsPU");
        Brandinfo[] bi = null;
        try {
            javax.persistence.Query q = emf.createEntityManager().createNamedQuery("Brandinfo.findByBrandname").setParameter("brandname", brandname);
            bi = (Brandinfo[]) q.getResultList().toArray(new Brandinfo[0]);
        } finally {
            emf.createEntityManager().close();
        }
        assertEquals(brandname, bi[0].getBrandname());
        assertEquals(brandurl, bi[0].getBrandurl());
        assertEquals(domicile, bi[0].getCountry());
        assertEquals(email, bi[0].getBrandemail());

    }

    //http://misschips.be/stores/saudi-arabia/
    @Test
    @Ignore
    public void getPlaceInformationFromNameForEmpireStateBuilding() {
//        https://issues.apache.org/jira/browse/HTTPCLIENT-1613
        GooglePlaces client = new GooglePlaces("AIzaSyAbA_omv6dng-yrsMc7RwNVT2Fh9gqoook ");
        String name = "Empire State Building";
        List<Place> places = client.getPlacesByQuery(name, GooglePlaces.MAXIMUM_RESULTS);
        Place empireStateBuilding = null;
        for (Place place : places) {
            if (place.getName().equals(name)) {
                empireStateBuilding = place;
                break;
            }
        }
        Place detailedEmpireStateBuilding1 = empireStateBuilding.getDetails();
        Place detailedEmpireStateBuilding = detailedEmpireStateBuilding1;
        assertEquals(name, detailedEmpireStateBuilding.getName());
        assertEquals("350 5th Ave, New York, NY 10118, United States", detailedEmpireStateBuilding.getAddress());
        assertEquals("(212) 736-3100", detailedEmpireStateBuilding.getPhoneNumber());
        assertEquals("http://www.esbnyc.com/", detailedEmpireStateBuilding.getWebsite());
        assertEquals("point_of_interest", detailedEmpireStateBuilding.getTypes().get(0));
        assertEquals("https://maps.google.com/?cid=15074921902713971043", detailedEmpireStateBuilding.getGoogleUrl());
    }

    @Test
    @Ignore
    public void parsePiolaWebPage() throws IOException {
//        http://www.piola.fr/en/revendeurs
        String html
                = "    <div class=\"Revendeur\" data-pays=\"United States\" data-id=\"77\">\n"
                + "        <div class=\"Revendeur-wrapVisuel\">\n"
                + "            <div class=\"Revendeur-visuel\" data-original=\"/img/revendeurs/77.jpg\" style=\"background-image: url('http://www.piola.fr/themes/piolav2/img/piolav2/default.png');\"></div>\n"
                + "        </div>\n"
                + "        <div class=\"Revendeur-content\">\n"
                + "            <h3 class=\"Revendeur-title\">Need Supply US</h3>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "    <div class=\"Revendeur\" data-pays=\"United States\" data-id=\"78\">\n"
                + "        <div class=\"Revendeur-wrapVisuel\">\n"
                + "            <div class=\"Revendeur-visuel\" data-original=\"/img/revendeurs/78.jpg\" style=\"background-image: url('http://www.piola.fr/themes/piolav2/img/piolav2/default.png');\"></div>\n"
                + "        </div>\n"
                + "        <div class=\"Revendeur-content\">\n"
                + "            <h3 class=\"Revendeur-title\">Ofy Shop</h3>\n"
                + "        </div>\n"
                + "    </div>\n";

        Document doc = Jsoup.parse(html);
//        Document doc = Jsoup.connect("http://www.piola.fr/en/revendeurs").get();
        Elements brandInfo = doc.select("div.Revendeur");

        ManageGooglePlaces gp = new ManageGooglePlaces();
        for (Element e : brandInfo) {
            System.out.println(e.child(1).select("div.Revendeur-content > h3").text() + " " + e.dataset().values().toArray()[0]);//e.child(1));

            gp.getAGooglePlaceFromTheString(e.child(1).select("div.Revendeur-content > h3").text() + " " + e.dataset().values().toArray()[0], "http://www.piola.fr/en/revendeurs", null);
        }
    }
//http://unionmadegoods.com/

    @Test
    @Ignore
    public void parseChristianMetzner() throws IOException {
//        where to buy
//        stockists
        String html
                = "<div class=\"entry-body\">\n"
                + "    <p>\n"
                //                + "        BERLIN\n"
                //                + "        <a href=\"http://www.oona-galerie.de/\">Oona Galerie BERLIN</a> –\n"
                //                + "        <a href=\"http://www.sueper-store.de/product/armreif-aus-glas\">Süper Store BERLIN</a> – <br />\n"
                //                + "        <a href=\"http://www.baerck.net/Baerck/\">Baerck BERLIN</a> –\n"
                //                + "        <a href=\"http://news.gestalten.com/stores\">Gestalten Pavilion BERLIN</a> &#8211;\n"
                //                + "        <a href=\"http://www.paperandtea.com/de/\">P &#038; T BERLIN</a> –<br />\n"
                //                + "        BEIJING   Blakk<br />\n"
                //                + "        BELFORT  <a href=\"http://www.rve-decoration.fr/\">Rve Décoration BELFORT </a><br />\n"
                //                + "        FREIBURG   <a href=\"http://www.takecare-freiburg.de/\">Take Care FREIBURG</a><br />\n"
                //                + "        HERFORD <a href=\"http://marta-herford.de/index.php/shop/\">Martha Herford HERFORD</a><br />\n"
                //                + "        MUNICH   <a href=\"http://www.stadthausmuenchen.com/index.html\">Stadthaus MUNICH</a><br />\n"
                //                + "        PARIS <a href=\"http://the-broken-arm.com/en/16_pb-0110\">Broken Arm PARIS</a><br />\n"
                //                + "        STUTTGART   <a href=\"http://www.ave-anziehsachen.de/\">Ave Anziehsachen STUTTGART</a><br />\n"
                //                + "        WELS <a href=\"http://www.kunstfaden.at/index.php?/kontakt/kontakt/\">Kunstfaden WELS</a><br />\n"
                //                + "        WIESBADEN   <a href=\"http://qompendium.com/workshop/index.php?route=product/category&#038;path=59_61\">Qompendium Work Shop WIESBADEN</a><br />\n"
                + "        ZURICH   <a href=\"http://www.affaire46.ch/\">Affaire46 ZURICH</a>\n"
                + "    </p>\n"
                + "</div>";
        Document doc = Jsoup.parse(html);
        //Document doc = Jsoup.connect("http://christian-metzner.com/en/2014/cmb/").get();
        Elements shopInfo = doc.select("div.entry-body > p > a[href]");
        //System.out.println("size " + shopInfo.size());
        //System.out.println("text " + shopInfo.text());
        ManageGooglePlaces gp = new ManageGooglePlaces();
        for (Element e : shopInfo) {
            System.out.println(e.text() + " " + e.attr("href"));

            gp.getAGooglePlaceFromTheString(e.text(), "http://christian-metzner.com/en/2014/cmb", e.attr("href"));
        }
    }

    @Test
    public void getEmailAddress() throws IOException {
//        String input = "this is more text janitha_j@hotmail.com";
        Document doc = Jsoup.connect("http://www.minishopmadrid.com/pages/about-us").get();
        String input = doc.body().text();
        Pattern p = Pattern.compile("\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(input);
        Set<String> emails1 = new HashSet<>();
        while (matcher.find()) {
            emails1.add(matcher.group());
        }
        assertEquals("janitha_j@hotmail.com", emails1.toArray()[0]);
    }

    @Test
    @Ignore
    public void logEmailAddress() {
        BasicCrawler bs = new BasicCrawler();
        bs.logEmailAddressestoTable("janitha_j@hotmail.com", "souce url");
        assertTrue(true);
    }

    @Test
    @Ignore
    public void getUrlsFromShopInfoTable() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ShopsPU");
        Brandinfo[] bi = null;
        try {
            javax.persistence.Query q = emf.createEntityManager().createQuery("SELECT distinct(shopurl) FROM shops.shops where shopurl is not null");
            bi = (Brandinfo[]) q.getResultList().toArray(new Brandinfo[0]);
        } finally {
            emf.createEntityManager().close();
        }
    }

    @Test
    public void getStoreNamesandUrlsFromNewBlack() {
        String html = "<HTML lang=\"en\">\n"
                + "<HEAD>\n"
                + "    <META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                + "    <LINK REL=\"STYLESHEET\" TYPE=\"text/css\" HREF=\"http://www.newblack.se/en/webshop.css.php?webshop=26626&amp;template=3698696&amp;v=47946148&amp;twsv=20151109185515\">\n"
                + "    <LINK REL=\"STYLESHEET\" TYPE=\"text/css\" HREF=\"http://www.newblack.se/common/dtree.css\">\n"
                + "    <TITLE>New Black - Retailers</TITLE>\n"
                + "    <link rel=\"shortcut icon\" href=\"http://www.newblack.se/shop/26626/favicon-024c4b.png\" type=\"image/vnd.microsoft.icon\">\n"
                + "    <link rel=\"alternate\" hreflang=\"sv\" href=\"http://www.newblack.se/sv/pages/aterforsaljare.html\">\n"
                + "    <link rel=\"alternate\" hreflang=\"en\" href=\"http://www.newblack.se/en/pages/retailers.html\">\n"
                + "    <meta name=\"keywords\" content=\"Clothing, Streetwear, street wear, Crew Neck, crew neck, Sweater, sweater, t-shirt, tee, t-shirts, tees, clothes, snapbacks, hoodies, hats, hooded sweat shirt, sweatshirt, New Black, new black Sweden, newblack, , new, black, new black, new black, NB, As Seen On TV, ASOT, ASOT12, web shop, web shop, E commerce, Online, Retail, 2012, two thousand twelve, Gothenburg, Stockholm, Sweden, Fashion, fashion, music, hiphop, hip hop, 90's, 90's, 90s, caliroots, sneakers stuff, Shelta, childrens store, frctn, lookbook, erik by erik, graffiti, t-shirt printing, t-shirt with print, biggie, tupac, 2Pac, Nas, Jay-Z, 99 problems, cookie, cookie, shirt, tee cookie, cookie t-shirt, Swedish design, Swedish fashion,\n"
                + "Clothing, Streetwear, street wear, Crew Neck, crew neck, Sweater, sweater, t-shirt, tee, t-shirts, tees, clothes, snapbacks, hoodies, hats, hooded sweat shirt, sweatshirt, New Black, new black Sweden, newblack, , new, black, new black, new black, NB, As Seen On TV, ASOT, ASOT12, web shop, web shop, E commerce, Online, Retail, 2012, two thousand twelve, Gothenburg, Stockholm, Sweden, Fashion, fashion, music, hiphop, hip hop, 90's, 90's, 90s, ralph lauren, polo, polo bear, teddy bear, teddy sun, teddy, common sensed, sneakers, jordan, jordan 7, jordan 7s, jordan Bordeux, basketball shoes, basketball shoes, basketball , caliroots, sneakers stuff, Shelta, childrens store, frctn, lookbook, erik by erik, graffiti, t-shirt printing, t-shirt with print, biggie, tupac, 2Pac, Nas, Jay-Z, 99 problems, cookie, cookie shirt, tee cookie, cookie t-shirt, Swedish design, Swedish fashion\n"
                + "Clothing, Streetwear, street wear, Crew Neck, crew neck, Sweater, sweater, t-shirt, tee, t-shirts, tees, clothes, snapbacks, hoodies, hats, hooded sweat shirt, sweatshirt, New Black, new black Sweden, newblack, , new, black, new black, new black, NB, As Seen On TV, ASOT, ASOT12, web shop, web shop, E commerce, Online, Retail, 2012, two thousand twelve, Gothenburg, Stockholm, Sweden, Fashion, fashion, music, hiphop, hip hop, 90's, 90's, 90s, Sleep Is The Cousin of Death, Cousin of Death, Nas - NY State Of Mind, Nas, N. Y. State of Mind, Illmatic, 1994 Nas, USA, NYC, New York City, Queensbridge, Queens, Nasir Jones, DJ Premier, Eric B. &amp; Rakim, Kool and the Gang, Donald Byrd, 1992, 4:54, caliroots, sneakers stuff; Shelta, childrens store, frctn, lookbook, erik by erik, graffiti, t-shirt printing, t-shirt with print, biggie, tupac, 2Pac, Nas, Jay-Z, 99 problems, cookie, cookie shirt, cookie tee, cookie t shirt, Swedish design, Swedish fashion\n"
                + "Clothing, Streetwear, street wear, Crew Neck, crew neck, Sweater, sweater, t-shirt, tee, t-shirts, tees, clothes, snapbacks, hoodies, hats, hooded sweat shirt, sweatshirt, New Black, new black Sweden, newblack, , new, black, new black, new black, NB, As Seen On TV, ASOT, ASOT12, web shop, web shop, E commerce, Online, Retail, 2012, two thousand twelve, Gothenburg, Stockholm, Sweden, Fashion, fashion, music, hiphop, hip hop, 90's, 90's, 90s, 100 miles and still runnin, 100 miles and still runnin, still runnin, 100 miles and runnin, NWA, niggaz Wit Attitudes, Arabian Prince, DJ Yella, Dr. Dre, Eazy-E, Ice Cube, MC Ren, Jerry Heller, Ruthless Records, 1990, USA, California, Cali, Los Angeles, LA, Compton, Kamurshol, Real niggaz, Sa Prize, Pt. 2, Just Do not Bite It, Open Fire, 2Pac, 2 pac, 2 pac, tupac, RU Still Down? (Remember Me), RU Still Down?, Are you still down, caliroots, sneakers stuff, Shelta, childrens store, frctn, lookbook, erik by erik, graffiti, t-shirt printing, t-shirt with print, biggie, tupac, 2Pac, Nas, Jay-Z, 99 problems, cookie, cookie, shirt, tee cookie, cookie t-shirt, Swedish design, Swedish fashion\n"
                + "Clothing, Streetwear, street wear, Crew Neck, crew neck, Sweater, sweater, t-shirt, tee, t-shirts, tees, clothes, snapbacks, hoodies, hats, hooded sweat shirt, sweatshirt, New Black, new black Sweden, newblack, , new, black, new black, new black, NB, As Seen On TV, ASOT, ASOT12, web shop, web shop, E commerce, Online, Retail, 2012, two thousand twelve, Gothenburg, Stockholm, Sweden, Fashion, fashion, music, hiphop, hip hop, 90's, 90's, 90s, New York get the blood money Dirty cash, Capone-N-Noreaga, Capone &amp; Noreaga, capone, noreaga, CNN, CNN, The War Report, Queens, New York, USA , Nas, Mobb Deep, Tragedy Khadafi, DJ Premier, Busta Rhymes, mafioso rap, Intro, Charlemagne, Bloody Money, Driver's Seat, The Hitmen, Imam THUG, Busta Rhymes, Stick You, Naughty Short, Parole Violation Thu, Iraq, See the World, EZ Elpee, Castro, Musaliny, Live On, Live Long, G-Money, Neva Die Alone, Buckwild, TONY, Top of New York, Channel 10, Lord Finesse, Capone Phone Home, Thug Paradise, Stay Tuned, Capone Bone , Marley Marl, Halfway Thugs, Charlemagne, LA LA, Marley Marl, J. Force, Mobb Deep, Tragedy Khadafi, Capone-N-Noreaga Live, Illegal Life, Tragedy Khadafi, Havoc, Black Gangstas, Buckwild, Closer, DJ Clark Kent , Nneka, Capone Phone Home, dollar, dollar bill, one dollar, The Wire, The Wire, HBO, television, television series, Baltimore, Maryland, Omar Little, Omar Devone Little, Michael K. Williams, Edmondson High School, West Baltimore , Newport cigarettes, Honey Nut Cheerios, .44 Magnum Desert Eagle, Colt Anaconda, .45 ACP semi-Automatics, .50 Action Express, drugs, drug dealers, drug money, homusexuell, caliroots, sneakers stuff, Shelta, childrens store, frctn, lookbook , erik by erik, graffiti, t-shirt printing, t-shirt with print, biggie, tupac, 2Pac, Nas, Jay-Z, 99 problems, cookie, cookie, shirt, tee cookie, cookie t-shirt, Swedish design, Swedish mode\n"
                + "Clothing, Streetwear, street wear, Crew Neck, crew neck, Sweater, sweater, t-shirt, tee, t-shirts, tees, clothes, snapbacks, hoodies, hats, hooded sweat shirt, sweatshirt, New Black, new black Sweden, newblack, , new, black, new black, new black, NB, As Seen On TV, ASOT, ASOT12, web shop, web shop, E commerce, Online, Retail, 2012, two thousand twelve, Gothenburg, Stockholm, Sweden, Fashion, fashion, music, hiphop, hip hop, 90's, 90's, 90s, The Wire, The Wire, HBO, television, television series, Baltimore, Maryland, drugs, drug dealers, drug money, gangs, organized crime, Stringer Bell, Avon Barksdale, Barksdale Organization , Baltimore City Community College, finance, marketing, rebranding, real estate, property investment, West Baltimore, think outside the g pack, cash bell, taco bell, Taco Bell, Irvine, California, fast food, tacos, burritos, quesadillas, nachos, think outside frames, think outside the box, entrepreneurial, cowboys, think outside the bun, caliroots, sneakers stuff, Shelta, childrens store, frctn, lookbook, erik by erik, graffiti, t-shirt printing, t-shirt with print, biggie, tupac, 2Pac, Nas, Jay-Z, 99 problems, cookie, cookie, shirt, tee cookie, cookie t-shirt, Swedish design, Swedish fashion, CSM2013\">\n"
                + "    <meta name=\"description\" content=\"Sweden: Aplace Aplace Bruno&amp;nbsp;(Stockholm) Aplace Malm&amp;ouml;&amp;nbsp;(Malm&amp;ouml;) Aplace Norrlandsgatan&amp;nbsp;(Stockholm) Caliroots&amp;nbsp; (Stockholm) Childstore (Gothenburg)\">\n"
                + "    <meta name=\"generator\" content=\"Textalk Webshop\">\n"
                + "    <script type=\"text/javascript\">//<![CDATA[\n"
                + "window.webshop = {\"language\":\"en\",\"currency\":\"EUR\",\"tws_version\":20151109185515,\"vatIncluded\":true,\"webshopId\":26626,\"page\":{\"type\":\"shopwindow\",\"id\":5572186}};\n"
                + "// ]]></script>\n"
                + "    <script type=\"text/javascript\" src=\"http://www.newblack.se/js/Shop.min.js?twsv=20151109185515\"></script>\n"
                + "    <script type=\"text/javascript\" src=\"/js/template.js.php?w=26626&amp;t=3698696&amp;v=47946148&amp;l=en&amp;twsv=20151109185515\"></script>\n"
                + "    <!--\n"
                + "\n"
                + "            - ---+ From the Secret Chambers of +-- -\n"
                + "             __             __                   __\n"
                + "            |  |--.--------|  |--.-----.-----.--|  |\n"
                + "            |  _  |        |  _  |__ --|  _  |  _  |\n"
                + "            |_____|__|__|__|_____|_____|__   |_____|\n"
                + "            ----------------------------- |__| -----\n"
                + "            INFO@BOMBSQUAD.SE   -   WWW.BOMBSQUAD.SE\n"
                + "\n"
                + "    -->\n"
                + "    <script type=\"text/javascript\" src=\"https://shop.textalk.se/shop/26626/files/jquery.nivo.slider.pack.js\"></script>\n"
                + "    <link rel=\"stylesheet\" href=\"https://shop.textalk.se/shop/26626/files/nivo-slider.css\" type=\"text/css\" media=\"screen\">\n"
                + "    <link rel=\"stylesheet\" href=\"https://shop.textalk.se/shop/26626/files/default.css\" type=\"text/css\" media=\"screen\">\n"
                + "    <script src=\"https://shop.textalk.se/shop/26626/files/instafeed.min.js\"></script>\n"
                + "</HEAD>"
                + "<body class=\"shopwindow-page shopwindow-page-5572186\">\n"
                + "    <form name=\"addtocartform\" action=\"http://www.newblack.se/en/article.php\" method=\"get\" style=\"margin:0px\">\n"
                + "        <input type=\"hidden\" name=\"id\" id=\"shopID\" value=\"26626\">\n"
                + "        <input type=\"hidden\" name=\"art\" value=\"\">\n"
                + "        <input type=\"hidden\" name=\"buy\" value=\"1\">\n"
                + "        <input type=\"hidden\" name=\"no\">\n"
                + "        <input type=\"hidden\" name=\"return\" value=\"YToyOntzOjQ6InBhZ2UiO3M6NDY6Imh0dHA6Ly93d3cubmV3YmxhY2suc2UvZW4vcGFnZXMvcmV0YWlsZXJzLmh0bWwiO3M6NjoicGFyYW1zIjthOjA6e319\">\n"
                + "    </form>"
                + "        <div id=\"upperField\" class=\"upperFieldNormal twsupper\">\n"
                + "            <TABLE WIDTH=\"100%\" summary=\"\">\n"
                + "                <TR VALIGN=\"middle\">\n"
                + "                    <TD><div class=\"object_horizontal object_linkbasket\" id=\"tob-62060654\"><div class=\"linkbasket basket_62060654\" style=\"\"></div></div></TD>\n"
                + "                    <TD><div class=\"object_horizontal object_login\" id=\"tob-62060655\"><div class=\"login\" style=\"\"><a href=\"http://www.newblack.se/en/buyer_login.php?id=26626\" class=\"smallloginbutton\">Log in</a></div></div></TD>\n"
                + "                    <TD>\n"
                + "                        <div class=\"object_horizontal object_customcontent\">\n"
                + "                            <div class=\"customcontent\" style=\"padding-top:0px;padding-bottom:0px;padding-left:5px;padding-right:0px;\">\n"
                + "                                <div style=\"width:100px; margin-top:11px; position: absolute; right: 0px;\">\n"
                + "                                    <img src=\"https://shop.textalk.se/shop/26626/art46/124646-ban-d8bf38.jpg\" border=\"0\" style=\"margin-right: 10px;\">\n"
                + "                                    <a href=\"http://www.newblack.se/sv/\"><img src=\"https://shop.textalk.se/shop/26626/art47/124647-ban-65e2b9.jpg\" border=\"0\"></a>\n"
                + "                                </div>\n"
                + "                            </div>\n"
                + "                        </div>\n"
                + "                    </TD>\n"
                + "                </TR>\n"
                + "            </TABLE>\n"
                + "        </div>"
                + "        <div id=\"topField\" class=\"topFieldWide twstop\">\n"
                + "            <table class=\"top-field-left\" summary=\"\">\n"
                + "                <tr>\n"
                + "                    <TD><div class=\"object_horizontal object_customcontent\"><div class=\"customcontent\" style=\"\"><div class=\"new_top_logo\"></div></div></div></TD>\n"
                + "                    <TD class=\"topnavcell\">\n"
                + "                        <div class=\"object_horizontal object_menu\" id=\"tob-62060658\">\n"
                + "                            <ul class=\"articleMenu articleMenuLevel0\">\n"
                + "                                <li id=\"articleMenuItem1158359\" class=\"articleMenuItem articleMenuItemTop articleMenuItemLevel0\">\n"
                + "                                    <a id=\"treenode1158359\" href=\"/en/products/tees-tanks/\" class=\"\">Tees &amp; Tanks</a>\n"
                + "                                </li>\n"
                + "                                <li id=\"articleMenuItem1158360\" class=\"articleMenuItem articleMenuItemTop articleMenuItemLevel0\">\n"
                + "                                    <a id=\"treenode1158360\" href=\"/en/products/sweats-hoods/\" class=\"\">Sweats &amp; Hoods</a>\n"
                + "                                </li>\n"
                + "                                <li id=\"articleMenuItem2235582\" class=\"articleMenuItem articleMenuItemTop articleMenuItemLevel0\">\n"
                + "                                    <a id=\"treenode2235582\" href=\"/en/products/shirts/\" class=\"\">Shirts</a>\n"
                + "                                </li>\n"
                + "                                <li id=\"articleMenuItem2235577\" class=\"articleMenuItem articleMenuItemTop articleMenuItemLevel0\">\n"
                + "                                    <a id=\"treenode2235577\" href=\"/en/products/pants-shorts/\" class=\"\">Pants &amp; Shorts</a>\n"
                + "                                </li>\n"
                + "                                <li id=\"articleMenuItem1158362\" class=\"articleMenuItem articleMenuItemTop articleMenuItemLevel0\">\n"
                + "                                    <a id=\"treenode1158362\" href=\"/en/products/outerwear/\" class=\"\">Outerwear</a>\n"
                + "                                </li>\n"
                + "                                <li id=\"articleMenuItem1158361\" class=\"articleMenuItem articleMenuItemTop articleMenuItemLevel0\">\n"
                + "                                    <a id=\"treenode1158361\" href=\"/en/products/caps-hats/\" class=\"\">Caps &amp; Hats</a>\n"
                + "                                </li>\n"
                + "                                <li id=\"articleMenuItem2235562\" class=\"articleMenuItem articleMenuItemTop articleMenuItemLevel0\">\n"
                + "                                    <a id=\"treenode2235562\" href=\"/en/products/underwear/\" class=\"\">Underwear</a>\n"
                + "                                </li>\n"
                + "                                <li id=\"articleMenuItem1736722\" class=\"articleMenuItem articleMenuItemTop articleMenuItemLevel0\">\n"
                + "                                    <a id=\"treenode1736722\" href=\"/en/products/sale/\" class=\"\">SALE</a>\n"
                + "                                </li>\n"
                + "                            </ul>\n"
                + "                        </div>\n"
                + "                    </TD>\n"
                + "                </tr>\n"
                + "            </table><div style=\"clear:both\"></div>\n"
                + "        </div>"
                + "        <div id=\"middleArea\">\n"
                + "            <div id=\"contentArea\">\n"
                + "                <div id=\"contentField\" class=\"twscontent\">\n"
                + "                    <!-- Content starts here! -->\n"
                + "                    <a name=\"shoptop\" id=\"top\"></a>\n"
                + "                    <div class=\"shopwindow_custom shopwindow\">\n"
                + "                        <div class=\"shopwindowcustom shopwindowcustom1\">\n"
                + "                            <div class=\"shopwindow_custom_text\">\n"
                + "                                <span style=\"background-color: #ffffff; color: #000000;\">Sweden:<br><a href=\"http://www.aplace.com/\" target=\"_blank\"><span>Aplace</span></a><br><span></span><a href=\"http://www.aplace.com/sv/stores\" target=\"_blank\">Aplace Bruno</a><span>&nbsp;(Stockholm)</span><br><span></span><a href=\"http://www.aplace.com/sv/stores\" target=\"_blank\">Aplace Malm&ouml;</a><span>&nbsp;(Malm&ouml;)</span><br><span></span><a href=\"http://www.aplace.com/sv/stores\">Aplace Norrlandsgatan</a><span>&nbsp;(Stockholm)</span></span><br><a href=\"http://www.caliroots.com/\" target=\"_blank\">Caliroots</a>&nbsp; (Stockholm)<br><a href=\"http://www.childstore.se/\" target=\"_blank\">Childstore</a> (Gothenburg)<br> <a href=\"https://plus.google.com/107418816429830660596/about?gl=SE&amp;hl=sv-SE&amp;ved=0CBUQzh0&amp;sa=X&amp;ei=oaoVU4qfLuKIzAOt3ILwCw\" target=\"_blank\">Childstore</a> (Stockholm)<br><a href=\"https://maps.google.com/maps?q=Nygatan+26,+%C3%96rebro,+Sweden&amp;hl=en&amp;ie=UTF8&amp;ll=59.27178,15.21173&amp;spn=0.011293,0.031972&amp;sll=59.270612,15.211698&amp;sspn=0.002845,0.007993&amp;oq=Nygatan+26,+%C3%B6re&amp;t=h&amp;hnear=Nygatan+26,+702+11+%C3%96rebro,+Sweden&amp;z=15&amp;iwloc=A\">Conspiracy With WeSC</a> (&Ouml;rebro)<br><a href=\"http://hatstore.se/\">Hatstore.se<br></a><a href=\"http://hlstore.com/\" target=\"_blank\">Highlights</a><span>&nbsp;(Stockholm)</span><br><a href=\"http://www.hollywood.se/\" target=\"_blank\">Hollywood.se</a><br><a href=\"http://www.hollywood.se/content/view/stores\" target=\"_blank\">Hollywood Norrlandsgatan</a> (Stockholm)<br><a href=\"http://www.hollywood.se/content/view/stores\" target=\"_blank\">Hollywood S&ouml;dra Larmgatan</a> (Gothenburg)<br><a href=\"http://www.junkyard.se/\" target=\"_blank\">Junkyard</a><br><a href=\"http://www.manky.se/\" target=\"_blank\">Manky.se</a><br><a href=\"http://nelly.com/\" target=\"_blank\">Nelly.com</a><br><a href=\"http://nlyman.com\" target=\"_blank\">NLY MAN</a><br><a href=\"http://www.paapi.se/\" target=\"_blank\">Paapi.se</a><br><a href=\"http://www.shelta.se/\" target=\"_blank\">Shelta</a> (Gothenburg)<br><a href=\"http://www.sneakersnstuff.com/\" target=\"_blank\">Sneakersnstuff</a> (Stockholm)<br><a href=\"http://www.sportifunlimited.se/\" target=\"_blank\">Sportif Unlimited</a> (Link&ouml;ping)<br><a href=\"http://www.streetlab.nu/\" target=\"_blank\">Street Lab</a> (Malm&ouml;)<br><a href=\"http://www.surfers.se\" target=\"_blank\">Surfers</a> (Varberg)<br><br>Czech Republic:<br><a href=\"http://www.queens.cz/\" target=\"_blank\">Queens</a><span>&nbsp;(Prague)</span><br><br>France:<br><a href=\"http://www.colette.fr/\" target=\"_blank\">Colette</a> (Paris)<br><a href=\"https://www.facebook.com/uptownmetz\" target=\"_blank\">Uptown</a><span>&nbsp;(Metz)</span><br><br>Germany:<span><br><a href=\"http://www.blaze-store.de/\" target=\"_blank\">Blaze</a><span>&nbsp;(Mannheim)</span></span><br><a href=\"http://www.animal-tracks.de/index.php\" target=\"_blank\">Animal Tracks</a> (Hamburg)<br><a href=\"http://www.inflammable.com/woyft/\" target=\"_blank\">Inflammable<br></a><a href=\"http://www.kickz.com/\" target=\"_blank\">Kickz</a><a href=\"http://www.inflammable.com/woyft/\" target=\"_blank\"><br><span></span></a><a href=\"http://www.kickz.com/de/stores\" target=\"_blank\">Kickz</a><span>&nbsp;(Hamburg)</span><a href=\"http://www.inflammable.com/woyft/\" target=\"_blank\"><br><span></span></a><a href=\"http://www.kickz.com/de/stores\" target=\"_blank\">Kickz</a>&nbsp;(Cologne)<br><span></span><a href=\"http://www.kickz.com/de/stores\" target=\"_blank\">Kickz</a><span>&nbsp;(Munich Stachus)</span><br><a href=\"https://www.facebook.com/NovacaneStoreBerlin\" target=\"_blank\">Novacane Store</a> (Berlin)<br><a href=\"http://www.overkillshop.com/\" target=\"_blank\">Overkill</a> (Berlin)<br><a href=\"http://www.snipes.com/\" target=\"_blank\">Snipes</a><br><br>Italy:<br><span><a href=\"http://www.baseshop.it/it/\" target=\"_blank\">Base</a>&nbsp;(Bolzano)<br></span><a href=\"http://www.big-one.it/\" target=\"_blank\">Big One</a> (Viadana)<br><span><a href=\"https://www.facebook.com/CroqueMonsieur.sm\" target=\"_blank\">Croque Monsieur</a>&nbsp;(Repubblica di San Marino)</span>\n"
                + "                                <div><span><a href=\"https://www.facebook.com/dablockshop\" target=\"_blank\">Da Block</a>&nbsp;(Roma)<br></span><a href=\"http://www.dusted.it/\" target=\"_blank\">Dusted</a> (Parma)<br><span><a href=\"https://plus.google.com/101562118214006158642/about?gl=se&amp;hl=en\" target=\"_blank\">Elle Stuff</a>&nbsp;(Carpi)</span><br><span><a href=\"http://hr500.blogspot.it/\" target=\"_blank\">Hero</a>&nbsp;(Bellaria Igea Marina)</span><br><span><a href=\"https://www.facebook.com/highlifestorepiacenza\" target=\"_blank\">Highlife</a>&nbsp;(Piacenza)</span></div>\n"
                + "                                <div><span><a href=\"http://www.iltsneakers.com/\" target=\"_blank\">I Love Tokyo</a>&nbsp;(Roma)</span><br><span><a href=\"http://www.sneakers-limitededition.com/sneakers.php\" target=\"_blank\">Limited Edition</a>&nbsp;(Bologna)</span><br><span><a href=\"http://www.maximrimini.com/\" target=\"_blank\">Maxim</a>&nbsp;(Rimini)</span><br><span><a href=\"https://www.facebook.com/pages/Mephisto-Street-Style/284403084949233\" target=\"_blank\">Mephisto</a>&nbsp;(Faenza)</span><br><span><a href=\"https://www.facebook.com/monroefiorano\" target=\"_blank\">Monroe</a>&nbsp;(Fiorano Modenese)</span></div>\n"
                + "                                <div>\n"
                + "                                    <span><a href=\"http://www.stayfoolishstore.com/\" target=\"_blank\">Stay Foolish</a>&nbsp;(Latina)</span><br><span><a href=\"http://www.tattersandco.com/\" target=\"_blank\">Tatters</a>&nbsp;(Riccione)</span><br><span><a href=\"http://www.westltdstore.it/\" target=\"_blank\">West ltd</a>&nbsp;(Reggio Emilia)</span><br>\n"
                + "                                    <div>\n"
                + "                                        <div><span>Wicked (Parma)</span></div>\n"
                + "                                    </div>\n"
                + "                                </div>\n"
                + "                                <br><span>The Netherlands:</span><br><span></span><a href=\"https://www.freshcotton.com\" target=\"_blank\">FreshCotton</a><br><br>Norway:<br><a href=\"http://www.grim.no/\" target=\"_blank\">Grim</a><span>&nbsp;(Oslo)</span><br><a href=\"http://www.highspeedsports.no/\" target=\"_blank\">High Speed Sports</a>&nbsp;(<span>&Aring;lesund)</span><br><a href=\"http://www.hollywood.no/\" target=\"_blank\">Hollywood.no</a><br><br><span>Poland:</span><br><span></span><a href=\"http://theavenue.pl/\" target=\"_blank\">The Avenue</a><br><br><span>UK:</span><br><span><a href=\"http://www.sneakersnstuff.com/en/content/view/sneakersnstuff-stores\" target=\"_blank\">Sneakersnstuff</a>&nbsp;(London)</span><br><br><span style=\"background-color: #ffffff; color: #000000;\">If you are a retailer interested in selling New Black, please email us at <a href=\"mailto:Andreas@newblack.se\" target=\"_blank\"><span style=\"text-decoration: underline;\">andreas@newblack.se<br></span></a><br></span><br><span style=\"font-size: x-small;\"></span>\n"
                + "                            </div>\n"
                + "                        </div>\n"
                + "                    </div>\n"
                + "                    <iframe class=\"mediaplayerMaxiMediaFileActive mediaplayerMaxiMediaFileActiveShopwindow\"\n"
                + "                            frameborder=\"0\" scrolling=\"no\"\n"
                + "                            style=\"width:1px;height:1px;\"\n"
                + "                            id=\"twsMediaplayerMaxiListenerIframeShopwindow\"\n"
                + "                            name=\"twsMediaplayerMaxiListenerIframeShopwindow\"\n"
                + "                            src=\"javascript:false;\"></iframe>\n"
                + "                    <SCRIPT type=\"text/javascript\" SRC=\"http://www.newblack.se/gadgets/mediaplayer-maxi/mediaplayer-maxi.js\"></SCRIPT><!-- Content ends here! -->\n"
                + "                </div>\n"
                + "            </div>\n"
                + "        </div>";
        Document doc = Jsoup.parseBodyFragment(html);
        Elements elm = doc.select("div.shopwindow_custom_text");
        assertEquals(1, elm.size());
//        int i = 0;
//        for (Element e : elm) {
//            System.out.println(i++ + " " + e.parent() );
//        }
        System.out.println(elm.first().childNodes());
        List<?> nd = elm.first().childNodes();
//        assertEquals("Czech Republic:", nd.get(1).toString());
        assertEquals("http://www.aplace.com/", elm.select("a[href]").attr("href"));
    }

    @Test
    @Ignore
    public void getTextBeforeANode() throws IOException {
        Response response = Jsoup.connect("http://www.newblack.se/en/pages/retailers.html")
                .ignoreContentType(true)
                .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                .referrer("http://www.google.com")
                .timeout(12000)
                .followRedirects(true)
                .execute();

        Document doc = response.parse();
//        Document doc = Jsoup.connect("http://www.newblack.se/en/pages/retailers.html").get();
        Elements elm = doc.select("div.shopwindow_custom_text");//doc.children();
        assertEquals(1, elm.size());
//        int i = 0;
//        for (Element e : elm) {
//            System.out.println(i++ + " " + e.childNodes());
//        }
//        System.out.println(elm.get(20).childNodes());
//        List<?> nd = elm.first().childNodes();
//        assertEquals("Czech Republic:", nd.get(1).toString());
        assertEquals("http://www.aplace.com/", elm.select("a[href]").attr("href"));
        assertEquals("Aplace", elm.select("a[href]").first().text());
        assertEquals("http://www.aplace.com/sv/stores", elm.select("a[href]").get(2).attr("href"));
        assertEquals("Aplace Malmö", elm.select("a[href]").get(2).text());
        //assertEquals("Aplace Malmö", elm.text().);
        //System.out.println(elm.);
        //System.out.println(elm.text());// + " " + e2.text());
//        int i = 1;
//        for (Element e2 : elm.select("a[href]")) {
//            if (i <= 23) {
//                System.out.println(e2.attr("href") + " " + e2.text() + " Sweden");
//            } else if (i == 24) {
//                System.out.println(e2.attr("href") + " " + e2.text() + " Czech Republic");
//            } else if (i <= 26) {
//                System.out.println(e2.attr("href") + " " + e2.text() + " France");
//            } else if (i <= 35) {
//                System.out.println(e2.attr("href") + " " + e2.text() + " Germany");
//            } else if (i <= 51) {
//                System.out.println(e2.attr("href") + " " + e2.text() + " Italy");
//            } else if (i <= 52) {
//                System.out.println(e2.attr("href") + " " + e2.text() + " The Netherlands");
//            } else if (i <= 54) {
//                System.out.println(e2.attr("href") + " " + e2.text() + " Norway");
//            } else if (i <= 56) {
//                System.out.println(e2.attr("href") + " " + e2.text() + " Poland");
//            } else if (i <= 57) {
//                System.out.println(e2.attr("href") + " " + e2.text() + " UK");
//            }
//            i++;
//        }
//        elm.select("a[href]").after(",");
        elm.select("a[href]").before(",");
//        elm.select("a[href]").remove();
//            System.out.println(elm);
        for (String s : elm.text().split(",")) {
            System.out.println((s.replace(")", "")).replace("(", ""));
//                String s2 = s1.replace("(","");
//                System.out.println(s1);
//            String [] s1 = (s.split("\\)"));
//            String [] s2 = s1[0].split("\\(");
//            for (String s3: s2) System.out.println(s2[0] + s2[1]);
        }
//        for (Element e : elm) {
//            System.out.println(e);
//        }
//        ManageGooglePlaces gp = new ManageGooglePlaces();
//        for (Element e2 : elm.select("a[href]")) {
//            System.out.println(e2.text() + " " + e2.attr("href"));
//            gp.getAGooglePlaceFromTheString(e2.text(), "http://www.newblack.se/en/pages/retailers.html", e2.attr("href"));
//        }
    }

    public static String br2nl(String html) {
        if (html == null) {
            return html;
        }
        Document document = Jsoup.parse(html);
        document.outputSettings(new Document.OutputSettings().prettyPrint(false));//makes html() preserve linebreaks and spacing
        document.select("br").append("\\n");
        document.select("p").prepend("\\n\\n");
        String s = document.html().replaceAll("\\\\n", "\n");
        return Jsoup.clean(s, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));
    }

    @Test
    @Ignore
    public void removeLineBreaksFromNewBlack() throws IOException {
        String html = "A<br>B<br>C";
        assertEquals("A\nB\nC", br2nl(html));
        Response response = Jsoup.connect("http://www.newblack.se/en/pages/retailers.html")
                .ignoreContentType(true)
                .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                .referrer("http://www.google.com")
                .timeout(12000)
                .followRedirects(true)
                .execute();

        Document doc = response.parse();
        doc.select("br").append(",");
//        System.out.println(doc);
        Elements elm = doc.select("div.shopwindow_custom_text");//doc.children();
        Elements hyperlinks = elm.select("a[href]");
        Iterator<Element> hypIterarot = hyperlinks.iterator();
        String country = null;
        String trimS;
        for (String s : elm.text().split(",")) {
            s = s.trim();
            System.out.println((s.replace(")", "")).replace("(", ""));
            if (s.contains(":")) {
                country = s.replace(":", "");
            } else if (!s.equals("")) {
                System.out.println(hypIterarot.next().attr("href") + " " + hypIterarot.next().ownText()/*.attr("href")*/ + " " + (s.replace(")", "")).replace("(", "") + " " + country);
            }
        }
//        System.out.println(elm.text());
    }
//    http://www.margauxlonnberg.com/retailers.php

    @Test
    @Ignore
    public void margauxlonnberg() throws IOException {
//        place 1 Asuna
//12 12 Arlington 1001C N Fillmore ST #135 Lynn Louisa
        Response response = Jsoup.connect("http://www.margauxlonnberg.com/retailers.php")
                .ignoreContentType(true)
                .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                .referrer("http://www.google.com")
                .timeout(12000)
                .followRedirects(true)
                .execute();

        Document doc = response.parse();
//        System.out.println(doc.select("div#retailers_wrapper >ul > li > ul"));
        Elements cities = doc.select("div#retailers_wrapper >ul > li > ul");
        assertEquals("Paris", cities.get(0).select("li.city_header > span").first().text());
        assertEquals("3e arrondissement hold up", cities.get(0).select("li.retailer").first().text());
        ManageGooglePlaces gp = new ManageGooglePlaces();
        int i = 0;
        int j = 0;
        Iterator<Element> it = cities.iterator();
        for (int k = 0; k < 22; k++) {
//setup so it will break Arlington 1001C N Fillmore ST #135 Lynn Louisa 
//            {
//   "error_message" : "This service requires an API key.",
//   "html_attributions" : [],
//   "results" : [],
//   "status" : "REQUEST_DENIED"
//}
            it.next();
        }
        while (it.hasNext()) {
            i++;

            Element city = it.next();
            Elements retailers = city.select("li.retailer");
            for (Element retailer : retailers) {
                j++;
                System.out.println(i + " " + j + " " + city.select("li.city_header > span").text() + " " + retailer.text());
                gp.getAGooglePlaceFromTheString(city.select("li.city_header > span").text() + " " + retailer.text(), "http://www.margauxlonnberg.com/retailers.php", "");
            }
        }
    }
//        System.out.println(cities.get(0).select("li.retailer").text());

    @Test
    @Ignore
    public void handleZeroResults() {
        //modified the source to return null and not an exception
        GooglePlaces client = new GooglePlaces("AIzaSyAbA_omv6dng-yrsMc7RwNVT2Fh9gqoook ");
        String name = "Online http://www.luxe-urbain.com/index.cfm Luxe urbain";
        List<Place> places = client.getPlacesByQuery(name, GooglePlaces.MAXIMUM_RESULTS);
        assertEquals(0, places.size());
        ManageGooglePlaces gp = new ManageGooglePlaces();
        gp.getAGooglePlaceFromTheString(name, "http://www.margauxlonnberg.com/retailers.php", "");
    }

    @Test
    @Ignore
    public void preFetchBrandNameFromTable() {
        int offset = 0;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ShopsPU");
        EntityManager em = emf.createEntityManager();
        List<Brandinfo> models;
        while ((models = em.createQuery("from Brandinfo m where m.country = 'UK' ", Brandinfo.class).
                setFirstResult(offset).
                setMaxResults(100).
                getResultList()).size() > 0) {
            em.getTransaction().begin();
            for (Brandinfo model : models) {
                Logger.getLogger(NewEmptyJUnitTest.class.getName()).log(Level.INFO, "do something with model: " + model.getBrandname());
            }

            em.flush();
            em.clear();
            em.getTransaction().commit();
            offset += models.size();
        }
    }
//http://stackoverflow.com/questions/5067619/jpa-what-is-the-proper-pattern-for-iterating-over-large-result-sets

    @Test
    public void getAddresseFromHenryChrist() throws IOException {
        String brandUrl = "http://henry-christ.com/hc/can-be-found-here/?lang=en";
        Response response = Jsoup.connect(brandUrl)
                .ignoreContentType(true)
                .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                .referrer("http://www.google.com")
                .timeout(12000)
                .followRedirects(true)
                .execute();

        Document doc = response.parse();
        Elements countries = doc.select("h3[id]");
        assertEquals(18, countries.size());
//        for (Element country : countries) {
//            System.out.println(country.attr("id"));
//        }
        Elements shopsincountries = doc.select("h3~div > ul, h3~ul");
        assertEquals(18, shopsincountries.size());
//        for (Element shopsincountry : shopsincountries) {
//            Elements shops = shopsincountry.select("li");
//            for (Element shop : shops) {
//                System.out.println(shop.text());
//            }
//        }
//        for (Element country : countries){
        ManageGooglePlaces gp = new ManageGooglePlaces();
        for (int s = 6; s < countries.size(); s++) {
//            System.out.println(countries.get(s).text());// + " " + shopsincountries.get(s));
            Elements shops = (shopsincountries.get(s)).select("li");
            for (int c = 0; c < shops.size(); c++) {//Element shop : shops) {
                Matcher matcher = android.util.Patterns.WEB_URL.matcher(shops.get(c).text());
                String url = "";
                while (matcher.find()) {
                    url = matcher.group();//gauranteed to be just one
                }
                Logger.getLogger(NewEmptyJUnitTest.class.getName()).log(Level.INFO, "{0} {1}", new Object[]{countries.get(s).attr("id"), shops.get(c).text().replace(url, "")});
//                System.out.println(countries.get(s).attr("id") + " " + shop.text().replace(url, "") + "url " + url);
                gp.getAGooglePlaceFromTheString(countries.get(s).attr("id") + " " + shops.get(c).text().replace(url, ""), brandUrl, url);
            }
        }
    }

    @Test
    public void findUrl() {
        String url = "                            <li>\n"
                + "                                Rudolf Alpine Fashion<br />\n"
                + "                                Promenada 33<br />\n"
                + "                                7018 Flims-Waldhaus<br />\n"
                + "                                www.rudolf-alpinefashion.ch\n"
                + "                            </li>\n"
                + "                            <li>\n"
                + "                                Lorenz Bach<br />\n"
                + "                                Hauptstrasse 2<br />\n"
                + "                                3780 Gstaad\n"
                + "                            </li>\n"
                + "                            <li>\n"
                + "                                Ahead Fashion<br />\n"
                + "                                Alte Bahnhofsstr. 9<br />\n"
                + "                                7250 Klosters\n"
                + "                            </li>\n"
                + "                            <li>\n"
                + "                                Pesko<br />\n"
                + "                                Voa principala 56<br />\n"
                + "                                7078 Lenzerheide<br />\n"
                + "                                www.pesko.ch\n"
                + "                            </li>\n"
                + "                            <li>\n"
                + "                                Rive Gauche<br />\n"
                + "                                Werchlaubengässli 14<br />\n"
                + "                                6004 Luzern<br />\n"
                + "                                www.maisondeboer.ch\n"
                + "                            </li>\n"
                + "                            <li>\n"
                + "                                Kaufhaus Krone<br />\n"
                + "                                Solothurnerstr. 8<br />\n"
                + "                                4600 Olten<br />\n"
                + "                                www.kaufhauskrone.ch\n"
                + "                            </li>\n"
                + "                            <li>\n"
                + "                                get2gether<br />\n"
                + "                                Kluggasse 5<br />\n"
                + "                                8640 Rapperswil<br />\n"
                + "                                www.get2gether.ch\n"
                + "                            </li>";

//        return emails;
        Matcher matcher = android.util.Patterns.WEB_URL.matcher(url);
        assertEquals(false, matcher.matches());

        while (matcher.find()) {
            System.out.println(matcher.group());
//            logger.info( addr);
//            emails.add(addr);
        }
    }

    @Test
    @Ignore //20151012
    public void getAddresseFromRawFashion() throws IOException {
        String brandUrl = "http://raw-fashion.com/butiki/";
        Response response = Jsoup.connect(brandUrl)
                .ignoreContentType(true)
                .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                .referrer("http://www.google.com")
                .timeout(12000)
                .followRedirects(true)
                .execute();

        Document doc = response.parse();
        Elements countries = doc.select("div.row.container.divided");// > div.small-12.large-3.columns");
        assertEquals(6, countries.size());
//        System.out.println(countries);
        ManageGooglePlaces gp = new ManageGooglePlaces();
        for (int c = 0; c < countries.size(); c++) {
            Elements shopsandcountries = countries.get(c).select("p.thin-font"); //div.small-12.large-3.columns div.column-inner  a[href]
//            System.out.println(shopsandcountries.first().text());
            for (int s = 1; s < shopsandcountries.size(); s++) {
//                if ((shopsandcountries.first().text() + " " + shopsandcountries.get(s).text() + " " + shopsandcountries.get(s).attr("href")).length() > 12) {
//                    System.out.println((shopsandcountries.first().text() + " " + shopsandcountries.get(s).text() + " " + shopsandcountries.get(s).attr("href")).length());
                Logger.getLogger(NewEmptyJUnitTest.class.getName()).log(Level.INFO, "{0} {1} {2}",
                        new Object[]{shopsandcountries.first().text(), shopsandcountries.get(s).text(), shopsandcountries.get(s).attr("href")});
//                System.out.println((shopsandcountries.first().text() + " " + shopsandcountries.get(s).text() + " " + shopsandcountries.get(s).attr("href")));
                gp.getAGooglePlaceFromTheString(shopsandcountries.first().text() + " " + shopsandcountries.get(s).text() + " " + shopsandcountries.get(s).attr("href"), brandUrl, "");
//                }
            }
        }
    }

    @Test
    @Ignore //20151212
    public void getAddresseFromKirobyKim() throws IOException {
        String brandUrl = "http://www.kirobykim.com/p/store-locator.html";
        Response response = Jsoup.connect(brandUrl)
                .ignoreContentType(true)
                .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                .referrer("http://www.google.com")
                .timeout(12000)
                .followRedirects(true)
                .execute();

        Document doc = response.parse();
        Elements countries = doc.select("div span[style*=font-family],b[style*=font-family] ");// > div.small-12.large-3.columns");
        assertEquals(57, countries.size());
        //assertTrue(countries.contains(Collections.singleton("<span style=\"font-family: Trebuchet MS, sans-serif;\"><br></span>")));
        countries.removeAll(Collections.singleton(new Element(Tag.valueOf("span"), "").attr("style", "font-family: Trebuchet MS, sans-serif;").html("<br>")));
        countries.removeAll(Collections.singleton(new Element(Tag.valueOf("span"), "").attr("style", "font-family: Trebuchet MS, sans-serif;")));
        countries.removeAll(Collections.singleton(new Element(Tag.valueOf("span"), "").attr("style", "font-family: 'Trebuchet MS', sans-serif; text-align: left;").html("<br>")));
        ManageGooglePlaces gp = new ManageGooglePlaces();
        for (int c = 40; c < countries.size() - 1; c = c + 1) {
//            if (!countries.get(c).text().trim().equals(""))
//            System.out.println(c + " " + countries.get(c));
            System.out.println(c + " " + countries.get(39).text().trim().replace(":", "") + " " + countries.get(c).text().trim() + " " + countries.get(c + 1).text().trim());
            gp.getAGooglePlaceFromTheString(countries.get(39).text().trim().replace(":", "") + " " + countries.get(c).text().trim() + " " + countries.get(c + 1).text().trim(), brandUrl, "");
        }
    }

    @Test
    public void testRemoveAllUsingCollectionSingleton() {
        String init[] = {"One", "<span style=\"font-family: Trebuchet MS, sans-serif;\"><br></span>", "Three", "One", "<span style=\"font-family: Trebuchet MS, sans-serif;\"><br></span>", "Three"};

        // create two lists
        List list1 = new ArrayList(Arrays.asList(init));
        List list2 = new ArrayList(Arrays.asList(init));

        // remove from list1
        list1.remove("One");
        assertTrue(list1.contains("One"));
//        System.out.println("List1 value: " + list1);

        // remove from list2 using singleton
        list2.removeAll(Collections.singleton("One"));
        list2.removeAll(Collections.singleton("<span style=\"font-family: Trebuchet MS, sans-serif;\"><br></span>"));
        assertFalse(list2.contains("One"));
        assertFalse(list2.contains("<span style=\"font-family: Trebuchet MS, sans-serif;\"><br></span>"));
//        System.out.println("The SingletonList is :" + list2);
        Element e1 = new Element(Tag.valueOf("span"), "");
        e1.attr("style", "font-family: Trebuchet MS, sans-serif;");
        e1.html("name of shop");
        Element e2 = new Element(Tag.valueOf("span"), "");
        e2.attr("style", "font-family: Trebuchet MS, sans-serif;");
        e2.html("<br>");
        System.out.println(e1);
        Elements es = new Elements(e1, e2);
        assertEquals(true, es.contains(e1));
        assertEquals(true, es.contains(e2));
        es.removeAll(Collections.singleton(e1));
        assertEquals(false, es.contains(e1));
        assertEquals(true, es.contains(e2));
    }

    @Test
    @Ignore
    public void getAddressesFromMajesticFilature() throws IOException {
        String brandUrl = "http://www.majesticfilatures.com/se/en/magasins";
        Response response = Jsoup.connect(brandUrl)
                .ignoreContentType(true)
                .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                .referrer("http://www.google.com")
                .timeout(12000)
                .followRedirects(true)
                .execute();

        Document doc = response.parse();
        Elements stores = doc.select("div.column ul.stores-list li.column-one-third ");// > div.small-12.large-3.columns");
        assertEquals(289, stores.size());
        String searchText;
        ManageGooglePlaces gp = new ManageGooglePlaces();
        for (int s = 283; s < stores.size(); s++) {
            searchText = stores.get(s).select("span").text();
            System.out.println(s + " " + searchText);
            gp.getAGooglePlaceFromTheString(searchText, brandUrl, "");
        }
    }

    @Test
    @Ignore
    public void getAddressesFromDrapeauNoir() throws IOException {
        String brandUrl = "http://drapeau-noir.fr/stockists/";
        Response response = Jsoup.connect(brandUrl)
                .ignoreContentType(true)
                .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                .referrer("http://www.google.com")
                .timeout(12000)
                .followRedirects(true)
                .execute();

        Document doc = response.parse();
        Elements stores = doc.select("div.o-stockists > div.o-stockist");// > div.small-12.large-3.columns");
        assertEquals(15, stores.size());
//        System.out.println(stores);
        String searchText;
//        Elements urls = stores.select("div.o-stockist > a[href]");
//        System.out.println(urls);
        ManageGooglePlaces gp = new ManageGooglePlaces();
        for (int s = 0; s < stores.size(); s++) {
            searchText = stores.get(s).select("div.o-stockist__name, div.o-stockist__address").text();
//            url = stores.get(s).select("href").text();
            System.out.println(s + " " + searchText + " " + stores.get(s).select("a[href]").html());// + " " + urls.get(s).html());
            gp.getAGooglePlaceFromTheString(searchText, brandUrl, stores.get(s).select("a[href]").html());
        }
    }

    @Test
    @Ignore
    public void getAddressesFromSchai() throws IOException {
        String brandUrl = "http://schaischai.com/pages/stores";
        Response response = Jsoup.connect(brandUrl)
                .ignoreContentType(true)
                .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                .referrer("http://www.google.com")
                .timeout(12000)
                .followRedirects(true)
                .execute();

        Document doc = response.parse();
        Elements stores = doc.select("div.small-12 p");// > div.small-12.large-3.columns");
        assertEquals(13, stores.size());
        String searchText;
//        Elements urls = stores.select("div.o-stockist > a[href]");
        System.out.println(stores);
        ManageGooglePlaces gp = new ManageGooglePlaces();
        for (int s = 0; s < stores.size(); s++) {
            searchText = stores.get(s).text();//.select("div.o-stockist__name, div.o-stockist__address").text();
//            url = stores.get(s).select("href").text();
            System.out.println(s + " " + searchText.replace("|", "") + " " + stores.get(s).select("a[href]").attr("href"));// + " " + urls.get(s).html());
            gp.getAGooglePlaceFromTheString(searchText.replace("|", ""), brandUrl, stores.get(s).select("a[href]").attr("href"));
        }
    }

    @Test
    public void getAddressesFromCluusa() throws IOException {
        String brandUrl = "http://www.cluusa.com/stockist/";
        Response response = Jsoup.connect(brandUrl)
                .ignoreContentType(true)
                .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                .referrer("http://www.google.com")
                .timeout(12000)
                .followRedirects(true)
                .execute();

        Document doc = response.parse();
//                System.out.println(doc);
        Elements regions = doc.select("div.BlockContent > div.panelcollapsed");// > div.small-12.large-3.columns");
//        assertEquals(3, regions.size());

//        Elements urls = stores.select("div.o-stockist > a[href]");
//        System.out.println(countriesandstore);
        ManageGooglePlaces gp = new ManageGooglePlaces();

        //searchText = countriesandstore.get(s).text;//.select("div.o-stockist__name, div.o-stockist__address").text();
//            url = stores.get(s).select("href").text();
        Elements continents = regions.get(1).select("div.panelcollapsed h3, div.panelcollapsed div.panelcontent");
//        assertEquals(56, continents.size());
//        for (int s = 2; s < 16; s += 2) {
////            System.out.println(s + " " + continents.get(s).text());
//            for (String store : continents.get(s + 1).html().split("<br>")) {
//                String searchText = Jsoup.parse(store).text() + " " + continents.get(s).text();
//                System.out.println(s + " " + searchText);
//                gp.getAGooglePlaceFromTheString(searchText, brandUrl, "");
//            }
////            
//        }
//        for (int s = 18; s < 36; s += 2) {
////            System.out.println(s + " " + continents.get(s).text());
//            for (String store : continents.get(s + 1).html().split("<br>")) {
//                String searchText = Jsoup.parse(store).text() + " " + continents.get(s).text();
//                System.out.println(s + " " + searchText);
//                gp.getAGooglePlaceFromTheString(searchText, brandUrl, "");
//            }
//        }
//
//        for (int s = 38; s < 48; s += 2) {
////            System.out.println(s + " " + continents.get(s).text());
//            for (String store : continents.get(s + 1).html().split("<br>")) {
//                String searchText = Jsoup.parse(store).text() + " " + continents.get(s).text();
//                System.out.println(s + " " + searchText);
//                gp.getAGooglePlaceFromTheString(searchText, brandUrl, "");
//            }
//        }
//
//        for (int s = 50; s < 56; s += 2) {
////            System.out.println(s + " " + continents.get(s).text());
//            for (String store : continents.get(s + 1).html().split("<br>")) {
//                String searchText = Jsoup.parse(store).text() + " " + continents.get(s).text();
//                System.out.println(s + " " + searchText);
//                gp.getAGooglePlaceFromTheString(searchText, brandUrl, "");
//            }
//        }
//
//        continents = regions.get(0).select("div.panelcollapsed h3, div.panelcollapsed div.panelcontent");
//        assertEquals(26, continents.size());
//        for (int s = 2; s < 26; s += 2) {
////            System.out.println(s + " " + continents.get(s).text());
//            for (String store : continents.get(s + 1).html().split("<br>")) {
//                String searchText = Jsoup.parse(store).text() + " " + continents.get(s).text();
//                System.out.println(s + " " + searchText);
//                gp.getAGooglePlaceFromTheString(searchText, brandUrl, "");
//            }
//        }

        continents = regions.get(2).select("div.panelcollapsed h3, div.panelcontent a");
        assertEquals(9, continents.size());
        for (int s =0; s < 9; s++) {
            System.out.println(s + " " + continents.get(s).attr("href"));

        }

    }
}
