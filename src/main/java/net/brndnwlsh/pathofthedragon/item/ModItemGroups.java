package net.brndnwlsh.pathofthedragon.item;

import net.brndnwlsh.pathofthedragon.PathOfTheDragon;
import net.brndnwlsh.pathofthedragon.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup DRAGON_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(PathOfTheDragon.MOD_ID, "dragon"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.dragon"))
                    .icon(() -> new ItemStack(Items.DIAMOND)).entries((displayContext, entries) -> {
                        entries.add(ModItems.INFINITE);
                        entries.add(ModBlocks.INFINITE_COAL_ORE);
                    }).build());

    public static void registerItemGroups() {
        PathOfTheDragon.LOGGER.info("Registering Mod Item Groups for " + PathOfTheDragon.MOD_ID);
    }
}
