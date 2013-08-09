package nl.steenbrink.kaasmod.lib;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

/**
 * Mythical-Gods-Mod
 * 
 * @author msvisser
 *         Copyright (c) 2013
 */
public class Texture {
    /**
     * Get the texture Icon for the texture name without the MODID
     * 
     * @param iconRegister IconRegister
     * @param name The texture name
     * @return The texture Icon
     */
    public static Icon getIconFromTextureName(IconRegister iconRegister, String name) {
        return iconRegister.registerIcon(Reference.RESOURCE_FOLDER + ":" + name);
    }

}
