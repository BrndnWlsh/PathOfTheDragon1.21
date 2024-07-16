package net.brndnwlsh.pathofthedragon.datagen;

import net.brndnwlsh.pathofthedragon.block.ModBlocks;
import net.brndnwlsh.pathofthedragon.block.custom.InfiniteOreBlock;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.ExplosionDecayLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.INFINITE_COAL_ORE, infiniteOreDrops(ModBlocks.INFINITE_COAL_ORE, Items.COAL));
    }

    public LootTable.Builder infiniteOreDrops(Block ore, Item drop) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return LootTable.builder().pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F)).with(ItemEntry.builder(drop)
                        .conditionally(BlockStatePropertyLootCondition.builder(ore).properties(StatePredicate.Builder
                                .create().exactMatch(InfiniteOreBlock.FULL, true))).apply(ApplyBonusLootFunction
                        .oreDrops(impl.getOrThrow(Enchantments.FORTUNE))).apply(ExplosionDecayLootFunction.builder())));
    } //TODO "random_sequence": "minecraft:blocks/oak_door"
}
