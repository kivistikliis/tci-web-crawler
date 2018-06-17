import crawling.interfaces.ILinkExtractor;
import crawling.interfaces.IPageRetriever;
import crawling.interfaces.IPageTracker;
import crawling.models.CrawlingResult;
import crawling.process.CrawlingJob;
import crawling.product.IProductExtractor;
import org.junit.Test;

import java.net.UnknownHostException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class CrawlingJobTest {

    @Test
    public void startShouldReturnValidObjectWhenNoExceptionWasThrown() {

        // Arrange
        ILinkExtractor mockedLinkExtractor = mock(ILinkExtractor.class);
        IPageRetriever mockedPageRetriever = mock(IPageRetriever.class);
        IPageTracker mockedPageTracker = mock(IPageTracker.class);
        IProductExtractor mockedProductExtractor = mock(IProductExtractor.class);

        CrawlingJob crawlingJob = new CrawlingJob(mockedLinkExtractor, mockedPageRetriever, mockedPageTracker, mockedProductExtractor);
        // Act
        CrawlingResult crawlingResult = crawlingJob.start();

        // Assert
        // Do some assertions, checking if the result is valid
        assertFalse(crawlingResult.getRetrievedData().equals(""));
        assertTrue(crawlingResult.getCrawlingJobID() > 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void startShouldThrowExceptionWhenPageRetrieverUrlIsIncorrect() {
        // TODO When interface implementations are merged
    }

    @Test(expected = UnknownHostException.class)
    public void startShouldThrowExceptionWhenPageRetrieverUrlIsUnreachable() {
        // TODO When interface implementations are merged

    }


}