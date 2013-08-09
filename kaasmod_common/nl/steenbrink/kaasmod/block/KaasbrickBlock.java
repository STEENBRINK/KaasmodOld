package nl.steenbrink.kaasmod.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import nl.steenbrink.kaasmod.lib.Texture;

public class KaasbrickBlock extends Block {

    public KaasbrickBlock(int id) {
        super(id, Material.rock);
        this.setHardness(2.0F);
        this.setResistance(10.0F);
        this.setStepSound(soundStoneFootstep);
        this.setCreativeTab(CreativeTabs.tabBlock);
        
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {
        this.blockIcon = Texture.getIconFromTextureName(par1IconRegister, "Kaasbricks");
    }


}