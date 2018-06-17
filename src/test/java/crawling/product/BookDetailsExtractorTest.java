package crawling.product;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookDetailsExtractorTest {

    String html = "<div class=\"media-picture\"> <span> <img src=\"img/media/clean_coder.jpg\" alt=\"The Clean Coder: A Code of Conduct for Professional Programmers\"/> </span> </div><div class=\"media-details\"> <h1>The Clean Coder: A Code of Conduct for Professional Programmers</h1> <table> <tr> <th>Category</th> <td>Books</td></tr><tr> <th>Genre</th> <td>Tech</td></tr><tr> <th>Format</th> <td>Audio</td></tr><tr> <th>Year</th> <td>2011</td></tr><tr> <th>Authors</th> <td>Robert C. Martin</td></tr><tr> <th>Publisher</th> <td>Prentice Hall</td></tr><tr> <th>ISBN</th> <td>007-6092046981</td></tr></table> </div>";

    @Test
    public void extract() {

        BookDetailsExtractor extractor = new BookDetailsExtractor(html);
        Book book = (Book) extractor.extract();

        assertEquals("The Clean Coder: A Code of Conduct for Professional Programmers", book.getName());
        Assert.assertEquals(GenreType.Tech, book.getGenre());
        Assert.assertEquals(ProductFormat.Audio, book.getFormat());
        assertEquals(2011, book.getYear());
        assertEquals("img/media/clean_coder.jpg", book.getImgUrl());

        assertEquals("Prentice Hall", book.getPublisher());
        assertEquals("007-6092046981", book.getISBN());

    }

}