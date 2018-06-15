package crawling.interfaces;

import java.util.List;

public interface IPageTracker {
    void addPages(List<String> pages);
    int getMaximumScanDepth();
    String getNext();
    List<String> getScannedPages();
    Boolean hasNext();
}
