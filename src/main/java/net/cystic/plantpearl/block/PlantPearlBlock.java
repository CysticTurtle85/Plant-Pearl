package net.cystic.plantpearl.block;

import net.cystic.plantpearl.item.PlantPearlItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.sapling.OakSaplingGenerator;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
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

            int treeNum = 15;
            for (int i = 0; i < treeNum; i++) {
                BlockPos randomPos = pos.add(new Vec3i(random.nextBetween(-15, 15), world.getHeight() - pos.getY(), random.nextBetween(-15, 15)));

                for (int y = world.getHeight(); y > 0; y--) {
                    randomPos = randomPos.down();
                    if (world.getBlockState(randomPos).isIn(BlockTags.DIRT) || world.getBlockState(randomPos).isIn(BlockTags.SAND) || world.getBlockState(randomPos).isOf(Blocks.SNOW_BLOCK) || world.getBlockState(randomPos).isOf(Blocks.GRASS_BLOCK)) { // blocks that trees can be placed on
                        if (world.getBlockState(randomPos.up()).isOf(Blocks.AIR) || world.getBlockState(randomPos.up()).isOf(Blocks.GRASS) || world.getBlockState(randomPos.up()).isOf(Blocks.SNOW)) { // blocks that trees can replace
                            PlantPearlItem.generateTree(state, saplingGenerator, world, randomPos.up(), random);
                        }
                    }
                }
            }
        }
        return ActionResult.SUCCESS;
    }




}