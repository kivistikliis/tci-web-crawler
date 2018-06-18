package api.models;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class CrawlingRequestTest {

    @Test
    @Parameters({"www.website1.com", "www.website2.com", "www.website3.com"})
    public void shouldAssignAddress(String url) {
        CrawlingRequest crawlingRequest = new CrawlingRequest();

        crawlingRequest.setBaseAddress(url);

        assertThat(crawlingRequest.getBaseAddress()).isEqualTo(url);
    }
}
