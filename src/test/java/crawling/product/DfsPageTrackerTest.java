package crawling.product;

import crawling.interfaces.IPageTracker;
import crawling.trackers.DfsPageTracker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DfsPageTrackerTest {

    private IPageTracker pageTracker;
    @Before
    public void setup(){

        this.pageTracker = new DfsPageTracker();
    }

    @Test
    public void shouldAddAPageToTheStack(){

        List<String> pages = new ArrayList<>();
        pages.add("a");

        this.pageTracker.addPages(pages);
        Assert.assertTrue(this.pageTracker.hasNext());
    }

    @Test
    public void shouldAddAPageOnlyOnce(){

        List<String> pages = new ArrayList<>();
        pages.add("a");
        pages.add("a");

        this.pageTracker.addPages(pages);

        List<String> scannedPages = new ArrayList<>();
        while ( this.pageTracker.hasNext()) {

            scannedPages.add( this.pageTracker.getNext());
        }

        Assert.assertEquals(1, scannedPages.size());
    }

    @Test
    public void shouldGetAListOfScannedPages(){

        List<String> pages = new ArrayList<>();
        pages.add("a");
        pages.add("a");

        this.pageTracker.addPages(pages);
        while ( this.pageTracker.hasNext()) {

            this.pageTracker.getNext();
        }
        Assert.assertEquals(1, this.pageTracker.getScannedPages().size());
    }

    @Test
    public void shouldGetThePagesInDFSOrder(){

        this.pageTracker.addPages(Arrays.asList("a"));
        Assert.assertEquals("a", this.pageTracker.getNext());

        // A is connected to B and E
        this.pageTracker.addPages(Arrays.asList("b", "e"));
        Assert.assertEquals("e", this.pageTracker.getNext());

        // E is connected to F and G
        this.pageTracker.addPages(Arrays.asList("f", "g"));
        Assert.assertEquals("g", this.pageTracker.getNext());
        Assert.assertEquals("f", this.pageTracker.getNext());

        // B is connected to C and D
        Assert.assertEquals("b", this.pageTracker.getNext());

        this.pageTracker.addPages(Arrays.asList("c", "d"));
        Assert.assertEquals("d", this.pageTracker.getNext());
        Assert.assertEquals("c", this.pageTracker.getNext());

    }

    @Test
    public void shouldGetThePagesInDFSOrderWithoutDuplicates(){

        this.pageTracker.addPages(Arrays.asList("a"));
        this.pageTracker.addPages(Arrays.asList("a"));
        Assert.assertEquals("a", this.pageTracker.getNext());

        // A is connected to B and E
        this.pageTracker.addPages(Arrays.asList("b", "e"));
        this.pageTracker.addPages(Arrays.asList("b", "e"));
        Assert.assertEquals("e", this.pageTracker.getNext());

        // E is connected to F and G
        this.pageTracker.addPages(Arrays.asList("f", "g"));
        this.pageTracker.addPages(Arrays.asList("f", "g"));
        Assert.assertEquals("g", this.pageTracker.getNext());
        Assert.assertEquals("f", this.pageTracker.getNext());

        // B is connected to C and D
        Assert.assertEquals("b", this.pageTracker.getNext());

        this.pageTracker.addPages(Arrays.asList("c", "d"));
        this.pageTracker.addPages(Arrays.asList("c", "d"));
        Assert.assertEquals("d", this.pageTracker.getNext());
        Assert.assertEquals("c", this.pageTracker.getNext());

    }

    @Test
    public void shouldGetMaxSearchDepth(){

        // Add A
        this.pageTracker.addPages(Arrays.asList("a"));
        // Get A
        Assert.assertEquals("a", this.pageTracker.getNext());
        // From A link to B and C
        this.pageTracker.addPages(Arrays.asList("b", "c"));
        // Get C
        Assert.assertEquals("c", this.pageTracker.getNext());
        // Get B
        Assert.assertEquals("b", this.pageTracker.getNext());
        // From B link D and E
        this.pageTracker.addPages(Arrays.asList("d", "e"));
        // Get E
        Assert.assertEquals("e", this.pageTracker.getNext());
        // Get D
        Assert.assertEquals("d", this.pageTracker.getNext());
        // From D link F and G
        this.pageTracker.addPages(Arrays.asList("f", "g"));
        // Get G
        Assert.assertEquals("g", this.pageTracker.getNext());
        // From G link H
        this.pageTracker.addPages(Arrays.asList("h"));
        // Get H
        Assert.assertEquals("h", this.pageTracker.getNext());
        // Get F
        Assert.assertEquals("f", this.pageTracker.getNext());

        Assert.assertEquals(5, this.pageTracker.getMaximumScanDepth());

    }

    @Test
    public void shouldGetMaxSearchDepthStraightTree(){

        // Add A
        this.pageTracker.addPages(Arrays.asList("a"));
        // Get A
        Assert.assertEquals("a", this.pageTracker.getNext());
        // From A link to B
        this.pageTracker.addPages(Arrays.asList("b"));
        // Get B
        Assert.assertEquals("b", this.pageTracker.getNext());
        // From B link to C
        this.pageTracker.addPages(Arrays.asList("c"));
        // Get C
        Assert.assertEquals("c", this.pageTracker.getNext());
        // From C link to D
        this.pageTracker.addPages(Arrays.asList("d"));
        // Get C
        Assert.assertEquals("d", this.pageTracker.getNext());
        // From D link to E
        this.pageTracker.addPages(Arrays.asList("e"));
        // Get E
        Assert.assertEquals("e", this.pageTracker.getNext());
        // From E link to F
        this.pageTracker.addPages(Arrays.asList("f"));
        // Get F
        Assert.assertEquals("f", this.pageTracker.getNext());
        Assert.assertEquals(6, this.pageTracker.getMaximumScanDepth());

    }
}
