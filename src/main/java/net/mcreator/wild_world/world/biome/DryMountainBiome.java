
package net.mcreator.wild_world.world.biome;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.GrassFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Blocks;

import net.mcreator.wild_world.block.BarrenTopsoilBlock;
import net.mcreator.wild_world.block.BarrenDirtBlock;
import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class DryMountainBiome extends WildWorldElements.ModElement {
	@ObjectHolder("wild_world:drymountain")
	public static final CustomBiome biome = null;
	public DryMountainBiome(WildWorldElements instance) {
		super(instance, 204);
	}

	@Override
	public void initElements() {
		elements.biomes.add(() -> new CustomBiome());
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		BiomeDictionary.addTypes(biome, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HOT,
				BiomeDictionary.Type.DRY);
		BiomeManager.addSpawnBiome(biome);
		BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(biome, 3));
	}
	static class CustomBiome extends Biome {
		public CustomBiome() {
			super(new Biome.Builder().downfall(0.3f).depth(0.45f).scale(0.4f).temperature(1f).precipitation(Biome.RainType.RAIN)
					.category(Biome.Category.NONE).waterColor(-14329397).waterFogColor(-14329397).parent("wild_world:drygrassland")
					.surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(BarrenTopsoilBlock.block.getDefaultState(),
							BarrenDirtBlock.block.getDefaultState(), BarrenDirtBlock.block.getDefaultState())));
			setRegistryName("drymountain");
			DefaultBiomeFeatures.addCarvers(this);
			DefaultBiomeFeatures.addStructures(this);
			DefaultBiomeFeatures.addMonsterRooms(this);
			DefaultBiomeFeatures.addOres(this);
			DefaultBiomeFeatures.addLakes(this);
			addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.DEFAULT_FLOWER,
					IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(2)));
			addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.GRASS,
					new GrassFeatureConfig(Blocks.GRASS.getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(20)));
		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public int getGrassColor(BlockPos pos) {
			return -2504554;
		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public int getFoliageColor(BlockPos pos) {
			return -2504554;
		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public int getSkyColorByTemp(float currentTemperature) {
			return -5916161;
		}
	}
}
