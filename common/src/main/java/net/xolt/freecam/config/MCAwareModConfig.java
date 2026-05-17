package net.xolt.freecam.config;

import net.minecraft.world.level.block.Block;
import net.xolt.freecam.config.controller.ConfigControllerRegistry;
import net.xolt.freecam.config.model.ModConfigDTO;

/**
 * Extends {@link ModConfig} with Minecraft-aware features.
 */
public interface MCAwareModConfig extends ModConfig {

    static MCAwareModConfig get() {
        return ConfigControllerRegistry.get(ModConfigDTO.class).getConfig();
    }

}
