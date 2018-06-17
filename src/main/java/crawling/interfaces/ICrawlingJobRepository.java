package crawling.interfaces;

import crawling.process.CrawlingJob;

public interface ICrawlingJobRepository {
    void save(CrawlingJob job);
    CrawlingJob getById(int id);
}
