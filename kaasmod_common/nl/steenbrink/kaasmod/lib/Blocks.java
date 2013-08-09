package nl.steenbrink.kaasmod.lib;

import java.util.ArrayList;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import nl.steenbrink.kaasmod.block.KaasBlock;
import nl.steenbrink.kaasmod.block.KaasbrickBlock;
import nl.steenbrink.kaasmod.block.KaasbrickdoubleslabBlock;
import nl.steenbrink.kaasmod.block.KaasbrickhalfslabBlock;
import nl.steenbrink.kaasmod.block.KaascreatorBlock;
import nl.steenbrink.kaasmod.block.PurifierBlock;
import nl.steenbrink.kaasmod.block.SaltoreBlock;
import nl.steenbrink.kaasmod.block.ToasterBlock;

public class Blocks {
    
    private static final String[] blockNames = new String[] { "Kaas", "Saltore" , "Purifier", "KaasCreator", "Toaster", "Kaasbricks", "Kaasbrickhalfslab", "Kaasbrickdoubleslab"};

    private static final ArrayList<Block> blocks = new ArrayList<Block>();

    public static final Block kaasBlock = new KaasBlock(ReferenceID.KAASBLOCK);
    public static final Block saltoreBlock = new SaltoreBlock(ReferenceID.SALTOREBLOCK);
    public static final Block purifierBlock = new PurifierBlock(ReferenceID.PURIFIERBLOCK);
    public static final Block kaascreatorBlock = new KaascreatorBlock(ReferenceID.KAASCREATORBLOCK);
    public static final Block toasterBlock = new ToasterBlock(ReferenceID.TOASTERBLOCK);
    public static final Block kaasbrickBlock = new KaasbrickBlock(ReferenceID.KAASBRICKBLOCK);
    public static final Block kaasbrickhalfslabBlock = new KaasbrickhalfslabBlock(ReferenceID.KAASBRICKHALFSLABBLOCK, false);
    public static final Block kaasbrickdoubleslabBlock = new KaasbrickdoubleslabBlock(ReferenceID.KAASBRICKDOUBLESLABBBLOCK, true);

    static {
        blocks.add(kaasBlock);
        blocks.add(saltoreBlock);
        blocks.add(purifierBlock);
        blocks.add(kaascreatorBlock);
        blocks.add(toasterBlock);
        blocks.add(kaasbrickBlock);
        blocks.add(kaasbrickhalfslabBlock);
        blocks.add(kaasbrickdoubleslabBlock);
    }

    /**
     * Initialize all the Blocks and their names.
     */
    public static void init() {
        
        MinecraftForge.setBlockHarvestLevel(saltoreBlock, "pickaxe", 1);
        
        for (int i = 0; i < blockNames.length; i++) {
            blocks.get(i).setUnlocalizedName(blockNames[i].replaceAll(" ", ""));
        }
        
        for (final Block block : blocks) {
            GameRegistry.registerBlock(block, Reference.MOD_ID + "_" + block.getUnlocalizedName().substring(block.getUnlocalizedName().indexOf(".") + 1));
        }
        
        for (int i = 0; i < blockNames.length; i++) {
            LanguageRegistry.addName(blocks.get(i), blockNames[i]);
        }
    }
}

