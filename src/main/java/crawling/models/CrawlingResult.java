package crawling.models;


public class CrawlingResult {
    public CrawlingResult(Object retrievedData, int crawlingJobID) {
        this.retrievedData = retrievedData;
        this.crawlingJobID = crawlingJobID;
    }

    public CrawlingResult(){}

    private Object retrievedData;
    private int crawlingJobID;

    // Getters are required, so the Spring Framework is able to retrieve the data (under the hood)
    public Object getRetrievedData() {
        return retrievedData;
    }

    public int getCrawlingJobID() {
        return crawlingJobID;
    }

}
