
package net.mcreator.wild_world.world.biome;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.BushConfig;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.block.Blocks;

import net.mcreator.customclass.StalagmiteFeature;
import net.mcreator.customclass.StalagmiteFeatureConfig;
import net.mcreator.customclass.MossPatchFeature;
import net.mcreator.customclass.DefaultSkyFeatures;
import net.mcreator.wild_world.block.MossyStoneBlock;

import net.mcreator.wild_world.WildWorldElements;

import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.placement.TopSolidWithNoiseConfig;
import net.minecraft.world.gen.placement.TwiceSurfaceWithNoise;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.TopSolidWithChance;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.Heightmap.Type;
import net.minecraft.world.gen.feature.BlockBlobConfig;
import java.util.Random;

@WildWorldElements.ModElement.Tag
public class CrystalFieldsBiome extends WildWorldElements.ModElement {
	@ObjectHolder("wild_world:crystalfields")
	public static final CustomBiome biome = null;
	public CrystalFieldsBiome(WildWorldElements instance) {
		super(instance, 266);
	}

	@Override
	public void initElements() {
		elements.biomes.add(() -> new CustomBiome());
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
	}
	static class CustomBiome extends Biome {
		public CustomBiome() {
			super(new Biome.Builder().downfall(0.5f).depth(0.08f).scale(0.16f).temperature(0.4f).precipitation(Biome.RainType.RAIN)
					.category(Biome.Category.NONE).waterColor(4159204).waterFogColor(329011)
					.surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(Blocks.STONE.getDefaultState(), Blocks.STONE.getDefaultState(),
							Blocks.STONE.getDefaultState())));
			setRegistryName("crystalfields");
			DefaultBiomeFeatures.addCarvers(this);
			DefaultBiomeFeatures.addStructures(this);
			DefaultBiomeFeatures.addMonsterRooms(this);
			DefaultBiomeFeatures.addOres(this);
			DefaultBiomeFeatures.addLakes(this);
			DefaultSkyFeatures.addStoneVariants(this);
			addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH,
					new BushConfig(Blocks.BROWN_MUSHROOM.getDefaultState()), Placement.CHANCE_HEIGHTMAP_DOUBLE, new ChanceConfig(1)));
			addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH,
					new BushConfig(Blocks.RED_MUSHROOM.getDefaultState()), Placement.CHANCE_HEIGHTMAP_DOUBLE, new ChanceConfig(1)));
			addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Biome.createDecoratedFeature(new StalagmiteFeature(StalagmiteFeatureConfig::deserialize), 
					new StalagmiteFeatureConfig(Blocks.STONE.getDefaultState(), 2, Blocks.STONE.getDefaultState(), 9), Placement.TOP_SOLID_HEIGHTMAP_NOISE_BIASED, new TopSolidWithNoiseConfig(32, 2, 0, Heightmap.Type.WORLD_SURFACE)));
			addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Biome.createDecoratedFeature(new StalagmiteFeature(StalagmiteFeatureConfig::deserialize), 
					new StalagmiteFeatureConfig(Blocks.STONE.getDefaultState(), 3, Blocks.STONE.getDefaultState(), 5), Placement.TOP_SOLID_HEIGHTMAP_NOISE_BIASED, new TopSolidWithNoiseConfig(2, 1, 0, Heightmap.Type.WORLD_SURFACE)));
			addFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, Biome.createDecoratedFeature(new MossPatchFeature(BlockBlobConfig::deserialize), 
					new BlockBlobConfig(MossyStoneBlock.block.getDefaultState(), 4), Placement.TOP_SOLID_HEIGHTMAP_NOISE_BIASED, new TopSolidWithNoiseConfig(2, 1, 0, Heightmap.Type.WORLD_SURFACE)));
					
		}
	}
}
