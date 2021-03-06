package crawling;

import crawling.interfaces.IPageTracker;
import crawling.trackers.BfsPageTracker;
import org.junit.Before;
import org.junit.Test;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

//TODO: Add comments to explain some tests
public class BfsPageTrackerTest {

    private IPageTracker bfsPageTracker;

    @Before
    public void setup() {
        bfsPageTracker = new BfsPageTracker();
    }

    @Test
    public void shouldBeInstanceOfIPageTracker() {
        assertThat(bfsPageTracker, instanceOf(IPageTracker.class));
    }

    @Test
    public void shouldAddPagesToTheList() {
        List<String> pages = Arrays.asList("www.website1.com");
        bfsPageTracker.addPages(pages);

        assertTrue(bfsPageTracker.hasNext());
    }

    @Test
    public void shouldNotAddANullElement() {
        bfsPageTracker.addPages(Arrays.asList(null, null));

        assertFalse(bfsPageTracker.hasNext());
    }

    @Test
    public void shouldNotAddAnAlreadyAddedPage() {
        List<String> pages = Arrays.asList("www.website1.com");
        bfsPageTracker.addPages(pages);
        bfsPageTracker.addPages(pages);

        List<String> scannedPages = new ArrayList<>();
        while (bfsPageTracker.hasNext()) {
            scannedPages.add(bfsPageTracker.getNext());
        }

        assertEquals(2, scannedPages.size());
    }

    @Test
    public void shouldNotAddDuplicatePages() {
        List<String> pages = Arrays.asList("www.website1.com", "www.website1.com");
        bfsPageTracker.addPages(pages);

        List<String> scannedPages = new ArrayList<>();
        while (bfsPageTracker.hasNext()) {
            scannedPages.add(bfsPageTracker.getNext());
        }

        assertEquals(2, scannedPages.size());
    }

    @Test
    public void shouldGetTheMaximumScanDepth() {
        List<String> pages = Arrays.asList("www.website1.com", "www.website2.com");
        bfsPageTracker.addPages(pages);

        assertEquals(1, bfsPageTracker.getMaximumScanDepth());
    }

    @Test
    public void shouldGetTheAlreadyScannedPages() {
        List<String> pages = Arrays.asList("www.website1.com", "www.website2.com", null, "www.website2.com");
        bfsPageTracker.addPages(pages);

        while (bfsPageTracker.hasNext()) {
            bfsPageTracker.getNext();
        }

        assertEquals(2, bfsPageTracker.getScannedPages().size());
    }

    @Test
    public void shouldReturnTrueIfThereIsANextPageToScan() {
        List<String> pages = Arrays.asList("www.website1.com", "www.website2.com");
        bfsPageTracker.addPages(pages);

        assertTrue(bfsPageTracker.hasNext());
    }

    @Test
    public void shouldReturnFalseIfThereIsNoPageToScan() {
        assertFalse(bfsPageTracker.hasNext());
    }

    @Test
    public void shouldGetNextPageToScan() {
        List<String> pages = Arrays.asList("www.website1.com", "www.website2.com");
        bfsPageTracker.addPages(pages);

        assertThat(bfsPageTracker.getNext(), is(notNullValue()));
    }

    @Test
    public void shouldReturnNullWhenGettingNextPageToScan() {
        List<String> pages = Arrays.asList("www.website1.com", "www.website2.com", null, "www.website2.com");
        bfsPageTracker.addPages(pages);

        while (bfsPageTracker.hasNext()) {
            bfsPageTracker.getNext();
        }

        assertThat(bfsPageTracker.getNext(), is(nullValue()));
    }
}
