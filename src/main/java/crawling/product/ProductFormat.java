package scraper;

public enum ProductFormat {
    Paperback("Paperback"),
    Ebook("Ebook"),
    Hardcover("Hardcover"),
    Audio("Audio"),
    DVD("DVD"),
    BluRay("Blu-ray"),
    CD("CD"),
    Vinyl("Vinyl"),
    Cassette("Cassette"),
    MP3("MP3");

    private String customName;
    ProductFormat(String customName){

        this.customName = customName;
    }

    public static ProductFormat fromString(String text) {

        for (ProductFormat productFormat : ProductFormat.values()) {

            if (productFormat.getCustomName().equalsIgnoreCase(text)) {
                
                return productFormat;
            }
        }
        return null;
    }

    public String getCustomName() {
        return customName;
    }
}
