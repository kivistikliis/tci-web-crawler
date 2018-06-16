package crawling.product;

import com.google.gson.Gson;

public class Album extends Product {

    private String artist;

    public Album(){

    }

    public String getArtist() {

        return artist;
    }

    void setArtist(String artist) {

        this.artist = artist;
    }

    @Override
    public String toString() {

        return new Gson().toJson(this);
    }
}
