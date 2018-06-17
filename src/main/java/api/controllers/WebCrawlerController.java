package api.controllers;


import api.models.CrawlingRequest;
import com.google.gson.Gson;
import crawling.models.CrawlingJobData;
import crawling.models.CrawlingResult;
import crawling.CrawlingApp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/web-crawler")
public class WebCrawlerController {

    @RequestMapping("")
    public String home() {
        return "Let's crawl the web guys!";
    }

    Gson json = new Gson();

    @PostMapping({"/crawl/{name}", "/crawl"})
    public ResponseEntity crawlProducts(@PathVariable("name") Optional<String> name, @RequestBody CrawlingRequest crawlingRequest) {

        CrawlingResult crawlingResult;

        // Retrieve the base address
        String baseAddress = crawlingRequest.getBaseAddress();

        // Start new crawling manager
        CrawlingApp crawlingApp = new CrawlingApp(baseAddress);

        // If it is called without a path variable, crawl all, otherwise by name
        if (name.isPresent()) {
            crawlingResult = crawlingApp.crawlProductsByName(name.get());
        } else {
            crawlingResult = crawlingApp.crawlAllProducts();
        }

        // Check if all crawling executed correctly and send a response
        if (crawlingResult == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else {
            return ResponseEntity.ok(json.toJson(crawlingResult));
        }
    }

    @GetMapping({"/crawling-jobs/{id}",})
    public ResponseEntity getCrawlingJobDataById(@PathVariable("id") int id) {
        // Start new crawling manager
        CrawlingApp crawlingApp = new CrawlingApp();

        CrawlingJobData crawlingJobData = crawlingApp.getCrawlingJobDataByID(id);

        // Check if all crawling executed correctly and send a response
        if (crawlingJobData == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else {
            return ResponseEntity.ok(crawlingJobData);
        }
    }
}


