package crawling.trackers;

import crawling.interfaces.IPageTracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DfsPageTracker implements IPageTracker {

    private ArrayList<String> pagesStack;
    private HashMap<String, Boolean> areViewedPages;
    private HashMap<String, Integer> levels;
    private int maxScanDepth = 0;

    public DfsPageTracker() {

        this.pagesStack = new ArrayList<>();
        this.areViewedPages = new HashMap<>();
        this.levels = new HashMap<>();
    }

    @Override
    public void addPages(List<String> pages) {

        this.maxScanDepth++;
        pages.forEach(page -> {

            if (!this.isPagedTracked(page)) {

                this.trackPage(page);
            }
        });
    }

    private boolean isPagedTracked(String page) {

        Boolean isPageViewed = this.areViewedPages.get(page);
        Boolean isPageQueued = this.pagesStack.contains(page);
        if(isPageQueued){

            return true;
        }else{

            return isPageViewed != null && isPageViewed;
        }
    }

    private void trackPage(String page) {

        this.pagesStack.add(page);
        this.areViewedPages.put(page, false);
    }

    @Override
    public int getMaximumScanDepth() {

        return this.maxScanDepth;
    }

    @Override
    public String getNext() {

        if (this.hasNext()) {

            Integer lastIndex = this.pagesStack.size() - 1;
            String nextPage = this.pagesStack.get(lastIndex);
            this.pagesStack.remove(nextPage);
            this.areViewedPages.put(nextPage, true);
            return nextPage;
        }
        return null;
    }

    @Override
    public List<String> getScannedPages() {

        ArrayList<String> scannedPages = new ArrayList<>();

        this.areViewedPages.forEach((key, value) -> {
            if (value) {

                scannedPages.add(key);
            }
        });
        return scannedPages;
    }

    @Override
    public Boolean hasNext() {

        return this.pagesStack.size() > 0;
    }
}
