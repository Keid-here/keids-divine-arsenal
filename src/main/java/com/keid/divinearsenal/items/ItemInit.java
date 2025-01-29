package com.keid.divinearsenal.items;

import com.keid.divinearsenal.items.alharu.trinkets.FightingSpirit;
import com.keid.divinearsenal.items.alharu.weapons.AlharusIronBlade;
import com.keid.divinearsenal.items.kiamaht.KiamahtIronBlade;
import com.keid.divinearsenal.items.nameless.trinkets.NamelessCompass;
import io.wispforest.owo.registration.reflect.ItemRegistryContainer;
import net.minecraft.world.item.Item;

public class ItemInit implements ItemRegistryContainer {

    public static final Item ALHARUSIRONBLADE = new AlharusIronBlade();
    public static final Item KIAMAHTIRONBLADE = new KiamahtIronBlade();
    public static final Item FIGHTINGSPIRIT = new FightingSpirit();
    public static final Item NAMELESSCOMPASS = new NamelessCompass();


}
