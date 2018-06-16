package crawling.product;

public class BookDetailsExtractor extends ProductDetailsExtractor {

    public BookDetailsExtractor(String htmlPage) {

        super(htmlPage);
    }

    @Override
    public Product extract() {

        if (!this.isDetailsPage()) return null;

        // Only interested in movies
        if (!this.getCategory().equals(CategoryType.Books)) return this.next();

        Book book = new Book();
        book.setImgUrl(this.extractImageUrl());
        book.setName(this.extractName());
        book.setGenre(this.extractGenreType());
        book.setFormat(this.extractFormat());
        book.setYear(this.extractYear());

        for (String author : this.extractAuthors()) {

            book.addAuthor(author);
        }
        book.setPublisher(this.extractPublisher());
        book.setISBN(this.extractISBN());
        return book;
    }

    String[] extractAuthors() {

        return ProductDetailsTableExtractor.getStringArray(this.getPage(), 4);
    }

    String extractPublisher() {

        return ProductDetailsTableExtractor.getString(this.getPage(), 5);
    }

    String extractISBN() {

        return ProductDetailsTableExtractor.getString(this.getPage(), 6);
    }

}
