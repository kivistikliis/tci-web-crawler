package crawling.models;

public class CrawlingJobData  {

    public CrawlingJobData(int crawlingJobID, int timeElapsed, int pagesExplored, int searchDepth, String algorithm) {
        this.crawlingJobID = crawlingJobID;
        this.timeElapsed = timeElapsed;
        this.pagesExplored = pagesExplored;
        this.searchDepth = searchDepth;
        this.algorithm = algorithm;
    }

    private int crawlingJobID;
    private int timeElapsed;
    private int pagesExplored;
    private int searchDepth;
    private String algorithm;

    // Getters are required, so the Spring Framework is able to retrieve the data (under the hood)
    public int getCrawlingJobID() {
        return crawlingJobID;
    }

    public int getTimeElapsed() {
        return timeElapsed;
    }

    public int getPagesExplored() {
        return pagesExplored;
    }

    public int getSearchDepth() {
        return searchDepth;
    }
    public String getAlgorithm() {
        return algorithm;
    }
}
