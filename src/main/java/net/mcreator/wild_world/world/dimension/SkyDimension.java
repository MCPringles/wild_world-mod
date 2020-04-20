
package net.mcreator.wild_world.world.dimension;

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

@WildWorldElements.ModElement.Tag
public class SkyDimension extends WildWorldElements.ModElement {
	@ObjectHolder("wild_world:sky")
	public static final ModDimension dimension = null;
	public static DimensionType type = null;
	private static Biome[] dimensionBiomes;
	public SkyDimension(WildWorldElements instance) {
		super(instance, 98);
		MinecraftForge.EVENT_BUS.register(this);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}
	public ClientPlayerEntity player;
	@net.minecraftforge.registries.ObjectHolder("wild_world")
	public static class SkySoundEvents extends SoundEvents {
		public final static SoundEvent MUSIC_SKY = register("music.sky");
		private static SoundEvent register(String key) {
			return Registry.register(Registry.SOUND_EVENT, key, new SoundEvent(new ResourceLocation(key)));
		}
	}
	@SubscribeEvent
	public void registerDimension(RegistryEvent.Register<ModDimension> event) {
		event.getRegistry().register(new CustomModDimension().setRegistryName("sky"));
	}
	public static class MusicTickerSky extends MusicTicker {
		public MusicTickerSky(Minecraft mc) {
			super(mc);
		}
		@OnlyIn(Dist.CLIENT)
		public static enum MusicType {
			SKY(SkySoundEvents.MUSIC_SKY, 12000, 24000);
			private final SoundEvent sound;
			private final int minDelay;
			private final int maxDelay;
			private MusicType(SoundEvent sound, int minDelayIn, int maxDelayIn) {
				this.sound = sound;
				this.minDelay = minDelayIn;
				this.maxDelay = maxDelayIn;
			}
		}
	}
	@OnlyIn(Dist.CLIENT)
	public MusicTickerSky.MusicType getAmbientMusicType() {
		return MusicTickerSky.MusicType.SKY;
	}

	@SubscribeEvent
	public void onRegisterDimensionsEvent(RegisterDimensionsEvent event) {
		if (DimensionType.byName(new ResourceLocation("wild_world:sky")) == null) {
			DimensionManager.registerDimension(new ResourceLocation("wild_world:sky"), dimension, null, true);
		}
		type = DimensionType.byName(new ResourceLocation("wild_world:sky"));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		dimensionBiomes = new Biome[]{ForgeRegistries.BIOMES.getValue(new ResourceLocation("wild_world:skyplains")),
				ForgeRegistries.BIOMES.getValue(new ResourceLocation("wild_world:skyforest")),
				ForgeRegistries.BIOMES.getValue(new ResourceLocation("wild_world:skyjungle")),
				ForgeRegistries.BIOMES.getValue(new ResourceLocation("wild_world:midnightmarsh")),
				ForgeRegistries.BIOMES.getValue(new ResourceLocation("wild_world:crystalfields")),};
	}
	public static class CustomModDimension extends ModDimension {
		@Override
		public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
			return CustomDimension::new;
		}
	}

	public class CustomWorldType extends WorldType {
		public CustomWorldType(String name) {
			super("Sky");
		}

		public BiomeProvider getBiomeProvider(World world) {
			return new BiomeProviderCustom(world);
		}

		@Override
		public float getCloudHeight() {
			return 2.0F;
		}

		@Override
		public double getHorizon(World world) {
			return 1.0D;
		}
	}

	public static class CustomDimension extends Dimension {
		public CustomDimension(World world, DimensionType type) {
			super(world, type);
			this.nether = false;
		}

		@Override
		@OnlyIn(Dist.CLIENT)
		public Vec3d getFogColor(float cangle, float ticks) {
			return new Vec3d(1, 1, 1);
		}

		@Override
		public ChunkGenerator<? extends GenerationSettings> createChunkGenerator() {
			ChunkGeneratorType<EndGenerationSettings, EndChunkGenerator> chunkgeneratortype3 = ChunkGeneratorType.FLOATING_ISLANDS;
			EndGenerationSettings endgenerationsettings = ChunkGeneratorType.FLOATING_ISLANDS.createSettings();
			endgenerationsettings.setSpawnPos(new BlockPos(100000, 40, 100000));
			endgenerationsettings.setDefaultBlock(Blocks.STONE.getDefaultState());
			endgenerationsettings.setDefaultFluid(Blocks.WATER.getDefaultState());
			return ChunkGeneratorType.FLOATING_ISLANDS.create(this.world, new BiomeProviderCustom(this.world), endgenerationsettings);
		}

		@Override
		public boolean isSurfaceWorld() {
			return true;
		}

		@Override
		public boolean canRespawnHere() {
			return false;
		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public boolean doesXZShowFog(int x, int z) {
			return false;
		}

		@Override
		public SleepResult canSleepAt(PlayerEntity player, BlockPos pos) {
			return SleepResult.DENY;
		}

		@Nullable
		public BlockPos findSpawn(ChunkPos chunkPos, boolean checkValid) {
			return null;
		}

		@Nullable
		public BlockPos findSpawn(int x, int z, boolean checkValid) {
			return null;
		}

		@Override
		public boolean doesWaterVaporize() {
			return false;
		}

		@Override /**
					 * Calculates the angle of sun and moon in the sky relative to a specified time
					 * (usually worldTime)
					 */
		public float calculateCelestialAngle(long worldTime, float partialTicks) {
			double d0 = MathHelper.frac((double) worldTime / 24000.0D - 0.25D);
			double d1 = 0.5D - Math.cos(d0 * Math.PI) / 2.0D;
			return (float) (d0 * 2.0D + d1) / 3.0F;
		}
	}

	public static class BiomeLayerCustom implements IC0Transformer {
		@Override
		public int apply(INoiseRandom context, int value) {
			return Registry.BIOME.getId(dimensionBiomes[context.random(dimensionBiomes.length)]);
		}
	}

	public static class BiomeProviderCustom extends BiomeProvider {
		private final Layer genBiomes;
		private final Layer biomeFactoryLayer;
		private final Biome[] biomes;
		private final SimplexNoiseGenerator generator;
		public BiomeProviderCustom(World world) {
			Layer[] aLayer = makeTheWorld(world.getSeed());
			this.genBiomes = aLayer[0];
			this.biomeFactoryLayer = aLayer[1];
			this.biomes = dimensionBiomes;
			this.generator = new SimplexNoiseGenerator(new SharedSeedRandom(world.getSeed()));
		}

		private Layer[] makeTheWorld(long seed) {
			LongFunction<IExtendedNoiseRandom<LazyArea>> contextFactory = l -> new LazyAreaLayerContext(25, seed, l);
			IAreaFactory<LazyArea> parentLayer = IslandLayer.INSTANCE.apply(contextFactory.apply(1));
			IAreaFactory<LazyArea> biomeLayer = (new BiomeLayerCustom()).apply(contextFactory.apply(200), parentLayer);
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
			return this.hasStructureCache.computeIfAbsent(structureIn, (p_205006_1_) -> {
				for (Biome biome : this.biomes) {
					if (biome.hasStructure(p_205006_1_)) {
						return true;
					}
				}
				return false;
			});
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
}
