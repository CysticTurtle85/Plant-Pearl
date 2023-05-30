package net.cystic.plantpearl.entity;

import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.cystic.plantpearl.item.ModItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.block.sapling.OakSaplingGenerator;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.command.PlaceCommand;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGenerators;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static net.minecraft.block.SaplingBlock.STAGE;

public class PlantPearlEntity extends ThrownItemEntity {

    public PlantPearlEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public PlantPearlEntity(World world, LivingEntity owner) {
        super(ModEntities.PlantPearlEntityType, owner, world); // null will be changed later
    }

    public PlantPearlEntity(World world, double x, double y, double z) {
        super(ModEntities.PlantPearlEntityType, x, y, z, world); // null will be changed later
    }

    @Environment(EnvType.CLIENT)
    private ParticleEffect getParticleParameters() {
        ItemStack itemStack = this.getItem();
        return (ParticleEffect)(itemStack.isEmpty() ? ParticleTypes.COMPOSTER : new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack));
    }

    @Environment(EnvType.CLIENT)
    public void handleStatus(byte status) {
        if (status == 3) {
            ParticleEffect particleEffect = this.getParticleParameters();

            for(int i = 0; i < 80; ++i) {
                this.world.addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }

    }
    SaplingGenerator saplingGenerator = new OakSaplingGenerator();
    BlockState blockState = null;
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.world.isClient) {
            this.world.sendEntityStatus(this, (byte) 3);
            BlockState blockState = getBlockStateAtPos();;
            this.kill();

            if (blockState != null) {
                saplingGenerator.generate(world.getServer().getWorld(world.getRegistryKey()).toServerWorld(), world.getServer().getWorld(world.getRegistryKey()).toServerWorld().getChunkManager().getChunkGenerator(), this.getBlockPos(), blockState, random);
            } else {
                // give item back
            }
        }
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.PLANT_PEARL;
    }
}
