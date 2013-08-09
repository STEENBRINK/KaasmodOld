package nl.steenbrink.kaasmod.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemReed;
import nl.steenbrink.kaasmod.lib.Blocks;
import nl.steenbrink.kaasmod.lib.Texture;

public class KaasItem extends ItemReed {

    public KaasItem(int id) {
        super(id, Blocks.kaasBlock);
        this.setCreativeTab(CreativeTabs.tabFood);
        this.setMaxStackSize(64);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        itemIcon = Texture.getIconFromTextureName(iconRegister, "KaasItem");

    }
}