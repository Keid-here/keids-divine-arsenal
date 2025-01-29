package com.keid.divinearsenal.events;

import com.keid.divinearsenal.events.entity.ChickenHitCounter;
import com.keid.divinearsenal.events.trinkets.FightingSpiritHelper;
import com.keid.divinearsenal.events.trinkets.NamelessCompassHelper;

public class EventRegister {
    public static void go(){
        DataWipeOnDeath.register();
        ChickenHitCounter.register();
        FightingSpiritHelper.register();
        NamelessCompassHelper.register();
    }
}
