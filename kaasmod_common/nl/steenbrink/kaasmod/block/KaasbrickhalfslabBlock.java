package nl.steenbrink.kaasmod.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import nl.steenbrink.kaasmod.lib.Blocks;
import nl.steenbrink.kaasmod.lib.Texture;

public class KaasbrickhalfslabBlock extends BlockHalfSlab {

    public KaasbrickhalfslabBlock(int id, boolean isFull) {
        super(id, isFull, Material.rock);
        this.setHardness(2.0F);
        this.setResistance(10.0F);
        this.setStepSound(soundStoneFootstep);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setUnlocalizedName("kaasbrickhalfslab");
    }
    
    
     
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {
        this.blockIcon = Texture.getIconFromTextureName(par1IconRegister, "Kaasbrickslab");
    }



    @Override
    public String getFullSlabName(int i) {
        return Blocks.kaasbrickdoubleslabBlock.getUnlocalizedName();
    }
    
    protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(Blocks.kaasbrickdoubleslabBlock);
    }
}
