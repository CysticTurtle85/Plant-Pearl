package net.cystic.plantpearl.block;

import net.cystic.plantpearl.PlantPearl;
import net.cystic.plantpearl.item.ModItemGroup;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block PLANT_PEARL_BLOCK = registerBlock("plant_pearl_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(0.2f)), ModItemGroup.PLANT_PEARL);

    private static Block registerBlock(String name, Block block, ItemGroup group) {
        registerBlockItem(name, block, group);
        return Registry.register(Registries.BLOCK, new Identifier(PlantPearl.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup group) {
        Item item = Registry.register(Registries.ITEM, new Identifier(PlantPearl.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
        return item; // Try to add to another item group
    }

    public static void registerModBlocks() {
        PlantPearl.LOGGER.info("Registering ModBlocks for " + PlantPearl.MOD_ID);
    }
}
