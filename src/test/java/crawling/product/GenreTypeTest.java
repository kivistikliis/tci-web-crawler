package crawling.product;

import org.junit.Assert;
import org.junit.Test;

public  class GenreTypeTest {

    @Test
    public void fromString() {

        Assert.assertEquals(GenreType.Tech, GenreType.fromString("Tech"));
    }
}