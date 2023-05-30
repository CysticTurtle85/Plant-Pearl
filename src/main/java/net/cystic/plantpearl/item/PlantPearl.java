package net.cystic.plantpearl.item;

import net.cystic.plantpearl.entity.PlantPearlEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class PlantPearl extends Item {

    public PlantPearl(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        ItemStack itemStack = playerEntity.getStackInHand(hand);
        playerEntity.playSound(SoundEvents.ENTITY_ENDER_PEARL_THROW, 1.0f, 1.0f);

        if (!world.isClient) {
            PlantPearlEntity plantPearlEntity = new PlantPearlEntity(world, playerEntity);

            plantPearlEntity.setItem(itemStack);
            plantPearlEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0f, 2f, 1.0f);
            world.spawnEntity(plantPearlEntity);
        }

        if (!playerEntity.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }

        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
}
