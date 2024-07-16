package net.brndnwlsh.pathofthedragon;

import net.brndnwlsh.pathofthedragon.block.ModBlocks;
import net.brndnwlsh.pathofthedragon.item.ModItemGroups;
import net.brndnwlsh.pathofthedragon.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PathOfTheDragon implements ModInitializer {
	public static final String MOD_ID = "pathofthedragon";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModItemGroups.registerItemGroups();
	}
}