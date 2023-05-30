package net.cystic.plantpearl.entity;

import net.cystic.plantpearl.item.ModItems;
import net.cystic.plantpearl.item.PlantPearl;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.block.sapling.OakSaplingGenerator;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class PlantPearlEntity extends ThrownItemEntity {

    public PlantPearlEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public PlantPearlEntity(World world, LivingEntity owner) {
        super(ModEntities.PlantPearlEntityType, owner, world);
    }

    public PlantPearlEntity(World world, double x, double y, double z) {
        super(ModEntities.PlantPearlEntityType, x, y, z, world);
    }

    SaplingGenerator saplingGenerator = new OakSaplingGenerator();
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.world.isClient) {
            this.world.sendEntityStatus(this, (byte) 3);
            this.kill();

            BlockState blockState = getBlockStateAtPos();;
            SaplingGenerator saplingGenerator = new OakSaplingGenerator();
            PlantPearl.generateTree(blockState, saplingGenerator, world, this.getBlockPos(), random);

        }
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.PLANT_PEARL;
    }
}
