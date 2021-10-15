package br.com.rafael.classes.customer;

public class Customer {
    private String name;
    private String age;
    private String profession;
    private String phoneNumber;

    public Customer(String name,String age,String profession,String phoneNumber){
        this.name=name;
        this.age=age;
        this.profession=profession;
        this.phoneNumber=phoneNumber;
    }

    public Customer(){}

    public void setName(String newName){this.name=newName;}

    public void setAge(String newAge){this.age=newAge;}

    public void setProfession(String newProf){this.profession=newProf;}

    public void setPhoneNumber(String newPN){this.phoneNumber=newPN;}

    public String getName(){return this.name;}

    public String getAge(){return this.age;}

    public String getProf(){return this.profession;}

    public String getPhoneNumber(){return this.phoneNumber;}

    @Override
    public String toString(){
        return String.format("Nome: %s%nIdade: %s%nTelefone: %s%n",this.name,this.age,this.phoneNumber);
    }
}
