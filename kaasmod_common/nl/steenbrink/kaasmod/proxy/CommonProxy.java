package nl.steenbrink.kaasmod.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import nl.steenbrink.kaasmod.container.ContainerKaascreator;
import nl.steenbrink.kaasmod.container.ContainerPurifier;
import nl.steenbrink.kaasmod.container.ContainerToaster;
import nl.steenbrink.kaasmod.gui.GUIKaascreator;
import nl.steenbrink.kaasmod.gui.GUIPurifier;
import nl.steenbrink.kaasmod.gui.GUIToaster;
import nl.steenbrink.kaasmod.lib.Reference;
import nl.steenbrink.kaasmod.tile.TileKaascreator;
import nl.steenbrink.kaasmod.tile.TilePurifier;
import nl.steenbrink.kaasmod.tile.TileToaster;
import cpw.mods.fml.common.network.IGuiHandler;


public class CommonProxy implements IGuiHandler{
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case Reference.GUI_KAASCREATOR_ID:
                TileKaascreator tileKaascreator = (TileKaascreator) world.getBlockTileEntity(x, y, z);
                return new ContainerKaascreator(player.inventory, tileKaascreator);
            case Reference.GUI_PURIFIER_ID:
                TilePurifier tilePurifier = (TilePurifier) world.getBlockTileEntity(x, y, z);
                return new ContainerPurifier(player.inventory, tilePurifier);
            case Reference.GUI_TOASTER_ID:
                TileToaster tileToaster = (TileToaster) world.getBlockTileEntity(x, y, z);
                return new ContainerToaster(player.inventory, tileToaster);
            default:
                return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case Reference.GUI_KAASCREATOR_ID:
                TileKaascreator tileKaascreator = (TileKaascreator) world.getBlockTileEntity(x, y, z);
                return new GUIKaascreator(player.inventory, tileKaascreator);
            case Reference.GUI_PURIFIER_ID:
                TilePurifier tilePurifier = (TilePurifier) world.getBlockTileEntity(x, y, z);
                return new GUIPurifier(player.inventory, tilePurifier);
            case Reference.GUI_TOASTER_ID:
                TileToaster tileToaster = (TileToaster) world.getBlockTileEntity(x, y, z);
                return new GUIToaster(player.inventory, tileToaster);
            default:
                return null;
        }
    }

}
