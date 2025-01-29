package com.keid.divinearsenal.networking.packets;

import net.minecraft.resources.ResourceLocation;

public record skillPacketC2S(String skill, ResourceLocation aMinecraftClass) {
}
