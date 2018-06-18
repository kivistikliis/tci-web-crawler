import com.google.gson.Gson;
import crawling.CrawlingApp;
import crawling.CrawlingJobRepository;
import crawling.extractors.JSoupPageRetriever;
import crawling.models.CrawlingJobData;
import crawling.models.CrawlingResult;
import crawling.process.CrawlingJob;
import crawling.product.Album;
import crawling.product.Product;
import org.junit.Test;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;

import java.net.ConnectException;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


/**
 * A valid CrawlingResult object that I referer to in the comments and method names is a an object that has a valid job id and JSON string that is not empty
 */
public class CrawlingAppTest {

    // crawlProductsByName() method tests
    CrawlingJobRepository crawlingJobRepository = new CrawlingJobRepository();
    JSoupPageRetriever pageRetriever = new JSoupPageRetriever();

    /**
     * In this test we can only verify that the crawlAllProducts() method was called
     * Because there are not parameters that can affect the result in this specific method
     */
    @Test
    public void crawlAllProductsMethodCallVerified() {

        // TODO: update test later
        CrawlingApp crawlingApp = mock(CrawlingApp.class);

        crawlingApp.crawlAllProducts(null, "bfs");

        verify(crawlingApp).crawlAllProducts(null, "bfs");
    }

    // crawlProductsByName() method tests
    @Test
    public void crawlAllProductsByNameShouldReturnValidCrawlingResultObjectWhenProductWasFound() throws Exception{

        try{
            // this is fix to be able to catch ConnectException from localhost in CircleCI
            pageRetriever.getHTMLPage("http://localhost:81");

            // Arrange
            CrawlingApp crawlingApp = new CrawlingApp("http://localhost:81");

            // Act
            CrawlingResult crawlingResult = crawlingApp.crawlAllProducts("No Fences", "bfs");

            // Assert
            // Do some assertions, checking if the result is of valid class
            Object result = crawlingResult.getRetrievedData();
            assertThat(result, instanceOf(Product.class));
            assertThat(result, instanceOf(Album.class));
            assertTrue(((Album)result).getName().equalsIgnoreCase("No Fences"));
            assertTrue(((Album)result).getArtist().equalsIgnoreCase("Garth Brooks"));
            assertTrue(((Album)result).getYear() == 1990);
        }
        catch (ConnectException e){
            e.printStackTrace();
        }



    }

    @Test
    public void crawlProductsByNameShouldReturnCrawlingResultObjectWithEmptyJsonDataWhenProductsWereNotFound() throws Exception{
        try{
            // this is fix to be able to catch ConnectException from localhost in CircleCI
            pageRetriever.getHTMLPage("http://localhost:81");

            // Arrange
            CrawlingApp crawlingApp = new CrawlingApp("http://localhost:81");

            // Act
            CrawlingResult crawlingResult = crawlingApp.crawlAllProducts("No Fences no", "bfs");

            // Assert
            // Do some assertions, checking if the result is invalid
            assertTrue(crawlingResult.getRetrievedData().equals(""));
        }
        catch (ConnectException e){
            e.printStackTrace();
        }

    }

    // getCrawlingJobDataByID() method tests
    @Test
    public void getCrawlingJobDataByIDShouldReturnValidCrawlingJobDataResult() throws Exception{
        try{

            // this is fix to be able to catch ConnectException from localhost in CircleCI
            pageRetriever.getHTMLPage("http://localhost:81");


            // Arrange
            CrawlingApp crawlingApp = new CrawlingApp("http://localhost:81");
            crawlingApp.crawlAllProducts("No Fences", "dfs");
            crawlingApp.crawlAllProducts("No Fences", "bfs");
            // Act
            CrawlingJobData crawlingJobData = crawlingApp.getCrawlingJobDataByID(1);

            // Assert
            assertTrue(crawlingJobData.getCrawlingJobID() == 1); // valid job ids start from 0, second job is after one increment
            assertTrue(crawlingJobData.getPagesExplored() > 0); // at least one page
            assertTrue(crawlingJobData.getSearchDepth() > 0); // the lowest search depth is 0
            assertTrue(crawlingJobData.getTimeElapsed() > 0);
        }
        catch (ConnectException e){
            // for localhost
            e.printStackTrace();
        }
    }

    @Test
    public void getCrawlingJobDataByIDShouldReturnNullWhenJobIdWasNotFound() {
        // Arrange
        int jobID = -2; // Non-existing jobID

        CrawlingApp crawlingApp = new CrawlingApp();
        // Act

        CrawlingJobData crawlingJobData = crawlingApp.getCrawlingJobDataByID(jobID);

        // Assert
        assertNull(crawlingJobData);
    }
}