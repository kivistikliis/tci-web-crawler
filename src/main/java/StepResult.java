import model.Product;

import java.util.List;

public class StepResult {

    public List<Product> collectedProducts;
    public int elapsedTime;
    public int maxSearchDepth;
    public List<String> scannedPages;

    public StepResult(List<Product> collectedProducts, int elapsedTime, int maxSearchDepth, List<String> scannedPages) {
    }
}
