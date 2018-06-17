package crawling.product;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Movie extends Product{

    private String director;
    private ArrayList<String> stars;
    private ArrayList<String> writers;

    public Movie() {

        this.stars = new ArrayList<>();
        this.writers = new ArrayList<>();
    }

    public void addStar(String star) {

        if (!this.stars.contains(star)) {

            this.stars.add(star);
        }
    }

    public ArrayList<String> getStars(){

        return  this.stars;
    }

    public ArrayList<String> getWriters(){

        return  this.writers;
    }

    public void addWriter(String writer) {

        if (!this.writers.contains(writer)) {

            this.writers.add(writer);
        }
    }

    public String getDirector() {

        return director;
    }

    public void setDirector(String director) {

        this.director = director;
    }

    @Override
    public String toString() {

        return new Gson().toJson(this);
    }

}
