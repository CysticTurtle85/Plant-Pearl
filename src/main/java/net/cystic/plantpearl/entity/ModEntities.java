package net.cystic.plantpearl.entity;

import net.cystic.plantpearl.PlantPearl;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import javax.swing.text.html.parser.Entity;

public class ModEntities {
    public static final EntityType<PlantPearlEntity> PlantPearlEntityType = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(PlantPearl.MOD_ID, "plant_pearl"),
            FabricEntityTypeBuilder.<PlantPearlEntity>create(SpawnGroup.MISC, PlantPearlEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                    .trackRangeBlocks(4).trackedUpdateRate(10)
                    .build()
    );

    public static void RenderEntities() {
        EntityRendererRegistry.register(ModEntities.PlantPearlEntityType, FlyingItemEntityRenderer::new);
    }

    public static void registerModEntities() {
        PlantPearl.LOGGER.info("Registering Mod Entities for " + PlantPearl.MOD_ID);

        RenderEntities();
    }
}
