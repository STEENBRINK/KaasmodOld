package nl.steenbrink.kaasmod.block;

import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import nl.steenbrink.kaasmod.lib.Texture;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class KaasbrickdoubleslabBlock extends BlockHalfSlab {

    public KaasbrickdoubleslabBlock(int id, boolean isFull) {
        super(id, isFull, Material.rock);
        this.setHardness(2.0F);
        this.setResistance(10.0F);
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("kaasbrickdoubleslab");
    }

    @Override
    public String getFullSlabName(int i) {
        return null;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {
        this.blockIcon = Texture.getIconFromTextureName(par1IconRegister, "Kaasbrickslab");
    }

}

