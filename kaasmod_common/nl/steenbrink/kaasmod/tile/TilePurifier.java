package nl.steenbrink.kaasmod.tile;

import com.google.common.io.ByteArrayDataInput;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import nl.steenbrink.kaasmod.lib.Items;
import nl.steenbrink.kaasmod.network.PacketHandler;

public class TilePurifier extends TileEntity implements ISidedInventory {

    private ItemStack[] purifierInv = new ItemStack[2];
    public int processTime = 0;
    private boolean isPowered = false;
    //private boolean wasProcessing = false;

    @Override
    public void updateEntity() {
        // check redstone powered
        // process items
        if (!this.worldObj.isRemote) {
            if (this.worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord,
                    zCoord))
                isPowered = true;
            else
                isPowered = false;

            if (isPowered
                    && (this.purifierInv[0] != null && this.purifierInv[0]
                            .getItem() == Items.rawsaltItem)) {
                if (this.purifierInv[1] == null
                        || (this.purifierInv[1].getItem() == Items.saltItem && this.purifierInv[1].stackSize < this.purifierInv[1]
                                .getMaxStackSize())) {
                    if (processTime == 200) {
                        this.purifierInv[0].stackSize--;
                        if (this.purifierInv[0].stackSize <= 0) {
                            this.purifierInv[0] = null;
                        }
                        if (this.purifierInv[1] == null) {
                            this.purifierInv[1] = new ItemStack(Items.saltItem,
                                    1);
                        } else {
                            this.purifierInv[1].stackSize++;
                        }
                        processTime = 0;
                        this.worldObj
                                .markBlockForUpdate(xCoord, yCoord, zCoord);
                    } else {
                        processTime++;

                        if (processTime % 20 == 0) {
                            this.worldObj.markBlockForUpdate(xCoord, yCoord,
                                    zCoord);
                        }
                    }
                }
            }
        }
    }

    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);

        NBTTagList tagList = nbtTagCompound.getTagList("Items");
        this.purifierInv = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < tagList.tagCount(); ++i) {
            NBTTagCompound tagCompound = (NBTTagCompound) tagList.tagAt(i);
            byte slot = tagCompound.getByte("Slot");
            if (slot >= 0 && slot < this.purifierInv.length) {
                this.purifierInv[slot] = ItemStack
                        .loadItemStackFromNBT(tagCompound);
            }
        }

        this.processTime = nbtTagCompound.getShort("ProcessTime");
    }

    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setShort("ProcessTime", (short) this.processTime);

        NBTTagList tagList = new NBTTagList();
        for (int currentIndex = 0; currentIndex < this.purifierInv.length; ++currentIndex) {
            if (this.purifierInv[currentIndex] != null) {
                NBTTagCompound tagCompound = new NBTTagCompound();
                tagCompound.setByte("Slot", (byte) currentIndex);
                this.purifierInv[currentIndex].writeToNBT(tagCompound);
                tagList.appendTag(tagCompound);
            }
        }
        nbtTagCompound.setTag("Items", tagList);

    }

    @Override
    public Packet getDescriptionPacket() {
            return PacketHandler.getPacket(this);
        }

    @SideOnly(Side.CLIENT)
    public int getProgressScaled(int par1) {
        return this.processTime * par1 / 200;
    }

    public int getStartInventorySide(ForgeDirection side) {
        return 0;
    }

    public int getSizeInventorySide(ForgeDirection side) {
        return 0;
    }

    public int getSizeInventory() {
        return this.purifierInv.length;
    }

    public ItemStack getStackInSlot(int var1) {
        return this.purifierInv[var1];
    }

    public ItemStack decrStackSize(int i, int j) {
        if (this.purifierInv[i] != null) {
            ItemStack var3;

            if (this.purifierInv[i].stackSize <= j) {
                var3 = this.purifierInv[i];
                this.purifierInv[i] = null;
                this.onInventoryChanged();
                return var3;
            } else {
                var3 = this.purifierInv[i].splitStack(j);

                if (this.purifierInv[i].stackSize == 0) {
                    this.purifierInv[i] = null;
                }

                this.onInventoryChanged();
                return var3;
            }
        } else {
            return null;
        }
    }

    public ItemStack getStackInSlotOnClosing(int var1) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int var1, ItemStack var2) {
        this.purifierInv[var1] = var2;
        if (var2 != null && var2.stackSize > this.getInventoryStackLimit()) {
            var2.stackSize = this.getInventoryStackLimit();
        }
        this.onInventoryChanged();
    }

    public String getInvName() {
        return "Purifier";
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    public boolean isUseableByPlayer(EntityPlayer var1) {
        return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this ? false : var1.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64.0D;
    }

    public void openChest() {
    }

    public void closeChest() {
    }

    @Override
    public boolean isInvNameLocalized() {
        return false;
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return false;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int var1) {
        return null;
    }

    @Override
    public boolean canInsertItem(int i, ItemStack itemstack, int j) {
        return false;
    }

    @Override
    public boolean canExtractItem(int i, ItemStack itemstack, int j) {
        return false;
    }

    public void handlePacketData(ByteArrayDataInput dat) {
        this.processTime = dat.readInt();
        
    }

}
