package net.mcreator.customclass;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.gen.layer.traits.IC0Transformer;
import net.minecraft.world.gen.layer.ZoomLayer;
import net.minecraft.world.gen.layer.VoroniZoomLayer;
import net.minecraft.world.gen.layer.Layer;
import net.minecraft.world.gen.layer.IslandLayer;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.area.LazyArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.SimplexNoiseGenerator;
import net.minecraft.world.gen.LazyAreaLayerContext;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.EndGenerationSettings;
import net.minecraft.world.gen.EndChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.BiomeLayer;
import net.minecraft.world.WorldType;
import net.minecraft.world.World;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.audio.MusicTicker.MusicType;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.client.Minecraft;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.mcreator.wild_world.WildWorldElements;

import javax.annotation.Nullable;

import java.util.function.LongFunction;
import java.util.function.BiFunction;
import java.util.Set;
import java.util.Random;
import java.util.List;
import java.util.Collections;

import com.google.common.collect.Sets;
import net.minecraft.world.gen.OverworldGenSettings;


public class SkyBiomeProvider extends BiomeProvider {
		private final Layer genBiomes;
		private final Layer biomeFactoryLayer;
		private Biome[] biomes;
		private final SimplexNoiseGenerator generator;
		public SkyBiomeProvider(World world) {
			Layer[] aLayer = makeTheWorld(world.getSeed());
			this.genBiomes = aLayer[0];
			this.biomeFactoryLayer = aLayer[1];
			this.generator = new SimplexNoiseGenerator(new SharedSeedRandom(world.getSeed()));
		}
		
		public void init(FMLCommonSetupEvent event) {
			biomes = new Biome[]{ForgeRegistries.BIOMES.getValue(new ResourceLocation("wild_world:skyplains")),
					ForgeRegistries.BIOMES.getValue(new ResourceLocation("wild_world:skyforest")),
					ForgeRegistries.BIOMES.getValue(new ResourceLocation("wild_world:skyjungle")),
					ForgeRegistries.BIOMES.getValue(new ResourceLocation("wild_world:midnightmarsh")),
					ForgeRegistries.BIOMES.getValue(new ResourceLocation("wild_world:crystalfields")),};
		}

		private Layer[] makeTheWorld(long seed) {
			LongFunction<IExtendedNoiseRandom<LazyArea>> contextFactory = l -> new LazyAreaLayerContext(25, seed, l);
			IAreaFactory<LazyArea> parentLayer = IslandLayer.INSTANCE.apply(contextFactory.apply(1));
			IAreaFactory<LazyArea> biomeLayer = (new SkyBiomeLayer()).apply(contextFactory.apply(200), parentLayer);
			biomeLayer = ZoomLayer.NORMAL.apply(contextFactory.apply(1000), biomeLayer);
			biomeLayer = ZoomLayer.NORMAL.apply(contextFactory.apply(1001), biomeLayer);
			biomeLayer = ZoomLayer.NORMAL.apply(contextFactory.apply(1002), biomeLayer);
			biomeLayer = ZoomLayer.NORMAL.apply(contextFactory.apply(1003), biomeLayer);
			biomeLayer = ZoomLayer.NORMAL.apply(contextFactory.apply(1004), biomeLayer);
			biomeLayer = ZoomLayer.NORMAL.apply(contextFactory.apply(1005), biomeLayer);
			IAreaFactory<LazyArea> voronoizoom = VoroniZoomLayer.INSTANCE.apply(contextFactory.apply(10), biomeLayer);
			return new Layer[]{new Layer(biomeLayer), new Layer(voronoizoom)};
		}

		@Override /**
					 * Gets the biome from the provided coordinates
					 */
		public Biome getBiome(int x, int y) {
			return this.biomeFactoryLayer.func_215738_a(x, y);
		}

		@Override
		public Biome func_222366_b(int p_222366_1_, int p_222366_2_) {
			return this.genBiomes.func_215738_a(p_222366_1_, p_222366_2_);
		}

		@Override
		public Biome[] getBiomes(int x, int z, int width, int length, boolean cacheFlag) {
			return this.biomeFactoryLayer.generateBiomes(x, z, width, length);
		}

		@Override
		public Set<Biome> getBiomesInSquare(int centerX, int centerZ, int sideLength) {
			int i = centerX - sideLength >> 2;
			int j = centerZ - sideLength >> 2;
			int k = centerX + sideLength >> 2;
			int l = centerZ + sideLength >> 2;
			int i1 = k - i + 1;
			int j1 = l - j + 1;
			Set<Biome> set = Sets.newHashSet();
			Collections.addAll(set, this.genBiomes.generateBiomes(i, j, i1, j1));
			return set;
		}

		@Override
		@Nullable
		public BlockPos findBiomePosition(int x, int z, int range, List<Biome> biomes, Random random) {
			int i = x - range >> 2;
			int j = z - range >> 2;
			int k = x + range >> 2;
			int l = z + range >> 2;
			int i1 = k - i + 1;
			int j1 = l - j + 1;
			Biome[] abiome = this.genBiomes.generateBiomes(i, j, i1, j1);
			BlockPos blockpos = null;
			int k1 = 0;
			for (int l1 = 0; l1 < i1 * j1; ++l1) {
				int i2 = i + l1 % i1 << 2;
				int j2 = j + l1 / i1 << 2;
				if (biomes.contains(abiome[l1])) {
					if (blockpos == null || random.nextInt(k1 + 1) == 0) {
						blockpos = new BlockPos(i2, 0, j2);
					}
					++k1;
				}
			}
			return blockpos;
		}

		@Override
		public boolean hasStructure(Structure<?> structureIn) {
			return false;
		}

		@Override
		public Set<BlockState> getSurfaceBlocks() {
			if (this.topBlocksCache.isEmpty()) {
				for (Biome biome : this.biomes) {
					this.topBlocksCache.add(biome.getSurfaceBuilderConfig().getTop());
				}
			}
			return this.topBlocksCache;
		}

		@Override
		public float func_222365_c(int p_222365_1_, int p_222365_2_) {
			int i = p_222365_1_ / 2;
			int j = p_222365_2_ / 2;
			int k = p_222365_1_ % 2;
			int l = p_222365_2_ % 2;
			float f = 100.0F - MathHelper.sqrt((float) (p_222365_1_ * p_222365_1_ + p_222365_2_ * p_222365_2_)) * 8.0F;
			f = MathHelper.clamp(f, -100.0F, 80.0F);
			for (int i1 = -12; i1 <= 12; ++i1) {
				for (int j1 = -12; j1 <= 12; ++j1) {
					long k1 = (long) (i + i1);
					long l1 = (long) (j + j1);
					if (k1 * k1 + l1 * l1 > 4096L && this.generator.getValue((double) k1, (double) l1) < (double) -0.9F) {
						float f1 = (MathHelper.abs((float) k1) * 3439.0F + MathHelper.abs((float) l1) * 147.0F) % 13.0F + 9.0F;
						float f2 = (float) (k - i1 * 2);
						float f3 = (float) (l - j1 * 2);
						float f4 = 100.0F - MathHelper.sqrt(f2 * f2 + f3 * f3) * f1;
						f4 = MathHelper.clamp(f4, -100.0F, 80.0F);
						f = Math.max(f, f4);
					}
				}
			}
			return f;
		}
	}
