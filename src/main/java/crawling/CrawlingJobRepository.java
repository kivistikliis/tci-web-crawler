package crawling;

import crawling.process.CrawlingJob;

import java.util.ArrayList;
import java.util.List;

public final class CrawlingJobRepository {

    private static List<CrawlingJob> crawlingJobList;

    public CrawlingJobRepository() {
        crawlingJobList = new ArrayList<>();
    }


    public static void save(CrawlingJob job) {
        crawlingJobList.add(job);
    }


    public static CrawlingJob getById(int id) {
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
