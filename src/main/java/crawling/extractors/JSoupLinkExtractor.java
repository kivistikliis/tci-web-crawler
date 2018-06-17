package crawling.extractors;

import crawling.interfaces.ILinkExtractor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Implementation of ILinkExtractor
 */
public class JSoupLinkExtractor implements ILinkExtractor {
    /**
     * Extracts all links from the given HTML
     * @param page HTML string of given page
     * @param baseURL baseUrl needed to complete links that only represent a relative path
     * @return String array of links on the given page
     */
    @Override
    public String[] extractLinks(String page, String baseURL) {
        Document doc = Jsoup.parse(page);
        Elements elements = doc.getElementsByTag("a");

        String[] links = new String[elements.size()];
        for (int i = 0 ; i < elements.size() ; i ++){

            String linkReference = elements.get(i).attr("href");
            if(!linkReference.startsWith("http")){

                if(linkReference.charAt(0)=='/')
                    linkReference = linkReference.substring(1); // to avoid double / in url

                if(baseURL.endsWith("/"))
                    baseURL = baseURL.substring(0, baseURL.length()-1); // to avoid double / in url

                linkReference = baseURL + "/" + linkReference; // to complete all links
            }
            links[i] = linkReference;
        }

        return links;
    }
}
