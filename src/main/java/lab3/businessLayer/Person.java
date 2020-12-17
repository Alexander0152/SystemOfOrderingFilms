package lab3.businessLayer;

public class Person {

    private String name;
    private PersonStatus status;

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }


    public PersonStatus getStatus(){
        return this.status;
    }
    public void setStatus(PersonStatus status){
        this.status = status;
    }

}
