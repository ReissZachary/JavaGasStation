import com.MarceloZometa.*;
import com.MarceloZometa.State.State;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GasPumpTest {
    //Because of the singleton
    @AfterEach
    public void destroy(){
        GasStationSingleton.getInstance().destroyGasStation();
    }

    @Test
    public void ShowPumpStatusTestBusy() {
        //FuelType fuelType, Double requestedGallons, String carModel, Integer year, Card card) {
        //       String cardNumber, String lastName, int pin, String firstName

        GasPump gaspump1= new GasPump();
        GasPump gasPump2 = new GasPump();
        FuelType fuel = FuelType.low;
        Double gallons = 10.0;
        String model= new String("Ford");
        Integer year = 2010;
        Card card = new Card("1232456", "Smith", 1234, "John");
        Car ford1 = new Car(fuel, gallons, model, year  ,card);

        //gaspump1.setCar(ford1);
        gaspump1.assignCar(ford1);

        assertEquals(false, gaspump1.getAvailable());

    }

    @Test
    public void ShowPumpStatusTestAvailable() {
        //FuelType fuelType, Double requestedGallons, String carModel, Integer year, Card card) {
        //       String cardNumber, String lastName, int pin, String firstName

        GasPump gaspump1= new GasPump();
        GasPump gasPump2 = new GasPump();
        FuelType fuel = FuelType.low;
        Double gallons = 10.0;
        String model= new String("Ford");
        Integer year = 2010;
        Card card = new Card("1232456", "Smith", 1234, "John");
        Car ford1 = new Car(fuel, gallons, model, year  ,card);

        //gaspump1.setCar(ford1);
        //gaspump1.assignCar(ford1);

        assertEquals(true, gaspump1.getAvailable());

    }

    @Test
    public void ShowOneCarPerPump() {
        //FuelType fuelType, Double requestedGallons, String carModel, Integer year, Card card) {
        //       String cardNumber, String lastName, int pin, String firstName

        GasPump gaspump1= new GasPump();
        //GasPump gasPump2 = new GasPump();
        FuelType fuel = FuelType.low;
        Double gallons = 10.0;
        String model= new String("Ford");
        Integer year = 2010;
        Card card = new Card("1232456", "Smith", 1234, "John");
        Car ford1 = new Car(fuel, gallons, model, year  ,card);
        Car ford2 = new Car(fuel, gallons, model, year  ,card);

        //gaspump1.setCar(ford1);
        gaspump1.assignCar(ford1);
        gaspump1.assignCar(ford2);

        assertEquals(false, gaspump1.getAvailable());
    }

    @Test
    public void MultiplePumpsCanBeUsed() {
        //FuelType fuelType, Double requestedGallons, String carModel, Integer year, Card card) {
        //       String cardNumber, String lastName, int pin, String firstName

        GasStationSingleton gasStationSingleton = GasStationSingleton.getInstance();
        GasPump gaspump1= new GasPump();
        GasPump gasPump2 = new GasPump();
        gasStationSingleton.addGasPump(gaspump1);
        gasStationSingleton.addGasPump(gasPump2);


        FuelType fuel = FuelType.low;
        Double gallons = 10.0;
        String model= new String("Ford");
        Integer year = 2010;
        Card card = new Card("1232456", "Smith", 1234, "John");
        Car ford1 = new Car(fuel, gallons, model, year  ,card);
        Car ford2 = new Car(fuel, gallons, model, year  ,card);

        //gaspump1.setCar(ford1);
        //gaspump1 .assignCar(ford1);
        //gaspump1.assignCar(ford2);

        gasStationSingleton.assignCarToPump(ford1);
        gasStationSingleton.assignCarToPump(ford2);

        assertEquals(0, gasStationSingleton.countAvailablePumps());

    }

    @Test
    public void RequestFuelFromAPump() {
        //FuelType fuelType, Double requestedGallons, String carModel, Integer year, Card card) {
        //       String cardNumber, String lastName, int pin, String firstName


        State currentState;

        GasStationSingleton gasStationSingleton = GasStationSingleton.getInstance();
        GasPump gaspump1= new GasPump();
        GasPump gasPump2 = new GasPump();
        gasStationSingleton.addGasPump(gaspump1);
        gasStationSingleton.addGasPump(gasPump2);


        FuelType fuel = FuelType.low;
        Double gallons = 10.0;
        String model= new String("Ford");
        Integer year = 2010;
        Card card = new Card("1232456", "Smith", 1234, "John");
        Car ford1 = new Car(fuel, gallons, model, year  ,card);
        Car ford2 = new Car(fuel, gallons, model, year  ,card);

        //gaspump1.setCar(ford1);
        //gaspump1 .assignCar(ford1);
        //gaspump1.assignCar(ford2);

        gasStationSingleton.assignCarToPump(ford1);
        gasStationSingleton.assignCarToPump(ford2);

        gaspump1.setFueltype(ford1.getFuelType());
       currentState= gaspump1.getCurrentState();
        System.out.println(currentState);

        assertEquals(0, gasStationSingleton.countAvailablePumps());

        //add action to get fuel
        //state should change to pumping
    }

    // TEST: 2 cars at same time
    @Test
    public void cars2atATime(){
        State currentState;

        //Initializing gas station
        GasStationSingleton gasStationSingleton = GasStationSingleton.getInstance();
        GasPump gaspump1= new GasPump();
        GasPump gasPump2 = new GasPump();
        gasStationSingleton.addGasPump(gaspump1);
        gasStationSingleton.addGasPump(gasPump2);

        //Creation of cars
        FuelType fuel = FuelType.low;
        Double gallons = 10.0;
        String model= new String("Ford");
        Integer year = 2010;
        Card card = new Card("1232456", "Smith", 1234, "John");
        Car ford1 = new Car(fuel, gallons, model, year  ,card);
        Car ford2 = new Car(fuel, gallons, model, year  ,card);

        //gaspump1.setCar(ford1);
        //gaspump1 .assignCar(ford1);
        //gaspump1.assignCar(ford2);

        Thread thread1 = new Thread(ford1);
        Thread thread2 = new Thread(ford2);

        thread1.start();
        thread2.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertTrue(ford1.getAtPump());
        assertTrue(ford2.getAtPump());
    }

    // TEST: Display TOTAL from each PUMP: #car served, #gallons pumped
    @Test
    public void CarRequestLow20GalPumpGive20GalLow(){
        State currentState;

        GasStationSingleton gasStationSingleton = GasStationSingleton.getInstance();
        GasPump gaspump1= new GasPump();
        //GasPump gasPump2 = new GasPump();
        gasStationSingleton.addGasPump(gaspump1);
        //gasStationSingleton.addGasPump(gasPump2);


        FuelType fuel = FuelType.low;
        Double gallons = 10.0;
        String model= new String("Ford");
        Integer year = 2010;
        Card card = new Card("1232456", "Smith", 1234, "John");
        Car ford1 = new Car(fuel, gallons, model, year  ,card);
        Car ford2 = new Car(fuel, gallons, model, year  ,card);

        Thread thread1 = new Thread(ford1);
        Thread thread2 = new Thread(ford2);

        System.out.println("Pump 1 is " + gaspump1.getAvailable());
        thread1.start();
        System.out.println("I am starting car1");
        thread2.start();
        System.out.println("I am starting car2");

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Gas pump 1 pumped " + gaspump1.getTotalGas() + " gallons of gas");

        assertEquals(FuelType.low, gaspump1.getFueltype());
        assertEquals(20, gaspump1.getTotalGas());
        assertEquals(2, gaspump1.getCarsServed());
    }

    // TEST: Display TOTAL from GasStation: total # cars served, #gallons pumped
    @Test
    public void displayTotalGasStaion(){
        State currentState;

        GasStationSingleton gasStationSingleton = GasStationSingleton.getInstance();
        GasPump gaspump1= new GasPump();
        GasPump gasPump2 = new GasPump();
        gasStationSingleton.addGasPump(gaspump1);
        gasStationSingleton.addGasPump(gasPump2);


        FuelType fuel = FuelType.low;
        Double gallons = 10.0;
        String model= new String("Ford");
        Integer year = 2010;
        Card card = new Card("1232456", "Smith", 1234, "John");
        Car ford1 = new Car(fuel, gallons, model, year  ,card);
        Car ford2 = new Car(fuel, gallons, model, year  ,card);
        Car ford3 = new Car(fuel, gallons, model, year  ,card);
        Car ford4 = new Car(fuel, gallons, model, year  ,card);

        //gaspump1.setCar(ford1);
        //gaspump1 .assignCar(ford1);
        //gaspump1.assignCar(ford2);

        Thread thread1 = new Thread(ford1);
        Thread thread2 = new Thread(ford2);
        Thread thread3 = new Thread(ford3);
        Thread thread4 = new Thread(ford4);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(40, GasStationSingleton.getInstance().getGallonsPumped());
        assertEquals(4, GasStationSingleton.getInstance().getCarsServed());
    }
}