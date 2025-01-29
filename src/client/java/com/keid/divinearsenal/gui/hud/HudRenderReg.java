package com.keid.divinearsenal.gui.hud;

import com.mrcrayfish.framework.api.sync.SyncedDataKey;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;

import java.util.Arrays;
import java.util.List;

import static com.keid.divinearsenal.KeidsDivineArsenal.MOD_ID;
import static com.keid.divinearsenal.KeidsDivineArsenalClient.hudToggle;
import static com.keid.divinearsenal.data.DataInit.HATE_COUNTER;
import static com.keid.divinearsenal.data.DataInit.SPARK_COUNTER;

public class HudRenderReg {
    private static float totalTickDelta;

    public static void register() {
        HudRenderCallback.EVENT.register((context, tickDelta) -> {

            if (hudToggle){
                int y = 100;
                for (SyncedDataKey key : CounterArray())  {
                    if (Integer.parseInt(key.getValue(Minecraft.getInstance().player).toString()) > 0 ){

                        //texture
                        String path = "textures/icons/" + key.id().getPath() + ".png";
                        ResourceLocation texture = new ResourceLocation(MOD_ID, path);
                        // texture, x, y, u, v, width, height, textureWidth, textureHeight
                        context.blit(texture, 5, y, 0, 0, 16, 16, 16, 16);
                        y = y + 3;

                        //text
                        context.drawString(Minecraft.getInstance().gui.getFont(), key.getValue(Minecraft.getInstance().player).toString(), 20, y, 0xFFFFFFFF, true);
                        y = y + 13;
                    }
                }
            }
        });
    }

    //contains every counter. put in pls :3
    public static List<SyncedDataKey> CounterArray() {
        return Arrays.asList(
                SPARK_COUNTER, HATE_COUNTER
        );
    }
}


