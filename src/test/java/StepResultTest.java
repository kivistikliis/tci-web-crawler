import model.Product;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.instanceOf;

public class StepResultTest {

    private StepResult stepResult;

    @Before
    public void setup() {
    }

    @Test
    public void constructorCreatesStepResultObject() {
        assertThat(stepResult, instanceOf(StepResult.class));
    }

    @Test
    public void constructorShouldSetValues() {
        assertThat(stepResult.collectedProducts, containsInAnyOrder(new Product(), new Product()));
        assertEquals(stepResult.elapsedTime, 20);
        assertEquals(stepResult.elapsedTime, 30);
        assertThat(stepResult.scannedPages, containsInAnyOrder("page 1", "page 2"));
    }

    // TODO: Use parameterized tests
    @Test
    public void shouldAddNewProductsAtTheEndOfCollectedProducts() {
        Product prod1 = new Product();
        Product prod2 = new Product();

        stepResult.collectedProducts.add(prod1);
        stepResult.collectedProducts.add(prod2);

        assertEquals(stepResult.collectedProducts.get(stepResult.collectedProducts.size() - 1), prod1);
        assertEquals(stepResult.collectedProducts.get(stepResult.collectedProducts.size() - 1), prod2);
    }

    // TODO: Use parameterized tests
    @Test
    public void shouldAddNewPagesAtTheEndOfScannedPages() {

        stepResult.scannedPages.add("String 1");
        stepResult.scannedPages.add("String 2");

        assertEquals(stepResult.scannedPages.get(stepResult.collectedProducts.size() - 1), "String 1");
        assertEquals(stepResult.scannedPages.get(stepResult.collectedProducts.size() - 1), "String 2");
    }
}
