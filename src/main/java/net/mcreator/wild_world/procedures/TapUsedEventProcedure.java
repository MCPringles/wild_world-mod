package net.mcreator.wild_world.procedures;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wild_world.block.MapleLogWildBlock;
import net.mcreator.wild_world.block.BlossieLogWildBlock;
import net.mcreator.wild_world.block.BirchLogWildBlock;
import net.mcreator.wild_world.WildWorldElements;

import java.util.Random;

@WildWorldElements.ModElement.Tag
public class TapUsedEventProcedure extends WildWorldElements.ModElement {
	public TapUsedEventProcedure(WildWorldElements instance) {
		super(instance, 220);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure TapUsedEvent!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure TapUsedEvent!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure TapUsedEvent!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure TapUsedEvent!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			System.err.println("Failed to load dependency itemstack for procedure TapUsedEvent!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure TapUsedEvent!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		World world = (World) dependencies.get("world");
		if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == MapleLogWildBlock.block.getDefaultState().getBlock())) {
			if ((!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false))) {
				if (itemstack.attemptDamageItem((int) 1, new Random(), null)) {
					itemstack.shrink(1);
					itemstack.setDamage(0);
				}
			}
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("world", world);
				$_dependencies.put("x", (int) (x));
				$_dependencies.put("y", (int) (y));
				$_dependencies.put("z", (int) (z));
				MapleTapEventsProcedure.executeProcedure($_dependencies);
			}
		}
		if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlossieLogWildBlock.block.getDefaultState().getBlock())) {
			if ((!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false))) {
				if (itemstack.attemptDamageItem((int) 1, new Random(), null)) {
					itemstack.shrink(1);
					itemstack.setDamage(0);
				}
			}
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("world", world);
				$_dependencies.put("x", (int) (x));
				$_dependencies.put("y", (int) (y));
				$_dependencies.put("z", (int) (z));
				BlossieTapEventsProcedure.executeProcedure($_dependencies);
			}
		}
		if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BirchLogWildBlock.block.getDefaultState().getBlock())) {
			if ((!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false))) {
				if (itemstack.attemptDamageItem((int) 1, new Random(), null)) {
					itemstack.shrink(1);
					itemstack.setDamage(0);
				}
			}
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("world", world);
				$_dependencies.put("x", (int) (x));
				$_dependencies.put("y", (int) (y));
				$_dependencies.put("z", (int) (z));
				BirchTapEventsProcedure.executeProcedure($_dependencies);
			}
		}
	}
}
