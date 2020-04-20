package net.mcreator.wild_world.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.Difficulty;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec2f;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;

import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class NetherCrawlerOnInitialMobSpawnProcedure extends WildWorldElements.ModElement {
	public NetherCrawlerOnInitialMobSpawnProcedure(WildWorldElements instance) {
		super(instance, 198);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure NetherCrawlerOnInitialMobSpawn!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure NetherCrawlerOnInitialMobSpawn!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure NetherCrawlerOnInitialMobSpawn!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure NetherCrawlerOnInitialMobSpawn!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure NetherCrawlerOnInitialMobSpawn!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if ((Math.random() <= 0.001)) {
			entity.setCustomName(new StringTextComponent("Nether_Jockey"));
			if (!world.isRemote && world.getServer() != null) {
				world.getServer().getCommandManager().handleCommand(
						new CommandSource(ICommandSource.field_213139_a_, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
								new StringTextComponent(""), world.getServer(), null).withFeedbackDisabled(),
						"/execute at @e[name=Nether_Jockey,type=wild_world:NetherCrawler] run /summon wild_world:NetherCrawler ~ ~ ~ {Passengers:[{id:\"minecraft:wither_skeleton\"}]}");
			}
			entity.remove();
		}
		if ((world.getDifficulty() == Difficulty.HARD)) {
			if ((Math.random() <= 0.01)) {
				if ((Math.random() <= 0.4)) {
					if (entity instanceof LivingEntity)
						((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SPEED, (int) 10000, (int) 1));
				} else {
					if ((Math.random() <= 0.33)) {
						if (entity instanceof LivingEntity)
							((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.STRENGTH, (int) 10000, (int) 1));
					} else {
						if ((Math.random() <= 0.5)) {
							if (entity instanceof LivingEntity)
								((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.REGENERATION, (int) 10000, (int) 1));
						} else {
							if (entity instanceof LivingEntity)
								((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.INVISIBILITY, (int) 10000, (int) 1));
						}
					}
				}
			}
		}
	}
}
