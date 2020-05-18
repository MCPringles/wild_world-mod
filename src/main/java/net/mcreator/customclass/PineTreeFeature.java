package net.mcreator.customclass;

import net.minecraft.block.BlockState;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraftforge.common.IPlantable;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import com.mojang.datafixers.Dynamic;
import java.util.Set;
import java.util.function.Function;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;

import net.mcreator.wild_world.WildWorldElements;
import net.mcreator.wild_world.block.PineSaplingBlock;
import net.mcreator.wild_world.block.PineLogBlock;
import net.mcreator.wild_world.block.PineLeavesBlock;

import java.util.Random;

public class PineTreeFeature extends AbstractTreeFeature<NoFeatureConfig> {
	private static final BlockState TRUNK = PineLogBlock.block.getDefaultState();
   	private static final BlockState LEAF = PineLeavesBlock.block.getDefaultState();
    private final int minTreeHeight = 5;

    public PineTreeFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i51469_1_)
    {
        super(p_i51469_1_, false);
      	setSapling(PineSaplingBlock.block);
    }

    public boolean place(Set<BlockPos> changedBlocks, IWorldGenerationReader worldIn, Random rand, BlockPos position, MutableBoundingBox p_208519_5_) {
      int i = rand.nextInt(3) + 9;
      //Tree Height
      int j = i - rand.nextInt(3) - 6;
      //Leaves height
      int k = i - j;
      //Leaves bottom
      int l = 2 + rand.nextInt(1);
      //Leaves Poofiness
      if (position.getY() >= 1 && position.getY() + i + 2 <= worldIn.getMaxHeight()) {
         boolean flag = true;

         for(int i1 = position.getY(); i1 <= position.getY() + 2 + i && flag; ++i1) {
            int j1 = 1;
            if (i1 - position.getY() < j) {
               j1 = 0;
            } else {
               j1 = l;
            }

            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for(int k1 = position.getX() - j1; k1 <= position.getX() + j1 && flag; ++k1) {
               for(int l1 = position.getZ() - j1; l1 <= position.getZ() + j1 && flag; ++l1) {
                  if (i1 >= 0 && i1 < worldIn.getMaxHeight()) {
                     if (!func_214587_a(worldIn, blockpos$mutableblockpos.setPos(k1, i1, l1))) {
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
         } else if (isSoil(worldIn, position.down(), getSapling()) && position.getY() < worldIn.getMaxHeight() - i - 2) {
            this.setDirtAt(worldIn, position.down(), position);
            float jj = 0;
            //Leaves poof

            for(int k2 = position.getY() + i; k2 >= position.getY() + j; --k2) {
               int j2 = (int) jj;
               //Leaves poof int
               for(int i3 = position.getX() - j2; i3 <= position.getX() + j2; ++i3) {
                  int j3 = i3 - position.getX();
                  //Relative X

                  for(int k3 = position.getZ() - j2; k3 <= position.getZ() + j2; ++k3) {
                     int i2 = k3 - position.getZ();
                     //Relative Z
                     
                     if (Math.abs(j3) != j2 || Math.abs(i2) != j2 || j2 <= 0) {
                        BlockPos blockpos = new BlockPos(i3, k2, k3);
                        if (isAirOrLeaves(worldIn, blockpos)) {
                           this.setLogState(changedBlocks, worldIn, blockpos, LEAF, p_208519_5_);
                        }
                     }
                  }
               }

               if (j2 >= 1 && k2 == position.getY() + j + 1 && rand.nextBoolean() == true) {
                  jj -= 1;
               } else if (j2 >= 1 && k2 == position.getY() + j + 1 && rand.nextBoolean() == true) {
		 		  ++jj;
               }else if (j2 == 0 && k2 == position.getY() + i - 1) {
		 		  ++jj;
               } else if (j2 < l) {
               	  if (rand.nextBoolean() == true) {
               	  	jj += 0.5f;
               	  }
               } 
            }

            for(int l2 = 0; l2 < i - 2; ++l2) {
               if (isAirOrLeaves(worldIn, position.up(l2))) {
                  this.setLogState(changedBlocks, worldIn, position.up(l2), TRUNK, p_208519_5_);
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
