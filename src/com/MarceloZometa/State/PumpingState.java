package com.MarceloZometa.State;

import com.MarceloZometa.GasPump;

public class PumpingState implements State {
    @Override
    public void autoPumpStop(GasPump context) {
        context.setCurrentState( new PumpingState());
    }

    @Override
    public void cardSwipe(GasPump context) {

    }

    @Override
    public void hitLow(GasPump context) {

    }

    @Override
    public void hitMed(GasPump context) {

    }

    @Override
    public void hitHigh(GasPump context) {

    }

    @Override
    public void trigger(GasPump context) {
        context.setPumping(true);

        for(short i = 0; i < context.getCar().getRequestedGallons() * 10; i++){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        context.increaseGasTotal();

//        if(context.isPumping()){
//            //System.out.println("^^ Pumping fuel ^^");
//            //Logic to reduce the amount of gas needed
//            context.increaseGasTotal();
//
////            try {
////                Thread.sleep(1000);
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
//        }
//        else {
//            //System.out.println("**** Not pumping fuel ****");
//        }
    }

    @Override
    public void pumpReturnLever(GasPump context) {
        context.setPumping(false);

        context.setCurrentState(new ReceiptState());
    }
}
