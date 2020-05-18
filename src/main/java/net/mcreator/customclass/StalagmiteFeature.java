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
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import net.mcreator.wild_world.WildWorldElements;
import java.awt.RadialGradientPaint;
import java.awt.RadialGradientPaint;
import net.minecraft.util.math.MathHelper;
import net.mcreator.customclass.StalagmiteFeatureConfig;

public class StalagmiteFeature extends Feature<StalagmiteFeatureConfig> {

   public StalagmiteFeature(Function<Dynamic<?>, ? extends StalagmiteFeatureConfig> configIn) {
      super(configIn);
   }

   protected static boolean isStone(IWorldGenerationBaseReader worldIn, BlockPos pos) {
      return worldIn.hasBlockState(pos, (p_214583_0_) -> {
         return p_214583_0_.getBlock() == Blocks.STONE;
      });
   }

   protected static boolean isValid(IWorld worldIn, BlockPos pos, StalagmiteFeatureConfig configIn) {
   		if (worldIn.getBlockState(pos) == configIn.placementBlock) {
   			return true;
   		}
   		else {
   			return false;
   		}
   }

   public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos position, StalagmiteFeatureConfig config) {
	  BlockState STONE = config.material;
      int i = 4 + rand.nextInt(config.height);
      //Height rand.nextInt(9) + 4
      int r1 = rand.nextInt(config.maxRadius);
      //Max radius rand.nextInt(2);
	  float e = 0.2F + (rand.nextInt(6) * 0.1F);


      boolean flag = true;
      if (position.getY() == 0) 
      {
		  flag = false;
      }

     if (!flag) {
        return false;
     } else if (position.getY() < worldIn.getMaxHeight() - i - 1) {
        for(int h1 = position.getY(); h1 < position.getY() + i - 2; ++h1) {
        	int y1 = h1 - position.getY();
        	//Relative Y coord
            int r = (int) r1 - (int) (y1 * e);
            //Radius based on height

            r =  Math.max(r, 0);

           for(int x = position.getX() - r; x <= position.getX() + r; ++x) {
              int i3 = x - position.getX();
              //Relative X

              for(int z = position.getZ() - r; z <= position.getZ() + r; ++z) {
                 int k1 = z - position.getZ();
                 //Relative Z
                 if (Math.abs(i3) != r || Math.abs(k1) != r || rand.nextInt(7 + y1) == 1 && r != 0) {
                    BlockPos blockpos = new BlockPos(x, h1, z);
                    if (isValid(worldIn, blockpos.down(), config)) {
                    	this.setBlockState(worldIn, blockpos, STONE);
                    	this.setBlockState(worldIn, blockpos.down(), STONE);
                    }
                    else if (Math.abs(i3) == r && Math.abs(k1) == r && rand.nextInt(7 + y1) == 1) {
                    	this.setBlockState(worldIn, blockpos, STONE);
                    }
                 }
              }
           }
        }

        for(int i2 = 0; i2 < i; ++i2) {
           this.setBlockState(worldIn, position.up(i2), STONE);
        }

        return true;
     } else {
        return false;
     }
   }
}