package com.keid.divinearsenal.data.counter;

import static com.keid.divinearsenal.data.DataInit.SPARK_COUNTER;
import static com.keid.divinearsenal.data.DataInit.SPARK_COUNTER_CAP;

import net.minecraft.server.level.ServerPlayer;

public class SparkHelper {
    public static void add(ServerPlayer player, int amount) {
        int cap = SPARK_COUNTER_CAP.getValue(player);
        int spark = SPARK_COUNTER.getValue(player);

        SPARK_COUNTER.setValue(player, Math.min(cap, spark + amount));
    }

    /**
     * @param amount will return false if the amount is higher than the owned counters, they will not be removed
     */
    public static boolean remove(ServerPlayer player, int amount, boolean enforceCost) {
        int spark = SPARK_COUNTER.getValue(player);

        if (enforceCost) {
            if (spark - amount < 0) {
                return false;
            } else {
                SPARK_COUNTER.setValue(player, spark - amount);
                return true;
            }
        }else {
            SPARK_COUNTER.setValue(player, Math.max(spark - amount, 0));
            return true;
        }
    }
}
