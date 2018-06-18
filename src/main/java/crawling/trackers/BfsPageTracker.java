package crawling.trackers;

import crawling.interfaces.IPageTracker;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;


public class BfsPageTracker implements IPageTracker {

    private List<String> pagesToScan;
    private int nextPage = -1;
    private int maxdepth = 0;

    public BfsPageTracker() {
        pagesToScan = new ArrayList<>();
    }

    private void filterAddedPages(List<String> pages) {
        List<String> pagesWithoutNulls = new ArrayList<>(pages);
        pagesWithoutNulls.removeAll(Collections.singleton(null));

        List<String> pagesWithoutDuplicates = pagesWithoutNulls.stream().distinct().collect(Collectors.toList());
        filterPagesToScan(pagesWithoutDuplicates);
    }

    private void filterPagesToScan(List<String> pagesWithoutNulls) {
        Collection<String> uniqueLinks = CollectionUtils.removeAll(pagesWithoutNulls, pagesToScan);
        pagesToScan.addAll(uniqueLinks);
    }

    @Override
    public void addPages(List<String> pages) {
        if(pagesToScan.isEmpty()) {
            filterAddedPages(pages);
            if(pagesToScan.size()>0) {
                pagesToScan.add(null);
                maxdepth++;
            }
        } else if(hasNext()) {
            if (pagesToScan.get(nextPage + 1) != null) {
                filterAddedPages(pages);
            } else {
                filterAddedPages(pages);
                pagesToScan.add(null);
                maxdepth++;
            }
        }
    }

    @Override
    public int getMaximumScanDepth() {
        return maxdepth;
    }

    @Override
    public String getNext() {
        if (hasNext()) {
            nextPage++;
            if(pagesToScan.get(nextPage)==null)
            {
                if(hasNext())
                {
                    nextPage++;
                }

            }
            return pagesToScan.get(nextPage);
        }
        return null;
    }

    @Override
    public List<String> getScannedPages() {
        List<String> pagesWithoutNulls = new ArrayList<>(pagesToScan.subList(0, nextPage));
        pagesWithoutNulls.removeAll(Collections.singleton(null));
        return pagesWithoutNulls;
    }

    @Override
    public Boolean hasNext() {
        return nextPage + 1 < pagesToScan.size();
    }
}
