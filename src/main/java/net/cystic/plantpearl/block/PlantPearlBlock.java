package net.cystic.plantpearl.block;

import net.cystic.plantpearl.item.PlantPearl;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.sapling.OakSaplingGenerator;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class PlantPearlBlock extends Block {


    public PlantPearlBlock(Settings settings) {
        super(settings);
    }
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            SaplingGenerator saplingGenerator = new OakSaplingGenerator();
            Random random = net.minecraft.util.math.random.Random.create();
            world.breakBlock(pos,false);

            PlantPearl.generateTree(state, saplingGenerator, world, pos, random); // Generate 1 tree on block
            int treeNum = 10;
            for (int i = 0; i < treeNum; i++) {
                BlockPos randomPos = pos.add(new Vec3i(random.nextBetween(-20, 20), world.getHeight() - pos.getY(), random.nextBetween(-20, 20)));

                for (int y = world.getHeight(); y > 0; y--) {
                    randomPos = randomPos.down();
                    if (world.getBlockState(randomPos).isIn(BlockTags.DIRT) || world.getBlockState(randomPos).isIn(BlockTags.SAND) || world.getBlockState(randomPos).isOf(Blocks.GRASS_BLOCK)) {
                        if (world.getBlockState(randomPos.up()).isOf(Blocks.AIR) || world.getBlockState(randomPos.up()).isOf(Blocks.GRASS)) {
                            PlantPearl.generateTree(state, saplingGenerator, world, randomPos.up(), random);
                        }
                    }
                }
            }
        }
        return ActionResult.SUCCESS;
    }
}