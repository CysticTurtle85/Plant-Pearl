package net.cystic.plantpearl.item;

import net.cystic.plantpearl.PlantPearl;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item PLANT_PEARL = registerItem("plant_pearl",
            new Item(new FabricItemSettings().maxCount(16)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(PlantPearl.MOD_ID, name), item);
    }

    public static void addItemsToItemGroup() {
        addToItemGroup(ItemGroups.INGREDIENTS, PLANT_PEARL);
        addToItemGroup(ModItemGroup.PLANT_PEARL, PLANT_PEARL);
    }

    private static void addToItemGroup(ItemGroup group, Item item) {
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));

    }
    public static void registerModItems() {
        PlantPearl.LOGGER.info("Registering Mod Items for " + PlantPearl.MOD_ID);

        addItemsToItemGroup();
    }
}
