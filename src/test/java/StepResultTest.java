import crawling.process.StepResult;
import crawling.product.Product;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertEquals;
import static junitparams.JUnitParamsRunner.$;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;


@RunWith(JUnitParamsRunner.class)
public class StepResultTest {

    private StepResult stepResult;

    private static Object[] getTwoProducts() {
        return $(
                $(new Product(), new Product()),
                $(null, new Product()),
                $(new Product(), null)
        );
    }

    private static Object[] getTwoPages() {
        return $(
                $("Page 1", "Page 2"),
                $(null, "Page 2"),
                $("Page 1", null)
        );
    }

    @Before
    public void setup() {
        stepResult = new StepResult();
    }

    @Test
    public void constructorCreatesStepResultObject() {
        assertThat(stepResult, instanceOf(StepResult.class));
    }

    @Test
    public void constructorShouldInitializeWithoutValues() {
        assertEquals(0, stepResult.collectedProducts.size());
        assertEquals(0, stepResult.elapsedTime);
        assertEquals(0, stepResult.maxSearchDepth);
        assertEquals(0, stepResult.scannedPages.size());
    }

    @Test
    public void constructorShouldSetValues() {
        stepResult = new StepResult(20,30);

        assertEquals(20, stepResult.elapsedTime);
        assertEquals(30, stepResult.maxSearchDepth);
    }

    @Test
    @Parameters(method = "getTwoProducts")
    public void shouldAddNewProductsAtTheEndOfCollectedProducts(Product prod1, Product prod2) {
        stepResult.collectedProducts.add(prod1);
        stepResult.collectedProducts.add(prod2);

        assertEquals(prod1, stepResult.collectedProducts.get(stepResult.collectedProducts.size() - 2));
        assertEquals(prod2, stepResult.collectedProducts.get(stepResult.collectedProducts.size() - 1));
    }

    @Test
    @Parameters(method = "getTwoPages")
    public void shouldAddNewPagesAtTheEndOfScannedPages(String page1, String page2) {
        stepResult.scannedPages.add(page1);
        stepResult.scannedPages.add(page2);

        assertEquals(page1, stepResult.scannedPages.get(stepResult.scannedPages.size() - 2));
        assertEquals(page2, stepResult.scannedPages.get(stepResult.scannedPages.size() - 1));
    }
}
