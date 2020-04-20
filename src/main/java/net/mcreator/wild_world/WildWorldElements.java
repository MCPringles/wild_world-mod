/*
 *    MCreator note:
 *
 *    This file is autogenerated to connect all MCreator mod elements together.
 *
 */
package net.mcreator.wild_world;

import net.minecraftforge.forgespi.language.ModFileScanData;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.storage.WorldSavedData;
import net.minecraft.world.biome.Biome;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tags.Tag;
import net.minecraft.network.PacketBuffer;
import net.minecraft.item.Item;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.block.Block;

import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.BiConsumer;
import java.util.Set;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Collections;
import java.util.ArrayList;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;

public class WildWorldElements {
	public final List<ModElement> elements = new ArrayList<>();
	public final List<Supplier<Block>> blocks = new ArrayList<>();
	public final List<Supplier<Item>> items = new ArrayList<>();
	public final List<Supplier<Biome>> biomes = new ArrayList<>();
	public final List<Supplier<EntityType<?>>> entities = new ArrayList<>();
	public static Map<ResourceLocation, net.minecraft.util.SoundEvent> sounds = new HashMap<>();
	public WildWorldElements() {
		sounds.put(new ResourceLocation("wild_world", "records.droopyface"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("wild_world", "records.droopyface")));
		sounds.put(new ResourceLocation("wild_world", "records.eleven"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("wild_world", "records.eleven")));
		sounds.put(new ResourceLocation("wild_world", "music.sky4"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("wild_world", "music.sky4")));
		sounds.put(new ResourceLocation("wild_world", "music.sky1"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("wild_world", "music.sky1")));
		sounds.put(new ResourceLocation("wild_world", "records.ki"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("wild_world", "records.ki")));
		sounds.put(new ResourceLocation("wild_world", "music.sky3"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("wild_world", "music.sky3")));
		sounds.put(new ResourceLocation("wild_world", "records.dog"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("wild_world", "records.dog")));
		sounds.put(new ResourceLocation("wild_world", "records.droopyricochet"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("wild_world", "records.droopyricochet")));
		sounds.put(new ResourceLocation("wild_world", "music.sky2"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("wild_world", "music.sky2")));
		try {
			ModFileScanData modFileInfo = ModList.get().getModFileById("wild_world").getFile().getScanResult();
			Set<ModFileScanData.AnnotationData> annotations = modFileInfo.getAnnotations();
			for (ModFileScanData.AnnotationData annotationData : annotations) {
				if (annotationData.getAnnotationType().getClassName().equals(ModElement.Tag.class.getName())) {
					Class<?> clazz = Class.forName(annotationData.getClassType().getClassName());
					if (clazz.getSuperclass() == WildWorldElements.ModElement.class)
						elements.add((WildWorldElements.ModElement) clazz.getConstructor(this.getClass()).newInstance(this));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Collections.sort(elements);
		elements.forEach(WildWorldElements.ModElement::initElements);
		this.addNetworkMessage(WildWorldVariables.WorldSavedDataSyncMessage.class, WildWorldVariables.WorldSavedDataSyncMessage::buffer,
				WildWorldVariables.WorldSavedDataSyncMessage::new, WildWorldVariables.WorldSavedDataSyncMessage::handler);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public void registerSounds(RegistryEvent.Register<net.minecraft.util.SoundEvent> event) {
		for (Map.Entry<ResourceLocation, net.minecraft.util.SoundEvent> sound : sounds.entrySet())
			event.getRegistry().register(sound.getValue().setRegistryName(sound.getKey()));
	}

	@SubscribeEvent
	public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		if (!event.getPlayer().world.isRemote) {
			WorldSavedData mapdata = WildWorldVariables.MapVariables.get(event.getPlayer().world);
			WorldSavedData worlddata = WildWorldVariables.WorldVariables.get(event.getPlayer().world);
			if (mapdata != null)
				WildWorld.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()),
						new WildWorldVariables.WorldSavedDataSyncMessage(0, mapdata));
			if (worlddata != null)
				WildWorld.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()),
						new WildWorldVariables.WorldSavedDataSyncMessage(1, worlddata));
		}
	}

	@SubscribeEvent
	public void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
		if (!event.getPlayer().world.isRemote) {
			WorldSavedData worlddata = WildWorldVariables.WorldVariables.get(event.getPlayer().world);
			if (worlddata != null)
				WildWorld.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()),
						new WildWorldVariables.WorldSavedDataSyncMessage(1, worlddata));
		}
	}
	private int messageID = 0;
	public <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, PacketBuffer> encoder, Function<PacketBuffer, T> decoder,
			BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
		WildWorld.PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
		messageID++;
	}

	public List<ModElement> getElements() {
		return elements;
	}

	public List<Supplier<Block>> getBlocks() {
		return blocks;
	}

	public List<Supplier<Item>> getItems() {
		return items;
	}

	public List<Supplier<Biome>> getBiomes() {
		return biomes;
	}

	public List<Supplier<EntityType<?>>> getEntities() {
		return entities;
	}
	public static class ModElement implements Comparable<ModElement> {
		@Retention(RetentionPolicy.RUNTIME)
		public @interface Tag {
		}
		protected final WildWorldElements elements;
		protected final int sortid;
		public ModElement(WildWorldElements elements, int sortid) {
			this.elements = elements;
			this.sortid = sortid;
		}

		public void initElements() {
		}

		public void init(FMLCommonSetupEvent event) {
		}

		public void serverLoad(FMLServerStartingEvent event) {
		}

		@Override
		public int compareTo(ModElement other) {
			return this.sortid - other.sortid;
		}
	}
}
