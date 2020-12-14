package com.MarceloZometa;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hi mom");

        //Phase 3 - (volume simulator) (30 points) 10 points code, 20 points proof of functionality)

        // make random number of cars
        //randomize card info fuel type and requested gallons
        ArrayList<Car> cars = new ArrayList<Car>();


        GasStationSingleton gasStationSingleton = GasStationSingleton.getInstance();

        for (int i = 0; i<10; i++){
            cars.add(randoCar());
        }

        for ( int i = 0; i <12; i++){
            GasPump gasPump = new GasPump();
            gasStationSingleton.addGasPump(gasPump);
        }




;


        // CODE:  CarArrivalSimulator encompases GasStation (which has pumps) and Car (which has demand and fuel type).

        // TEST: 2 cars at same time

        // TEST: 2 more cars than sum of pumps (14 cars on 12 pumps)

        // TEST: Display TOTAL from each PUMP: #car served, #gallons pumped

        // TEST: Display TOTAL from GasStation: total # cars served, #gallons pumped
    }


    // car : FuelType fuelType, Double requestedGallons, String carModel, Integer year, Card card
    //Card : String cardNumber, String lastName, int pin, String firstName
    public static Car randoCar(){
        Car car = new Car(randoFuelType(),randoFuelAmount(), randoModel(),randoYear(),randoCard());
        return car;

    }

    public static FuelType randoFuelType(){
        FuelType fuel ;
        int number = (int) (Math.random() * 3);

        switch (number) {
            case 0:
                fuel = FuelType.low;
                break;
            case 1:
                fuel = FuelType.Medium;
                break;
            case 2:
                fuel = FuelType.High;
                break;
            default:
                fuel= FuelType.low;
        }
        return fuel;
    }
    public static Double randoFuelAmount(){

        Double number = (Double) (Math.random() * 15);
        return 0.0;
    }

    public static String randoModel() {
        String model = new String();
        int number = (int) (Math.random() * 3);

        switch (number) {
            case 0:
                model = "Ford";
                break;
            case 1:
                model = "GM";
                break;
            case 2:
                model = "Hyundai";
                break;
            default:
                model = "Dodge";
        }
                return model;

    }

    public static Integer randoYear(){
            Integer year;
            int number = (int) (Math.random() * 3);

            switch (number) {
                case 0:
                    year = 1980;
                    break;
                case 1:
                    year = 2000;
                    break;
                case 2:
                    year = 2010;
                    break;
                default:
                    year = 2020;
            }
                    return year;
            }


    //Card : String cardNumber, String lastName, int pin, String firstName
    public static Card randoCard(){
                Card card = new Card(randoCardNumber(),randoLastName(),randoPin(), randoFirstName());
                return card;

            }
            public static String randoCardNumber(){
                int number = (int) (Math.random() * 3);
                String cardNumber = new String();

                switch (number) {
                    case 0:
                        cardNumber = "1980200020102020";
                        break;
                    case 1:
                        cardNumber = "1980200020102021";
                        break;
                    case 2:
                        cardNumber = "1980200020102022";
                        break;
                    default:
                        cardNumber = "1980200020102023";
                }
                return cardNumber;
            }

            public static String randoLastName(){
                int number = (int) (Math.random() * 3);
                String lasrName = new String();

                switch (number) {
                    case 0:
                        lasrName = "Johnson";
                        break;
                    case 1:
                        lasrName = "Smith";
                        break;
                    case 2:
                        lasrName = "Reiss";
                        break;
                    default:
                        lasrName = "Allen";
                }
                return lasrName;
            }

            public static int randoPin(){
                 int number = (int) (Math.random() * 1000+1000);

                return number;
            }

            public static String randoFirstName(){
                int number = (int) (Math.random() * 3);
                String firstName = new String();

                switch (number) {
                    case 0:
                        firstName = "Johnson";
                        break;
                    case 1:
                        firstName = "Smith";
                        break;
                    case 2:
                        firstName = "Reiss";
                        break;
                    default:
                        firstName = "Allen";
                }
                return firstName;

            }

        }