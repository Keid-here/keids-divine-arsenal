package com.keid.divinearsenal.data;

import com.keid.divinearsenal.events.entity.ChickenHitCounter;
import com.keid.divinearsenal.skills.GenericSkill;
import com.mrcrayfish.framework.api.FrameworkAPI;
import com.mrcrayfish.framework.api.sync.Serializers;
import com.mrcrayfish.framework.api.sync.SyncedClassKey;
import com.mrcrayfish.framework.api.sync.SyncedDataKey;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.player.Player;

import static com.keid.divinearsenal.KeidsDivineArsenal.MOD_ID;

public class DataInit implements ModInitializer {

    public static final SyncedDataKey<Chicken, Integer> HIT_COUNT = SyncedDataKey.builder(SyncedClassKey.CHICKEN, Serializers.INTEGER)
            .id(new ResourceLocation(MOD_ID, "hit_count"))
            .defaultValueSupplier(() -> 0)
            .saveToFile()
            .syncMode(SyncedDataKey.SyncMode.TRACKING_ONLY)
            .build();

    //Skill slots
    public static final SyncedDataKey<Player, String> ACTIVE_SKILL_SLOT_1 = SyncedDataKey.builder(SyncedClassKey.PLAYER, Serializers.STRING)
            .id(new ResourceLocation(MOD_ID, "active_skill_slot_1"))
            .defaultValueSupplier(() -> "")
            .saveToFile()
            .syncMode(SyncedDataKey.SyncMode.SELF_ONLY)
            .build();



    //Spark Counter
    public static final SyncedDataKey<Player, Integer> SPARK_COUNTER = SyncedDataKey.builder(SyncedClassKey.PLAYER, Serializers.INTEGER)
            .id(new ResourceLocation(MOD_ID, "spark_counter"))
            .defaultValueSupplier(() -> 0)
            .saveToFile()
            .resetOnDeath()
            .syncMode(SyncedDataKey.SyncMode.SELF_ONLY)
            .build();
    public static final SyncedDataKey<Player, Integer> SPARK_COUNTER_CAP = SyncedDataKey.builder(SyncedClassKey.PLAYER, Serializers.INTEGER)
            .id(new ResourceLocation(MOD_ID, "spark_counter_cap"))
            .defaultValueSupplier(() -> 30)
            .saveToFile()
            .syncMode(SyncedDataKey.SyncMode.SELF_ONLY)
            .build();

    //Hate Counter
    public static final SyncedDataKey<Player, Integer> HATE_COUNTER = SyncedDataKey.builder(SyncedClassKey.PLAYER, Serializers.INTEGER)
            .id(new ResourceLocation(MOD_ID, "hate_counter"))
            .defaultValueSupplier(() -> 0)
            .saveToFile()
            .resetOnDeath()
            .syncMode(SyncedDataKey.SyncMode.SELF_ONLY)
            .build();
    public static final SyncedDataKey<Player, Integer> HATE_COUNTER_CAP = SyncedDataKey.builder(SyncedClassKey.PLAYER, Serializers.INTEGER)
            .id(new ResourceLocation(MOD_ID, "hate_counter_cap"))
            .defaultValueSupplier(() -> 10)
            .saveToFile()
            .syncMode(SyncedDataKey.SyncMode.SELF_ONLY)
            .build();

    @Override
    public void onInitialize() {

        FrameworkAPI.registerSyncedDataKey(HIT_COUNT);

        FrameworkAPI.registerSyncedDataKey(ACTIVE_SKILL_SLOT_1);

        FrameworkAPI.registerSyncedDataKey(SPARK_COUNTER);
        FrameworkAPI.registerSyncedDataKey(SPARK_COUNTER_CAP);

        FrameworkAPI.registerSyncedDataKey(HATE_COUNTER);
        FrameworkAPI.registerSyncedDataKey(HATE_COUNTER_CAP);




    }
}
