package crawling;

import crawling.interfaces.ILinkExtractor;
import crawling.interfaces.IPageRetriever;
import crawling.interfaces.IPageTracker;
import crawling.process.CrawlingJob;
import crawling.product.IProductExtractor;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


public class CrawlingJobRepositoryTest {

    @Mock
    private ILinkExtractor linkExtractor;

    @Mock
    private IPageRetriever pageRetriever;

    @Mock
    private IPageTracker pageTracker;

    @Mock
    private IProductExtractor productExtractor;

    private CrawlingJobRepository crawlingJobRepository;

    @Before
    public void setup() {
        crawlingJobRepository = new CrawlingJobRepository();
    }

    @Test
    public void shouldSaveANewJob() {
        CrawlingJob job = new CrawlingJob(linkExtractor, pageRetriever, pageTracker, productExtractor);
        crawlingJobRepository.save(job);

        assertThat(crawlingJobRepository.getById(job.jobId), equalTo(job));
        assertThat(crawlingJobRepository.getAll().size(), equalTo(1));
    }

    @Test
    public void getById() {
        CrawlingJob job1 = new CrawlingJob(linkExtractor, pageRetriever, pageTracker, productExtractor);
        CrawlingJob job2 = new CrawlingJob(linkExtractor, pageRetriever, pageTracker, productExtractor);
        CrawlingJob job3 = new CrawlingJob(linkExtractor, pageRetriever, pageTracker, productExtractor);

        crawlingJobRepository.save(job1);
        crawlingJobRepository.save(job2);
        crawlingJobRepository.save(job3);


        assertThat(crawlingJobRepository.getById(job1.jobId), equalTo(job1));
        assertThat(crawlingJobRepository.getById(job2.jobId), equalTo(job2));
        assertThat(crawlingJobRepository.getById(job3.jobId), equalTo(job3));

        assertThat(crawlingJobRepository.getAll().size(), equalTo(3));
    }
}