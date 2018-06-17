import crawling.CrawlingApp;
import crawling.models.CrawlingResult;
import crawling.process.CrawlingJob;
import org.junit.Test;

import static org.mockito.Mockito.*;


/**
 * A valid CrawlingResult object that I referer to in the comments and method names is a an object that has a valid job id and JSON string that is not empty
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

        // Work on the verification
        CrawlingApp crawlingApp = mock(CrawlingApp.class);

        crawlingApp.crawlAllProducts();

        verify(crawlingApp).crawlAllProducts();
    }

    // crawlProductsByName() method tests
    @Test
    public void crawlProductsByNameShouldReturnValidCrawlingResultObjectWhenProductWasFound() {

    }


    @Test
    public void crawlProductsByNameShouldReturnCrawlingResultObjectWithEmptyJsonDataWhenProductsWereNotFound() {


    }

    // getCrawlingJobDataByID() method tests
    @Test
    public void getCrawlingJobDataByIDShouldReturnValidCrawlingJobDataResult() {

    }

    @Test
    public void getCrawlingJobDataByIDShouldReturnNullWhenJobIdWasNotFound() {

    }
}