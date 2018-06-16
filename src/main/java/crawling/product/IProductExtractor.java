package crawling.product;

import java.util.ArrayList;

public interface IProductExtractor {

    ArrayList<Product> extractProducts(String page);
}
