package nl.steenbrink.kaasmod.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import nl.steenbrink.kaasmod.kaasmod;
import nl.steenbrink.kaasmod.lib.Reference;
import nl.steenbrink.kaasmod.lib.Texture;
import nl.steenbrink.kaasmod.tile.TileToaster;

public class ToasterBlock extends BlockContainer {
    public ToasterBlock(int id) {
        super(id, Material.rock);
        this.setHardness(5f);
        this.setStepSound(Block.soundMetalFootstep);
        this.setCreativeTab(CreativeTabs.tabRedstone);
    }

    public String getTextureFile() {
        return ("/nl/steenbrink/kaasNew/Blocks.png");

    }

    @Override
    public TileEntity createNewTileEntity(World var1) {
        return new TileToaster();
    }

    @SideOnly(Side.CLIENT)
    private Icon iconFront;
    @SideOnly(Side.CLIENT)
    private Icon iconActivated;

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        this.blockIcon = Texture.getIconFromTextureName(iconRegister, "ToasterSides");
        this.iconFront = Texture.getIconFromTextureName(iconRegister, "ToasterDisabled");
        this.iconActivated = Texture.getIconFromTextureName(iconRegister, "ToasterActivated");
    }
    
    
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getBlockTexture(IBlockAccess par1iBlockAccess, int x, int y, int z, int side) 
    {
        int metaData = par1iBlockAccess.getBlockMetadata(x, y, z);
        TileToaster tile = (TileToaster) par1iBlockAccess.getBlockTileEntity(x, y, z);
        
        return side != metaData ? this.blockIcon
                : ((tile.processTime > 0) ? this.iconActivated : this.iconFront);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
    {
        int direction = 0;
        int playerFacing = MathHelper.floor_double((double)(entityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        switch (playerFacing) {
            case 0:
                direction = ForgeDirection.NORTH.ordinal();
                break;
            case 1:
                direction = ForgeDirection.EAST.ordinal();
                break;
            case 2:
                direction = ForgeDirection.SOUTH.ordinal();
                break;
            case 3:
                direction = ForgeDirection.WEST.ordinal();
                break;
        }
        
        world.setBlockMetadataWithNotify(x, y, z, direction, 2);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int par6, float par7, float par8, float par9) {
        if (world.isRemote) {
            return true;
        } else {
            TileToaster tileToaster = (TileToaster) world.getBlockTileEntity(x, y, z);
            
            if (tileToaster != null) {
                entityPlayer.openGui(kaasmod.instanceK, Reference.GUI_TOASTER_ID, world, x, y, z);
            }

            return true;
        }
    }
    
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
        par1World.markBlockForUpdate(par2, par3, par4);
    }

    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        Random rand = new Random();    
        TileToaster var7 = (TileToaster)par1World.getBlockTileEntity(par2, par3, par4);

            if (var7 != null)
            {
                for (int var8 = 0; var8 < var7.getSizeInventory(); ++var8)
                {
                    ItemStack var9 = var7.getStackInSlot(var8);

                    if (var9 != null)
                    {
                        float var10 = rand.nextFloat() * 0.8F + 0.1F;
                        float var11 = rand.nextFloat() * 0.8F + 0.1F;
                        float var12 = rand.nextFloat() * 0.8F + 0.1F;

                        while (var9.stackSize > 0)
                        {
                            int var13 = rand.nextInt(21) + 10;

                            if (var13 > var9.stackSize)
                            {
                                var13 = var9.stackSize;
                            }

                            var9.stackSize -= var13;
                            EntityItem var14 = new EntityItem(par1World, (double)((float)par2 + var10), (double)((float)par3 + var11), (double)((float)par4 + var12), new ItemStack(var9.itemID, var13, var9.getItemDamage()));

                            if (var9.hasTagCompound())
                            {
                                var14.readFromNBT((NBTTagCompound)var9.getTagCompound().copy());
                            }

                            float var15 = 0.05F;
                            var14.motionX = (double)((float)rand.nextGaussian() * var15);
                            var14.motionY = (double)((float)rand.nextGaussian() * var15 + 0.2F);
                            var14.motionZ = (double)((float)rand.nextGaussian() * var15);
                            par1World.spawnEntityInWorld(var14);
                        }
                    }
                }
            }

        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }
    
    

}
