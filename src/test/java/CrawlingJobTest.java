import crawling.interfaces.ILinkExtractor;
import crawling.interfaces.IPageRetriever;
import crawling.interfaces.IPageTracker;
import crawling.interfaces.IProductExtractor;
import crawling.process.CrawlingJob;
import org.junit.Test;

import java.net.ConnectException;
import java.net.UnknownHostException;

import static org.junit.Assert.*;

public class CrawlingJobTest {

    @Test
    public void startShouldReturnValidObjectWhenNoExceptionWasThrown() {

        ILinkExtractor linkExtractor = null;
        IPageRetriever pageRetriever = null;
        IPageTracker pageTracker = null;
        IProductExtractor productExtractor = null;

        CrawlingJob crawlingJob = new CrawlingJob(linkExtractor, pageRetriever, pageTracker, productExtractor);


    }

    @Test(expected = IllegalArgumentException.class)
    public void startShouldThrowExceptionWhenPageRetrieverUrlIsIncorrect() {

    }

    @Test(expected = UnknownHostException.class)
    public void startShouldThrowExceptionWhenPageRetrieverUrlIsUnreachable() {

    }



}