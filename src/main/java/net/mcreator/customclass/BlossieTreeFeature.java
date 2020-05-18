package net.mcreator.customclass;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.mcreator.wild_world.block.BlossieLogWildBlock;
import net.mcreator.wild_world.block.BlossieLeavesBlock;
import net.mcreator.wild_world.block.BlossieSaplingBlock;

import net.mcreator.wild_world.WildWorldElements;

public class BlossieTreeFeature extends AbstractTreeFeature<NoFeatureConfig> {
   private static final BlockState LOG = BlossieLogWildBlock.block.getDefaultState();
   private static final BlockState LEAF = BlossieLeavesBlock.block.getDefaultState();

   public BlossieTreeFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configIn, boolean doBlockNotifyIn) {
      super(configIn, doBlockNotifyIn);
      this.setSapling((net.minecraftforge.common.IPlantable)BlossieSaplingBlock.block);
   }

   public boolean place(Set<BlockPos> changedBlocks, IWorldGenerationReader worldIn, Random rand, BlockPos position, MutableBoundingBox p_208519_5_) {
      int i = rand.nextInt(3) + 7;
      //height

      boolean flag = true;
      if (position.getY() >= 1 && position.getY() + i + 1 <= worldIn.getMaxHeight()) {
         for(int j = position.getY(); j <= position.getY() + 1 + i; ++j) {
            int k = 1;
            if (j == position.getY()) {
               k = 0;
            }

            if (j >= position.getY() + 1 + i - 2) {
               k = 2;
            }

            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for(int l = position.getX() - k; l <= position.getX() + k && flag; ++l) {
               for(int i1 = position.getZ() - k; i1 <= position.getZ() + k && flag; ++i1) {
                  if (j >= 0 && j < worldIn.getMaxHeight()) {
                     if (!func_214587_a(worldIn, blockpos$mutableblockpos.setPos(l, j, i1))) {
                        flag = false;
                     }
                  } else {
                     flag = false;
                  }
               }
            }
         }

         if (!flag) {
            return false;
         } else if ((isSoil(worldIn, position.down(), getSapling())) && position.getY() < worldIn.getMaxHeight() - i - 1) {
            this.setDirtAt(worldIn, position.down(), position);

            for(int l1 = position.getY() - 5 + i; l1 <= position.getY() + i; ++l1) {
            	//Leaves Y coord (From the bottom)
               int j2 = l1 - (position.getY() + i);
               //-leaves height
               int k2 = 5 / 2;
               //Radius based on height
               if (l1 == position.getY() - 5 + i || l1 == position.getY() + i) {
               	k2 = 3/2;
               }

               for(int l2 = position.getX() - k2; l2 <= position.getX() + k2; ++l2) {
                  int i3 = l2 - position.getX();
                  //Relative X

                  for(int j1 = position.getZ() - k2; j1 <= position.getZ() + k2; ++j1) {
                     int k1 = j1 - position.getZ();
                     //Relative Z
                     if (Math.abs(i3) != k2 || Math.abs(k1) != k2 || rand.nextInt(3) != 0 && j2 != 0) {
                        BlockPos blockpos = new BlockPos(l2, l1, j1);
                        if (isAirOrLeaves(worldIn, blockpos)) {
                           this.setLogState(changedBlocks, worldIn, blockpos, LEAF, p_208519_5_);
                        }
                     }
                  }
               }
            }

            for(int i2 = 0; i2 < i; ++i2) {
               if (isAirOrLeaves(worldIn, position.up(i2))) {
                  this.setLogState(changedBlocks, worldIn, position.up(i2), LOG, p_208519_5_);
               }
            }

            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }
}