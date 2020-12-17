package lab3.businessLayer;

import java.util.List;

public class Film {

    private int id;
    private String name;
    private double price;
    private List<String> reviews;


    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public double getPrice(){
        return this.price;
    }
    public void setPrice(double price){
        this.price = price;
    }

    public List<String> getReviews(){
        return this.reviews;
    }
    public void setReviews(List<String> reviews){
        this.reviews = reviews;
    }

}
