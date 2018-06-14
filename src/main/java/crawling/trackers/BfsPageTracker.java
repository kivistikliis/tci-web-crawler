package crawling.trackers;

import crawling.interfaces.IPageTracker;

import java.util.List;

public class BfsPageTracker implements IPageTracker {
    @Override
    public void addPages(List<String> pages) {

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
        return null;
    }
}
