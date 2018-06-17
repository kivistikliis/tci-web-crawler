package crawling.product;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public abstract class ProductDetailsExtractor {

    private ProductDetailsExtractor next;
    private Document page;

    public ProductDetailsExtractor(String htmlPage) {

        this.next = null;
        this.page = Jsoup.parse(htmlPage);
    }

    abstract Product extract();

    CategoryType getCategory(){

        return CategoryType.fromString(ProductDetailsTableExtractor.getString(this.getPage(), 0));
    }

    boolean isDetailsPage() {

        return this.getPage().selectFirst(".media-details") != null;
    }

    private boolean hasNext() {

        return this.next != null;
    }

    private ProductDetailsExtractor getNext() {

        return this.next;
    }

    public Product next() {

        if (this.hasNext()) {

            return this.getNext().extract();
        }
        return null;
    }

    public void setNext(ProductDetailsExtractor next) {

        this.next = next;
    }

    Document getPage() {

        return page;
    }


    String extractName() {

        Element detailsDiv = this.getPage().selectFirst(".media-details");
        if (detailsDiv == null) return "";
        Elements nameHeading = detailsDiv.getElementsByTag("h1");
        if (nameHeading.isEmpty()) return "";
        return nameHeading.first().text();
    }

    String extractImageUrl() {

        Element imageDiv = this.getPage().selectFirst(".media-picture");
        if (imageDiv == null) return "";
        Elements imageTags = imageDiv.getElementsByAttribute("src");
        if (imageTags.isEmpty()) return "";
        return imageTags.first().attr("src");
    }


    GenreType extractGenreType() {

        String genre = ProductDetailsTableExtractor.getString(this.getPage(), 1);
        return GenreType.fromString(genre);
    }

    ProductFormat extractFormat() {

        String format = ProductDetailsTableExtractor.getString(this.getPage(), 2);
        return ProductFormat.fromString(format);
    }

    int extractYear() {

        return ProductDetailsTableExtractor.getInt(this.getPage(), 3);
    }


}
