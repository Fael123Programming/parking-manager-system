package br.com.rafael.classes.vacancy;

import br.com.rafael.classes.automobile.Automobile;
import br.com.rafael.classes.customer.Customer;

import java.util.*;

public class Vacancy {
    private String arrivalTime;
    private Customer customer;
    private Automobile customersAuto;
    private String vacancyIdent;
    private double hourPrice;

    public Vacancy(Customer customer,Automobile customersAuto,String
            vacancyIdent,double hourPrice){
        Date tm=new Date();
        this.arrivalTime=tm.toString().split(" ")[3];
        this.customer=customer;
        this.customersAuto=customersAuto;
        this.vacancyIdent=vacancyIdent;
        this.hourPrice=hourPrice;
    }

    public Vacancy(){
        Date tm=new Date();
        this.arrivalTime=tm.toString().split(" ")[3];
    }

    public String getArrivalTime(){return this.arrivalTime;}

    public String getCurrentTime(){
        Date tm=new Date();
        return tm.toString().split(" ")[3];
    }

    public String getVacancyIdent(){return this.vacancyIdent;}

    public Automobile getCustomersAuto(){return this.customersAuto;}

    public double getHourPrice(){return this.hourPrice;}

    public double calculatePrice(double hoursPrice){
        String []dividedArrivalTime=this.getArrivalTime().split(":");
        String []dividedCurrentTime=this.getCurrentTime().split(":");
        int arrivalTimeInSecs=Integer.parseInt(dividedArrivalTime[0])*
                3600+Integer.parseInt(dividedArrivalTime[1])
                *60+Integer.parseInt(dividedArrivalTime[2]);
        int currentTimeInSecs=Integer.parseInt(dividedCurrentTime[0])*
                3600+Integer.parseInt(dividedCurrentTime[1])
                *60+Integer.parseInt(dividedCurrentTime[2]);
        if(arrivalTimeInSecs<currentTimeInSecs){
            return (currentTimeInSecs-arrivalTimeInSecs)/3600.00*hoursPrice;
        }else if(currentTimeInSecs<arrivalTimeInSecs){
            return ((86400-arrivalTimeInSecs)+currentTimeInSecs)/3600.00*hoursPrice;
        }
        return 0.0;
    }

    public String calculateTotalTime(){
        String []dividedArrivalTime=this.getArrivalTime().split(":");
        String []dividedCurrentTime=this.getCurrentTime().split(":");
        int arrivalTimeInSecs=Integer.parseInt(dividedArrivalTime[0])*
                3600+Integer.parseInt(dividedArrivalTime[1])
                *60+Integer.parseInt(dividedArrivalTime[2]);
        int currentTimeInSecs=Integer.parseInt(dividedCurrentTime[0])*
                3600+Integer.parseInt(dividedCurrentTime[1])
                *60+Integer.parseInt(dividedCurrentTime[2]);
        if(arrivalTimeInSecs<currentTimeInSecs){
            int timeInSecs=currentTimeInSecs-arrivalTimeInSecs;
            int hours=timeInSecs/3600,mins=timeInSecs%3600/60,secs=timeInSecs
                    %3600%60;
            return String.format("%d:%d:%d",hours,mins,secs);

        }else if(currentTimeInSecs<arrivalTimeInSecs){
            int timeInSecs=(86400-arrivalTimeInSecs)+currentTimeInSecs;
            int hours=timeInSecs/3600,mins=timeInSecs%3600/60,secs=timeInSecs
                    %3600%60;
            return String.format("%d:%d:%d",hours,mins,secs);
        }
        return null;
    }
    @Override
    public String toString(){
        return String.format("-----------------------------------------------------------------------------------------------------\nCliente\n%s\nAutomóvel\n%s\nHorário de chegada: %s%nTempo de estacionamento: %s%nTotal a pagar: R$ %.2f",this.customer,this.customersAuto,this.arrivalTime,this.calculateTotalTime(), this.calculatePrice(this.hourPrice));
    }
}
