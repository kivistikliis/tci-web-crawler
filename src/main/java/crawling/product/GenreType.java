package crawling.product;

public enum GenreType {
    Tech,
    Drama,
    Comedy,
    Classical,
    Rock,
    Country,
    Jaz;

    public static GenreType fromString(String text) {

        for (GenreType genre : GenreType.values()) {

            if (genre.name().equalsIgnoreCase(text)) {

                return genre;
            }
        }
        return null;
    }
}
