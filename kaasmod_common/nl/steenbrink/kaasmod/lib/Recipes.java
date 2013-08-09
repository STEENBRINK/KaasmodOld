package nl.steenbrink.kaasmod.lib;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Recipes {
    
    public static void init() {
        GameRegistry.addRecipe(new ItemStack(Blocks.toasterBlock), new Object[] { "III", "IFI", "IRI", 'I', Item.ingotIron, 'F', Block.furnaceIdle, 'R', Item.redstone});
        GameRegistry.addRecipe(new ItemStack(Blocks.kaascreatorBlock), new Object[] { "STS", "SBS", "SRS", 'S', Block.stone, 'T', Item.stick, 'B', Item.bucketEmpty, 'R', Item.redstone});
        GameRegistry.addRecipe(new ItemStack(Blocks.purifierBlock), new Object[] { "SOS", "SPS", "SRS", 'S', Block.stone, 'O', Block.obsidian, 'P', Items.rawsaltItem, 'R', Item.redstone});
        GameRegistry.addRecipe(new ItemStack(Items.kaasItem), new Object[] { "KKK", "KKK", 'K', Items.cheesesliceItem});
        
        GameRegistry.addRecipe(new ItemStack(Items.cheesebreadItem), new Object[] { "KKK", "WWW", 'K', Items.cheesesliceItem, 'W', Item.wheat});
        GameRegistry.addRecipe(new ItemStack(Items.cheesebaconbreadItem), new Object[] { " B ", "KKK", "WWW", 'B', Item.porkCooked, 'K', Items.cheesesliceItem, 'W', Item.wheat});
        GameRegistry.addRecipe(new ItemStack(Items.cheeseslicerItem), new Object[] { "  I", " S ", "S  ", 'S', Item.stick, 'I', Item.ingotIron});
        
        GameRegistry.addShapelessRecipe(new ItemStack(Items.ingredientsItem), new Object[] { new ItemStack(Items.saltItem), new ItemStack(Item.bucketMilk)});

    }

}
