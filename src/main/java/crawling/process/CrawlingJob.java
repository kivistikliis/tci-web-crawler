package crawling.process;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import crawling.interfaces.ILinkExtractor;
import crawling.interfaces.IPageRetriever;
import crawling.interfaces.IPageTracker;
import crawling.models.CrawlingJobData;
import crawling.models.CrawlingResult;
import crawling.product.IProductExtractor;
import crawling.product.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CrawlingJob {
    static int idCounter;
    int jobId;
    Gson json = new Gson();
    public CrawlingResult crawlingResult;
    public CrawlingJobData crawlingJobData;

    public CrawlingJob(ILinkExtractor linkExtractor, IPageRetriever pageRetriever, IPageTracker pageTracker, IProductExtractor productExtractor) {
        this.linkExtractor = linkExtractor;
        this.pageRetriever = pageRetriever;
        this.pageTracker = pageTracker;
        this.productExtractor = productExtractor;
        this.jobId = idCounter++;
    }

    private ILinkExtractor linkExtractor;
    private IPageRetriever pageRetriever;
    private IPageTracker pageTracker;
    private IProductExtractor productExtractor;


    public void start(String baseAddress, String searchName) {

        long startTimeMs = System.currentTimeMillis();

        // initialize pageTracker with beginning url
        pageTracker.addPages(Arrays.asList(baseAddress));

        // to gather all products
        List<Product> allProducts = new ArrayList<>();

        // loop through BFSTracker
        while (pageTracker.hasNext()) {
            try{
                String addressToAnalyse = pageTracker.getNext();

                if(addressToAnalyse.contains("facebook") || addressToAnalyse.contains("twitter"))
                    continue;

                // get HTML content of the given address
                String HTMLString = pageRetriever.getHTMLPage(addressToAnalyse);

                // get all the products
                ArrayList<Product> products = productExtractor.extractProducts(HTMLString);

                // get all the links and send to pageTracker
                String[] links = linkExtractor.extractLinks(HTMLString, addressToAnalyse);
                pageTracker.addPages(Arrays.asList(links));

                // store found product
                allProducts.addAll(products);

                if(searchName!=null){
                    if(products.get(0).getName().toLowerCase().equals(searchName.toLowerCase())){
                        finalize(startTimeMs, json.toJson(products.get(0)));
                        return;
                    }
                }
            }
            catch (Exception e){
                // let the crawling continue, skip the invalid addresses
                e.printStackTrace();
            }
        }
        finalize(startTimeMs, json.toJson(allProducts));
    }

    private void finalize(long startTimeMs, String jsonResults){
        long endTimeMs = System.currentTimeMillis();
        int pagesExplored = pageTracker.getScannedPages().size();
        int maxDepth = pageTracker.getMaximumScanDepth();
        this.crawlingJobData = new CrawlingJobData(jobId, (int)(endTimeMs-startTimeMs), pagesExplored, maxDepth);
        this.crawlingResult = new CrawlingResult(jsonResults, jobId);
    }

}
