package com.keid.divinearsenal.gui.hud;

import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.core.*;
import io.wispforest.owo.ui.hud.Hud;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.text.Text;

import java.util.function.Supplier;

import static com.keid.divinearsenal.KeidsDivineArsenalClient.HUD;
import static com.keid.divinearsenal.data.DataInit.SPARK_COUNTER;

public class HudReg {
    public static void register() {
        final Supplier<Component> hudComponent = () ->
                Containers.verticalFlow(Sizing.content(), Sizing.content())
                        .child(Components.item(Items.DIAMOND.getDefaultStack()).margins(Insets.of(3)))
                        .child(Components.label(Text.literal("Spark Counter: " + SPARK_COUNTER.getValue(MinecraftClient.getInstance().player))))
                        .child(Components.entity(Sizing.fixed(50), EntityType.ALLAY, null))
                        .alignment(HorizontalAlignment.CENTER, VerticalAlignment.CENTER)
                        .padding(Insets.of(5))
                        .surface(Surface.PANEL)
                        .margins(Insets.of(5))
                        .positioning(Positioning.relative(100, 25));

        Hud.remove(HUD);
        Hud.add(HUD, hudComponent);
    }

}
