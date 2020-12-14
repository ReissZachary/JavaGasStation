package com.MarceloZometa;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Car implements Runnable{
    private FuelType fuelType;
    private Double requestedGallons;
    private String carModel;
    private Integer year;
    private Card card;
    private Boolean isAtPump = false;


    public Car(FuelType fuelType, Double requestedGallons, String carModel, Integer year, Card card) {
        this.fuelType = fuelType;
        this.requestedGallons = requestedGallons;
        this.carModel = carModel;
        this.year = year;
        this.card = card;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public Double getRequestedGallons() {
        return requestedGallons;
    }

    public void setRequestedGallons(Double requestedGallons) {
        this.requestedGallons = requestedGallons;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Boolean askForPump(GasStationSingleton gasStation){

        //System.out.println(gasStation.assignCarToPump(this));
        Boolean answer = gasStation.assignCarToPump(this);

        //System.out.println(answer);
        return answer;
    }

    public Boolean getAtPump() {
        return isAtPump;
    }

    private void selectFuelType(GasPump gasPump) {
        if(this.fuelType == FuelType.low){
            gasPump.hitLow();
        }
        else if(this.fuelType == FuelType.Medium){
            gasPump.hitMed();
        }
        else{
            gasPump.hitHigh();
        }
    }

    public void fuelTank(GasPump gasPump){
        gasPump.trigger();
    }

    @Override
    public void run() {
        //Car arrives to gas station. Asks for pump

        if(askForPump(GasStationSingleton.getInstance())){
            isAtPump = true;

            //Get pump to which the car was assigned
            GasPump gasPump = GasStationSingleton.getInstance().getGasPump(this);

            //Give type of gas needed (Change state to FuelSelectionState)
            gasPump.cardSwipe();
            this.selectFuelType(gasPump);
            gasPump.trigger();

            //Give amount of gas need (Change to PumpingState)
            //gasPump.changeToPumpingState();
            gasPump.trigger();
            gasPump.pumpReturnLever();

            //Give ticket to person. Restart pump. (Change state to ReceiptState)
            //gasPump.changeToReceiptState();
            gasPump.pumpReturnLever();

        }
//        else{
//            System.out.println("**** Sorry, all pumps are busy ****");
//        }

        //Integer gallonsRequested =
    }


}
