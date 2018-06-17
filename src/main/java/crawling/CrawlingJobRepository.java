package crawling;

import crawling.interfaces.ICrawlingJobRepository;
import crawling.process.CrawlingJob;

import java.util.ArrayList;
import java.util.List;

public class CrawlingJobRepository implements ICrawlingJobRepository {

    private List<CrawlingJob> crawlingJobList;

    public CrawlingJobRepository() {
        crawlingJobList = new ArrayList<>();
    }

    @Override
    public void save(CrawlingJob job) {
        crawlingJobList.add(job);
    }

    @Override
    public CrawlingJob getById(int id) {
        for (CrawlingJob job : crawlingJobList) {
            if (job.jobId == id)
                return job;
        }
        return null;
    }

    public List<CrawlingJob> getAll() {
        return crawlingJobList;
    }
}
