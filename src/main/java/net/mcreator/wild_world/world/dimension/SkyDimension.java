
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
import net.minecraft.world.gen.IChunkGeneratorFactory;
import java.util.function.Supplier;
import net.minecraft.world.gen.EndChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.WorldType;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraftforge.common.extensions.IForgeWorldType;
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
import net.mcreator.customclass.SkyChunkGenerator;
import net.mcreator.customclass.SkyBiomeProvider;


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

	public class CustomWorldType extends WorldType implements IForgeWorldType {
		public CustomWorldType(String name) {
			super("Sky");
		}

		public BiomeProvider getBiomeProvider(World world) {
			return new SkyBiomeProvider(world);
		}

		public double getHorizon(IWorld world) {
			return -2.0D;
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

		public double getHorizon(IWorld world) {
			return -65.0D;
		}
		
		public float getCloudHeight() {
			return 2.0F;
		}

		@Override
		public ChunkGenerator<? extends GenerationSettings> createChunkGenerator() {
			return new SkyChunkGenerator(world, new SkyBiomeProvider(world), new EndGenerationSettings());
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
}
