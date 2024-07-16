package net.brndnwlsh.pathofthedragon.block;

import net.brndnwlsh.pathofthedragon.PathOfTheDragon;
import net.brndnwlsh.pathofthedragon.block.custom.InfiniteOreBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {

    public static final Block INFINITE_COAL_ORE = registerBlock("infinite_coal_ore",
            new InfiniteOreBlock(UniformIntProvider.create(0, 2), AbstractBlock.Settings.copy(Blocks.COAL_ORE)));


    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(PathOfTheDragon.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, Identifier.of(PathOfTheDragon.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        PathOfTheDragon.LOGGER.info("Registering ModBlocks for " + PathOfTheDragon.MOD_ID);
    }
}
