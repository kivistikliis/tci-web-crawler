package crawling.product;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MovieTest {

    @Test
    public void addAStar() {

        Movie movie = new Movie();
        movie.addStar("test");
        Assert.assertArrayEquals(new String[]{"test"},movie.getStars().toArray());
    }

    @Test
    public void addStartIfNotAlreadyAdded() {

        Movie movie = new Movie();
        movie.addStar("test");
        movie.addStar("test");
        Assert.assertEquals(1, movie.getStars().size());

        Assert.assertArrayEquals(new String[]{"test"},movie.getStars().toArray());
    }

    @Test
    public void initializeStars() {

        Movie movie = new Movie();
        Assert.assertArrayEquals(new String[]{},movie.getStars().toArray());
    }

    @Test
    public void addAWriter() {

        Movie movie = new Movie();
        movie.addWriter("test");
        Assert.assertArrayEquals(new String[]{"test"},movie.getWriters().toArray());
    }

    @Test
    public void addWriterIfNotAlreadyAdded() {

        Movie movie = new Movie();
        movie.addWriter("test");
        movie.addWriter("test");
        Assert.assertEquals(1, movie.getWriters().size());

        Assert.assertArrayEquals(new String[]{"test"},movie.getWriters().toArray());
    }

    @Test
    public void initializeWriters() {

        Movie movie = new Movie();
        Assert.assertArrayEquals(new String[]{},movie.getWriters().toArray());
    }

    @Test
    public void shouldGiveJSONWhenToString(){

        Movie movie = new Movie();
        movie.addStar("test");
        Assert.assertEquals("{\"stars\":[\"test\"],\"writers\":[],\"year\":0}", movie.toString());
    }
}