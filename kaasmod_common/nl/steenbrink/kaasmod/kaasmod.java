package nl.steenbrink.kaasmod;

import net.minecraft.util.ResourceLocation;
import nl.steenbrink.kaasmod.lib.Blocks;
import nl.steenbrink.kaasmod.lib.Items;
import nl.steenbrink.kaasmod.lib.Recipes;
import nl.steenbrink.kaasmod.lib.Reference;
import nl.steenbrink.kaasmod.lib.ReferenceID;
import nl.steenbrink.kaasmod.lib.SaltGenerator;
import nl.steenbrink.kaasmod.network.PacketHandler;
import nl.steenbrink.kaasmod.proxy.CommonProxy;
import nl.steenbrink.kaasmod.tile.TileKaascreator;
import nl.steenbrink.kaasmod.tile.TilePurifier;
import nl.steenbrink.kaasmod.tile.TileToaster;
import nl.steenbrink.kaasmod.villager.JannekeVillageTrades;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;

/**
 * Steenbrink's Kaasmod
 * 
 * @author STEENBRINK
 *         Copyright (c) 2013
 */

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
@NetworkMod(channels = {Reference.CHANNEL_NAME}, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandler.class)
public class kaasmod {


    
    @Instance(Reference.MOD_ID)
    public static kaasmod instanceK;
    
    @SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY)
    public static CommonProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        Blocks.init();
        Items.init();
        
        Recipes.init();
        
        GameRegistry.registerWorldGenerator(new SaltGenerator());
        
        VillagerRegistry.instance().registerVillagerId(ReferenceID.JANNEKE);
        VillagerRegistry.instance().registerVillagerSkin(ReferenceID.JANNEKE, new ResourceLocation(Reference.PATH_RESOURCES, Reference.PATH_JANNEKE + "janneke.png"));
        VillagerRegistry.instance().registerVillageTradeHandler(ReferenceID.JANNEKE, new JannekeVillageTrades());
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.instance().registerGuiHandler(instanceK, proxy);
        GameRegistry.registerTileEntity(TileKaascreator.class, "KaascreatorTile");
        GameRegistry.registerTileEntity(TilePurifier.class, "PurifierTile");
        GameRegistry.registerTileEntity(TileToaster.class, "ToasterTile");
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
        
    }

}
