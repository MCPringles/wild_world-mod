package net.mcreator.customclass;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import java.awt.RadialGradientPaint;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class StalagmiteFeatureConfig implements IFeatureConfig {
   public final BlockState material;
   public final int maxRadius;
   public final BlockState placementBlock;
   public final int height;

   public StalagmiteFeatureConfig(BlockState material, int maxRadius, BlockState placementBlock, int height) {
      this.material = material;
      this.maxRadius = maxRadius;
      this.placementBlock = placementBlock;
      this.height = height;
   }

   public <T> Dynamic<T> serialize(DynamicOps<T> ops) {
      return new Dynamic<>(ops, ops.createMap(ImmutableMap.of(ops.createString("material"), BlockState.serialize(ops, this.material).getValue(), ops.createString("maxradius"), ops.createInt(this.maxRadius), ops.createString("placementblock"), BlockState.serialize(ops, this.placementBlock).getValue(), ops.createString("height"), ops.createInt(this.height))));
   }

   public static <T> StalagmiteFeatureConfig deserialize(Dynamic<T> p_214685_0_) {
      BlockState blockstate = p_214685_0_.get("material").map(BlockState::deserialize).orElse(Blocks.STONE.getDefaultState());
      int i = p_214685_0_.get("maxradius").asInt(2);
      BlockState spawnBlock = p_214685_0_.get("material").map(BlockState::deserialize).orElse(Blocks.STONE.getDefaultState());
      int h = p_214685_0_.get("height").asInt(0);
      return new StalagmiteFeatureConfig(blockstate, i, spawnBlock, h);
   }
}