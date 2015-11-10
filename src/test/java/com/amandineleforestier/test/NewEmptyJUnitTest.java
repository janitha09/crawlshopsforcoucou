package com.amandineleforestier.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.amandineleforestier.crawlshopsforcoucou.BasicCrawler;
import com.amandineleforestier.crawlshopsforcoucou.ManageGooglePlaces;
import com.amandineleforestier.model.Brandinfo;
import com.amandineleforestier.model.Shops;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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

    public NewEmptyJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    @Ignore
    public void hello() {
        CrawlConfig config = new CrawlConfig();

        config.setCrawlStorageFolder("C:\\Users\\janitha\\Documents\\NetBeansProjects\\crawlshopsforcoucou\\target\\interim");

        /*
         * Be polite: Make sure that we don't send more than 1 request per
         * second (1000 milliseconds between requests).
         */
        config.setPolitenessDelay(1000);

        /*
         * You can set the maximum crawl depth here. The default value is -1 for
         * unlimited depth
         */
        config.setMaxDepthOfCrawling(2);

        /*
         * You can set the maximum number of pages to crawl. The default value
         * is -1 for unlimited number of pages
         */
        config.setMaxPagesToFetch(1000);

        /**
         * Do you want crawler4j to crawl also binary data ? example: the
         * contents of pdf, or the metadata of images etc
         */
        config.setIncludeBinaryContentInCrawling(false);

        /*
         * Do you need to set a proxy? If so, you can use:
         * config.setProxyHost("proxyserver.example.com");
         * config.setProxyPort(8080);
         *
         * If your proxy also needs authentication:
         * config.setProxyUsername(username); config.getProxyPassword(password);
         */

 /*
         * This config parameter can be used to set your crawl to be resumable
         * (meaning that you can resume the crawl from a previously
         * interrupted/crashed crawl). Note: if you enable resuming feature and
         * want to start a fresh crawl, you need to delete the contents of
         * rootFolder manually.
         */
        config.setResumableCrawling(false);

        /*
         * Instantiate the controller for this crawl.
         */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = null;
        try {
            controller = new CrawlController(config, pageFetcher, robotstxtServer);
            /*
             * For each crawl, you need to add some seed urls. These are the first
             * URLs that are fetched and then the crawler starts following links
             * which are found in these pages
             */
            controller.addSeed("http://christian-metzner.com/en/");
//            controller.addSeed("https://www.alfredogonzales.com");
            //controller.addSeed("http://www.ics.uci.edu/~welling/");

            /*
             * Start the crawl. This is a blocking operation, meaning that your code
             * will reach the line after this only when crawling is finished.
             */
            controller.start(BasicCrawler.class, 1);
        } catch (Exception ex) {
            Logger.getLogger(NewEmptyJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
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
        GooglePlaces client = new GooglePlaces("AIzaSyAbA_omv6dng-yrsMc7RwNVT2Fh9gqoook ");
        String name = "Empire State Building";
        Place detailedEmpireStateBuilding = getPlaceDetails(client, name);
        assertEquals(name, detailedEmpireStateBuilding.getName());
        assertEquals("350 5th Ave, New York, NY 10118, United States", detailedEmpireStateBuilding.getAddress());
        assertEquals("(212) 736-3100", detailedEmpireStateBuilding.getPhoneNumber());
        assertEquals("http://www.esbnyc.com/", detailedEmpireStateBuilding.getWebsite());
        assertEquals("point_of_interest", detailedEmpireStateBuilding.getTypes().get(0));
        assertEquals("https://maps.google.com/?cid=15074921902713971043", detailedEmpireStateBuilding.getGoogleUrl());
    }

    private Place getPlaceDetails(GooglePlaces client, String name) {
        List<Place> places = client.getPlacesByQuery(name, GooglePlaces.MAXIMUM_RESULTS);
        Place empireStateBuilding = null;
        for (Place place : places) {
            if (place.getName().equals(name)) {
                empireStateBuilding = place;
                break;
            }
        }
        Place detailedEmpireStateBuilding = empireStateBuilding.getDetails();
        return detailedEmpireStateBuilding;
    }

    @Test
//    @Ignore
    public void parsePiolaWebPage() throws IOException {
//        http://www.piola.fr/en/revendeurs
        String html
                = "    <div class=\"Revendeur\" data-pays=\"Switzerland\" data-id=\"59\">\n"
                + "        <div class=\"Revendeur-wrapVisuel\">\n"
                + "            <div class=\"Revendeur-visuel\" data-original=\"/img/revendeurs/59.jpg\" style=\"background-image: url('http://www.piola.fr/themes/piolav2/img/piolav2/default.png');\"></div>\n"
                + "        </div>\n"
                + "        <div class=\"Revendeur-content\">\n"
                + "            <h3 class=\"Revendeur-title\">Tête à clack</h3>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "    <div class=\"Revendeur\" data-pays=\"Switzerland\" data-id=\"61\">\n"
                + "        <div class=\"Revendeur-wrapVisuel\">\n"
                + "            <div class=\"Revendeur-visuel\" data-original=\"/img/revendeurs/61.jpg\" style=\"background-image: url('http://www.piola.fr/themes/piolav2/img/piolav2/default.png');\"></div>\n"
                + "        </div>\n"
                + "        <div class=\"Revendeur-content\">\n"
                + "            <h3 class=\"Revendeur-title\">Supremo</h3>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "    <div class=\"Revendeur\" data-pays=\"Germany\" data-id=\"62\">\n"
                + "        <div class=\"Revendeur-wrapVisuel\">\n"
                + "            <div class=\"Revendeur-visuel\" data-original=\"/img/revendeurs/62.jpg\" style=\"background-image: url('http://www.piola.fr/themes/piolav2/img/piolav2/default.png');\"></div>\n"
                + "        </div>\n"
                + "        <div class=\"Revendeur-content\">\n"
                + "            <h3 class=\"Revendeur-title\">Anchor</h3>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "    <div class=\"Revendeur\" data-pays=\"Germany\" data-id=\"63\">\n"
                + "        <div class=\"Revendeur-wrapVisuel\">\n"
                + "            <div class=\"Revendeur-visuel\" data-original=\"/img/revendeurs/63.jpg\" style=\"background-image: url('http://www.piola.fr/themes/piolav2/img/piolav2/default.png');\"></div>\n"
                + "        </div>\n"
                + "        <div class=\"Revendeur-content\">\n"
                + "            <h3 class=\"Revendeur-title\">Bubeundkonig </h3>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "    <div class=\"Revendeur\" data-pays=\"Germany\" data-id=\"64\">\n"
                + "        <div class=\"Revendeur-wrapVisuel\">\n"
                + "            <div class=\"Revendeur-visuel\" data-original=\"/img/revendeurs/64.jpg\" style=\"background-image: url('http://www.piola.fr/themes/piolav2/img/piolav2/default.png');\"></div>\n"
                + "        </div>\n"
                + "        <div class=\"Revendeur-content\">\n"
                + "            <h3 class=\"Revendeur-title\">H Hardy</h3>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "    <div class=\"Revendeur\" data-pays=\"Germany\" data-id=\"65\">\n"
                + "        <div class=\"Revendeur-wrapVisuel\">\n"
                + "            <div class=\"Revendeur-visuel\" data-original=\"/img/revendeurs/65.jpg\" style=\"background-image: url('http://www.piola.fr/themes/piolav2/img/piolav2/default.png');\"></div>\n"
                + "        </div>\n"
                + "        <div class=\"Revendeur-content\">\n"
                + "            <h3 class=\"Revendeur-title\">HEIMAT DESIGN</h3>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "    <div class=\"Revendeur\" data-pays=\"Germany\" data-id=\"66\">\n"
                + "        <div class=\"Revendeur-wrapVisuel\">\n"
                + "            <div class=\"Revendeur-visuel\" data-original=\"/img/revendeurs/66.jpg\" style=\"background-image: url('http://www.piola.fr/themes/piolav2/img/piolav2/default.png');\"></div>\n"
                + "        </div>\n"
                + "        <div class=\"Revendeur-content\">\n"
                + "            <h3 class=\"Revendeur-title\">Sleeping dogs </h3>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "    <div class=\"Revendeur\" data-pays=\"Germany\" data-id=\"67\">\n"
                + "        <div class=\"Revendeur-wrapVisuel\">\n"
                + "            <div class=\"Revendeur-visuel\" data-original=\"/img/revendeurs/67.jpg\" style=\"background-image: url('http://www.piola.fr/themes/piolav2/img/piolav2/default.png');\"></div>\n"
                + "        </div>\n"
                + "        <div class=\"Revendeur-content\">\n"
                + "            <h3 class=\"Revendeur-title\">Vau </h3>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "    <div class=\"Revendeur\" data-pays=\"Belgium\" data-id=\"70\">\n"
                + "        <div class=\"Revendeur-wrapVisuel\">\n"
                + "            <div class=\"Revendeur-visuel\" data-original=\"/img/revendeurs/70.jpg\" style=\"background-image: url('http://www.piola.fr/themes/piolav2/img/piolav2/default.png');\"></div>\n"
                + "        </div>\n"
                + "        <div class=\"Revendeur-content\">\n"
                + "            <h3 class=\"Revendeur-title\">For Brussels</h3>\n"
                + "        </div>\n"
                + "    </div>";

        Document doc = Jsoup.parse(html);
//        Document doc = Jsoup.connect("http://www.piola.fr/en/revendeurs").get();
        Elements brandInfo = doc.select("div.Revendeur");

        ManageGooglePlaces gp = new ManageGooglePlaces();
        for (Element e : brandInfo) {
//            System.out.println(e.child(1).select("div.Revendeur-content > h3").text() + " " + e.dataset().values().toArray()[0]);//e.child(1));

            gp.getAGooglePlaceFromTheString(e.child(1).select("div.Revendeur-content > h3").text() + " " + e.dataset().values().toArray()[0], "http://www.piola.fr/en/revendeurs");
        }
    }
//http://unionmadegoods.com/

    @Test
    @Ignore
    public void flagURLIfItContainsANameOfCityOrCountry() {
//        where to buy
//        stockists
    }

}
