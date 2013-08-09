package nl.steenbrink.kaasmod.lib;

import java.util.ArrayList;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import nl.steenbrink.kaasmod.item.CheesebaconbreadItem;
import nl.steenbrink.kaasmod.item.CheesebreadItem;
import nl.steenbrink.kaasmod.item.CheesesliceItem;
import nl.steenbrink.kaasmod.item.CheeseslicerItem;
import nl.steenbrink.kaasmod.item.IngredientsItem;
import nl.steenbrink.kaasmod.item.KaasItem;
import nl.steenbrink.kaasmod.item.RawsaltItem;
import nl.steenbrink.kaasmod.item.RoastedsandwichItem;
import nl.steenbrink.kaasmod.item.SaltItem;

public class Items {

    private static final String[] itemNames = new String[] { "Kaas", "Raw Salt", "Cheese Slice", "Salt", "Ingredients", "Cheese Slicer", "Cheesebread", "Cheese-Baconbread", "Roasted Sandwich"};

    private static final ArrayList<Item> items = new ArrayList<Item>();

    public static final Item kaasItem = new KaasItem(ReferenceID.KAASITEM);
    public static final Item rawsaltItem = new RawsaltItem(ReferenceID.RAWSALTITEM);
    public static final Item cheesesliceItem = new CheesesliceItem(ReferenceID.CHEESESLICEITEM);
    public static final Item saltItem = new SaltItem(ReferenceID.SALTITEM);
    public static final Item ingredientsItem = new IngredientsItem(ReferenceID.INGREDIENTSITEM);
    public static final Item cheeseslicerItem = new CheeseslicerItem(ReferenceID.CHEESESLICERITEM, EnumToolMaterial.IRON);
    public static final Item cheesebreadItem = new CheesebreadItem(ReferenceID.CHEESEBREADITEM);
    public static final Item cheesebaconbreadItem = new CheesebaconbreadItem(ReferenceID.CHEESEBACONBREADITEM);
    public static final Item roastedsandwichItem = new RoastedsandwichItem(ReferenceID.ROASTEDSANDWICHITEM);
    
    static {
        items.add(kaasItem);
        items.add(rawsaltItem);
        items.add(cheesesliceItem);
        items.add(saltItem);
        items.add(ingredientsItem);
        items.add(cheeseslicerItem);
        items.add(cheesebreadItem);
        items.add(cheesebaconbreadItem);
        items.add(roastedsandwichItem);
    }

    /**
     * Initialize all Items and their names
     */
    public static void init() {
        
        for (int i = 0; i < itemNames.length; i++) {
            items.get(i).setUnlocalizedName(itemNames[i].replaceAll(" ", ""));
        }
        
        for (int i = 0; i < itemNames.length; i++) {
            LanguageRegistry.addName(items.get(i), itemNames[i]);
        }
    }

}
