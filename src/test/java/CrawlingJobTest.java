import crawling.extractors.JSoupLinkExtractor;
import crawling.extractors.JSoupPageRetriever;
import crawling.interfaces.ILinkExtractor;
import crawling.interfaces.IPageRetriever;
import crawling.interfaces.IPageTracker;
import crawling.models.CrawlingResult;
import crawling.process.CrawlingJob;
import crawling.product.Album;
import crawling.product.IProductExtractor;
import crawling.product.StandardProductExtractor;
import crawling.trackers.BfsPageTracker;
import crawling.trackers.DfsPageTracker;
import org.junit.Test;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class CrawlingJobTest {

    JSoupPageRetriever pageRetriever = new JSoupPageRetriever();

    @Test(expected = Exception.class)
    public void startShouldThrowExceptionWhenPageRetrieverUrlIsIncorrect() throws Exception{
        CrawlingJob crawlingJob = new CrawlingJob(new JSoupLinkExtractor(), new JSoupPageRetriever(), new BfsPageTracker(), new StandardProductExtractor());
        crawlingJob.start("http://imaginarypage.page", null);
    }

    @Test
    public void startShouldCrawlTheSiteSuccessfullyIfBaseURLisCorrect() throws Exception{
        try{
            // this is fix to be able to catch ConnectException from localhost in CircleCI
            pageRetriever.getHTMLPage("http://localhost:81");

            CrawlingJob crawlingJob = new CrawlingJob(new JSoupLinkExtractor(), new JSoupPageRetriever(), new BfsPageTracker(), new StandardProductExtractor());
            crawlingJob.start("http://localhost:81", null);

            assertNotNull(crawlingJob.crawlingJobData);
            assertNotNull(crawlingJob.crawlingResult);
        }catch (ConnectException e){
            e.printStackTrace();
        }


    }

    @Test
    public void shouldReturnSingleObjectAsResultWhenProductNameIsSpecified() throws Exception{
        try{
            // this is fix to be able to catch ConnectException from localhost in CircleCI
            pageRetriever.getHTMLPage("http://localhost:81");

            CrawlingJob crawlingJob = new CrawlingJob(new JSoupLinkExtractor(), new JSoupPageRetriever(), new BfsPageTracker(), new StandardProductExtractor());
            crawlingJob.start("http://localhost:81", "Elvis Forever");

            Object searchResult = crawlingJob.crawlingResult.getRetrievedData();
            assertThat(searchResult, instanceOf(Album.class));
        }catch (ConnectException e){
            e.printStackTrace();
        }


    }

    @Test
    public void shouldReturnCollectionAsResultWhenProductNameNotSpecified() throws Exception{
        try{
            // this is fix to be able to catch ConnectException from localhost in CircleCI
            pageRetriever.getHTMLPage("http://localhost:81");

            CrawlingJob crawlingJob = new CrawlingJob(new JSoupLinkExtractor(), new JSoupPageRetriever(), new BfsPageTracker(), new StandardProductExtractor());
            crawlingJob.start("http://localhost:81", null);

            Object searchResult = crawlingJob.crawlingResult.getRetrievedData();
            assertThat(searchResult, instanceOf(Collection.class));
        }catch (ConnectException e){
            e.printStackTrace();
        }


    }

    @Test
    public void shouldReturnEmptyAsResultWhenProductNameSpecifiedButNotFound() throws Exception{
        try{
            // this is fix to be able to catch ConnectException from localhost in CircleCI
            pageRetriever.getHTMLPage("http://localhost:81");

            CrawlingJob crawlingJob = new CrawlingJob(new JSoupLinkExtractor(), new JSoupPageRetriever(), new BfsPageTracker(), new StandardProductExtractor());
            crawlingJob.start("http://localhost:81", "I do not exist");

            Object searchResult = crawlingJob.crawlingResult.getRetrievedData();
            assertTrue(searchResult.equals(""));
        }catch (ConnectException e){
            e.printStackTrace();
        }


    }

    @Test
    public void shouldReturnCorrectAlgorithmUsedForAJobWithBFS() throws Exception{
        try{
            // this is fix to be able to catch ConnectException from localhost in CircleCI
            pageRetriever.getHTMLPage("http://localhost:81");

            CrawlingJob crawlingJob = new CrawlingJob(new JSoupLinkExtractor(), new JSoupPageRetriever(), new BfsPageTracker(), new StandardProductExtractor());
            crawlingJob.start("http://localhost:81", null);

            String expectedAlgorithm = "bfs";
            String actualAlgorithm = crawlingJob.crawlingJobData.getAlgorithm();
            assertTrue(expectedAlgorithm.equalsIgnoreCase(actualAlgorithm));
        }catch (ConnectException e){
            e.printStackTrace();
        }


    }

    @Test
    public void shouldReturnCorrectAlgorithmUsedForAJobWithDFS() throws Exception{
        try{
            // this is fix to be able to catch ConnectException from localhost in CircleCI
            pageRetriever.getHTMLPage("http://localhost:81");

            CrawlingJob crawlingJob = new CrawlingJob(new JSoupLinkExtractor(), new JSoupPageRetriever(), new DfsPageTracker(), new StandardProductExtractor());
            crawlingJob.start("http://localhost:81", null);

            String expectedAlgorithm = "dfs";
            String actualAlgorithm = crawlingJob.crawlingJobData.getAlgorithm();
            assertTrue(expectedAlgorithm.equalsIgnoreCase(actualAlgorithm));
        }catch (ConnectException e){
            e.printStackTrace();
        }


    }


}