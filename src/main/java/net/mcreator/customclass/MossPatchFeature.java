package net.mcreator.customclass;

import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.BlockBlobFeature;
import net.minecraft.world.gen.feature.BlockBlobConfig;
import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.block.BlockState;

import net.mcreator.wild_world.block.MossyStoneBlock;
import net.mcreator.wild_world.block.MossyGabbroBlock;
import net.mcreator.wild_world.block.MossyPeridotiteBlock;
import net.mcreator.wild_world.block.GabbroBlock;
import net.mcreator.wild_world.block.PeridotiteBlock;

public class MossPatchFeature extends Feature<BlockBlobConfig> {
   public MossPatchFeature(Function<Dynamic<?>, ? extends BlockBlobConfig> p_i49915_1_) {
      super(p_i49915_1_);
   }

   public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, BlockBlobConfig config) {
      while(true) {
         label50: {
            if (pos.getY() > 3) {
               if (worldIn.isAirBlock(pos.down())) {
                  break label50;
               }

               Block block = worldIn.getBlockState(pos.down()).getBlock();
               if (!Block.isRock(block)) {
                  break label50;
               }
            }

            if (pos.getY() <= 3) {
               return false;
            }

            int i1 = config.startRadius;

            for(int i = 0; i1 >= 0 && i < 3; ++i) {
               int j = i1 + rand.nextInt(2);
               int k = i1 + rand.nextInt(2);
               int l = i1 + rand.nextInt(2);
               float f = (float)(j + k + l) * 0.333F + 0.5F;

               for(BlockPos blockpos : BlockPos.getAllInBoxMutable(pos.add(-j, -k, -l), pos.add(j, k, l))) {
                  if (blockpos.distanceSq(pos) <= (double)(f * f) && worldIn.isAirBlock(blockpos.up()) && worldIn.getBlockState(blockpos) == GabbroBlock.block.getDefaultState()) {
                     worldIn.setBlockState(blockpos, MossyGabbroBlock.block.getDefaultState(), 4);
                  }

                  if (blockpos.distanceSq(pos) <= (double)(f * f) && worldIn.isAirBlock(blockpos.up()) && worldIn.getBlockState(blockpos) == PeridotiteBlock.block.getDefaultState()) {
                     worldIn.setBlockState(blockpos, MossyPeridotiteBlock.block.getDefaultState(), 4);
                  }

                  if (blockpos.distanceSq(pos) <= (double)(f * f) && worldIn.isAirBlock(blockpos.up()) && worldIn.getBlockState(blockpos) == Blocks.STONE.getDefaultState()) {
                     worldIn.setBlockState(blockpos, MossyStoneBlock.block.getDefaultState(), 4);
                  }
               }

               pos = pos.add(-(i1 + 1) + rand.nextInt(2 + i1 * 2), 0 - rand.nextInt(2), -(i1 + 1) + rand.nextInt(2 + i1 * 2));
            }

            return true;
         }

         pos = pos.down();
      }
   }
}