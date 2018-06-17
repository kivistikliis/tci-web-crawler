package crawling.product;

import com.google.gson.Gson;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

@RunWith(JUnitParamsRunner.class)
public class StandardProductExtractorTest {

    private static Gson json = new Gson();

    private static Object[] getTestPages() {

        Book book = new Book();
        book.setName("The Clean Coder: A Code of Conduct for Professional Programmers");
        book.setGenre(GenreType.Tech);
        book.setFormat(ProductFormat.Audio);
        book.setYear(2011);
        book.setImgUrl("img/media/clean_coder.jpg");
        book.setPublisher("Prentice Hall");
        book.setISBN("007-6092046981");
        book.addAuthor("Robert C. Martin");

        Movie movie = new Movie();
        movie.setName("Office Space");
        movie.setGenre(GenreType.Comedy);
        movie.setFormat(ProductFormat.BluRay);
        movie.setYear(1999);
        movie.setImgUrl("img/media/office_space.jpg");
        movie.setDirector("Mike Judge");
        movie.addWriter("William Goldman");
        movie.addStar("Ron Livingston");
        movie.addStar("Jennifer Aniston");

        Album album = new Album();
        album.setName("Elvis Forever");
        album.setGenre(GenreType.Rock);
        album.setFormat(ProductFormat.Vinyl);
        album.setYear(2015);
        album.setImgUrl("img/media/elvis_presley.jpg");
        album.setArtist("Elvis Presley");

        return new Object[]{
                // Book
                new Object[]{"<div class=\"media-picture\"> <span> <img src=\"img/media/clean_coder.jpg\" alt=\"The Clean Coder: A Code of Conduct for Professional Programmers\"/> </span> </div><div class=\"media-details\"> <h1>The Clean Coder: A Code of Conduct for Professional Programmers</h1> <table> <tr> <th>Category</th> <td>Books</td></tr><tr> <th>Genre</th> <td>Tech</td></tr><tr> <th>Format</th> <td>Audio</td></tr><tr> <th>Year</th> <td>2011</td></tr><tr> <th>Authors</th> <td>Robert C. Martin</td></tr><tr> <th>Publisher</th> <td>Prentice Hall</td></tr><tr> <th>ISBN</th> <td>007-6092046981</td></tr></table> </div>", json.toJson(book)},
                // Movie
                new Object[]{
                        "<div class=\"media-picture\"> <span> <img src=\"img/media/office_space.jpg\" alt=\"Office Space\"/> </span> </div><div class=\"media-details\"> <h1>Office Space</h1> <table> <tr> <th>Category</th> <td>Movies</td></tr><tr> <th>Genre</th> <td>Comedy</td></tr><tr> <th>Format</th> <td>Blu-ray</td></tr><tr> <th>Year</th> <td>1999</td></tr><tr> <th>Director</th> <td>Mike Judge</td></tr><tr> <th>Writers</th> <td>William Goldman</td></tr><tr> <th>Stars</th> <td>Ron Livingston, Jennifer Aniston</td></tr></table> </div>", json.toJson(movie)
                },
                // Album
                new Object[]{
                        "<div class=\"media-picture\"> <span> <img src=\"img/media/elvis_presley.jpg\" alt=\"Elvis Forever\"/> </span> </div><div class=\"media-details\"> <h1>Elvis Forever</h1> <table> <tr> <th>Category</th> <td>Music</td></tr><tr> <th>Genre</th> <td>Rock</td></tr><tr> <th>Format</th> <td>Vinyl</td></tr><tr> <th>Year</th> <td>2015</td></tr><tr> <th>Artist</th> <td>Elvis Presley</td></tr></table> </div>", json.toJson(album)
                }
        };
    }

    @Test
    @Parameters(method = "getTestPages")
    public void shouldExtractDifferentProductsFromPages(String inputPage, String expectedProduct) {

        IProductExtractor productExtractor = new StandardProductExtractor();
        ArrayList<Product> extractedProducts = productExtractor.extractProducts(inputPage);
        Assert.assertEquals(1, extractedProducts.size());
        Assert.assertEquals(expectedProduct, json.toJson(extractedProducts.get(0)));
    }
}
