import model.Product;

import java.util.ArrayList;
import java.util.List;

public class StepResult {

    public List<Product> collectedProducts;
    public int elapsedTime;
    public int maxSearchDepth;
    public List<String> scannedPages;

    public StepResult(){
        this.collectedProducts = new ArrayList<>();
        this.scannedPages = new ArrayList<>();
    }
    public StepResult(int elapsedTime, int maxSearchDepth) {
        this.elapsedTime = elapsedTime;
        this.maxSearchDepth = maxSearchDepth;
        this.collectedProducts = new ArrayList<>();
        this.scannedPages = new ArrayList<>();
    }
}
