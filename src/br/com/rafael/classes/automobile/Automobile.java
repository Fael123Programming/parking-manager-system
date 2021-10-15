package br.com.rafael.classes.automobile;

public class Automobile {
    private String brand;
    private String model;
    private String licensePlate;
    private String year;

    public Automobile(String brand,String model,String licensePlate,String year){
        this.brand=brand;
        this.model=model;
        this.licensePlate=licensePlate;
        this.year=year;
    }

    public Automobile(){}

    public void setBrand(String newBrand){this.brand=newBrand;}

    public void setModel(String newModel){this.model=newModel;}

    public void setLicensePlate(String newLP){this.licensePlate=newLP;}

    public void setYear(String newYear){this.year=newYear;}

    public String getBrand(){return this.brand;}

    public String getModel(){return this.model;}

    public String getLP(){return this.licensePlate;}

    public String getYear(){return this.year;}

    @Override
    public String toString(){
        return String.format("Marca: %s%nModelo: %s%nPlaca: %s%nAno: %s%n",this.brand,this.model,this.licensePlate,this.year);
    }
}
