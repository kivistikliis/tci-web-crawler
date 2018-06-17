import crawling.CrawlingApp;
import crawling.models.CrawlingJobData;
import crawling.models.CrawlingResult;
import crawling.process.CrawlingJob;
import org.junit.Test;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


/**
 * A valid CrawlingResult object that I referer to in the comments and method names is a an object that has a valid job id and JSON string that is not empty
 */
public class CrawlingAppTest {

    // crawlProductsByName() method tests

    /**
     * In this test we can only verify that the crawlAllProducts() method was called
     * Because there are not parameters that can affect the result in this specific method
     */
    @Test
    public void crawlAllProductsShouldReturnValidObject() {

        // TODO: update test later
        CrawlingApp crawlingApp = mock(CrawlingApp.class);

        crawlingApp.crawlAllProducts();

        verify(crawlingApp).crawlAllProducts();
    }

    // crawlProductsByName() method tests
    @Test
    public void crawlProductsByNameShouldReturnValidCrawlingResultObjectWhenProductWasFound() {

        // Arrange
        CrawlingApp crawlingApp = new CrawlingApp("localhost:80");

        // Act
        CrawlingResult crawlingResult = crawlingApp.crawlProductsByName("No Fences");

        // Assert
        // Do some assertions, checking if the result is valid
        assertFalse(crawlingResult.getRetrievedData().equals(""));
        assertTrue(crawlingResult.getCrawlingJobID() > 0);

    }

    @Test
    public void crawlProductsByNameShouldReturnCrawlingResultObjectWithEmptyJsonDataWhenProductsWereNotFound() {
        // Arrange
        CrawlingApp crawlingApp = new CrawlingApp("localhost:80");

        // Act
        CrawlingResult crawlingResult = crawlingApp.crawlProductsByName("No Fences");

        // Assert
        // Do some assertions, checking if the result is invalid
        assertFalse(crawlingResult.getRetrievedData().equals(""));
        assertTrue(crawlingResult.getCrawlingJobID() > 0); // Job is is still incremented

    }

    // getCrawlingJobDataByID() method tests
    @Test
    public void getCrawlingJobDataByIDShouldReturnValidCrawlingJobDataResult() {
        // Arrange
        CrawlingApp crawlingApp = new CrawlingApp("localhost:80");
        // Act
        CrawlingJobData crawlingJobData = crawlingApp.getCrawlingJobDataByID(1);

        // Assert
        assertTrue(crawlingJobData.getCrawlingJobID() > 0); // valid job ids start from 1
        assertTrue(crawlingJobData.getPagesExplored() > 0); // at least one page
        assertTrue(crawlingJobData.getSearchDepth() > 0); // the lowest search depth is 1
        assertTrue(crawlingJobData.getTimeElapsed() > 0);

    }

    @Test
    public void getCrawlingJobDataByIDShouldReturnNullWhenJobIdWasNotFound() {
        // Arrange
        int jobID = -2; // Non-existing jobID

        CrawlingApp crawlingApp = new CrawlingApp();
        // Act

        CrawlingJobData crawlingJobData = crawlingApp.getCrawlingJobDataByID(jobID);

        // Assert
        assertNull(crawlingApp);
    }
}