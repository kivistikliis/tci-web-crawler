package crawling.product;

import org.junit.Assert;
import org.junit.Test;

public class AlbumTest {

    @Test
    public void shouldGiveJSONWhenToString(){

        Album album = new Album();
        album.setArtist("test");
        Assert.assertEquals("{\"artist\":\"test\",\"year\":0}", album.toString());
    }
}
