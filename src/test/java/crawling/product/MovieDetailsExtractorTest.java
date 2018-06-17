package crawling.product;

import org.junit.Assert;
import org.junit.Test;

public class MovieDetailsExtractorTest {

    String html = "<div class=\"media-picture\"> <span> <img src=\"img/media/office_space.jpg\" alt=\"Office Space\"/> </span> </div><div class=\"media-details\"> <h1>Office Space</h1> <table> <tr> <th>Category</th> <td>Movies</td></tr><tr> <th>Genre</th> <td>Comedy</td></tr><tr> <th>Format</th> <td>Blu-ray</td></tr><tr> <th>Year</th> <td>1999</td></tr><tr> <th>Director</th> <td>Mike Judge</td></tr><tr> <th>Writers</th> <td>William Goldman</td></tr><tr> <th>Stars</th> <td>Ron Livingston, Jennifer Aniston, David Herman, Ajay Naidu, Diedrich Bader, Stephen Root</td></tr></table> </div>";

    @Test
    public void extract() {

        MovieDetailsExtractor extractor = new MovieDetailsExtractor(html);
        Movie movie = (Movie) extractor.extract();

        Assert.assertEquals("Office Space", movie.getName());
        Assert.assertEquals(GenreType.Comedy, movie.getGenre());
        Assert.assertEquals(ProductFormat.BluRay, movie.getFormat());
        Assert.assertEquals(1999, movie.getYear());
        Assert.assertEquals("img/media/office_space.jpg", movie.getImgUrl());

        Assert.assertEquals("Mike Judge", movie.getDirector());
        Assert.assertArrayEquals(new String[]{"William Goldman"}, movie.getWriters().toArray());
        Assert.assertArrayEquals(new String[]{"Ron Livingston", "Jennifer Aniston", "David Herman", "Ajay Naidu", "Diedrich Bader", "Stephen Root"}, movie.getStars().toArray());
    }

}