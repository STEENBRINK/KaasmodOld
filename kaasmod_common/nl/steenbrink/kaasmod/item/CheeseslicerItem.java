package nl.steenbrink.kaasmod.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemTool;
import nl.steenbrink.kaasmod.lib.Blocks;
import nl.steenbrink.kaasmod.lib.Texture;

public class CheeseslicerItem extends ItemTool {
    
    public static final Block[] blocksEffectiveAgainst = new Block[] {Blocks.kaasBlock};

    public CheeseslicerItem(int par1, EnumToolMaterial par2EnumToolMaterial)
    { 
        super(par1, 3, par2EnumToolMaterial, blocksEffectiveAgainst);
        this.setCreativeTab(CreativeTabs.tabTools);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        itemIcon = Texture.getIconFromTextureName(iconRegister, "CheeseslicerItem");

    }

}
