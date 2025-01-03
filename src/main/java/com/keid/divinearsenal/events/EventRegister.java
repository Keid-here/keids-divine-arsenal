package com.keid.divinearsenal.events;

import com.keid.divinearsenal.events.entity.ChickenHitCounter;

public class EventRegister {
    public static void go(){
        ChickenHitCounter.register();
    }
}
