package crawling.trackers;

import crawling.interfaces.IPageTracker;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
        CollectionUtils.removeAll(pagesWithoutNulls, pagesToScan);
        pagesToScan.addAll(pagesWithoutNulls);
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
        return pagesToScan.subList(0, nextPage);
    }

    @Override
    public Boolean hasNext() {
        return nextPage + 1 < pagesToScan.size();
    }
}
