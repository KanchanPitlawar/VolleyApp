package bynry.com.volleyapp.models;

public class Actors {

    public String name;

    public String imageURL;

    public String images;
    public String trial;


    public Actors(String name, String imageURL){
        this.name = name;
        this.imageURL = imageURL;
    }

    public String getName(){
        return name;
    }

    public String getImageURL() {
        return imageURL;
    }
}
