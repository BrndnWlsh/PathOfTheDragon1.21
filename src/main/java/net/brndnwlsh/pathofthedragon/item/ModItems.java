package net.brndnwlsh.pathofthedragon.item;

import net.brndnwlsh.pathofthedragon.PathOfTheDragon;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item INFINITE = registerItem("infinite", new Item(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(PathOfTheDragon.MOD_ID, name) , item);
    }

    public static void registerModItems() {
        PathOfTheDragon.LOGGER.info("Registering ModItems for " + PathOfTheDragon.MOD_ID);
    }
}
