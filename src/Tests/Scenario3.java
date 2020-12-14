import com.MarceloZometa.*;
import com.MarceloZometa.State.State;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class Scenario3 {
    //Because of the singleton
    @AfterEach
    public void destroy(){
        GasStationSingleton.getInstance().destroyGasStation();
    }

    @Test
    public void scenario3(){
        List<GasPump> gasPumps = new ArrayList<>(12);

        List<Car> highCars = new ArrayList<>(8);
        List<Car> medCars = new ArrayList<>(10);
        List<Car> lowCars = new ArrayList<>(7);

        List<Thread> highThreads = new ArrayList<>(8);
        List<Thread> medThreads = new ArrayList<>(10);
        List<Thread> lowThreads = new ArrayList<>(7);
        List<Thread> masterThread = new ArrayList<>();

        Card card = new Card("1232456", "Smith", 1234, "John");
        short j = 0;
        Double sumOfPumps = 0.0;
        Double lowSum = 0.0;
        Double medSum = 0.0;
        Double highSum = 0.0;

        //create 8 cars asking for high gas
        for(short i = 0; i < 8; i++){
            highCars.add(new Car(FuelType.High, 8.00, "Chevy", 2021, card));
        }

        //create 10 cars asking for med gas
        for(short i = 0; i < 10; i++){
            medCars.add(new Car(FuelType.Medium, 10.00, "Chevy", 2021, card));
        }

        //create 7 cars asking for low gas
        for(short i = 0; i < 7; i++){
            lowCars.add(new Car(FuelType.low, 7.00, "Chevy", 2021, card));
        }

        //create 12 pumps
        for(short i = 0; i < 12; i++){
            gasPumps.add(new GasPump());
        }

        //Add pumps to gas staiton
        for(GasPump pump : gasPumps){
            GasStationSingleton.getInstance().addGasPump(pump);
        }

        //create 8 high threads
        for(Car car : highCars){
            highThreads.add(new Thread(car));
        }

        //create 10 med threads
        for(Car car : medCars){
            medThreads.add(new Thread(car));
        }

        //create 7 med threads
        for(Car car : lowCars){
            lowThreads.add(new Thread(car));
        }

        //Concatenete threads to a loop
        masterThread.addAll(highThreads);
        masterThread.addAll(medThreads);
        masterThread.addAll(lowThreads);

        //Start all threads
        for(Thread t : masterThread){
            t.start();
        }

        //Start all threads
        for(Thread t : masterThread){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        assertEquals(64, GasStationSingleton.getInstance().getHighGallonsPumped());
        assertEquals(100, GasStationSingleton.getInstance().getMedGallonsPumped());
        assertEquals(49, GasStationSingleton.getInstance().getLowGallonsPumped());

        for(GasPump p : gasPumps){
            System.out.println("GasPump" + (j+1) + " pumped: " + p.getLowGallonsPumped() + " of low, " +
                    p.getMedGallonsPumped() + " of medium, and " + p.getHighGallonsPumped() + " of high");

            sumOfPumps += p.getTotalGas();
            lowSum += p.getLowGallonsPumped();
            medSum += p.getMedGallonsPumped();
            highSum += p.getHighGallonsPumped();

            j++;
        }

        assertEquals(64, highSum);
        assertEquals(100, medSum);
        assertEquals(49, lowSum);

        assertEquals(25, GasStationSingleton.getInstance().getCarsServed());
        System.out.println("Gas station served " + GasStationSingleton.getInstance().getCarsServed() + " of 25 cars");

    }
}
