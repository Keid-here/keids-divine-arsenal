package com.keid.divinearsenal.gui.hud;

import com.mrcrayfish.framework.api.sync.SyncedDataKey;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.core.*;
import io.wispforest.owo.ui.hud.Hud;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.Items;

import static com.keid.divinearsenal.KeidsDivineArsenalClient.HUD;
import static com.keid.divinearsenal.data.DataInit.HATE_COUNTER;
import static com.keid.divinearsenal.data.DataInit.SPARK_COUNTER;

public class HudReg {
    public static void register() {
        final Supplier<Component> hudComponent = () -> {
            Containers.verticalFlow(Sizing.content(), Sizing.content()).id("main");
            for (SyncedDataKey key : CounterList())  {
                if (Integer.parseInt(key.getValue(Minecraft.getInstance().player).toString()) > 0 ) {
                    return Containers.verticalFlow(Sizing.content(), Sizing.content())
                            .child(Components.item(Items.DIAMOND.getDefaultInstance()).margins(Insets.of(3)))
                            .child(Components.label(net.minecraft.network.chat.Component.literal(key.getValue(Minecraft.getInstance().player) + key.id().toString())))
                            .alignment(HorizontalAlignment.CENTER, VerticalAlignment.CENTER)
                            .padding(Insets.of(5))
                            .surface(Surface.PANEL)
                            .margins(Insets.of(5))
                            .positioning(Positioning.relative(100, 25));
                }
            }
            {return Containers.verticalFlow(Sizing.fixed(5), Sizing.fixed(5))
                    .positioning(Positioning.relative(100, 25));}
        };

                /* Containers.verticalFlow(Sizing.content(), Sizing.content())
                    .child(Components.item(Items.DIAMOND.getDefaultStack()).margins(Insets.of(3)))
                    .child(Components.label(Text.literal("Spark Counter: " + SPARK_COUNTER.getValue(MinecraftClient.getInstance().player))))
                    .child(Components.entity(Sizing.fixed(50), EntityType.ALLAY, null))
                    .alignment(HorizontalAlignment.CENTER, VerticalAlignment.CENTER)
                    .padding(Insets.of(5))
                    .surface(Surface.PANEL)
                    .margins(Insets.of(5))
                    .positioning(Positioning.relative(100, 25));

                 */

        Hud.remove(HUD);
        Hud.add(HUD, hudComponent);
    }

    public static List<SyncedDataKey> CounterList() {
        return Arrays.asList(
                SPARK_COUNTER, HATE_COUNTER
        );
    }

}
