package nl.steenbrink.kaasmod.lib;

/**
 * Steenbrink's Kaasmod
 * 
 * @author STEENBRINK
 *         Copyright (c) 2013
 */

public class Reference {

    public static final String MOD_ID = "Kaasmod";
    public static final String MOD_NAME = "Steenbrink's Kaasmod";
    public static final String VERSION = "0.0.1";
    public static final String CHANNEL_NAME = MOD_ID;
    public static final String CLIENT_PROXY = "nl.steenbrink.kaasmod.proxy.ClientProxy";
    public static final String SERVER_PROXY = "nl.steenbrink.kaasmod.proxy.CommonProxy";
    
    public static final String RESOURCE_FOLDER = MOD_ID.toLowerCase();
    public static final String PATH_RESOURCES = "/assets/" + RESOURCE_FOLDER + "/";
    public static final String PATH_TEXTURES = "textures/";
    public static final String PATH_GUI = PATH_TEXTURES + "gui/";
    public static final String PATH_JANNEKE = PATH_TEXTURES + "entity/";
    
    public static final int GUI_KAASCREATOR_ID = 200;
    public static final int GUI_PURIFIER_ID = 201;
    public static final int GUI_TOASTER_ID = 202;
}
