package net.mcreator.wild_world.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.mcreator.wild_world.item.TapWoodItem;
import net.mcreator.wild_world.item.TapIronItem;
import net.mcreator.wild_world.item.TapGoldItem;
import net.mcreator.wild_world.item.BirchSapItemItem;
import net.mcreator.wild_world.WildWorldElements;

import java.util.Iterator;

@WildWorldElements.ModElement.Tag
public class BirchTapEventsProcedure extends WildWorldElements.ModElement {
	public BirchTapEventsProcedure(WildWorldElements instance) {
		super(instance, 305);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure BirchTapEvents!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure BirchTapEvents!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure BirchTapEvents!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure BirchTapEvents!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure BirchTapEvents!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if ((!(entity.isSneaking()))) {
			if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
					.getItem() == new ItemStack(TapWoodItem.block, (int) (1)).getItem())) {
				if ((Math.random() >= 0.25)) {
					if (!world.isRemote) {
						ItemEntity entityToSpawn = new ItemEntity(world, (x + (((entity.posX) - x) / 2)), y, (z + (((entity.posZ) - z) / 2)),
								new ItemStack(BirchSapItemItem.block, (int) (1)));
						entityToSpawn.setPickupDelay(10);
						world.addEntity(entityToSpawn);
					}
				}
				if ((Math.random() >= 0.5)) {
					if (!world.isRemote) {
						ItemEntity entityToSpawn = new ItemEntity(world, (x + (((entity.posX) - x) / 2)), y, (z + (((entity.posZ) - z) / 2)),
								new ItemStack(BirchSapItemItem.block, (int) (1)));
						entityToSpawn.setPickupDelay(10);
						world.addEntity(entityToSpawn);
					}
				}
				if ((Math.random() >= 0.75)) {
					if (!world.isRemote) {
						ItemEntity entityToSpawn = new ItemEntity(world, (x + (((entity.posX) - x) / 2)), y, (z + (((entity.posZ) - z) / 2)),
								new ItemStack(BirchSapItemItem.block, (int) (1)));
						entityToSpawn.setPickupDelay(10);
						world.addEntity(entityToSpawn);
					}
				}
				if ((Math.random() >= 0.25)) {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.BIRCH_LOG.getDefaultState(), 3);
				}
				world.playSound((PlayerEntity) null, x, y, z,
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.wooden_button.click_on")),
						SoundCategory.NEUTRAL, (float) 1, (float) 1.15);
				if (entity instanceof ServerPlayerEntity) {
					Advancement _adv = ((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
							.getAdvancement(new ResourceLocation("wild_world:acheivementmaplesap"));
					AdvancementProgress _ap = ((ServerPlayerEntity) entity).getAdvancements().getProgress(_adv);
					if (!_ap.isDone()) {
						Iterator _iterator = _ap.getRemaningCriteria().iterator();
						while (_iterator.hasNext()) {
							String _criterion = (String) _iterator.next();
							((ServerPlayerEntity) entity).getAdvancements().grantCriterion(_adv, _criterion);
						}
					}
				}
			}
			if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
					.getItem() == new ItemStack(TapIronItem.block, (int) (1)).getItem())) {
				if ((Math.random() >= 0.1)) {
					if (!world.isRemote) {
						ItemEntity entityToSpawn = new ItemEntity(world, (x + (((entity.posX) - x) / 2)), y, (z + (((entity.posZ) - z) / 2)),
								new ItemStack(BirchSapItemItem.block, (int) (1)));
						entityToSpawn.setPickupDelay(10);
						world.addEntity(entityToSpawn);
					}
				}
				if ((Math.random() >= 0.5)) {
					if (!world.isRemote) {
						ItemEntity entityToSpawn = new ItemEntity(world, (x + (((entity.posX) - x) / 2)), y, (z + (((entity.posZ) - z) / 2)),
								new ItemStack(BirchSapItemItem.block, (int) (1)));
						entityToSpawn.setPickupDelay(10);
						world.addEntity(entityToSpawn);
					}
				}
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.BIRCH_LOG.getDefaultState(), 3);
				world.playSound((PlayerEntity) null, x, y, z,
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
								.getValue(new ResourceLocation("block.wooden_pressure_plate.click_on")),
						SoundCategory.NEUTRAL, (float) 1, (float) 1.05);
				if (entity instanceof ServerPlayerEntity) {
					Advancement _adv = ((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
							.getAdvancement(new ResourceLocation("wild_world:acheivementmaplesap"));
					AdvancementProgress _ap = ((ServerPlayerEntity) entity).getAdvancements().getProgress(_adv);
					if (!_ap.isDone()) {
						Iterator _iterator = _ap.getRemaningCriteria().iterator();
						while (_iterator.hasNext()) {
							String _criterion = (String) _iterator.next();
							((ServerPlayerEntity) entity).getAdvancements().grantCriterion(_adv, _criterion);
						}
					}
				}
			}
			if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
					.getItem() == new ItemStack(TapGoldItem.block, (int) (1)).getItem())) {
				if ((Math.random() >= 0.3)) {
					if (!world.isRemote) {
						ItemEntity entityToSpawn = new ItemEntity(world, (x + (((entity.posX) - x) / 2)), y, (z + (((entity.posZ) - z) / 2)),
								new ItemStack(BirchSapItemItem.block, (int) (1)));
						entityToSpawn.setPickupDelay(10);
						world.addEntity(entityToSpawn);
					}
				}
				if ((Math.random() >= 0.5)) {
					if (!world.isRemote) {
						ItemEntity entityToSpawn = new ItemEntity(world, (x + (((entity.posX) - x) / 2)), y, (z + (((entity.posZ) - z) / 2)),
								new ItemStack(BirchSapItemItem.block, (int) (1)));
						entityToSpawn.setPickupDelay(10);
						world.addEntity(entityToSpawn);
					}
				}
				if ((Math.random() >= 0.9)) {
					if (!world.isRemote) {
						ItemEntity entityToSpawn = new ItemEntity(world, (x + (((entity.posX) - x) / 2)), y, (z + (((entity.posZ) - z) / 2)),
								new ItemStack(BirchSapItemItem.block, (int) (1)));
						entityToSpawn.setPickupDelay(10);
						world.addEntity(entityToSpawn);
					}
				}
				if ((Math.random() >= 0.9)) {
					if (!world.isRemote) {
						ItemEntity entityToSpawn = new ItemEntity(world, (x + (((entity.posX) - x) / 2)), y, (z + (((entity.posZ) - z) / 2)),
								new ItemStack(BirchSapItemItem.block, (int) (1)));
						entityToSpawn.setPickupDelay(10);
						world.addEntity(entityToSpawn);
					}
				}
				if ((Math.random() >= 0.125)) {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.BIRCH_LOG.getDefaultState(), 3);
				}
				world.playSound((PlayerEntity) null, x, y, z,
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
								.getValue(new ResourceLocation("block.wooden_pressure_plate.click_on")),
						SoundCategory.NEUTRAL, (float) 1, (float) 1.2);
				if (entity instanceof ServerPlayerEntity) {
					Advancement _adv = ((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
							.getAdvancement(new ResourceLocation("wild_world:acheivementmaplesap"));
					AdvancementProgress _ap = ((ServerPlayerEntity) entity).getAdvancements().getProgress(_adv);
					if (!_ap.isDone()) {
						Iterator _iterator = _ap.getRemaningCriteria().iterator();
						while (_iterator.hasNext()) {
							String _criterion = (String) _iterator.next();
							((ServerPlayerEntity) entity).getAdvancements().grantCriterion(_adv, _criterion);
						}
					}
				}
			}
		}
	}
}
