package crawling.product;

import org.junit.Assert;
import org.junit.Test;

public class BookTest {

    @Test
    public void addAuthor() {

        Book book = new Book();
        book.addAuthor("test");
        Assert.assertArrayEquals(new String[]{"test"}, book.getAuthors().toArray());
    }

    @Test
    public void addAuthorIfNotAlreadyAdded() {

        Book book = new Book();
        book.addAuthor("test");
        book.addAuthor("test");
        Assert.assertEquals(1, book.getAuthors().size());
        Assert.assertArrayEquals(new String[]{"test"}, book.getAuthors().toArray());
    }

    @Test
    public void initializeAuthors() {

        Book book = new Book();
        Assert.assertArrayEquals(new String[]{}, book.getAuthors().toArray());
    }

    @Test
    public void shouldGiveJSONWhenToString(){

        Book book = new Book();
        book.addAuthor("test");
        Assert.assertEquals("{\"authors\":[\"test\"],\"year\":0}", book.toString());
    }
}