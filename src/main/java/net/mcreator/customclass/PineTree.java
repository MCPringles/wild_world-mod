package net.mcreator.customclass;

import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import net.mcreator.wild_world.WildWorldElements;

public class PineTree extends Tree {
   @Nullable
   protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random) {
      return new PineTreeFeature(NoFeatureConfig::deserialize);
   }
}