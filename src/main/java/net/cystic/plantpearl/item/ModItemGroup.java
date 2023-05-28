package net.cystic.plantpearl.item;

import net.cystic.plantpearl.PlantPearl;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static ItemGroup PLANT_PEARL;

    public static void registerItemGroups() {
        PLANT_PEARL = FabricItemGroup.builder(new Identifier(PlantPearl.MOD_ID, "plant_pearl"))
                .displayName(Text.translatable("itemgroup.plant_pearl"))
                .icon(() -> new ItemStack(ModItems.PLANT_PEARL)).build();
    }
}
