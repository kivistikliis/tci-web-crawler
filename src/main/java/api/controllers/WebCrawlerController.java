package api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web-crawler")
public class WebCrawlerController {

    @RequestMapping("/home")
    public String home() {
        return "Let's crawl the web guys!";
    }
}
