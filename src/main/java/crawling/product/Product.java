package crawling.product;

public class Product {

    private String name;
    private int year;
    private String imgUrl;
    private GenreType genre;
    private ProductFormat format;

    public String getName() {

        return this.name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getYear() {

        return this.year;
    }

    public void setYear(int year) {

        this.year = year;
    }

    public String getImgUrl() {

        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {

        this.imgUrl = imgUrl;
    }

    public GenreType getGenre() {
        return genre;
    }

    public void setGenre(GenreType genre) {
        this.genre = genre;
    }

    public ProductFormat getFormat() {
        return format;
    }

    public void setFormat(ProductFormat format) {
        this.format = format;
    }
}
