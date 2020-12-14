import com.MarceloZometa.*;
import com.MarceloZometa.State.State;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class Scenario1 {
    //Because of the singleton
    @AfterEach
    public void destroy(){
        GasStationSingleton.getInstance().destroyGasStation();
    }

    @Test
    public void scenario1Test(){
        GasStationSingleton gasStationSingleton = GasStationSingleton.getInstance();
        GasPump gasPump1= new GasPump();
        GasPump gasPump2 = new GasPump();
        gasStationSingleton.addGasPump(gasPump1);
        gasStationSingleton.addGasPump(gasPump2);

        FuelType fuel = FuelType.low;
        String model= new String("Ford");
        Integer year = 2010;
        Card card = new Card("1232456", "Smith", 1234, "John");
        Car car1 = new Car(fuel, 10.0, model, year  ,card);
        Car car2 = new Car(fuel, 8.0, model, year  ,card);

        Thread thread1 = new Thread(car1);
        Thread thread2 = new Thread(car2);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
            //Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(18, GasStationSingleton.getInstance().getLowGallonsPumped());
        assertEquals(2, GasStationSingleton.getInstance().getCarsServed());

        System.out.println("GasPUmp 1 pumped " + gasPump1.getTotalGas());
        System.out.println("GasPump 2 pumped " + gasPump2.getTotalGas());
        System.out.println("Gas station served " + GasStationSingleton.getInstance().getCarsServed() + " of 2 cars");
//        assertEquals(10, gasPump1.getTotalGas());
//        assertEquals(8, gasPump2.getTotalGas());
    }
}