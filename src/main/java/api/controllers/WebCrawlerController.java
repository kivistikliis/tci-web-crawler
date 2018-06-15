package api.controllers;

import api.response.models.CrawlingResponse;
import crawling.CrawlingManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/web-crawler")
public class WebCrawlerController {
    private class CrawlingRequest {
        private String baseAddress;

        String getBaseAddress() {
            return baseAddress;
        }

        public void setBaseAddress(String baseAddress) {
            this.baseAddress = baseAddress;
        }
    }

    @PostMapping("/crawl/{name}")
    public ResponseEntity crawlProducts(@PathVariable("name") String name, @RequestBody CrawlingRequest crawlingRequest) {
        CrawlingResponse crawlingResponse;

        // Retrieve the base address
        String baseAddress = crawlingRequest.getBaseAddress();

        // Start new crawling manager
        CrawlingManager crawlingManager = new CrawlingManager(baseAddress);

        // If it is called without a path variable, crawl all, otherwise by name
        if (name != null) {
            crawlingResponse = crawlingManager.crawlProductsByName(name);
        } else {
            crawlingResponse = crawlingManager.crawlAllProducts();
        }

        // Check if all crawling executed correctly and send a response
        if (crawlingResponse == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else {
            return ResponseEntity.ok(crawlingResponse);
        }
    }


    @RequestMapping("/home")
    public String home() {
        return "Let's crawl the web guys!";
    }
}


