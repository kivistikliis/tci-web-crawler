package crawling.product;

import java.util.ArrayList;

public class Book extends Product {

    private String ISBN;
    private ArrayList<String> authors;
    private String publisher;

    public Book() {

        this.authors = new ArrayList<>();
    }

    public void addAuthor(String author) {

        if (!this.authors.contains(author)) {

            this.authors.add(author);
        }
    }

    public ArrayList<String> getAuthors(){

        return this.authors;
    }

    public String getISBN() {

        return ISBN;
    }

    public void setISBN(String ISBN) {

        this.ISBN = ISBN;
    }

    public String getPublisher() {

        return publisher;
    }

    public void setPublisher(String publisher) {

        this.publisher = publisher;
    }
}
