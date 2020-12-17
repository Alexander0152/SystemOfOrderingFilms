package lab3.businessLayer;

import java.util.List;

public class Customer extends Person{

    private boolean discount;
    private List<Film> orderedFilms;


    public boolean getDiscount(){
        return this.discount;
    }
    public void setDiscount(boolean discount){
        this.discount = discount;
    }

    public List<Film> getOrderedFilms(){
        return this.orderedFilms;
    }
    public void setOrderedFilms(List<Film> orderedFilms){
        this.orderedFilms = orderedFilms;
    }
}
