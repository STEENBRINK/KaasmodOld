package nl.steenbrink.kaasmod.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockCake;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import nl.steenbrink.kaasmod.lib.Items;
import nl.steenbrink.kaasmod.lib.Texture;

/**
 * Steenbrink's Kaasmod
 * 
 * @author STEENBRINK
 *         Copyright (c) 2013
 */

public class KaasBlock extends BlockCake {
    
    @SideOnly(Side.CLIENT)
    private Icon iconTop;
    @SideOnly(Side.CLIENT)
    private Icon iconBottom;
    @SideOnly(Side.CLIENT)
    private Icon iconInside;
    

    public KaasBlock(int id) {
        super(id);
        this.setStepSound(soundClothFootstep);
        this.setHardness(0.5F);
    }

    @Override
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) {
        if (!world.isRemote && meta < 6) {

            EntityItem k = new EntityItem(world, x + 0.5, y + 0.5, z + 0.5, new ItemStack(Items.cheesesliceItem, 6 - meta));
            k.setVelocity(0, 0.2, 0);
            world.spawnEntityInWorld(k);
        }
    }
    
    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int side, int metadata)
    {
        return side == 1 ? this.iconTop : (side == 0 ? this.iconBottom : (metadata > 0 && side == 4 ? this.iconInside : this.blockIcon));
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = Texture.getIconFromTextureName(par1IconRegister, "KaasSide");
        this.iconInside = Texture.getIconFromTextureName(par1IconRegister, "KaasInside");
        this.iconTop = Texture.getIconFromTextureName(par1IconRegister, "KaasTop");
        this.iconBottom = Texture.getIconFromTextureName(par1IconRegister, "KaasBottom");
    }

}
