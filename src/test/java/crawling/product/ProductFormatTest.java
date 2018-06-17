package crawling.product;

import org.junit.Assert;
import org.junit.Test;

public class ProductFormatTest {

    @Test
    public void fromString() {

        Assert.assertEquals(ProductFormat.Paperback, ProductFormat.fromString("Paperback"));
    }
}