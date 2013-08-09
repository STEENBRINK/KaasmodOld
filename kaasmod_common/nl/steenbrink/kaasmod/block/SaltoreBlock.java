package nl.steenbrink.kaasmod.block;

import java.util.Random;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockOre;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import nl.steenbrink.kaasmod.lib.Items;
import nl.steenbrink.kaasmod.lib.Texture;

public class SaltoreBlock extends BlockOre {

    public SaltoreBlock(int id) {
        super(id);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setStepSound(soundStoneFootstep);
        this.setHardness(3.0F);
    }

    @Override
    public int idDropped(int par1, Random par1Random, int par3) {
        return Items.rawsaltItem.itemID;
    }

    @Override
    public int quantityDropped(Random par1Random) {
        return 1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {
        this.blockIcon = Texture.getIconFromTextureName(par1IconRegister,
                "RawsaltOre");
    }

}
