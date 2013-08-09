package nl.steenbrink.kaasmod.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import nl.steenbrink.kaasmod.lib.Texture;

public class SaltItem extends Item {
    public SaltItem(int id) {
        super(id);
        this.setCreativeTab(CreativeTabs.tabMaterials);        
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        itemIcon = Texture.getIconFromTextureName(iconRegister, "SaltItem");

    }

}
