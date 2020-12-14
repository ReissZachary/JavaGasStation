package com.MarceloZometa;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GasStationSingleton {
    private static GasStationSingleton gasStationSingleton;

    private Double lowRate;
    private Double mediumRate;
    private Double highRate;

    private Double LowGallonsPumped = 0.0;
    private Double MedGallonsPumped = 0.0;
    private Double HighGallonsPumped = 0.0;
    private Double gallonsPumped =0.0;

    private Integer carsServed = 0;
    private Double gasTank;
    private int rejectedCars=0;

    private ArrayList<GasPump> gasPumps = new ArrayList<GasPump>(12);

    private GasStationSingleton(){
    }

    public void destroyGasStation(){
        gasStationSingleton = null;
    }

    public static GasStationSingleton getInstance(){
        if(gasStationSingleton == null){
            gasStationSingleton = new GasStationSingleton();
            return gasStationSingleton;
        }
        return gasStationSingleton;
    }

    public int getRejectedCars() {
        return rejectedCars;
    }

    public void setRejectedCars(int rejectedCars) {
        this.rejectedCars = rejectedCars;
    }

    public void addGasPump(GasPump gasPump){
        if(gasPumps.size() < 12)
            gasPumps.add(gasPump);
        else
            System.out.println("**** Cannot add pump. At Max capacity. ****");
    }

    public Boolean assignCarToPump(Car car){
        //Try finding a gas pump 5 times
        //for(short attempts = 0; attempts < 2; attempts++){
        boolean isAssigned = false;

        do{
            for(GasPump pump : gasPumps){
                synchronized (pump){
                    if(pump.getAvailable() == true) {
                        pump.assignCar(car);
                        isAssigned = true;
                        return true;
                    }
                }
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while(isAssigned == false);

        //}
        //System.out.println("************* Car was rejected *************");
        //rejectedCars++;

        return false;
    }

    public int countAvailablePumps(){
        int count = 0;
        for(GasPump pump : gasPumps){
            if(pump.getAvailable()==true){
                count++;
            }
        }
        return count;
    }

    public synchronized void incrementGallonsPumped(Double gallons){
        this.gallonsPumped += gallons;
    }

    public synchronized void incrementCarsServed(){
        this.carsServed++;
    }

    public synchronized Double getGallonsPumped() {
        return gallonsPumped;
    }

    public synchronized Integer getCarsServed() {
        return carsServed;
    }

    public Double getLowRate() {
        return lowRate;
    }

    public void setLowRate(Double lowRate) {
        this.lowRate = lowRate;
    }

    public Double getMediumRate() {
        return mediumRate;
    }

    public void setMediumRate(Double mediumRate) {
        this.mediumRate = mediumRate;
    }

    public Double getHighRate() {
        return highRate;
    }

    public void setHighRate(Double highRate) {
        this.highRate = highRate;
    }

    public synchronized GasPump getGasPump(Car car){
        for(GasPump p : gasPumps){
            if(p.getCar() == car){
                return  p;
            }
        }
        return null;
    }

    public synchronized void incrementRejectedCars() {
        rejectedCars++;
    }

    public synchronized void incrementLowGallonsPumped(Double requestedGallons) {
        this.LowGallonsPumped += requestedGallons;
    }

    public synchronized void incrementMedGallonsPumped(Double requestedGallons) {
        this.MedGallonsPumped += requestedGallons;
    }

    public synchronized void incrementHighGallonsPumped(Double requestedGallons) {
        this.HighGallonsPumped += requestedGallons;
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
}
