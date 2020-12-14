package com.MarceloZometa.State;

import com.MarceloZometa.GasPump;

public interface State {
    void autoPumpStop(GasPump context);
    void cardSwipe(GasPump context);
    void hitLow(GasPump context);
    void hitMed(GasPump context);
    void hitHigh(GasPump context);
    void trigger(GasPump context);
    void pumpReturnLever(GasPump context);
}
