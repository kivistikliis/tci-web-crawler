package crawling.trackers;

import crawling.interfaces.IPageTracker;

import java.util.*;

public class BfsPageTracker implements IPageTracker {

    private List<String> pagesToScan;
    private int nextPage = 0;
    private int maxdepth = 0;

    public BfsPageTracker() {
        pagesToScan = new ArrayList<>();
    }

    private List<String> filterPages(List<String> pages) {
        //TODO: make prettier using Liis method
        Set<String> hs1 = new HashSet<>();
        hs1.addAll(pages);
        hs1.remove(null);
        pagesToScan.addAll(hs1);

        Set<String> hs2 = new HashSet<>();
        hs2.addAll(pagesToScan);
        pagesToScan.clear();
        pagesToScan.addAll(hs2);

        return pagesToScan;
    }

    @Override
    public void addPages(List<String> pages) {
        if(pagesToScan.isEmpty()) {
            int sizeBeforeFiltering = pagesToScan.size();
            int sizeAfterFiltering = filterPages(pages).size();

            if(sizeAfterFiltering>sizeBeforeFiltering) {
                pagesToScan.add(null);
                maxdepth++;
            }
        } else if(hasNext()) {
            if (pagesToScan.get(nextPage + 1) != null) {
                filterPages(pages);
            }
        }
    }

    @Override
    public int getMaximumScanDepth() {
        return maxdepth;
    }

    @Override
    public String getNext() {
        nextPage++;
        return pagesToScan.get(nextPage - 1);
    }

    @Override
    public List<String> getScannedPages() {
        return null;
    }

    @Override
    public Boolean hasNext() {
        if (nextPage + 1 <= pagesToScan.size())
            return true;
        return false;
    }
}
