package nl.steenbrink.kaasmod.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import nl.steenbrink.kaasmod.container.ContainerToaster;
import nl.steenbrink.kaasmod.lib.Reference;
import nl.steenbrink.kaasmod.tile.TileToaster;
import org.lwjgl.opengl.GL11;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GUIToaster extends GuiContainer {

    private TileToaster tileToaster;

    public GUIToaster(InventoryPlayer inventoryPlayer, TileToaster tileToaster) {
        super(new ContainerToaster(inventoryPlayer, tileToaster));
        this.tileToaster = tileToaster;
        this.ySize = 176;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        String s = this.tileToaster.isInvNameLocalized() ? this.tileToaster.getInvName() : StatCollector.translateToLocal(this.tileToaster.getInvName());
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.func_110577_a(new ResourceLocation(Reference.RESOURCE_FOLDER, Reference.PATH_GUI + "Toaster.png"));
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
        
        int progressScaled = this.tileToaster.getProgressScaled(24);
        this.drawTexturedModalRect(xStart + 79 + 4, yStart + 34, 176, 14, progressScaled + 1, 16);
    }
}