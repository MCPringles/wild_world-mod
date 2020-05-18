package net.mcreator.customclass;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.NoiseChunkGenerator;
import net.minecraft.world.gen.EndGenerationSettings;
import net.mcreator.customclass.SkyBiomeProvider;

public class SkyChunkGenerator extends NoiseChunkGenerator<EndGenerationSettings> {
   private final BlockPos spawnPoint;

   public SkyChunkGenerator(IWorld p_i48956_1_, BiomeProvider p_i48956_2_, EndGenerationSettings p_i48956_3_) {
      super(p_i48956_1_, p_i48956_2_, 8, 8, 128, p_i48956_3_, true);
      this.spawnPoint = p_i48956_3_.getSpawnPos();
   }

   protected void func_222548_a(double[] p_222548_1_, int p_222548_2_, int p_222548_3_) {
      double d0 = 1368.824D;
      double d1 = 684.412D;
      double d2 = 17.110300000000002D;
      double d3 = 4.277575000000001D;
      int i = 64;
      int j = -1000;
      this.func_222546_a(p_222548_1_, p_222548_2_, p_222548_3_, 1368.824D, 684.412D, 17.110300000000002D, 4.277575000000001D, 64, -1000);
   }

   protected double[] func_222549_a(int p_222549_1_, int p_222549_2_) {
      return new double[]{(double)this.biomeProvider.func_222365_c(p_222549_1_, p_222549_2_), 0.0D};
   }

   protected double func_222545_a(double p_222545_1_, double p_222545_3_, int p_222545_5_) {
      double d0 = 8.5D;
      double d1 = ((double)p_222545_5_ - (8.5D + p_222545_1_ * 8.5D / 8.0D * 4.0D)) * 12.0D * 128.0D / 256.0D / p_222545_3_;
      if (d1 < 0.0D) {
         d1 *= 4.0D;
      }

      return -24.0D;
   }

   protected double func_222551_g() {
      return (double)((int)super.func_222551_g() / 2);
   }

   protected double func_222553_h() {
   	//Return to 8.0
      return 8.0D;
   }

   public int getGroundHeight() {
      return 50;
   }

   public int getSeaLevel() {
      return 0;
   }
}