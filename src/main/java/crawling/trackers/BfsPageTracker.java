package crawling.trackers;

import crawling.interfaces.IPageTracker;

import java.util.ArrayList;
import java.util.List;

public class BfsPageTracker implements IPageTracker {

    private List<String> pagesToScan;
    private int nextPage = 0;

    public BfsPageTracker() {
        pagesToScan = new ArrayList<>();
    }

    @Override
    public void addPages(List<String> pages) {
        pagesToScan.addAll(pages);
    }

    @Override
    public int getMaximumScanDepth() {
        return 0;
    }

    @Override
    public String getNext() {
        return null;
    }

    @Override
    public List<String> getScannedPages() {
        return null;
    }

    @Override
    public Boolean hasNext() {
        if(nextPage+1 <= pagesToScan.size())
            return true;
        return false;
    }
}
