package nl.steenbrink.kaasmod.villager;

import java.util.Random;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import nl.steenbrink.kaasmod.lib.Blocks;
import nl.steenbrink.kaasmod.lib.Items;
import nl.steenbrink.kaasmod.lib.ReferenceID;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler;

public class JannekeVillageTrades implements IVillageTradeHandler
{

    @Override
    public void manipulateTradesForVillager (EntityVillager villager, MerchantRecipeList recipeList, Random random)
    {
        if (villager.getProfession() == ReferenceID.JANNEKE)
        {
                recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.emerald, 3), new ItemStack(Items.cheesebaconbreadItem, 2)));
                recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.emerald, 2), new ItemStack(Items.kaasItem)));
                recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.ingotIron, 2), new ItemStack(Items.cheeseslicerItem)));
                recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.bucketMilk), new ItemStack(Item.emerald), new ItemStack(Items.kaasItem)));
                recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.emerald, 20), new ItemStack(Blocks.kaascreatorBlock)));
                recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.emerald, 20), new ItemStack(Blocks.purifierBlock)));
                recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.emerald, 20), new ItemStack(Blocks.toasterBlock)));
        }
    }

}
