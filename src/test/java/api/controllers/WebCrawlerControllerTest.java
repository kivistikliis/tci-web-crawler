package api.controllers;

import api.models.CrawlingRequest;
import crawling.models.CrawlingResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebCrawlerControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldReturnHomePageTextMessage() {
        String body = this.restTemplate.getForObject("/web-crawler", String.class);
        assertThat(body).isEqualTo("Let's crawl the web guys!");
    }

    @Test
    public void shouldSuccessfullyCrawlAWebsite() {
        CrawlingRequest crawlingRequest = new CrawlingRequest();
        crawlingRequest.setBaseAddress("https://volunteero-events.herokuapp.com");
        crawlingRequest.setAlgorithm("bfs");

        ResponseEntity<CrawlingResult> response =
                restTemplate.postForEntity("/web-crawler/crawl", crawlingRequest, CrawlingResult.class);

        response.getStatusCode();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void shouldBeNullIfTheUrlAddressIsWrong() {
        CrawlingRequest crawlingRequest = new CrawlingRequest();
        crawlingRequest.setBaseAddress("www.page.page");
        crawlingRequest.setAlgorithm("bfs");

        ResponseEntity<CrawlingResult> response =
                restTemplate.postForEntity("/web-crawler/crawl",
                        crawlingRequest, CrawlingResult.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isEqualTo(null);
    }

    @Test
    public void shouldBeNullIfThereIsNotCrawlingJobToReturn() {
        ResponseEntity<String> response =
                this.restTemplate.getForEntity("/web-crawler/crawling-jobs/1", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isEqualTo(null);
    }
}
