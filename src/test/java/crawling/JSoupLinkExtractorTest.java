package crawling;

import crawling.extractors.JSoupLinkExtractor;
import crawling.extractors.JSoupPageRetriever;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.util.StringUtils;
import java.net.ConnectException;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class JSoupLinkExtractorTest{
    JSoupLinkExtractor linkExtractor = new JSoupLinkExtractor();
    JSoupPageRetriever pageRetriever = new JSoupPageRetriever();

    @Test
    @Parameters({
            "https://example.com/, 1",
            "http://localhost:81/, 11",
            "http://localhost:81/catalog.php, 19"
    })
    public void linkExtractorReturnsCorrectAmountOfLinks(String url, Integer nrOfLinks) throws Exception{
        try {
            String[] links = linkExtractor.extractLinks(pageRetriever.getHTMLPage(url), url);
            assertThat(links.length, is(nrOfLinks));
        } catch (ConnectException e) {
            // to catch localhost not running on CI
            e.printStackTrace();
        }
    }

    @Test
    @Parameters({
            "http://localhost:81/",
            "http://localhost:81/catalog.php"
    })
    public void linkExtractorReturnsLinksAsComplete(String url) throws Exception {
        try {
            String[] links = linkExtractor.extractLinks(pageRetriever.getHTMLPage(url), url);

            boolean allComplete = true;
            for (String link : links) {
                if (!link.startsWith("http"))
                    allComplete = false;

                if(StringUtils.countOccurrencesOf(link, "//") != 1)
                    allComplete = false;
            }

            assertThat(allComplete, is(true));
        } catch (ConnectException e) {
            // to catch localhost not running with CI
            e.printStackTrace();
        }
    }

    @Test
    public void linkExtractorDoesNotThrowExceptionOnNonHTMLString(){
            String[] links = linkExtractor.extractLinks("I am not a proper HTML", "");
            assertThat(links.length, is(0));
    }
}
