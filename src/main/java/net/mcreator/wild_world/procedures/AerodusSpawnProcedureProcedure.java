package net.mcreator.wild_world.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.world.Difficulty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class AerodusSpawnProcedureProcedure extends WildWorldElements.ModElement {
	public AerodusSpawnProcedureProcedure(WildWorldElements instance) {
		super(instance, 408);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure AerodusSpawnProcedure!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure AerodusSpawnProcedure!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure AerodusSpawnProcedure!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure AerodusSpawnProcedure!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure AerodusSpawnProcedure!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		double attackChance = 0;
		if ((((!(world.getDifficulty() == Difficulty.PEACEFUL))
				&& (!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false)))
				&& ((ForgeRegistries.BIOMES.getKey(world.getBiome(new BlockPos((int) (entity.posX), (int) (entity.posY), (int) (entity.posZ))))
						.equals(new ResourceLocation("wild_world:skyjungle"))) && (5 < (entity.posY))))) {
			attackChance = (double) 0;
			attackChance = (double) (entity.getPersistentData().getDouble("attackChance"));
			if ((world.isDaytime())) {
				attackChance = (double) ((attackChance) + 0.0075);
			} else {
				attackChance = (double) ((attackChance) + 0.01);
			}
			if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1) < 20)) {
				attackChance = (double) ((attackChance)
						+ ((20 - ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1)) * 0.001));
			}
			if (((attackChance) > (100 + (Math.random() * 400)))) {
				{
					java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
					$_dependencies.put("world", world);
					$_dependencies.put("x", (int) (x));
					$_dependencies.put("y", (int) (y));
					$_dependencies.put("z", (int) (z));
					SpawnAerodusProcedure.executeProcedure($_dependencies);
				}
				entity.getPersistentData().putDouble("attackChance", 0);
			} else {
				entity.getPersistentData().putDouble("attackChance", (attackChance));
			}
		}
	}

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			Entity entity = event.player;
			World world = entity.world;
			int i = (int) entity.posX;
			int j = (int) entity.posY;
			int k = (int) entity.posZ;
			java.util.HashMap<String, Object> dependencies = new java.util.HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("event", event);
			this.executeProcedure(dependencies);
		}
	}
}
