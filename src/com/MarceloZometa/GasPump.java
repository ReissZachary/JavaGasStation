package com.MarceloZometa;

import com.MarceloZometa.State.*;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GasPump {
    private State currentState;
    private FuelType fueltype;
    private Card card;
    private Boolean available = true;
    private Car car;
    private boolean isPumping= false;
    private Double totalGas = 0.0;
    private Integer carsServed = 0;

    private Double LowGallonsPumped = 0.0;
    private Double MedGallonsPumped = 0.0;
    private Double HighGallonsPumped = 0.0;

    private IdleState _IdleState;
    private FuelSelectionState _FuelSelectionState;
    private PumpingState _PumpingState;
    private ReceiptState _ReceiptState;

    public boolean isPumping() {
        return isPumping;
    }

    public void setPumping(boolean pumping) {
        isPumping = pumping;
    }

    public GasPump(){
        this._IdleState = new IdleState();
        this._FuelSelectionState = new FuelSelectionState();
        this._PumpingState = new PumpingState();
        this._ReceiptState = new ReceiptState();
        this.setCurrentState(_IdleState);
    }

    public void changeToFuelSelecetion(){
        this.currentState = this._FuelSelectionState;
    }

    public void changeToPumpingState(){
        this.currentState = this._PumpingState;
    }

    public void changeToReceiptState() {
        this.currentState = this._ReceiptState;
    }

    public void cardSwipe(){
        currentState.cardSwipe(this);
    }

    public void hitLow(){
        currentState.hitLow(this);
    }

    public void hitMed(){
        currentState.hitMed(this);
    }
    public void hitHigh(){
        currentState.hitHigh(this);
    }

    public void trigger(){
        currentState.trigger(this);
    }

    public void pumpReturnLever(){
        currentState.pumpReturnLever(this);
    }

    public synchronized void assignCar(Car car) {

        if(this.car == null)
        {
            //System.out.println("^^ Car found gas pump ^^");
            this.setAvailable(false);
            this.car = car;
        }
        else
            GasStationSingleton.getInstance().incrementRejectedCars();
            //System.out.println("**** Pump is unavailable ****");

    }

    public void removeCar() {
        if(this.car != null){
            this.setAvailable(true);
            this.car = null;

            //System.out.println("Car finished");

        }
//        else
//            System.out.println("Car finished");
    }

    public void setCurrentState(State idleState) {
        this.currentState= idleState;
    }

    public State getCurrentState(){
        return currentState;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Card getCardInfo() {
        //possibly do a menu thing where the user inputs there own info
        //maybe just their pin

        //get card info
//        String lastName = "Reiss";
//        String firstName = "Marcelo";
//        String cardNumber = "1234567890";
//        Integer pin = 1234;
        return car.getCard();
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public void setFueltype(FuelType fueltype) {
        this.fueltype = fueltype;
    }
    public FuelType getFueltype(){
        return fueltype;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        if (car == null){
            this.car = null;
            return;
        }
        this.car = car;
        this.fueltype = car.getFuelType();
        this.card = car.getCard();
    }

    public Double getTotalGas() {
        return totalGas;
    }

    public Integer getCarsServed() {
        return carsServed;
    }

    public Double getLowGallonsPumped() {
        return LowGallonsPumped;
    }

    public Double getMedGallonsPumped() {
        return MedGallonsPumped;
    }

    public Double getHighGallonsPumped() {
        return HighGallonsPumped;
    }

    public void increaseCarsServed() {
        this.carsServed++;
        GasStationSingleton.getInstance().incrementCarsServed();
    }

    public synchronized void increaseGasTotal(){
        GasStationSingleton station = GasStationSingleton.getInstance();
        Double g = this.car.getRequestedGallons();

        switch(this.car.getFuelType()){
            case low:
                station.incrementLowGallonsPumped(g);
                this.LowGallonsPumped += this.car.getRequestedGallons();
                break;
            case Medium:
                GasStationSingleton.getInstance().incrementMedGallonsPumped(this.car.getRequestedGallons());
                this.MedGallonsPumped += this.car.getRequestedGallons();
                break;
            case High:
                GasStationSingleton.getInstance().incrementHighGallonsPumped(this.car.getRequestedGallons());
                this.HighGallonsPumped += this.car.getRequestedGallons();
                break;
            default:
                System.out.println("How did you get here?");
                break;
        }
        GasStationSingleton.getInstance().incrementGallonsPumped(this.car.getRequestedGallons());

        this.totalGas += this.car.getRequestedGallons();
    }
}
