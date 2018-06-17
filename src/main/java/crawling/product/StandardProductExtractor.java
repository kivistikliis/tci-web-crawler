package crawling.product;

import java.util.ArrayList;

public class StandardProductExtractor implements IProductExtractor {

    @Override
    public ArrayList<Product> extractProducts(String page) {

        ProductDetailsExtractor album = new AlbumDetailsExtractor(page);
        ProductDetailsExtractor movie = new MovieDetailsExtractor(page);
        ProductDetailsExtractor book = new BookDetailsExtractor(page);
        album.setNext(movie);
        movie.setNext(book);
        ArrayList<Product> results = new ArrayList<>();
        Product product = album.extract();
        if (product != null) results.add(product);
        return results;
    }
}
