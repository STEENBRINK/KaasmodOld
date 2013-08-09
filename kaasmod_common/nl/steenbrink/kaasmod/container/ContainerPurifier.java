package nl.steenbrink.kaasmod.container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import nl.steenbrink.kaasmod.tile.TilePurifier;

public class ContainerPurifier extends Container {
    private TilePurifier tilePurifier;
    private int lastProgress = 0;

    public ContainerPurifier(InventoryPlayer playerInventory, TilePurifier TilePurifier) {
        this.tilePurifier = TilePurifier;
        this.addSlotToContainer(new Slot(TilePurifier, 0, 56, 35));
        this.addSlotToContainer(new SlotFurnace(playerInventory.player, TilePurifier, 1, 116, 35));
        int i;

        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 94 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(playerInventory, i, 8 + i * 18, 152));
        }
    }

    public void addCraftingToCrafters(ICrafting par1ICrafting) {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, this.tilePurifier.processTime);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting icrafting = (ICrafting) this.crafters.get(i);

            if (this.lastProgress != this.tilePurifier.processTime) {
                icrafting.sendProgressBarUpdate(this, 0, this.tilePurifier.processTime);
            }
        }

        this.lastProgress = this.tilePurifier.processTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int value) {
        if (id == 0) {
            this.tilePurifier.processTime=(value);
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
        return this.tilePurifier.isUseableByPlayer(par1EntityPlayer);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex) {
        ItemStack itemStack = null;
        Slot slot = (Slot) inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack()) {
            ItemStack slotItemStack = slot.getStack();
            itemStack = slotItemStack.copy();

            // Shift-clicking out of the grinder
            if (slotIndex < tilePurifier.getSizeInventory()) {
                if (!this.mergeItemStack(slotItemStack, tilePurifier.getSizeInventory(), inventorySlots.size(), false)) {
                    return null;
                }
            } else {
                // Put the item in the Input slot of the Grinder
                if (!this.mergeItemStack(slotItemStack, 0, 1, false)) {
                    return null;
                }
            }

            if (slotItemStack.stackSize == 0) {
                slot.putStack((ItemStack) null);
            } else {
                slot.onSlotChanged();
            }
        }

        return itemStack;
    }
}