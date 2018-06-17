package api.models.response;

public class CrawlingResult {
    public CrawlingResult(String retrievedData, int crawlingJobID) {
        this.retrievedData = retrievedData;
        this.crawlingJobID = crawlingJobID;
    }

    private String retrievedData;

    private int crawlingJobID;

    // Getters are required, so the Spring Framework is able to retrieve the data (under the hood)
    public String getRetrievedData() {
        return retrievedData;
    }

    public int getCrawlingJobID() {
        return crawlingJobID;
    }

}