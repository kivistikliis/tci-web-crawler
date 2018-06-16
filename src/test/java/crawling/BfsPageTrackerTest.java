package crawling;

import crawling.interfaces.IPageTracker;
import crawling.trackers.BfsPageTracker;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

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
}
