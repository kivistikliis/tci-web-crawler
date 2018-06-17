package api.controllers;

import api.models.request.CrawlingRequest;
import api.models.response.CrawlingJobData;
import api.models.response.CrawlingResult;
import crawling.CrawlingManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/web-crawler")
public class WebCrawlerController {


    @PostMapping({"/crawl/{name}", "/crawl"})
    public ResponseEntity crawlProducts(@PathVariable("name") Optional<String> name, @RequestBody CrawlingRequest crawlingRequest) {

        CrawlingResult crawlingResult;

        // Retrieve the base address
        String baseAddress = crawlingRequest.getBaseAddress();

        // Start new crawling manager
        CrawlingManager crawlingManager = new CrawlingManager(baseAddress);

        // If it is called without a path variable, crawl all, otherwise by name
        if (name.isPresent()) {
            crawlingResult = crawlingManager.crawlProductsByName(name.get());
        } else {
            crawlingResult = crawlingManager.crawlAllProducts();
        }

        // Check if all crawling executed correctly and send a response
        if (crawlingResult == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else {
            return ResponseEntity.ok(crawlingResult);
        }
    }

    @GetMapping({"/crawling-jobs/{id}",})
    public ResponseEntity getCrawlingJobDataByID(@PathVariable("id") int id) {
        // Start new crawling manager
        CrawlingManager crawlingManager = new CrawlingManager();

        CrawlingJobData crawlingJobData = crawlingManager.getCrawlingJobDataByID(id);

        // Check if all crawling executed correctly and send a response
        if (crawlingJobData == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else {
            return ResponseEntity.ok(crawlingJobData);
        }
    }


    @RequestMapping("/home")
    public String home() {
        return "Let's crawl the web guys!";
    }
}


