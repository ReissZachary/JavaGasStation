import com.MarceloZometa.*;
import com.MarceloZometa.State.State;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class Scenario2 {
    //Because of the singleton
    @AfterEach
    public void destroy(){
        GasStationSingleton.getInstance().destroyGasStation();
    }

    @Test
    public void scenario2(){
        //Station should have 12 pumps
        //24 cars
        //Each car 7 gallons of low
        List<GasPump> gasPumps = new ArrayList<>(12);
        List<Car> cars = new ArrayList<>(24);
        List<Thread> threads = new ArrayList<>(24);

        Card card = new Card("1232456", "Smith", 1234, "John");
        short j = 0;
        Double sumOfPumps = 0.0;

        //create 12 pumps
        for(short i = 0; i < 12; i++){
            gasPumps.add(new GasPump());
        }

        //create 24 cars
        for(short i = 0; i < 24; i++){
            cars.add(new Car(FuelType.low, 7.00, "Chevy", 2021, card));
        }

        //create 24 threads
        for(Car car : cars){
            threads.add(new Thread(car));
        }

        //Add pumps to gas staiton
        for(GasPump pump : gasPumps){
            GasStationSingleton.getInstance().addGasPump(pump);
        }

        //Start all threads
        for(Thread t : threads){
            t.start();
        }

        //Wait for threads to finish
        for(Thread t: threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        assertEquals(168, GasStationSingleton.getInstance().getLowGallonsPumped());

        for(GasPump p : gasPumps){
            System.out.println("GasPump" + (j+1) + " pumped " + p.getTotalGas());
            sumOfPumps += p.getTotalGas();
            j++;
        }

        assertEquals(168, sumOfPumps);
        assertEquals(24, GasStationSingleton.getInstance().getCarsServed());
        System.out.println("Gas station served " + GasStationSingleton.getInstance().getCarsServed() + " of 24 cars");

    }
}
