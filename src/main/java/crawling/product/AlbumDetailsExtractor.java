package crawling.product;

public class AlbumDetailsExtractor extends ProductDetailsExtractor {

    public AlbumDetailsExtractor(String htmlPage) {

        super(htmlPage);
    }

    @Override
    public Product extract() {

        if (!this.isDetailsPage()) return null;

        // Only interested in artists
        if (!this.getCategory().equals(CategoryType.Music)) return this.next();

        Album album = new Album();
        album.setImgUrl(this.extractImageUrl());
        album.setName(this.extractName());
        album.setGenre(this.extractGenreType());
        album.setFormat(this.extractFormat());
        album.setYear(this.extractYear());

        album.setArtist(this.extractArtist());

        return album;
    }

    private String extractArtist() {

        return ProductDetailsTableExtractor.getString(this.getPage(), 4);
    }
}
