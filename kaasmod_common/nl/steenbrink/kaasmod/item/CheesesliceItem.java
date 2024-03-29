package nl.steenbrink.kaasmod.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import nl.steenbrink.kaasmod.lib.Texture;

public class CheesesliceItem extends ItemFood {
    
    public CheesesliceItem(int id) {
        super(id, 2, false);
        this.setCreativeTab(CreativeTabs.tabFood);        
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        itemIcon = Texture.getIconFromTextureName(iconRegister, "CheesesliceItem");
    }

}
