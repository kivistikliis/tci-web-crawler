package scraper;

public enum CategoryType {

    Movies,
    Books,
    Music;

    public static CategoryType fromString(String text) {

        for (CategoryType category : CategoryType.values()) {

            if (category.name().equalsIgnoreCase(text)) {

                return category;
            }
        }
        return null;
    }
}
