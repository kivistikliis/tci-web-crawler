package crawling.product;

import org.jsoup.Jsoup;
import org.junit.Assert;
import org.junit.Test;

public class ProductDetailsTableExtractorTest {

    @Test
    public void getEmptyIfNoMediaDetails() {

        String result = ProductDetailsTableExtractor.getString(Jsoup.parse(""), 1);
        Assert.assertEquals("", result);
    }

    @Test
    public void getEmptyIfNoTableBody() {

        String result = ProductDetailsTableExtractor.getString(Jsoup.parse("<div class=\"media-details\"></div>"), 1);
        Assert.assertEquals("", result);
    }

    @Test
    public void getEmptyIfNoSpecifiedRow() {

        String result = ProductDetailsTableExtractor.getString(
                Jsoup.parse(
                        "<div class=\"media-details\">" +
                                "<tbody></tbody>" +
                                "</div>"),
                1
        );
        Assert.assertEquals("", result);
    }


    @Test
    public void getEmptyIfNoSpecifiedTableColumn() {

        String result = ProductDetailsTableExtractor.getString(
                Jsoup.parse(
                        "<div class=\"media-details\">" +
                                "<tbody>" +
                                "<tr></tr>" +
                                "</tbody>" +
                                "</div>"),
                0
        );
        Assert.assertEquals("", result);
    }

    @Test
    public void getEmptyIfException() {

        String result = ProductDetailsTableExtractor.getString(
                Jsoup.parse("<div class=\"media-details\"><h1></h1><table> <tr> <th>Category</th> <td>Hello</td></tr></table> </div>"),
                2
        );
        Assert.assertEquals("", result);
    }

    @Test
    public void getString() {

        String result = ProductDetailsTableExtractor.getString(
                Jsoup.parse("<div class=\"media-details\"><h1></h1><table> <tr> <th>Category</th> <td>Hello</td></tr></table> </div>"),
                0
        );
        Assert.assertEquals("Hello", result);
    }

    @Test
    public void getStringArray() {

        String[] result = ProductDetailsTableExtractor.getStringArray(
                Jsoup.parse("<div class=\"media-details\"><h1></h1><table> <tr> <th>Category</th> <td>Hello,World</td></tr></table> </div>"),
                0
        );
        Assert.assertArrayEquals(new String[]{"Hello", "World"}, result);
    }

    @Test
    public void getInt() {

        int result = ProductDetailsTableExtractor.getInt(
                Jsoup.parse("<div class=\"media-details\"><h1></h1><table> <tr> <th>Category</th> <td>2</td></tr></table> </div>"),
                0
        );
        Assert.assertEquals(2, result);
    }
}