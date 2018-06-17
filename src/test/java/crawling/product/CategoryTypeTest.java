package crawling.product;

import org.junit.Assert;
import org.junit.Test;

public class CategoryTypeTest {

    @Test
    public void fromString() {

        Assert.assertEquals(CategoryType.Music, CategoryType.fromString("Music"));
    }
}