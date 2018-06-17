package crawling.product;

public class MovieDetailsExtractor extends ProductDetailsExtractor {

    public MovieDetailsExtractor(String html) {

        super(html);
    }

    @Override
    public Product extract() {

        if(!this.isDetailsPage()) return null;

        // Only interested in movies
        if (!this.getCategory().equals(CategoryType.Movies)) return this.next();

        Movie movie = new Movie();

        movie.setDirector(this.extractDirector());
        movie.setImgUrl(this.extractImageUrl());
        movie.setName(this.extractName());
        movie.setYear(this.extractYear());
        movie.setGenre(this.extractGenreType());
        movie.setFormat(this.extractFormat());
        for (String star : this.extractStars()) {

            movie.addStar(star.trim());
        }
        for (String writer : this.extractWriters()) {

            movie.addWriter(writer.trim());
        }
        return movie;
    }

    private String extractDirector(){

        return ProductDetailsTableExtractor.getString(this.getPage(), 4);
    }

    private String[] extractWriters(){

        return ProductDetailsTableExtractor.getStringArray(this.getPage(), 5);
    }

    private String[] extractStars(){

        return ProductDetailsTableExtractor.getStringArray(this.getPage(), 6);
    }
}
