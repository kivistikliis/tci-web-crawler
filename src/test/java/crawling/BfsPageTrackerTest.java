package crawling;

import crawling.interfaces.IPageTracker;
import crawling.trackers.BfsPageTracker;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

public class BfsPageTrackerTest {

    private BfsPageTracker bfsPageTracker;

    @Before
    public void setup() {
        bfsPageTracker = new BfsPageTracker();
    }

    @Test
    public void shouldBeInstanceOfIPageTracker() {
        assertThat(bfsPageTracker, instanceOf(IPageTracker.class));
    }

    @Test
    public void shouldAddPagesToTheStack() {

    }

    @Test
    public void shouldAddNullToTheStackWhenANewDepthLevelOccurs() {

    }

    @Test
    public void shouldRemoveVisitedNodesFromTheStack() {

    }

    @Test
    public void shouldGetTheMaximumScanDepth() {

    }

    @Test
    public void shouldGetTheNextPageToBeScanned() {

    }

    @Test
    public void shouldGetTheAlreadyScannedPages() {

    }

    @Test
    public void shouldIndicateWhetherThereIsANextPageToScan() {

    }
}
