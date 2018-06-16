package crawling.product;

import org.junit.Assert;
import org.junit.Test;

public class AlbumDetailsExtractorTest {

    String html = "<div class=\"media-picture\"> <span> <img src=\"img/media/elvis_presley.jpg\" alt=\"Elvis Forever\"/> </span> </div><div class=\"media-details\"> <h1>Elvis Forever</h1> <table> <tr> <th>Category</th> <td>Music</td></tr><tr> <th>Genre</th> <td>Rock</td></tr><tr> <th>Format</th> <td>Vinyl</td></tr><tr> <th>Year</th> <td>2015</td></tr><tr> <th>Artist</th> <td>Elvis Presley</td></tr></table> </div>";


    @Test
    public void extract() {

        AlbumDetailsExtractor extractor = new AlbumDetailsExtractor(html);
        Album album = (Album) extractor.extract();

        Assert.assertEquals("Elvis Forever", album.getName());
        Assert.assertEquals(GenreType.Rock, album.getGenre());
        Assert.assertEquals(ProductFormat.Vinyl, album.getFormat());
        Assert.assertEquals(2015, album.getYear());
        Assert.assertEquals("img/media/elvis_presley.jpg", album.getImgUrl());

        Assert.assertEquals("Elvis Presley", album.getArtist());
    }
}