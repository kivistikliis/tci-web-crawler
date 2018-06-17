import com.fasterxml.jackson.annotation.JsonTypeInfo;
import crawling.extractors.JSoupPageRetriever;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.jsoup.Jsoup;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.UnknownHostException;

@RunWith(JUnitParamsRunner.class)
public class JSoupPageRetrieverTest {

    JSoupPageRetriever pageRetriever = new JSoupPageRetriever();

    @Test
    @Parameters({
            "https://www.google.com/, Google" ,
            "https://example.com/, Example Domain",
            "https://www.youtube.com/, YouTube"
    })

    public void pageRetrieverReturnsCorrectTitle(String url, String expectedTitle) throws Exception{
        String htmlString = pageRetriever.getHTMLPage(url);
        String title = Jsoup.parse(htmlString).title();

        Assert.assertEquals(expectedTitle, title);
    }


    @Test
    public void pageRetrieverReturnsCorrectHTML() throws Exception{
        String actualHTMLString = pageRetriever.getHTMLPage("https://volunteero-events.herokuapp.com/");
        String expectedString = "<html> <head></head> <body>  Hello, welcome! Event-service is up and ready </body></html>";

        Assert.assertEquals(expectedString, actualHTMLString.replace("\n", ""));
    }


    @Test(expected = IllegalArgumentException.class)
    public void pageRetrieverThrowsExceptionWhenURLisNull() throws Exception{
        pageRetriever.getHTMLPage(null);
    }


    @Test(expected = UnknownHostException.class)
    @Parameters({
            "https://page.page/" ,
            "https://pagemypage.el/",
    })
    public void pageRetrieverThrowsExceptionWhenURLisInvalid(String wrongURL) throws Exception{
        pageRetriever.getHTMLPage(wrongURL);
    }


    @Test(expected = IllegalArgumentException.class)
    public void pageRetrieverThrowsExceptionWhenURLisEmpty() throws Exception{
        pageRetriever.getHTMLPage("");
    }


}
