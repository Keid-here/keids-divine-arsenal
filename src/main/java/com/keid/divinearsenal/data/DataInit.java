package com.keid.divinearsenal.data;

import com.keid.divinearsenal.events.entity.ChickenHitCounter;
import com.mrcrayfish.framework.api.FrameworkAPI;
import com.mrcrayfish.framework.api.sync.Serializers;
import com.mrcrayfish.framework.api.sync.SyncedClassKey;
import com.mrcrayfish.framework.api.sync.SyncedDataKey;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;

import static com.keid.divinearsenal.KeidsDivineArsenal.MOD_ID;

public class DataInit implements ModInitializer {

    public static final SyncedDataKey<ChickenEntity, Integer> HIT_COUNT = SyncedDataKey.builder(SyncedClassKey.CHICKEN, Serializers.INTEGER)
            .id(new Identifier(MOD_ID, "hit_count"))
            .defaultValueSupplier(() -> 0)
            .saveToFile()
            .syncMode(SyncedDataKey.SyncMode.TRACKING_ONLY)
            .build();

    @Override
    public void onInitialize() {

        FrameworkAPI.registerSyncedDataKey(HIT_COUNT);


    }
}
