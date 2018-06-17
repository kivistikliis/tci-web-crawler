package crawling.product;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ProductDetailsTableExtractor {

    public static String getString(Document page, int rowIndex) {

        try {


            Element detailsDiv = page.selectFirst(".media-details");
            if (detailsDiv == null) return "";

            Elements detailsTable = detailsDiv.getElementsByTag("tbody");
            if (detailsTable.isEmpty()) return "";

            Element customSection = detailsTable.first().children().get(rowIndex);
            if (customSection == null) return "";

            Element artistNameRow = customSection.getElementsByTag("td").first();
            if (artistNameRow == null) return "";

            return artistNameRow.text();
        } catch (Exception e) {

            return "";
        }
    }

    public static String[] getStringArray(Document page, int rowIndex) {

        return ProductDetailsTableExtractor.getString(page, rowIndex).split(",");
    }

    public static int getInt(Document page, int rowIndex) {

        return Integer.parseInt(ProductDetailsTableExtractor.getString(page, rowIndex));
    }
}
