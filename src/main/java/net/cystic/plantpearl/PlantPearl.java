package net.cystic.plantpearl;

import net.cystic.plantpearl.item.ModItemGroup;
import net.cystic.plantpearl.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.ItemGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlantPearl implements ModInitializer {
	public static String MOD_ID = "plantpearl";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroup.registerItemGroups();
		ModItems.registerModItems();
	}
}
