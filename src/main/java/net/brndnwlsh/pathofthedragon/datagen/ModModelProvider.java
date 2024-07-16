package net.brndnwlsh.pathofthedragon.datagen;

import net.brndnwlsh.pathofthedragon.block.ModBlocks;
import net.brndnwlsh.pathofthedragon.block.custom.InfiniteOreBlock;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;


public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        registerInfiniteOreBlock(blockStateModelGenerator, ModBlocks.INFINITE_COAL_ORE, Blocks.COAL_ORE, Blocks.STONE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }


    public void registerInfiniteOreBlock(BlockStateModelGenerator blockStateModelGenerator, Block block, Block full, Block empty) {
        Identifier identifier = ModelIds.getBlockModelId(full);
        Identifier identifier2 = ModelIds.getBlockModelId(empty);
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block)
                .coordinate(BlockStateModelGenerator.createBooleanModelMap(InfiniteOreBlock.FULL, identifier, identifier2)));
        blockStateModelGenerator.registerParentedItemModel(block, identifier);
    }

}
